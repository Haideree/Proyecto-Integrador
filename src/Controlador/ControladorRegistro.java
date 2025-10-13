package Controlador;

/**
 *
 * @author Haider
*/
import Controlador.ControladorRegistroTecnico;
import Controlador.ControladorRegistroPropietario;
import Controlador.ControladorRegistroProductor;
import Vista.*;
import javax.swing.*;
import java.awt.event.*;

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
        vista.dispose();


    } else if (vista.getButtonDueno().isSelected()) {
        Registroprop vistaProp = new Registroprop();
        new ControladorRegistroPropietario(vistaProp);
        vistaProp.setVisible(true);
        vista.dispose();

    } else if (vista.getButtonProductor().isSelected()) {  
        Registroprod vistaProd = new Registroprod();
        new ControladorRegistroProductor(vistaProd); // conecta el controlador
        vistaProd.setVisible(true); // muestra la vista
        vista.dispose(); // cierra la vista actual

    } else {
        JOptionPane.showMessageDialog(vista, "⚠ ¡Selecciona un tipo de usuario para continuar!");
    }
}


    private void manejarVolver() {
        Vista.vistas ventanaLogin = new Vista.vistas();
        ControladorLogin controladorLogin = new ControladorLogin(ventanaLogin);
        ventanaLogin.setVisible(true);
        vista.dispose();
    }
}