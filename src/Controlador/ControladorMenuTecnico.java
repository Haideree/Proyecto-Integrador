package Controlador;

/**
 *
 * @author Haider
 */

import Vista.MenuTecnico;
import Vista.VerInspeccion;
import Vista.Login;

import Modelado.InformeDAO;

import java.awt.Font;
import javax.swing.*;
import java.sql.Connection;

public class ControladorMenuTecnico {

    private final MenuTecnico vista;
    private final int idTecnico;
    private final Connection conexion;

    public ControladorMenuTecnico(MenuTecnico vista, int idTecnico, Connection conexion) {
        this.vista = vista;
        this.idTecnico = idTecnico;
        this.conexion = conexion;

        agregarListeners();
    }

    private void agregarListeners() {

        // Inspecciones pendientes
        vista.getbtnInspPendientes().addActionListener(e -> abrirInspeccionesPendientes());

        // Generar informe
        vista.getbtnGenerarInforme().addActionListener(e -> generarInformeTecnico());
        vista.getbtnCerrarSesion().addActionListener(e -> cerrarSesion());
    }

    // ==============================
    //    ABRIR INSPECCIONES
    // ==============================
    private void abrirInspeccionesPendientes() {

        VerInspeccion ventana = new VerInspeccion();
        new ControladorInspeccion(ventana, idTecnico, conexion);

        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);

        vista.dispose();
    }

    // ==============================
    //   GENERAR INFORME
    // ==============================
    private void generarInformeTecnico() {

        try {
            InformeDAO dao = new InformeDAO(conexion);
            String informe = dao.informeEstadisticoTecnico(idTecnico);

            JTextArea area = new JTextArea(informe, 20, 50);
            area.setEditable(false);
            area.setFont(new Font("Monospaced", Font.PLAIN, 14));

            JOptionPane.showMessageDialog(
                    vista,
                    new JScrollPane(area),
                    "Informe Estadístico del Técnico",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    vista,
                    "Error al generar el informe: " + ex.getMessage()
            );
        }
    }

    // ==============================
    //   CERRAR SESIÓN
    // ==============================
    private void cerrarSesion() {
        Login login = new Login();
        new ControladorLogin(login);

        login.setVisible(true);
        vista.dispose();
    }
}
