package Controlador;

import Modelado.InformeDAO;
import Vista.GestionPropietario;
import Vista.GestionTecnicos;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

public class ControladorMenuAdministrador implements ActionListener {

    private final AdminMenu vista;
  
    private final Connection conexion;

public ControladorMenuAdministrador(AdminMenu vista, Connection conexion) {
    this.vista = vista;
    this.conexion = conexion;

    this.vista.getBtnProductores().addActionListener(this);
    this.vista.getBtnTecnicos().addActionListener(this);
    this.vista.getBtnPropietarios().addActionListener(this);
    this.vista.getBtnReportes().addActionListener(this);
    this.vista.getBtnCerrarSesion().addActionListener(this);
    this.vista.getBtnAsignarInspeccion().addActionListener(this);
}

    
    public void generarInformeInspecciones() {
    try {
        InformeDAO dao = new InformeDAO(conexion);
        String reporte = dao.informeInspecciones();
        mostrarInformeEnEmergente("Informe General de Inspecciones", reporte);
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(vista,
                "Error generando informe: " + e.getMessage());
    }
}

    
    public void mostrarInformeEnEmergente(String titulo, String contenido) {

    // Crear un JDialog emergente
    javax.swing.JDialog dialogo = new javax.swing.JDialog(vista, titulo, true);
    dialogo.setSize(600, 500);
    dialogo.setLocationRelativeTo(vista);

    // Crear un área de texto con scroll
    javax.swing.JTextArea area = new javax.swing.JTextArea();
    area.setEditable(false);
    area.setText(contenido);

    javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(area);

    dialogo.add(scroll);
    dialogo.setVisible(true);
}





    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == vista.getBtnProductores()) {
            GestionProductores productores = new GestionProductores();
            new ControladorGestionProductores(productores,conexion);
            productores.setVisible(true);
            vista.dispose(); // Cierra AdminMenu
        } 
        else if (source == vista.getBtnTecnicos()) {
            GestionTecnicos tecnicos = new GestionTecnicos();
            new ControladorGestionTec(tecnicos,conexion);
            tecnicos.setVisible(true);
            vista.dispose();
        } 
        else if (source == vista.getBtnPropietarios()) {
            GestionPropietario propietarios = new GestionPropietario();
            new ControladorGestionProp(propietarios,conexion);
            propietarios.setVisible(true);
            vista.dispose();
        } 
        else if(source ==vista.getBtnAsignarInspeccion()){
            AsignarTecnico asignar = new AsignarTecnico();
            new ControladorAsignarInspeccion(asignar,conexion);
            asignar.setVisible(true);
            vista.dispose();
        }
        else if (source == vista.getBtnReportes()) {
    generarInformeInspecciones();
}



        else if (source == vista.getBtnCerrarSesion()) {
            Login login = new Login();
            new ControladorLogin(login);// o Login si así se llama tu ventana
            login.setVisible(true);
            vista.dispose();
        }
    }
}
