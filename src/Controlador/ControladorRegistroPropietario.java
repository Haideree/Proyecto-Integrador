package Controlador;

/**
 *
 * @author Haider
 */
import Vista.AdminMenu;
import Vista.vistas;
import Vista.Registroprop;
import Modelado.PropietarioDAO;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;

public class ControladorRegistroPropietario {

    private Registroprop vista;

    public ControladorRegistroPropietario(Registroprop vista) {
        this.vista = vista;
        inicializarEventos();
    }

    private void inicializarEventos() {
        vista.getButtonSiguiente().addActionListener(e -> registrarPropietario());
        vista.getBtnVolver().addActionListener(e -> volverARegistro());
    }

    private void registrarPropietario() {
        try {
            // 1️⃣ Obtener datos
            String docText = vista.getTextNumdocprop().getText().trim();
            String nombre = vista.getTextNomprop().getText().trim();
            String telText = vista.getTextTelefonoprop().getText().trim();
            String correo = vista.getTextCorreoprop().getText().trim();
            String contrasena = vista.getTXTContrasena().getText().trim();

            // 2️⃣ Validar campos vacíos
            if (docText.isEmpty() || nombre.isEmpty() || telText.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "⚠️  Todos los campos son obligatorios.");
                return;
            }

            // 3️⃣ Validar formato numérico
            if (!docText.matches("\\d+") || !telText.matches("\\d+")) {
                JOptionPane.showMessageDialog(vista, "⚠ Documento y teléfono deben contener solo números.");
                return;
            }

            // 4️⃣ Validar correo electrónico
            if (!esCorreoValido(correo)) {
                JOptionPane.showMessageDialog(vista, "⚠️  Ingresa un correo electrónico válido (ejemplo@dominio.com).");
                return;
            }

            // 5️⃣ Validar contraseña segura
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

            // 6️⃣ Convertir tipos
            int documento = Integer.parseInt(docText);
            long telefono = Long.parseLong(telText);

            // 7️⃣ Registrar propietario
            PropietarioDAO dao = new PropietarioDAO();
            dao.registrarPropietario(documento, nombre, telefono, correo, contrasena);

            JOptionPane.showMessageDialog(vista, "✅ Propietario registrado con éxito.");

            // 8️⃣ Limpiar campos
            limpiarCampos();

            // 9️⃣ Ir a la vista de login
            volverARegistro();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "⚠ Documento y teléfono deben ser numéricos.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "❌ Error: " + e.getMessage());
        }
    }

    private boolean esCorreoValido(String correo) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(regex, correo);
    }

    private boolean esContrasenaSegura(String contrasena) {
        // Al menos 8 caracteres, una mayúscula, un número y un carácter especial
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*._-]).{8,}$";
        return Pattern.matches(regex, contrasena);
    }

    private void limpiarCampos() {
        vista.getTextNumdocprop().setText("");
        vista.getTextNomprop().setText("");
        vista.getTextTelefonoprop().setText("");
        vista.getTextCorreoprop().setText("");
        vista.getTXTContrasena().setText("");
    }

 private void volverALogin() {
        vistas login = new vistas();
        new ControladorLogin(login);
        login.setVisible(true);
        vista.dispose();
    }
 
  private void volverARegistro() {
       AdminMenu menu = new AdminMenu();
            new ControladorMenuAdministrador(menu);
            menu.setVisible(true);
            menu.setLocationRelativeTo(null);
            vista.dispose();
    }
}
