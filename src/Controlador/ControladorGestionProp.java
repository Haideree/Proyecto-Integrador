/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

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
            System.out.println("Función eliminar aún no implementada");
        }

        // 🔃 BOTÓN ACTUALIZAR
        else if (source == vista.getBtnActualizar()) {
            mostrarPropietarios();
            System.out.println("✅ Tabla actualizada correctamente");
        }
    }
}

    
