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
            // Capturar datos desde la vista
            String docText = vista.getTxtDocumento().getText().trim();
            String nombre = vista.getTxtNombre().getText().trim();
            String telefono = vista.getTxtTelefono().getText().trim();
            String correo = vista.getTxtCorreo().getText().trim();
            String contrasena = vista.getTxtContrasena().getText().trim();

            // 🔹 Validaciones generales
            if (docText.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
                vista.mostrarMensaje("⚠️ Todos los campos son obligatorios.");
                return;
            }

            // 🔹 Validar documento numérico
            int documento;
            try {
                documento = Integer.parseInt(docText);
            } catch (NumberFormatException ex) {
                vista.mostrarMensaje("❌ El número de documento debe contener solo números.");
                return;
            }

            // 🔹 Validar correo electrónico
            if (!correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                vista.mostrarMensaje("📧 Correo inválido. Ejemplo válido: usuario@dominio.com");
                return;
            }

            // 🔹 Validar teléfono
            if (!telefono.matches("^\\d{7,10}$")) {
                vista.mostrarMensaje("📱 El teléfono debe tener entre 7 y 10 dígitos numéricos.");
                return;
            }

            // 🔹 Validar contraseña
            String mensajeContrasena = validarContrasena(contrasena);
            if (mensajeContrasena != null) {
                vista.mostrarMensaje("🔒 " + mensajeContrasena);
                return;
            }

            // 🔹 Si todo está bien, registrar
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

