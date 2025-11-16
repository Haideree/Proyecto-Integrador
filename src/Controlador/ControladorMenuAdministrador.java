package Controlador;

import Modelado.InformeDAO;
import Vista.GestionPropietario;
import Vista.GestionTecnicos;
import Vista.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
    this.vista.getBtnReporteGlobal().addActionListener(this);
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
    //generar informe global
    public void generarInformeEstadistico() {
    try {
        InformeDAO dao = new InformeDAO(conexion);
        String reporte = dao.informeEstadistico(); // <<— tu nuevo informe global
        mostrarInformeEnEmergente("Informe Estadístico General", reporte);
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(
                vista,
                "Error generando informe estadístico: " + e.getMessage(),
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE
        );
    }
}

    
    public void mostrarInformeEnEmergente(String titulo, String contenido) {

    // Crear JDialog
    JDialog dialogo = new JDialog(vista, titulo, true);
    dialogo.setSize(750, 600);
    dialogo.setLocationRelativeTo(vista);
    dialogo.setLayout(new BorderLayout());
    dialogo.setResizable(true);

    // Crear JTextArea
    JTextArea area = new JTextArea();
    area.setEditable(false);
    area.setFont(new Font("Monospaced", Font.PLAIN, 14));  // ✔ Se ve pro
    area.setText(contenido);
    area.setMargin(new Insets(15, 20, 15, 20));            // ✔ Espaciado interno
    area.setBackground(new Color(250, 250, 250));          // ✔ Fondo suave
    area.setLineWrap(false);                               // ✔ Mantener alineación del informe
    area.setWrapStyleWord(true);

    // ScrollPane
    JScrollPane scroll = new JScrollPane(area);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    // Bordes bonitos en el scroll
    scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Añadir al diálogo
    dialogo.add(scroll, BorderLayout.CENTER);

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

        else if (source == vista.getBtnReporteGlobal()) {
            generarInformeEstadistico();
        }

        else if (source == vista.getBtnCerrarSesion()) {
            Login login = new Login();
            new ControladorLogin(login);
            login.setVisible(true);
            vista.dispose();
        }

    }
}
