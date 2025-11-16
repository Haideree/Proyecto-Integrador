package Controlador;

import java.sql.SQLException;
import Modelado.LoteDAO;
import Vista.EditarLote;
import Vista.AdministrarLotes;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class ControladorEditarLote {

    private EditarLote vista;
    private LoteDAO dao;
    private Connection conexion;
    private int numLote;
    private int idPropietario;

    public ControladorEditarLote(EditarLote vista, Connection conexion, int numLote, int idPropietario) {
        this.vista = vista;
        this.conexion = conexion;
        this.dao = new LoteDAO(conexion);
        this.numLote = numLote;
        this.idPropietario = idPropietario;

        cargarCombos();
        cargarDatosDelLote();

        vista.getbtnGuardar().addActionListener(e -> guardarCambios());
        vista.getbtnRegresar().addActionListener(e -> regresar());
    }

    // -------------------------------------------------------------
    // CARGAR COMBOS
    // -------------------------------------------------------------
    private void cargarCombos() {
        vista.getcomboEditarCultivo().removeAllItems();
        vista.getcomboEditarLugar().removeAllItems();

        dao.obtenerCultivos().forEach(c -> vista.getcomboEditarCultivo().addItem(c));
        dao.obtenerLugares().forEach(l -> vista.getcomboEditarLugar().addItem(l));
    }

    // -------------------------------------------------------------
    // CARGAR DATOS DEL LOTE DESDE EL DAO
    // -------------------------------------------------------------
    private void cargarDatosDelLote() {
        try {
            var datos = dao.obtenerDatosLote(numLote);
            if (datos == null) {
                JOptionPane.showMessageDialog(vista, "No se encontró el lote.");
                return;
            }

            vista.getTxtEditarNombre().setText(datos.nombre);
            vista.getTxtEditarArea().setText(String.valueOf(datos.area));

            vista.getcomboEditarCultivo().setSelectedItem(datos.nombreCultivo);
            vista.getcomboEditarLugar().setSelectedItem(datos.nombreLugar);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                vista,
                "Error al cargar datos: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // -------------------------------------------------------------
    // GUARDAR CAMBIOS
    // -------------------------------------------------------------
    private void guardarCambios() {
        try {
            double area = Double.parseDouble(vista.getTxtEditarArea().getText());
            String nombre = vista.getTxtEditarNombre().getText();
            String cultivoNombre = vista.getcomboEditarCultivo().getSelectedItem().toString();
            String lugarNombre = vista.getcomboEditarLugar().getSelectedItem().toString();

            int idCultivo = dao.obtenerIdCultivo(cultivoNombre);
            int idLugar = dao.obtenerIdLugar(lugarNombre);

            if (dao.editarLote(numLote, area, nombre, idCultivo, idLugar)) {
                JOptionPane.showMessageDialog(null, "✔ Lote actualizado correctamente");
                regresar();
            } else {
                JOptionPane.showMessageDialog(null, "✘ No se pudo actualizar el lote");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "💥 Error SQL: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "⚠ El área debe ser numérica");
        }
    }

    // -------------------------------------------------------------
    // REGRESAR
    // -------------------------------------------------------------
    private void regresar() {
        AdministrarLotes ventana = new AdministrarLotes(conexion,idPropietario);
        new ControladorAdministrarLotes(ventana, conexion, idPropietario);
        ventana.setVisible(true);
        vista.dispose();
    }
}


