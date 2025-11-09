/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.Registrotec;
import Vista.AdminMenu;
import Vista.GestionTecnicos;
import Vista.Registroprod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Haider
 */

public class ControladorGestionTec implements ActionListener {

    private final GestionTecnicos vista;

    public ControladorGestionTec(GestionTecnicos vista) {
        this.vista = vista;

        // Asignar escuchas a los botones
        this.vista.getBtnAgregar().addActionListener(this);
        this.vista.getBtnVolver().addActionListener(this);
        this.vista.getBtnEditar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
        this.vista.getBtnActualizar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // ➕ BOTÓN AGREGAR: abrir la ventana de registro
        if (source == vista.getBtnAgregar()) {
            Registrotec regTec = new Registrotec();
            new ControladorRegistroTecnico (regTec);
            regTec.setVisible(true);
            regTec.setLocationRelativeTo(null); // Centra la nueva ventana
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
            System.out.println("Función actualizar aún no implementada");
        }
    }
}

    

