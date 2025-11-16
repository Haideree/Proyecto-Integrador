package Controlador;

import Vista.AdminMenu;
import Vista.Registroprod;
import Modelado.ProductorDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Connection;

public class ControladorRegistroProductor implements ActionListener {

    private Registroprod vista;
    private ProductorDAO dao;
    private Connection conexion;

    public ControladorRegistroProductor(Registroprod vista,Connection conexion) {
        this.vista = vista;
        this.dao = new ProductorDAO();
        this.conexion=conexion;

        // Escuchar botones
        this.vista.getBtnSiguiente().addActionListener(this);
        this.vista.getBtnVolver().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnSiguiente()) {
            registrarProductor();
        } else if (e.getSource() == vista.getBtnVolver()) {
            AdminMenu reg = new AdminMenu(conexion);
            new ControladorMenuAdministrador(reg,conexion);
            reg.setVisible(true);
            vista.dispose(); // O abrir otra ventana si aplica
        }
    }

    private void registrarProductor() {
    try {
        String docText = vista.getTxtDocumento().getText().trim();
        String nombre = vista.getTxtNombre().getText().trim();
        String telefono = vista.getTxtTelefono().getText().trim();
        String correo = vista.getTxtCorreo().getText().trim();
        String contrasena = vista.getTxtContrasena().getText().trim();

        // 🚫 Validar campos vacíos
        if (docText.isEmpty() || nombre.isEmpty() || telefono.isEmpty() ||
                correo.isEmpty() || contrasena.isEmpty()) {
            vista.mostrarMensaje("⚠️ Todos los campos son obligatorios.");
            return;
        }

        // 🚫 Verificar espacios internos
        if (docText.contains(" ") || nombre.contains(" ") || telefono.contains(" ")
                || correo.contains(" ") || contrasena.contains(" ")) {
            vista.mostrarMensaje("⚠️ Ningún campo debe contener espacios.");
            return;
        }

        // 🆔 Documento: exactamente 10 dígitos
        if (!docText.matches("^\\d{10}$")) {
            vista.mostrarMensaje("🪪 El documento debe contener exactamente 10 dígitos numéricos.");
            return;
        }

        int documento;
        try {
            documento = Integer.parseInt(docText);
        } catch (Exception e) {
            vista.mostrarMensaje("❌ El documento debe ser completamente numérico.");
            return;
        }

        // 📝 Validar nombre (solo letras y espacios)
        if (!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) {
            vista.mostrarMensaje("📝 El nombre solo puede contener letras.");
            return;
        }

        // 📝 Longitud mínima de nombre
        if (nombre.length() < 3) {
            vista.mostrarMensaje("📝 El nombre debe tener mínimo 3 caracteres.");
            return;
        }

        // 📱 Teléfono: exactamente 10 dígitos
        if (!telefono.matches("^\\d{10}$")) {
            vista.mostrarMensaje("📱 El teléfono debe contener exactamente 10 dígitos.");
            return;
        }

        // 📧 Validar correo
        if (correo.length() > 40) {
            vista.mostrarMensaje("📧 El correo es demasiado largo (máximo 40 caracteres).");
            return;
        }

        if (!correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            vista.mostrarMensaje("📧 Correo inválido. Ejemplo: usuario@dominio.com");
            return;
        }

        // 🔐 Validar contraseña avanzada
        String mensajeContrasena = validarContrasena(contrasena);
        if (mensajeContrasena != null) {
            vista.mostrarMensaje("🔒 " + mensajeContrasena);
            return;
        }

        // 🚫 Evitar contraseñas fáciles
        if (contrasena.equalsIgnoreCase(nombre) ||
            contrasena.equalsIgnoreCase(correo) ||
            contrasena.equals(docText)) {
            vista.mostrarMensaje("🔒 La contraseña no puede ser igual al nombre, correo o documento.");
            return;
        }

        // 💾 Registrar si todo está ok
        dao.registrarProductor(documento, nombre, telefono, correo, contrasena);
        vista.mostrarMensaje("✅ Productor registrado exitosamente.");

        limpiarCampos();
        volverARegistro();

    } catch (Exception ex) {
        vista.mostrarMensaje("❌ Error al registrar el productor: " + ex.getMessage());
        ex.printStackTrace();
    }
}


    /**
     * Verifica que la contraseña cumpla con:
     * - mínimo 8 caracteres
     * - al menos una mayúscula
     * - al menos una minúscula
     * - al menos un número
     * - al menos un carácter especial
     */
    private String validarContrasena(String contrasena) {
        if (contrasena.length() < 8) {
            return "La contraseña debe tener al menos 8 caracteres.";
        }
        Pattern patron = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*._-]).+$");
        Matcher matcher = patron.matcher(contrasena);
        if (!matcher.matches()) {
            return "La contraseña debe incluir mayúsculas, minúsculas, números y un símbolo (!@#$%^&*._-).";
        }
        return null; // ✅ Contraseña válida
    }

    private void limpiarCampos() {
        vista.getTxtDocumento().setText("");
        vista.getTxtNombre().setText("");
        vista.getTxtTelefono().setText("");
        vista.getTxtCorreo().setText("");
        vista.getTxtContrasena().setText("");
    }
      private void volverARegistro() {
        AdminMenu ventanaRegistro = new AdminMenu(conexion);
        new ControladorMenuAdministrador(ventanaRegistro,conexion);
        ventanaRegistro.setVisible(true);
        vista.dispose();
    }
}

