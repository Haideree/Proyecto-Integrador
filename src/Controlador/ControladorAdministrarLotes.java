package Controlador;

import Vista.EditarLote;
import Modelado.LoteDAO;
import Vista.RegistroLote;
import Vista.AdministrarLotes;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorAdministrarLotes {

    private AdministrarLotes vista;
    private LoteDAO dao;
    private Connection conexion;

    public ControladorAdministrarLotes(AdministrarLotes vista, Connection conexion) {
        this.vista = vista;
        this.conexion = conexion;
        this.dao = new LoteDAO(conexion);

        // Eventos de botones
        this.vista.getBtnActualizar().addActionListener(e -> cargarTabla());
        this.vista.getBtnEliminar().addActionListener(e -> eliminarLote());
        this.vista.getBtnAgregar().addActionListener(e -> abrirAgregar());
        this.vista.getBtnEditar().addActionListener(e -> abrirEditar());
   


        // Cargar tabla al abrir
        cargarTabla();
    }

    // ========================================================
    // 🔹 CARGAR TABLA DE LOTES
    // ========================================================
    private void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) vista.getTablaLotes().getModel();
        modelo.setRowCount(0);

        String sql = "SELECT NUM_LOTE, NOMBRE_LOTE, AREA, "
                   + "(SELECT NOMBRE_ESPECIE FROM CULTIVO c WHERE c.ID = l.IDCULTIVO) AS CULTIVO, "
                   + "(SELECT NOMBRE FROM LUGAR_PRODUCCION lp WHERE lp.NUM_REGISTRO_ICA = l.ID_LUGARPROD) AS LUGAR, "
                   + "ESTADO "
                   + "FROM LOTE l ORDER BY NUM_LOTE";

        try (var st = conexion.createStatement();
             var rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Object fila[] = new Object[]{
                    rs.getInt("NUM_LOTE"),
                    rs.getString("NOMBRE_LOTE"),
                    rs.getDouble("AREA"),
                    rs.getString("CULTIVO"),
                    rs.getString("LUGAR"),
                    rs.getString("ESTADO")
                };
                modelo.addRow(fila);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error al cargar lotes:\n" + e.getMessage());
        }
    }

    // ========================================================
    // 🔹 ELIMINAR LOTE
    // ========================================================
    private void eliminarLote() {
        int fila = vista.getTablaLotes().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un lote para eliminar.");
            return;
        }

        int numLote = Integer.parseInt(vista.getTablaLotes().getValueAt(fila, 0).toString());

        int confirm = JOptionPane.showConfirmDialog(
                vista,
                "¿Está seguro de eliminar el lote #" + numLote + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.NO_OPTION) return;

        try (var ps = conexion.prepareStatement("DELETE FROM LOTE WHERE NUM_LOTE = ?")) {
            ps.setInt(1, numLote);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(vista, "Lote eliminado correctamente.");
            cargarTabla();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "No se pudo eliminar el lote:\n" + e.getMessage());
        }
    }

    // ========================================================
    // 🔹 BOTÓN AGREGAR
    // ========================================================
   private void abrirAgregar() {

    // Crear la vista de RegistroLote con la misma conexión
    RegistroLote vistaRegistro = new RegistroLote(conexion);

    // Crear su controlador
    ControladorRegistroLote controladorRegistro =
            new ControladorRegistroLote(vistaRegistro, conexion);

    // Mostrar la ventana
    vistaRegistro.setVisible(true);

    // Cerrar la vista actual si quieres
    vista.dispose(); 
}


    // ========================================================
    // 🔹 BOTÓN EDITAR
    // ========================================================
    private void abrirEditar() {
    int fila = vista.getTablaLotes().getSelectedRow();

    if (fila == -1) {
        JOptionPane.showMessageDialog(vista, "Seleccione un lote para editar.");
        return;
    }

    int numLote = Integer.parseInt(vista.getTablaLotes().getValueAt(fila, 0).toString());

    // Abrir vista EditarLote
    EditarLote vistaEditar = new EditarLote(conexion);
    ControladorEditarLote ctrl = new ControladorEditarLote(vistaEditar, conexion, numLote);

    vistaEditar.setVisible(true);
    vista.dispose();
}
}

