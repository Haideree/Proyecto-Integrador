package Controlador;

/**
 *
 * @author Haider
 */
import Modelado.ProductorDAO;
import Vista.Registroprod;
import Vista.Registro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorRegistroProductor implements ActionListener {

    private Registroprod vista;
    private ProductorDAO modelo;

    public ControladorRegistroProductor(Registroprod vista) {
        this.vista = vista;
        this.modelo = new ProductorDAO();

        // Escuchar botones
        this.vista.getBtnSiguiente().addActionListener(this);
        this.vista.getBtnVolver().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnSiguiente()) {
            registrarProductor();
        } else if (e.getSource() == vista.getBtnVolver()) {
            volverARegistro();
        }
    }

    // =========================================================
    // 🔹 Método para registrar un productor
    // =========================================================
    private void registrarProductor() {
        try {
            String docStr = vista.getTxtDocumento().getText().trim();
            String nombre = vista.getTxtNombre().getText().trim();
            String telStr = vista.getTxtTelefono().getText().trim();
            String correo = vista.getTxtCorreo().getText().trim();
            String contrasena = vista.getTxtContrasena().getText().trim();

            if (docStr.isEmpty() || nombre.isEmpty() || telStr.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
                vista.mostrarMensaje("⚠ Todos los campos son obligatorios.");
                return;
            }

            int documento = Integer.parseInt(docStr);

            if (!telStr.matches("\\d+")) {
                vista.mostrarMensaje("⚠ El teléfono debe contener solo dígitos.");
                return;
            }

            if (!correo.contains("@") || !correo.contains(".")) {
                vista.mostrarMensaje("⚠ Ingresa un correo válido.");
                return;
            }

            modelo.registrarProductor(documento, nombre, telStr, correo, contrasena);
            vista.mostrarMensaje("✅ Productor registrado con éxito.");

            // Limpiar campos
            vista.getTxtDocumento().setText("");
            vista.getTxtNombre().setText("");
            vista.getTxtTelefono().setText("");
            vista.getTxtCorreo().setText("");
            vista.getTxtContrasena().setText("");

            // Volver al login
            Vista.vistas login = new Vista.vistas();
            new ControladorLogin(login);
            login.setVisible(true);
            vista.dispose();

        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("⚠ El documento debe ser un número válido.");
        } catch (Exception ex) {
            ex.printStackTrace();
            vista.mostrarMensaje("❌ Error al registrar el productor: " + ex.getMessage());
        }
    }

    // =========================================================
    // 🔹 Método para volver a la ventana anterior
    // =========================================================
    private void volverARegistro() {
        Registro ventanaRegistro = new Registro();
        new ControladorRegistro(ventanaRegistro);
        ventanaRegistro.setVisible(true);
        vista.dispose();
    }
}
