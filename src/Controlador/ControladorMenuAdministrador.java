package Controlador;


import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

public class ControladorMenuAdministrador implements ActionListener {

    private final AdminMenu vista;

    public ControladorMenuAdministrador(AdminMenu vista) {
        this.vista = vista;
        // Escuchar botones
        this.vista.getBtnProductores().addActionListener(this);
        this.vista.getBtnTecnicos().addActionListener(this);
        this.vista.getBtnPropietarios().addActionListener(this);
        this.vista.getBtnReportes().addActionListener(this);
        this.vista.getBtnCerrarSesion().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == vista.getBtnProductores()) {
            GestionProductores productores = new GestionProductores();
            new ControladorGestionProductores(productores);
            productores.setVisible(true);
            vista.dispose(); // Cierra AdminMenu
        } 
        else if (source == vista.getBtnTecnicos()) {
            GestionTecnicos tecnicos = new GestionTecnicos();
            new ControladorGestionTec(tecnicos);
            tecnicos.setVisible(true);
            vista.dispose();
        } 
        else if (source == vista.getBtnPropietarios()) {
            GestionPropietario propietarios = new GestionPropietario();
            new ControladorGestionProp(propietarios);
            propietarios.setVisible(true);
            vista.dispose();
        } 
        /*else if (source == vista.getBtnReportes()) {
            ReportesVista reportes = new ReportesVista();
            reportes.setVisible(true);
            vista.dispose();
        } */
        else if (source == vista.getBtnCerrarSesion()) {
            vistas login = new vistas();
            new ControladorLogin(login);// o Login si así se llama tu ventana
            login.setVisible(true);
            vista.dispose();
        }
    }
}
