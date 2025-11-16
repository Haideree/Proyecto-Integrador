package Controlador;

/**
 *
 * @author Haider
 */

import java.sql.Connection;
import Vista.Registrotec;
import Modelado.RegistrotecDAO;
import Vista.AdminMenu;
import javax.swing.JOptionPane;
import Vista.Login;
import java.util.regex.Pattern;
// import java.security.MessageDigest;  // ← Descomenta si quieres encriptar contraseña
// import java.security.NoSuchAlgorithmException;

public class ControladorRegistroTecnico {
    
    private Connection conexion;
    private final Registrotec vista;

    public ControladorRegistroTecnico(Registrotec vista,Connection conexion) {
        this.vista = vista;
        this.conexion=conexion;
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

        // 1️⃣ Validar selección de tipo
        if (!esICA && !esParticular) {
            JOptionPane.showMessageDialog(vista, "⚠ Debes seleccionar el tipo de técnico (ICA o Particular).");
            return;
        }

        // 2️⃣ Validar campos obligatorios
        if (docStr.isEmpty() || nombre.isEmpty() || telStr.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "⚠ Todos los campos obligatorios deben llenarse.");
            return;
        }

        // 3️⃣ Validar tarjeta profesional (solo ICA)
        if (esICA && tarjStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "⚠ La tarjeta profesional es obligatoria para técnicos del ICA.");
            return;
        }

        // 4️⃣ Validaciones numéricas
if (!docStr.matches("\\d+")) {
    JOptionPane.showMessageDialog(vista, "⚠ El documento debe ser numérico.");
    return;
}
if (!telStr.matches("\\d+")) {
    JOptionPane.showMessageDialog(vista, "⚠ El teléfono debe contener solo números.");
    return;
}
// ✅ Validación de longitud del teléfono
if (telStr.length() != 10) {
    JOptionPane.showMessageDialog(vista, "⚠ El teléfono debe tener 10 dígitos.");
    return;
}

if (esICA && !tarjStr.matches("\\d+")) {
    JOptionPane.showMessageDialog(vista, "⚠ La tarjeta profesional debe ser numérica.");
    return;
}

        // 5️⃣ Validar correo electrónico
        if (!esCorreoValido(correo)) {
            JOptionPane.showMessageDialog(vista, "⚠ Correo electrónico no válido.");
            return;
        }

        // 6️⃣ Validar seguridad de contraseña
        if (!esContrasenaSegura(contrasena)) {
            JOptionPane.showMessageDialog(vista, """
                ⚠️ Contraseña insegura.
                Debe tener al menos:
                • 8 caracteres
                • 1 mayúscula
                • 1 número
                • 1 carácter especial (!@#$%^&*._-)
                """);
            return;
        }

        // // 🔒 7️⃣ (Opcional) Encriptar contraseña antes de guardarla
        // contrasena = encriptarSHA256(contrasena);

        // 8️⃣ Registrar en la base de datos
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
            limpiarCampos();
            volverARegistro();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "❌ Error al registrar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ✅ Validar formato del correo
    private boolean esCorreoValido(String correo) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(regex, correo);
    }

    // ✅ Validar fuerza de contraseña
    private boolean esContrasenaSegura(String contrasena) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*._-]).{8,}$";
        return Pattern.matches(regex, contrasena);
    }

    private void limpiarCampos() {
        vista.getText_numdoctec().setText("");
        vista.getText_numtarjtec().setText("");
        vista.getText_nombretec().setText("");
        vista.getText_telefonotec().setText("");
        vista.getText_correotec().setText("");
        vista.getTXT_contrasena().setText("");
    }

    private void volverARegistro() {
       AdminMenu menu = new AdminMenu(conexion);
            new ControladorMenuAdministrador(menu,conexion);
            menu.setVisible(true);
            menu.setLocationRelativeTo(null);
            vista.dispose();
    }

    private void volverALogin() {
        Login login = new Login();
        new ControladorLogin(login);
        login.setVisible(true);
        vista.dispose();
    }
}
