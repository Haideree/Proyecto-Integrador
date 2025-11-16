package Controlador;

import Modelado.PredioDAO;
import Vista.EditarPredio;
import Modelado.CConexion;
import Vista.AdministrarPredios;
import Vista.MenuPropietario;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class ControladorAdministrarPredio {

    private AdministrarPredios vista;
    private PredioDAO dao;
    private Connection conexion;
    private int docPropietario; 

    public ControladorAdministrarPredio(AdministrarPredios vista, Connection conexion, int docPropietario){
        this.vista = vista;
        this.conexion = conexion;
        this.docPropietario = docPropietario;
        this.dao = new PredioDAO(conexion);
        System.out.println("ID que recibe ControladorAdministrarPredio = " + docPropietario);


        cargarTabla();

        // EVENTOS
        vista.getBtnActualizar().addActionListener(e -> cargarTabla());
        vista.getBtnEliminar().addActionListener(e -> eliminarPredio());
        vista.getBtnVolver().addActionListener(e -> volver());
        vista.getBtnAgregar().addActionListener(e -> abrirAgregar());
        vista.getBtnEditar().addActionListener(e -> abrirEditar());
    }

    private void cargarTabla() {
        vista.getTablaPredios().setModel(dao.obtenerPredios(docPropietario));
    }

    private void eliminarPredio() {

        int fila = vista.getTablaPredios().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un predio para eliminar.");
            return;
        }

        int numICA = Integer.parseInt(
                vista.getTablaPredios().getValueAt(fila, 0).toString()
        );

        int confirm = JOptionPane.showConfirmDialog(
                vista,
                "¿Está seguro de eliminar el predio con registro ICA: " + numICA + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {

            if (dao.eliminarPredio(numICA)) {
                JOptionPane.showMessageDialog(vista, "✔ Predio eliminado con éxito.");
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "✘ No se pudo eliminar el predio.");
            }
        }
    }

    private void volver() {
        MenuPropietario menu = new MenuPropietario(conexion, docPropietario);
        new ControladorMenuPropietario(menu, conexion, docPropietario);
        menu.setVisible(true);
        vista.dispose();
    }

    private void abrirAgregar() {
    Vista.RegistroPredio ventanaRegistro = new Vista.RegistroPredio(conexion, docPropietario);
    new ControladorRegistroPredio(ventanaRegistro, conexion, docPropietario);

    ventanaRegistro.setVisible(true);
    vista.dispose();

   
}
    
        private int obtenerIdSeleccionadoDeLaTabla() {
        int fila = vista.getTablaPredios().getSelectedRow();

        if (fila == -1) {
            return -1; // No hay selección
        }

        Object valor = vista.getTablaPredios().getValueAt(fila, 0);

        try {
            return Integer.parseInt(valor.toString());
        } catch (Exception e) {
            return -1;
        }
    }

    private void abrirEditar() {
    int numRegistroIca = obtenerIdSeleccionadoDeLaTabla(); // <- tú ya debes tener esto

    if (numRegistroIca == -1) {
        JOptionPane.showMessageDialog(vista, "Selecciona un predio primero.");
        return;
    }

    EditarPredio editar = new EditarPredio();
    PredioDAO pdao = new PredioDAO(CConexion.getConnectionPorRol("propietario"));

    new ControladorEditarPredio(
            editar,        // vista
            pdao,          // dao
            vista,         // vista anterior
            numRegistroIca // ID del predio real
    );

    vista.setVisible(false);
    editar.setVisible(true);
}


}
