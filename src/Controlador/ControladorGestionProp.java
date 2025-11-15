package Controlador;

import Modelado.EliminarDAO;
import Modelado.Propietario;
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

        // 📝 BOTÓN EDITAR
        else if (source == vista.getBtnEditar()) {
            System.out.println("Función editar aún no implementada");
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

    
