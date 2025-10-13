package Controlador;

/**
 *
 * @author Haider
 */

import Modelado.LoginDAO;
import Vista.Predios;
import Vista.Registro;
import Vista.vistas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControladorLogin implements ActionListener {

    private final vistas vista;
    private final LoginDAO modelo;

    public ControladorLogin(vistas vista) {
        this.vista = vista;
        this.modelo = new LoginDAO();

        // Escuchadores de botones
        this.vista.getButtonIngresar().addActionListener(this);
        this.vista.getButtonRegistrar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getButtonIngresar()) {
            iniciarSesion();
        } else if (e.getSource() == vista.getButtonRegistrar()) {
            abrirRegistro();
        }
    }

    private void iniciarSesion() {
        String correo = vista.getTxtUsuario().getText().trim();
        String contrasena = new String(vista.getTxtContrasena().getPassword()).trim();

        if (correo.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "⚠️ Ingresa usuario y contraseña.");
            return;
        }

        // 🔹 Ahora devuelve NUMERODOCUMENTO para propietarios, "tecnico" o "productor" para otros
        String resultado = modelo.validarUsuario(correo, contrasena);

        if (resultado == null) {
            JOptionPane.showMessageDialog(vista, "❌ Usuario o contraseña incorrectos.");
            return;
        }

        try {
            if (resultado.matches("\\d+")) {
                // 🔹 Propietario: resultado es NUMERODOCUMENTO
                JOptionPane.showMessageDialog(vista, "Bienvenido al sistema Propietario 👷‍♂️");
        
                // Cerrar login
                vista.dispose();

            } else if ("tecnico".equals(resultado)) {
                JOptionPane.showMessageDialog(vista, "Bienvenido al sistema Técnico 🔧");
                vista.dispose();

            } else if ("productor".equals(resultado)) {
                JOptionPane.showMessageDialog(vista, "Bienvenido al sistema Productor 🌱");
                Predios menu = new Predios();
                menu.setVisible(true);
                menu.setLocationRelativeTo(null);
                vista.dispose();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al abrir ventana: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void abrirRegistro() {
        Registro vistaRegistro = new Registro();
        ControladorRegistro controladorRegistro = new ControladorRegistro(vistaRegistro);
        vistaRegistro.setVisible(true);
        vistaRegistro.setLocationRelativeTo(null);
        vista.dispose();
    }
}

