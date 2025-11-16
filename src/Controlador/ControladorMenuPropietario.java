package Controlador;

/**
 *
 * @author Haider
 */

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import Modelado.InformeDAO;
import Vista.MenuPropietario;
import Vista.AdministrarLotes;
import Vista.AdministrarPredios;
import Vista.Login;
import java.awt.Font;
import java.sql.Connection;

public class ControladorMenuPropietario {

    private MenuPropietario vista;
    private Connection conexion;
    private int docPropietario;

    // ======================
    //      CONSTRUCTOR
    // ======================
    public ControladorMenuPropietario(MenuPropietario vista, Connection conexion, int docPropietario) {
        this.vista = vista;
        this.conexion = conexion;
        this.docPropietario = docPropietario;

        inicializarEventos();
    }

    // ======================
    //   EVENTOS BOTONES
    // ======================
    private void inicializarEventos() {

        // Abrir predios
        vista.getBtnAdministrarPredio().addActionListener(e -> abrirAdministrarPredios());

        // Abrir lotes
        vista.getBtnAdministrarLote().addActionListener(e -> abrirAdministrarLotes());

        // Generar informe
        vista.getBtnGenerarInforme().addActionListener(e -> generarInforme());

        // Cerrar sesión
        vista.getBtnCerrarSesion().addActionListener(e -> cerrarSesion());
        
        //Generar informe global
        
        
    }

    // ==========================
    //     ABRIR VISTA LOTES
    // ==========================
    private void abrirAdministrarLotes() {
        AdministrarLotes v = new AdministrarLotes(conexion, docPropietario);
        new ControladorAdministrarLotes(v, conexion, docPropietario);

        v.setVisible(true);
        vista.dispose();
    }

    // ==========================
    //     ABRIR VISTA PREDIOS
    // ==========================
    private void abrirAdministrarPredios() {
        AdministrarPredios v = new AdministrarPredios(conexion, docPropietario);
        new ControladorAdministrarPredio(v, conexion, docPropietario);

        v.setVisible(true);
        vista.dispose();
    }

    // ==========================
    //    GENERAR INFORME
    // ==========================
   private void generarInforme() {
    try {
        InformeDAO dao = new InformeDAO(conexion);

        String informe = dao.informePropietario(docPropietario);

        JTextArea area = new JTextArea(informe);
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.setEditable(false);
        area.setColumns(60);   // 🔥 Tamaño ancho recomendado
        area.setRows(25);      // 🔥 Tamaño alto recomendado

        JScrollPane scroll = new JScrollPane(area);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JOptionPane.showMessageDialog(
                vista,
                scroll,
                "Informe del Propietario",
                JOptionPane.INFORMATION_MESSAGE
        );

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                vista,
                "Error al generar informe: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
   
  




    // ==========================
    //       CERRAR SESIÓN
    // ==========================
    private void cerrarSesion() {
        Login login = new Login();
        new ControladorLogin(login);

        login.setVisible(true);
        vista.dispose();
    }
}
