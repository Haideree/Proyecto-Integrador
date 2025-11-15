package Controlador;

import Modelado.ProductorDAO;
import Modelado.Productor;
import Modelado.EliminarDAO;
import Vista.GestionProductores;
import Vista.Registroprod;
import Vista.AdminMenu;
import javax.swing.JOptionPane;
import Modelado.EditarDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ControladorGestionProductores implements ActionListener {

    private final GestionProductores vista;

    public ControladorGestionProductores(GestionProductores vista) {
        this.vista = vista;

        this.vista.getBtnAgregar().addActionListener(this);
        this.vista.getBtnVolver().addActionListener(this);
        this.vista.getBtnEditar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
        this.vista.getBtnActualizar().addActionListener(this);

        mostrarProductores();
    }

    private void mostrarProductores() {
        try {
            ProductorDAO dao = new ProductorDAO();
            List<Productor> lista = dao.obtenerProductores();


            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Documento");
            modelo.addColumn("Nombre");
            modelo.addColumn("Teléfono");
            modelo.addColumn("Correo");
            modelo.addColumn("Contraseña");

            // IDs ocultos
            modelo.addColumn("ID_CORREO");
            modelo.addColumn("ID_TELEFONO");

            for (Productor p : lista) {
                modelo.addRow(new Object[]{
                    p.getDocumento(),
                    p.getNombre(),
                    p.getTelefono(),
                    p.getCorreo(),
                    p.getContrasena(),
                    p.getIdCorreo(),
                    p.getIdTelefono()
                });
            }

            vista.getTablaProd().setModel(modelo);

            // ➤ Ocultar columnas
            vista.getTablaProd().getColumnModel().getColumn(5).setMinWidth(0);
            vista.getTablaProd().getColumnModel().getColumn(5).setMaxWidth(0);
            vista.getTablaProd().getColumnModel().getColumn(5).setWidth(0);

            vista.getTablaProd().getColumnModel().getColumn(6).setMinWidth(0);
            vista.getTablaProd().getColumnModel().getColumn(6).setMaxWidth(0);
            vista.getTablaProd().getColumnModel().getColumn(6).setWidth(0);

        } catch (Exception e) {
            System.out.println("❌ Error al mostrar productores: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // ➕ Agregar productor
        if (source == vista.getBtnAgregar()) {
            Registroprod reg = new Registroprod();
            new ControladorRegistroProductor(reg);
            reg.setVisible(true);
            reg.setLocationRelativeTo(null);
            vista.dispose();
        }

        // ⮜ Volver
        else if (source == vista.getBtnVolver()) {
            AdminMenu menu = new AdminMenu();
            new ControladorMenuAdministrador(menu);
            menu.setVisible(true);
            menu.setLocationRelativeTo(null);
            vista.dispose();
        }

       // 📝 Editar Productor
else if (source == vista.getBtnEditar()) {
    try {
        // Seleccionamos el productor desde la tabla
        int fila = vista.getTablaProd().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un productor para editar.");
            return;
        }

        int idProd = Integer.parseInt(vista.getTablaProd().getValueAt(fila, 0).toString());
        int idCorreo = Integer.parseInt(vista.getTablaProd().getValueAt(fila, 5).toString());
        int idTelefono = Integer.parseInt(vista.getTablaProd().getValueAt(fila, 6).toString());

        // Pedimos los nuevos datos
        String nuevoNombre = JOptionPane.showInputDialog(vista, "Nuevo nombre:",
                vista.getTablaProd().getValueAt(fila, 1).toString());
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) return;

        String nuevaContrasena = JOptionPane.showInputDialog(vista, "Nueva contraseña:",
                vista.getTablaProd().getValueAt(fila, 4).toString());
        if (nuevaContrasena == null || nuevaContrasena.trim().isEmpty()) return;

        String nuevoCorreo = JOptionPane.showInputDialog(vista, "Nuevo correo:",
                vista.getTablaProd().getValueAt(fila, 3).toString());
        if (nuevoCorreo == null || nuevoCorreo.trim().isEmpty()) return;

        String nuevoTelefono = JOptionPane.showInputDialog(vista, "Nuevo teléfono:",
                vista.getTablaProd().getValueAt(fila, 2).toString());
        if (nuevoTelefono == null || nuevoTelefono.trim().isEmpty()) return;

        // Ejecutamos el DAO con los IDs
        EditarDAO editarDAO = new EditarDAO();
        boolean exito = editarDAO.editarProductor(idProd, nuevoNombre, nuevaContrasena,
                nuevoCorreo, nuevoTelefono, idCorreo, idTelefono);

        JOptionPane.showMessageDialog(vista, exito ? "Productor editado ✅" : "Error al editar ❌");

        // Actualizamos tabla
        if (exito) {
            mostrarProductores();
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(vista, "⚠️ Error: número de documento inválido");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(vista, "💥 Error: " + ex.getMessage());
        ex.printStackTrace();
    }
}





        // ❌ Eliminar productor
        else if (source == vista.getBtnEliminar()) {

            int fila = vista.getTablaProd().getSelectedRow();

            if (fila == -1) {
                javax.swing.JOptionPane.showMessageDialog(null, "Seleccione un productor para eliminar.");
                return;
            }

            int documento = Integer.parseInt(vista.getTablaProd().getValueAt(fila, 0).toString());
            int idCorreo = Integer.parseInt(vista.getTablaProd().getValueAt(fila, 5).toString());
            int idTelefono = Integer.parseInt(vista.getTablaProd().getValueAt(fila, 6).toString());

            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                    null,
                    "¿Está seguro de eliminar al productor con documento " + documento + "?",
                    "Confirmar eliminación",
                    javax.swing.JOptionPane.YES_NO_OPTION
            );

            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                try {
                    EliminarDAO dao = new EliminarDAO();
                    boolean eliminado = dao.eliminarProductor(documento, idCorreo, idTelefono);

                    if (eliminado) {
                        javax.swing.JOptionPane.showMessageDialog(null, "Productor eliminado correctamente.");
                        mostrarProductores();
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(null, "No se pudo eliminar el productor.");
                    }

                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage());
                }
            }
        }

        // 🔄 Actualizar tabla
        else if (source == vista.getBtnActualizar()) {
            mostrarProductores();
        }
    }
}

