/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author Haider
 */

import Controlador.ControladorRegistroProductor;
import Modelado.Productor;
import Modelado.ProductorDAO;
import Vista.AdminMenu;
import Vista.GestionProductores;
import Vista.Registroprod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import Modelado.Productor;
import Modelado.ProductorDAO;
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class ControladorGestionProductores implements ActionListener {

    private final GestionProductores vista;

    public ControladorGestionProductores(GestionProductores vista) {
        this.vista = vista;

        // Asignar escuchas a los botones
        this.vista.getBtnAgregar().addActionListener(this);
        this.vista.getBtnVolver().addActionListener(this);
        this.vista.getBtnEditar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
        this.vista.getBtnActualizar().addActionListener(this);

        // 👇 Al abrir la ventana, muestra los productores automáticamente
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

            for (Productor p : lista) {
                modelo.addRow(new Object[]{
                    p.getDocumento(),
                    p.getNombre(),
                    p.getTelefono(),
                    p.getCorreo(),
                    p.getContrasena()
                });
            }

            vista.getTablaProd().setModel(modelo);

        } catch (Exception e) {
            System.out.println("❌ Error al mostrar productores: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // ➕ BOTÓN AGREGAR: abrir la ventana de registro
        if (source == vista.getBtnAgregar()) {
            Registroprod regProd = new Registroprod();
            new ControladorRegistroProductor(regProd);
            regProd.setVisible(true);
            regProd.setLocationRelativeTo(null); // Centra la nueva ventana
            vista.dispose(); // Cierra la ventana actual (opcional)
        }

        // ⮜ BOTÓN VOLVER
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
            mostrarProductores();
            System.out.println("✅ Tabla actualizada correctamente");
        }
    }
}
