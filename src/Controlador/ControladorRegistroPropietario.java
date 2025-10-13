package Controlador;

/**
 *
 * @author Haider
 */
import Vista.vistas;
import Controlador.ControladorLogin;
import Vista.Registroprop;
import Vista.Registro;
import Modelado.PropietarioDAO;
import javax.swing.JOptionPane;

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
            // 1. Obtener datos
            String docText = vista.getTextNumdocprop().getText().trim();
            String nombre = vista.getTextNomprop().getText().trim();
            String telText = vista.getTextTelefonoprop().getText().trim();
            String correo = vista.getTextCorreoprop().getText().trim();
            String contrasena = vista.getTXTContrasena().getText().trim();

            // 2. Validar
            if (docText.isEmpty() || telText.isEmpty() || correo.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "⚠️  Debes llenar documento, teléfono y correo.");
                return;
            }

            // 3. Convertir tipos
            int documento = Integer.parseInt(docText);
            long telefono = Long.parseLong(telText);

            // 4. Guardar usando DAO
            PropietarioDAO dao = new PropietarioDAO();
            dao.registrarPropietario(documento, nombre, telefono, correo, contrasena);

            JOptionPane.showMessageDialog(vista, "✅ Propietario registrado con éxito.");

            // 5. Limpiar campos
            vista.getTextNumdocprop().setText("");
            vista.getTextNomprop().setText("");
            vista.getTextTelefonoprop().setText("");
            vista.getTextCorreoprop().setText("");
            vista.getTXTContrasena().setText("");

            // 6. Volver al menú de registros
            vistas ventanaLogin = new vistas();
            new ControladorLogin(ventanaLogin); // conectar controlador
            ventanaLogin.setVisible(true);
            vista.dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "⚠ Documento y teléfono deben ser numéricos.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "❌ Error: " + e.getMessage());
        }
    }

    private void volverARegistro() {
        Registro ventanaRegistro = new Registro();
        new ControladorRegistro(ventanaRegistro);
        ventanaRegistro.setVisible(true);
        vista.dispose();
    }
}