package Controlador;

/**
 *
 * @author Haider
 */

import Controlador.ControladorLogin;
import Vista.Registrotec;
import Modelado.RegistrotecDAO;
import Vista.Registro;
import javax.swing.JOptionPane;
import Vista.vistas;

public class ControladorRegistroTecnico {

    private final Registrotec vista;

    public ControladorRegistroTecnico(Registrotec vista) {
        this.vista = vista;

        // Eventos de botones
        this.vista.getButton_siguiente().addActionListener(e -> registrarTecnico());
        this.vista.getBtnVolver().addActionListener(e -> volverARegistro());

        // Comportamiento dinámico del campo de tarjeta profesional
        this.vista.getRadioICA().addActionListener(e -> vista.getText_numtarjtec().setEnabled(true));
        this.vista.getRadioParticular().addActionListener(e -> {
            vista.getText_numtarjtec().setEnabled(false);
            vista.getText_numtarjtec().setText("");
        });
    }

    private void registrarTecnico() {
        String docStr = vista.getText_numdoctec().getText().trim();
        String tarjStr = vista.getText_numtarjtec().getText().trim();
        String nombre = vista.getText_nombretec().getText().trim();
        String telStr = vista.getText_telefonotec().getText().trim();
        String correo = vista.getText_correotec().getText().trim();
        String contrasena = vista.getTXT_contrasena().getText().trim();

        boolean esICA = vista.getRadioICA().isSelected();
        boolean esParticular = vista.getRadioParticular().isSelected();

        // Validar que se haya seleccionado un tipo
        if (!esICA && !esParticular) {
            JOptionPane.showMessageDialog(vista, "⚠ Debes seleccionar el tipo de técnico (ICA o Particular).");
            return;
        }

        // Validar campos obligatorios
        if (docStr.isEmpty() || nombre.isEmpty() || telStr.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "⚠ Todos los campos obligatorios deben llenarse.");
            return;
        }

        // Si es del ICA, la tarjeta es obligatoria
        if (esICA && tarjStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "⚠ La tarjeta profesional es obligatoria para técnicos del ICA.");
            return;
        }

        // Validaciones básicas
        try {
            Integer.parseInt(docStr);
            if (!telStr.matches("\\d+")) {
                JOptionPane.showMessageDialog(vista, "⚠ El teléfono debe contener solo números.");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "⚠ El documento debe ser numérico.");
            return;
        }

        if (!correo.contains("@") || !correo.contains(".")) {
            JOptionPane.showMessageDialog(vista, "⚠ Correo no válido.");
            return;
        }

        // DAO
        try {
            RegistrotecDAO dao = new RegistrotecDAO();
            dao.registrarTecnico(
                Integer.parseInt(docStr),
                tarjStr.isEmpty() ? null : Long.parseLong(tarjStr),
                nombre,
                telStr,
                correo,
                contrasena,
                esICA ? "ICA" : "Particular"
            );

            JOptionPane.showMessageDialog(vista, "✅ Técnico registrado correctamente (" + (esICA ? "ICA" : "Particular") + ").");
            volverALogin();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "❌ Error al registrar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void volverARegistro() {
        Registro ventanaRegistro = new Registro();
        new ControladorRegistro(ventanaRegistro);
        ventanaRegistro.setVisible(true);
        vista.dispose();
    }
    
    private void volverALogin() {
        vistas login = new vistas();
        new ControladorLogin(login); // ← Aquí va el controlador correcto
        login.setVisible(true);
        vista.dispose();
    }
}