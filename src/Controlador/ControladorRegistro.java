package Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Vista.*;

public class ControladorRegistro implements ActionListener {

    private Registro vista;

    public ControladorRegistro(Registro vista) {
        this.vista = vista;

        // Escuchamos eventos
        this.vista.getButtonSiguiente().addActionListener(this);
        this.vista.getBtnVolver().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getButtonSiguiente()) {
            manejarSiguiente();
        } else if (e.getSource() == vista.getBtnVolver()) {
            manejarVolver();
        }
    }

    private void manejarSiguiente() {
        if (vista.getButtonTecnico().isSelected()) {
            Registrotec vistaTec = new Registrotec();
            new ControladorRegistroTecnico(vistaTec);
            vistaTec.setVisible(true);
            Window window = SwingUtilities.getWindowAncestor(vista);
            if (window != null) {
                window.dispose();
            }

        } else if (vista.getButtonDueno().isSelected()) {
            Registroprop vistaProp = new Registroprop();
            new ControladorRegistroPropietario(vistaProp);
            vistaProp.setVisible(true);
            SwingUtilities.getWindowAncestor(vista).dispose();

        } else if (vista.getButtonProductor().isSelected()) {
            Registroprod vistaProd = new Registroprod();
            new ControladorRegistroProductor(vistaProd);
            vistaProd.setVisible(true);
            SwingUtilities.getWindowAncestor(vista).dispose();


        } else {
            JOptionPane.showMessageDialog(vista, "⚠ ¡Selecciona un tipo de usuario para continuar!");
        }
    }

    private void manejarVolver() {
        vistas ventanaLogin = new vistas();
        new ControladorLogin(ventanaLogin);
        ventanaLogin.setVisible(true);
        SwingUtilities.getWindowAncestor(vista).dispose();

    }
}
