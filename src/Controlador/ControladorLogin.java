package Controlador;

/**
 *
 * @author Haider
 */
import Controlador.ControladorMostrarPredios;
import Modelado.LoginDAO;
import Modelado.CConexion;
import Vista.AdminMenu;
import Vista.Predios;
import Vista.vistas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class ControladorLogin implements ActionListener {

    private final vistas vista;
    private final LoginDAO modelo;

    // 🔹 Nueva variable: conexión activa del usuario logueado
    private Connection conexionActiva;

    public ControladorLogin(vistas vista) {
        this.vista = vista;
        this.modelo = new LoginDAO();

        // Escuchador del botón "Ingresar"
        this.vista.getButtonIngresar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getButtonIngresar()) {
            iniciarSesion();
        }
    }

    private void iniciarSesion() {
        String correo = vista.getTxtUsuario().getText().trim();
        String contrasena = new String(vista.getTxtContrasena().getPassword()).trim();

        if (correo.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "⚠️ Ingresa usuario y contraseña.");
            return;
        }

        // 🔹 El DAO valida el usuario y determina su rol
        String resultado = modelo.validarUsuario(correo, contrasena);

        if (resultado == null) {
            JOptionPane.showMessageDialog(vista, "❌ Usuario o contraseña incorrectos.");
            return;
        }

        // 🔹 Obtenemos la conexión activa según el rol
        conexionActiva = modelo.getConexionRol();

        try {
            if (resultado.matches("\\d+")) {
                // 🔹 Propietario
                JOptionPane.showMessageDialog(vista, "Bienvenido al sistema Propietario 👷‍♂️");

                // Ejemplo: puedes pasar la conexión a su controlador
                // PropietarioDAO dao = new PropietarioDAO(conexionActiva);
                //new ControladorPropietario(dao);

                vista.dispose();

            } else if ("tecnico".equalsIgnoreCase(resultado)) {
                JOptionPane.showMessageDialog(vista, "Bienvenido al sistema Técnico 🔧");

                // Ejemplo: TécnicoDAO daoTec = new TécnicoDAO(conexionActiva);
                // new ControladorTecnico(daoTec);

                vista.dispose();

            } else if ("productor".equalsIgnoreCase(resultado)) {
    JOptionPane.showMessageDialog(vista, "Bienvenido al sistema Productor 🌱");

    // ✅ 1️⃣ Obtenemos la conexión del usuario logueado
    Connection conexionActiva = modelo.getConexionRol();

    // ✅ 2️⃣ Creamos la vista
    Predios menu = new Predios(conexionActiva);

    // ✅ 3️⃣ Creamos el controlador pasándole la conexión activa
    new ControladorMostrarPredios(menu, conexionActiva);

    // ✅ 4️⃣ Mostramos la vista
    menu.setVisible(true);
    menu.setLocationRelativeTo(null);

    // ✅ 5️⃣ Cerramos el login
    vista.dispose();
}
 else if ("administrador".equalsIgnoreCase(resultado)) {
                JOptionPane.showMessageDialog(vista, "Bienvenido al sistema Admin 👑");

                AdminMenu menu = new AdminMenu();
                // ✅ Aquí también puedes pasar la conexión si lo necesitas
                new ControladorMenuAdministrador(menu /*, conexionActiva */);

                menu.setVisible(true);
                vista.dispose();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "💥 Error al abrir ventana: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // 🔹 Si quieres cerrar la conexión al salir
    public void cerrarConexion() {
        CConexion.cerrarConexion(conexionActiva);
    }
}


