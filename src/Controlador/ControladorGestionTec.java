package Controlador;

import Modelado.EliminarDAO;
import Modelado.EditarDAO;
import Modelado.RegistrotecDAO;
import Modelado.Tecnico;
import Vista.Registrotec;
import Vista.AdminMenu;
import Vista.GestionTecnicos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ControladorGestionTec implements ActionListener {

    private final GestionTecnicos vista;

    public ControladorGestionTec(GestionTecnicos vista) {
        this.vista = vista;

        this.vista.getBtnAgregar().addActionListener(this);
        this.vista.getBtnVolver().addActionListener(this);
        this.vista.getBtnEditar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
        this.vista.getBtnActualizar().addActionListener(this);

        mostrarTecnicos();
    }
        
    private void mostrarTecnicos() {
        try {
            RegistrotecDAO dao = new RegistrotecDAO();
            List<Tecnico> lista = dao.obtenerTecnicos();
            
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Documento");
            modelo.addColumn("Tarjeta Profesional");
            modelo.addColumn("Nombre");
            modelo.addColumn("Correo");
            modelo.addColumn("Teléfono");
            modelo.addColumn("Contraseña");
            modelo.addColumn("Tipo Técnico");

            // Los IDs (se ocultarán)
            modelo.addColumn("ID_CORREO");
            modelo.addColumn("ID_TELEFONO");
            
            for (Tecnico t : lista) {
                modelo.addRow(new Object[]{
                    t.getIdentificacion(),
                    t.getTarjetapro(),
                    t.getNombre(),
                    t.getCorreo(),
                    t.getTelefono(),
                    t.getContrasena(),
                    t.getTipoTecnico(),
                    t.getIdCorreo(),
                    t.getIdTelefono()
                });
            }

            vista.getTablaTecnicos().setModel(modelo);

            // Ocultar columnas ID_CORREO y ID_TELEFONO
            vista.getTablaTecnicos().getColumnModel().getColumn(7).setMinWidth(0);
            vista.getTablaTecnicos().getColumnModel().getColumn(7).setMaxWidth(0);
            vista.getTablaTecnicos().getColumnModel().getColumn(7).setWidth(0);

            vista.getTablaTecnicos().getColumnModel().getColumn(8).setMinWidth(0);
            vista.getTablaTecnicos().getColumnModel().getColumn(8).setMaxWidth(0);
            vista.getTablaTecnicos().getColumnModel().getColumn(8).setWidth(0);

        } catch (Exception e) {
            System.out.println("❌ Error al mostrar técnicos: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // ➕ ABRIR REGISTRO
        if (source == vista.getBtnAgregar()) {
            Registrotec regTec = new Registrotec();
            new ControladorRegistroTecnico(regTec);
            regTec.setVisible(true);
            regTec.setLocationRelativeTo(null);
            vista.dispose();
        }

        // ⮜ VOLVER AL MENÚ
        else if (source == vista.getBtnVolver()) {
            AdminMenu menu = new AdminMenu();
            new ControladorMenuAdministrador(menu);
            menu.setVisible(true);
            menu.setLocationRelativeTo(null);
            vista.dispose();
        }

      

        // ❌ ELIMINAR
        else if (source == vista.getBtnEliminar()) {

            int fila = vista.getTablaTecnicos().getSelectedRow();

            if (fila == -1) {
                javax.swing.JOptionPane.showMessageDialog(null, "Seleccione un técnico para eliminar.");
                return;
            }

            int documento = Integer.parseInt(vista.getTablaTecnicos().getValueAt(fila, 0).toString());
            int idCorreo = Integer.parseInt(vista.getTablaTecnicos().getValueAt(fila, 7).toString());
            int idTelefono = Integer.parseInt(vista.getTablaTecnicos().getValueAt(fila, 8).toString());

            int confirm = javax.swing.JOptionPane.showConfirmDialog(null,
                    "¿Está seguro de eliminar al técnico con documento " + documento + "?",
                    "Confirmar eliminación",
                    javax.swing.JOptionPane.YES_NO_OPTION);

            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                try {
                    EliminarDAO dao = new EliminarDAO();
                    boolean eliminado = dao.eliminarTecnico(documento, idCorreo, idTelefono);

                    if (eliminado) {
                        javax.swing.JOptionPane.showMessageDialog(null, "Técnico eliminado correctamente.");
                        mostrarTecnicos();
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(null, "No se pudo eliminar el técnico.");
                    }

                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage());
                }
            }
        }

        // 🔄 ACTUALIZAR TABLA
        else if (source == vista.getBtnActualizar()) {
            mostrarTecnicos();
        }
        // 📝 EDITAR TÉCNICO
else if (source == vista.getBtnEditar()) {
    try {
        // Seleccionamos el técnico desde la tabla
        int fila = vista.getTablaTecnicos().getSelectedRow();
        if (fila == -1) {
            javax.swing.JOptionPane.showMessageDialog(vista, "Seleccione un técnico para editar.");
            return;
        }

        int idTec = Integer.parseInt(vista.getTablaTecnicos().getValueAt(fila, 0).toString());
        int idCorreo = Integer.parseInt(vista.getTablaTecnicos().getValueAt(fila, 7).toString());
        int idTelefono = Integer.parseInt(vista.getTablaTecnicos().getValueAt(fila, 8).toString());

        // Pedimos los nuevos datos
        String nuevoNombre = javax.swing.JOptionPane.showInputDialog(vista, "Nuevo nombre:",
                vista.getTablaTecnicos().getValueAt(fila, 2).toString());
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) return;

        String nuevaContrasena = javax.swing.JOptionPane.showInputDialog(vista, "Nueva contraseña:",
                vista.getTablaTecnicos().getValueAt(fila, 5).toString());
        if (nuevaContrasena == null || nuevaContrasena.trim().isEmpty()) return;

        // 🔹 Validar contraseña
        if (!nuevaContrasena.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*._-]).{8,}$")) {
            javax.swing.JOptionPane.showMessageDialog(vista, """
                ⚠️ Contraseña insegura.
                Debe tener al menos:
                • 8 caracteres
                • 1 mayúscula
                • 1 número
                • 1 carácter especial (!@#$%^&*._-)
                """);
            return;
        }

        String nuevoTipo = javax.swing.JOptionPane.showInputDialog(vista, "Nuevo tipo de técnico (ICA o Particular):",
                vista.getTablaTecnicos().getValueAt(fila, 6).toString());
        if (nuevoTipo == null || nuevoTipo.trim().isEmpty()) return;

        // 🔹 Validar tipo de técnico
        nuevoTipo = nuevoTipo.trim();
        if (!nuevoTipo.equalsIgnoreCase("ICA") && !nuevoTipo.equalsIgnoreCase("Particular")) {
            javax.swing.JOptionPane.showMessageDialog(vista, "⚠ Tipo de técnico inválido. Debe ser 'ICA' o 'Particular'.");
            return;
        }
        nuevoTipo = nuevoTipo.equalsIgnoreCase("ICA") ? "ICA" : "Particular";

        String nuevoCorreo = javax.swing.JOptionPane.showInputDialog(vista, "Nuevo correo:",
                vista.getTablaTecnicos().getValueAt(fila, 3).toString());
        if (nuevoCorreo == null || nuevoCorreo.trim().isEmpty()) return;

        // 🔹 Validar correo
        if (!nuevoCorreo.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            javax.swing.JOptionPane.showMessageDialog(vista, "⚠ Correo electrónico no válido.");
            return;
        }

        String nuevoTelefono = javax.swing.JOptionPane.showInputDialog(vista, "Nuevo teléfono (10 dígitos):",
                vista.getTablaTecnicos().getValueAt(fila, 4).toString());
        if (nuevoTelefono == null || nuevoTelefono.trim().isEmpty()) return;

        // 🔹 Validar teléfono
        if (!nuevoTelefono.matches("\\d{10}")) {
            javax.swing.JOptionPane.showMessageDialog(vista, "⚠ El teléfono debe contener exactamente 10 números.");
            return;
        }

        // Ejecutamos el DAO
        EditarDAO editarDAO = new EditarDAO();
        boolean exito = editarDAO.editarTecnico(idTec, nuevoNombre, nuevaContrasena, nuevoTipo,
                idCorreo, nuevoCorreo, idTelefono, nuevoTelefono);

        javax.swing.JOptionPane.showMessageDialog(vista, exito ? "Técnico editado ✅" : "Error al editar ❌");

        // Actualizamos tabla
        if (exito) {
            mostrarTecnicos();
        }

    } catch (NumberFormatException ex) {
        javax.swing.JOptionPane.showMessageDialog(vista, "⚠️ Error: número de documento inválido");
    } catch (Exception ex) {
        javax.swing.JOptionPane.showMessageDialog(vista, "💥 Error: " + ex.getMessage());
        ex.printStackTrace();
    }
}


    }

}
    


    

