package Controlador;

import Modelado.EliminarDAO;
import Modelado.Propietario;
import Modelado.EditarDAO;
import Modelado.PropietarioDAO;
import Vista.Registroprop;
import Vista.AdminMenu;
import Vista.GestionPropietario;
import Vista.Registroprod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Haider
 */

public class ControladorGestionProp implements ActionListener {

    private final GestionPropietario vista;

    public ControladorGestionProp(GestionPropietario vista) {
        this.vista = vista;

        // Asignar escuchas a los botones
        this.vista.getBtnAgregar().addActionListener(this);
        this.vista.getBtnVolver().addActionListener(this);
        this.vista.getBtnEditar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
        this.vista.getBtnActualizar().addActionListener(this);
    }
    
    private void mostrarPropietarios(){
        try {
            PropietarioDAO dao = new PropietarioDAO();
            List<Propietario> lista = dao.obtenerPropietarios();
            
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Numero Documento");
            modelo.addColumn("Nombre");
            modelo.addColumn("Telefono");
            modelo.addColumn("Correo");
            modelo.addColumn("Contraseña");
            
            for (Propietario p : lista) {
                modelo.addRow(new Object[]{
                    p.getDocumento(),
                    p.getNombre(),
                    p.getTelefono(),
                    p.getCorreo(),
                    p.getContrasena()
                });
            }
            vista.getTablaPropietarios().setModel(modelo);
            
        }catch (Exception e) {
            System.out.println("❌ Error al mostrar productores: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // ➕ BOTÓN AGREGAR: abrir la ventana de registro
        if (source == vista.getBtnAgregar()) {
            Registroprop regProp = new Registroprop();
            new ControladorRegistroPropietario (regProp);
            regProp.setVisible(true);
            regProp.setLocationRelativeTo(null); // Centra la nueva ventana
            vista.dispose(); // Cierra la ventana actual (opcional)
        }

        // ⮜ BOTÓN VOLVER (si tienes un menú anterior)
        else if (source == vista.getBtnVolver()) {
            
            AdminMenu menu = new AdminMenu();
            new ControladorMenuAdministrador(menu);
            menu.setVisible(true);
            menu.setLocationRelativeTo(null);
            vista.dispose();
        }

        // 📝 BOTÓN EDITAR PROPIETARIO
else if (source == vista.getBtnEditar()) {
    try {
        int fila = vista.getTablaPropietarios().getSelectedRow();
        if (fila == -1) {
            javax.swing.JOptionPane.showMessageDialog(vista, "Seleccione un propietario para editar.");
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) vista.getTablaPropietarios().getModel();
        int filaModelo = vista.getTablaPropietarios().convertRowIndexToModel(fila);

        int idProp = Integer.parseInt(modelo.getValueAt(filaModelo, 0).toString());

        // Pedimos los nuevos datos
        String nuevoNombre = javax.swing.JOptionPane.showInputDialog(vista, "Nuevo nombre:",
                modelo.getValueAt(filaModelo, 1).toString());
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) return;

        String nuevaContrasena = javax.swing.JOptionPane.showInputDialog(vista, "Nueva contraseña:",
                modelo.getValueAt(filaModelo, 4).toString());
        if (nuevaContrasena == null || nuevaContrasena.trim().isEmpty()) return;

        // 🔹 Validar contraseña segura
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

        String nuevoCorreo = javax.swing.JOptionPane.showInputDialog(vista, "Nuevo correo:",
                modelo.getValueAt(filaModelo, 2).toString());
        if (nuevoCorreo == null || nuevoCorreo.trim().isEmpty()) return;

        // 🔹 Validar correo
        if (!nuevoCorreo.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            javax.swing.JOptionPane.showMessageDialog(vista, "⚠ Correo electrónico no válido.");
            return;
        }

        String nuevoTelefono = javax.swing.JOptionPane.showInputDialog(vista, "Nuevo teléfono (10 dígitos):",
                modelo.getValueAt(filaModelo, 3).toString());
        if (nuevoTelefono == null || nuevoTelefono.trim().isEmpty()) return;

        // 🔹 Validar teléfono
        if (!nuevoTelefono.matches("\\d{10}")) {
            javax.swing.JOptionPane.showMessageDialog(vista, "⚠ El teléfono debe contener exactamente 10 números.");
            return;
        }

        // Ejecutamos el DAO
        EditarDAO editarDAO = new EditarDAO();
        boolean exito = editarDAO.editarPropietario(idProp, nuevoNombre, nuevaContrasena,
                nuevoCorreo, nuevoTelefono);

        javax.swing.JOptionPane.showMessageDialog(vista, exito ? "Propietario editado ✅" : "Error al editar ❌");

        // Actualizamos tabla
        if (exito) {
            mostrarPropietarios();
        }

    } catch (NumberFormatException ex) {
        javax.swing.JOptionPane.showMessageDialog(vista, "⚠️ Error: número de documento inválido");
    } catch (Exception ex) {
        javax.swing.JOptionPane.showMessageDialog(vista, "💥 Error: " + ex.getMessage());
        ex.printStackTrace();
    }
}



        // ➖ BOTÓN ELIMINAR
else if (source == vista.getBtnEliminar()) {

    int fila = vista.getTablaPropietarios().getSelectedRow();

    if (fila == -1) {
        javax.swing.JOptionPane.showMessageDialog(null,
                "Selecciona un propietario para eliminar.",
                "Aviso",
                javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Capturar documento de la tabla
    int documento = Integer.parseInt(
            vista.getTablaPropietarios().getValueAt(fila, 0).toString()
    );

    // Confirmación
    int confirm = javax.swing.JOptionPane.showConfirmDialog(null,
            "¿Seguro que deseas eliminar al propietario con documento: " + documento + "?",
            "Confirmar eliminación",
            javax.swing.JOptionPane.YES_NO_OPTION);

    if (confirm != javax.swing.JOptionPane.YES_OPTION) return;

    // Llamar al DAO
    EliminarDAO dao = new EliminarDAO();
    boolean eliminado = dao.eliminarPropietario(documento);

    if (eliminado) {
        javax.swing.JOptionPane.showMessageDialog(null,
                "Propietario eliminado correctamente.",
                "Éxito",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);

        mostrarPropietarios(); // refrescar tabla

    } else {
        javax.swing.JOptionPane.showMessageDialog(null,
                "No se pudo eliminar el propietario.",
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}


        // 🔃 BOTÓN ACTUALIZAR
        else if (source == vista.getBtnActualizar()) {
            mostrarPropietarios();
            System.out.println("✅ Tabla actualizada correctamente");
        }
    }
}

    
