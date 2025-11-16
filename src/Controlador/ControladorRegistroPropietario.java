package Controlador;

/**
 *
 * @author Haider
 */
import Vista.AdminMenu;
import Vista.Login;
import Vista.Registroprop;
import Modelado.PropietarioDAO;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;

public class ControladorRegistroPropietario {
    private Connection conexion;
    private Registroprop vista;

    public ControladorRegistroPropietario(Registroprop vista, Connection conexion) {
    this.conexion = conexion;     // ahora sí funciona
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
            JOptionPane.showMessageDialog(vista, "⚠️ Todos los campos son obligatorios.");
            return;
        }

        // 3️⃣ Documento: exactamente 10 dígitos
        if (!docText.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(vista, "⚠️ El documento debe tener exactamente 10 dígitos numéricos.");
            return;
        }

        // 4️⃣ Nombre: solo letras y espacios
        if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            JOptionPane.showMessageDialog(vista, "⚠️ El nombre solo puede contener letras y espacios.");
            return;
        }

        // 5️⃣ Teléfono: exactamente 10 dígitos
        if (!telText.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(vista, "⚠️ El teléfono debe tener exactamente 10 dígitos numéricos.");
            return;
        }

        // 6️⃣ Correo válido
        if (!esCorreoValido(correo)) {
            JOptionPane.showMessageDialog(vista, "⚠️ Ingresa un correo electrónico válido.");
            return;
        }

        // 7️⃣ Contraseña segura
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

        // 8️⃣ Convertir tipos
        int documento = Integer.parseInt(docText);
        long telefono = Long.parseLong(telText);

        // 9️⃣ Registrar en BD
        PropietarioDAO dao = new PropietarioDAO();
        dao.registrarPropietario(documento, nombre, telefono, correo, contrasena);

        JOptionPane.showMessageDialog(vista, "✅ Propietario registrado con éxito.");

        // 🔟 Limpiar y volver
        limpiarCampos();
        volverARegistro();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(vista, "❌ Error: " + e.getMessage());
        e.printStackTrace();
    }
}

private boolean esCorreoValido(String correo) {
    String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
    return Pattern.matches(regex, correo);
}

private boolean esContrasenaSegura(String contrasena) {
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
        Login login = new Login();
        new ControladorLogin(login);
        login.setVisible(true);
        vista.dispose();
    }
 
  private void volverARegistro() {
       AdminMenu menu = new AdminMenu(conexion);
            new ControladorMenuAdministrador(menu,conexion);
            menu.setVisible(true);
            menu.setLocationRelativeTo(null);
            vista.dispose();
    }
}
