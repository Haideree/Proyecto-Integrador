package Controlador;

import Modelado.LugarProduccionDAO;
import Vista.RegistroLugarProdu;
import Vista.Predios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class ControladorRegistroLugarProdu implements ActionListener {

    private final RegistroLugarProdu vista;
    private final LugarProduccionDAO dao;
    private final Connection conexionActiva; // 🔹 conexión del usuario logueado

    // ✅ Recibe la conexión activa (del rol logueado)
    public ControladorRegistroLugarProdu(RegistroLugarProdu vista, Connection conexionRol) {
        this.vista = vista;
        this.conexionActiva = conexionRol;
        this.dao = new LugarProduccionDAO(conexionRol); // 💥 conexión inyectada

        // Escuchar botones
        this.vista.getButtonSiguiente().addActionListener(this);
        this.vista.getBtnVolver().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.getButtonSiguiente()) {
            String nombreLugar = vista.getTextNombreLugar().getText().trim();
            String empresa = vista.getTextEmpresaResp().getText().trim();

            if (nombreLugar.isEmpty() || empresa.isEmpty()) {
                JOptionPane.showMessageDialog(vista,
                        "⚠️ Todos los campos son obligatorios.",
                        "Campos vacíos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                dao.registrarLugarProduccion(nombreLugar, empresa);

                JOptionPane.showMessageDialog(vista,
                        "✅ Lugar de producción registrado correctamente 🎉",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                vista.getTextNombreLugar().setText("");
                vista.getTextEmpresaResp().setText("");

                vista.dispose();
                Predios ventanaPredios = new Predios(conexionActiva);
                new ControladorMostrarPredios(ventanaPredios, conexionActiva);
                ventanaPredios.setVisible(true);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista,
                        "❌ Error al registrar el lugar de producción: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }

        if (e.getSource() == vista.getBtnVolver()) {
            vista.dispose();
            Predios ventanaPredios = new Predios(conexionActiva);
            new ControladorMostrarPredios(ventanaPredios, conexionActiva);
            ventanaPredios.setVisible(true);
        }
    }
}

