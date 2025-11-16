package Controlador;

import Modelado.InformeDAO;
import java.sql.Connection;
import Modelado.MostrarLugaresDAO;
import Vista.MenuProductor;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class ControladorMostrarLugares {

    private final MenuProductor vista;
    private final MostrarLugaresDAO dao;
    private final Connection conexion;

    public ControladorMostrarLugares(MenuProductor vista, Connection conexionRol) {
        this.vista = vista;
        this.conexion = conexionRol;
        this.dao = new MostrarLugaresDAO(conexionRol);
        
        this.vista.getBtnInforme().addActionListener(e -> generarInforme() );
        this.vista.getBtnConsultar().addActionListener(e -> mostrarLugares());
        this.vista.getBtnEliminar().addActionListener(e -> eliminarLugar());
        this.vista.getBtnEditar().addActionListener(e -> editarLugar());
    }

    public void mostrarLugares() {
        cargarTabla();
    }

    private void cargarTabla() {
        String[] columnas = {
            "N° Registro ICA",
            "Nombre del Lugar",
            "Empresa Responsable"
        };

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };

        List<Object[]> lista = dao.listarLugares();
        for (Object[] fila : lista) modelo.addRow(fila);

        vista.getTablaPredios().setModel(modelo);
    }

    // 🔥 ELIMINAR
    private void eliminarLugar() {
        int fila = vista.getTablaPredios().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "⚠ Selecciona un lugar para eliminar.");
            return;
        }

        int numReg = (int) vista.getTablaPredios().getValueAt(fila, 0);

        int confirm = JOptionPane.showConfirmDialog(
                vista,
                "¿Eliminar el lugar con registro " + numReg + "?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            if (dao.eliminarLugar(numReg)) {
                JOptionPane.showMessageDialog(vista, "✔ Lugar eliminado.");
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "❌ No se pudo eliminar.");
            }
        }
    }

    // 🔥 EDITAR (abre un mini formulario)
    private void editarLugar() {
        int fila = vista.getTablaPredios().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "⚠ Selecciona un lugar para editar.");
            return;
        }

        int numReg = (int) vista.getTablaPredios().getValueAt(fila, 0);
        String nombre = (String) vista.getTablaPredios().getValueAt(fila, 1);
        String empresa = (String) vista.getTablaPredios().getValueAt(fila, 2);

        String nuevoNombre = JOptionPane.showInputDialog(vista, "Nuevo nombre:", nombre);
        String nuevaEmpresa = JOptionPane.showInputDialog(vista, "Nueva empresa:", empresa);

        if (nuevoNombre != null && nuevaEmpresa != null) {
            if (dao.actualizarLugar(numReg, nuevoNombre, nuevaEmpresa)) {
                JOptionPane.showMessageDialog(vista, "✔ Lugar actualizado.");
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "❌ No se pudo actualizar.");
            }
        }
    }
    
    // Generar Informe
private void generarInforme() {

    try {
        // Crear DAO usando la conexión activa
        InformeDAO dao = new InformeDAO(conexion);

        // Obtener el texto del informe
        String informe = dao.informeProductor();

        // Mostrarlo en un JTextArea dentro de un JScrollPane
        JTextArea area = new JTextArea(20, 50);
        area.setEditable(false);
        area.setText(informe);

        JScrollPane scroll = new JScrollPane(area);

        JOptionPane.showMessageDialog(
                vista,
                scroll,
                "Informe de Producción",
                JOptionPane.INFORMATION_MESSAGE
        );

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(vista,
                "Error al generar el informe: " + e.getMessage());
    }
}


}
