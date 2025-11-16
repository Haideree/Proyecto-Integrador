package Controlador;

import Modelado.LugarProduccionDAO;
import Vista.RegistroLugarProdu;
import Vista.MenuProductor;
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

// =============================
// 🔹 Validación de campos vacíos
// =============================
if (nombreLugar.isEmpty() || empresa.isEmpty()) {
    JOptionPane.showMessageDialog(vista,
            "⚠️ Todos los campos son obligatorios.",
            "Campos vacíos",
            JOptionPane.WARNING_MESSAGE);
    return;
}

// =============================
// 🔹 Validación robusta de nombres
// =============================

// No permitir solo números
if (nombreLugar.matches("\\d+")) {
    JOptionPane.showMessageDialog(vista,
            "❌ El nombre del lugar no puede ser únicamente numérico.",
            "Dato inválido",
            JOptionPane.ERROR_MESSAGE);
    return;
}

if (empresa.matches("\\d+")) {
    JOptionPane.showMessageDialog(vista,
            "❌ El nombre de la empresa no puede ser únicamente numérico.",
            "Dato inválido",
            JOptionPane.ERROR_MESSAGE);
    return;
}

// Deben tener por lo menos 2 letras reales
if (!nombreLugar.matches(".*[A-Za-z].*")) {
    JOptionPane.showMessageDialog(vista,
            "❌ El nombre del lugar debe contener letras.",
            "Dato inválido",
            JOptionPane.ERROR_MESSAGE);
    return;
}

if (!empresa.matches(".*[A-Za-z].*")) {
    JOptionPane.showMessageDialog(vista,
            "❌ La empresa responsable debe contener letras.",
            "Dato inválido",
            JOptionPane.ERROR_MESSAGE);
    return;
}

// Evitar nombres de 1 solo carácter
if (nombreLugar.length() < 2 || empresa.length() < 2) {
    JOptionPane.showMessageDialog(vista,
            "❌ Cada campo debe tener al menos 2 caracteres.",
            "Dato inválido",
            JOptionPane.ERROR_MESSAGE);
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
                MenuProductor ventanaPredios = new MenuProductor(conexionActiva);
                new ControladorMostrarLugares(ventanaPredios, conexionActiva);
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
            MenuProductor ventanaPredios = new MenuProductor(conexionActiva);
            new ControladorMostrarLugares(ventanaPredios, conexionActiva);
            ventanaPredios.setVisible(true);
        }
    }
}

