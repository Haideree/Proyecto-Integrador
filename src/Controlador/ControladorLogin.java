package Controlador;

import Vista.MenuPropietario;
import Modelado.PredioDAO;
import Vista.MenuTecnico;
import Vista.AdministrarPredios;
import Modelado.LoginDAO;
import Modelado.CConexion;
import Vista.AdminMenu;
import Vista.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class ControladorLogin implements ActionListener {

    private final Login vista;
    private final LoginDAO modelo;

    // 🔹 Conexión activa del usuario logueado
    private Connection conexionActiva;
    private int idTecnico;
    public ControladorLogin(Login vista) {
        this.vista = vista;
        this.modelo = new LoginDAO();

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

        // 🔹 Validamos y obtenemos el rol
        String rol = modelo.validarUsuario(correo, contrasena);

        if (rol == null) {
            JOptionPane.showMessageDialog(vista, "❌ Usuario o contraseña incorrectos.");
            return;
        }

        // 🔹 Obtenemos conexión del usuario
        conexionActiva = modelo.getConexionRol();

        try {
            switch (rol.toLowerCase()) {

                case "propietario": {
                JOptionPane.showMessageDialog(vista, "Bienvenido al sistema Propietario 👷‍♂️");

                int idPropietario = modelo.getIdUsuario();   // ⭐ Aquí obtienes el numerodocprop

                    // ⭐⭐ GUARDAR ID EN EL DAO ⭐⭐
                    PredioDAO.setIdPropietarioLogueado(idPropietario);

                    MenuPropietario menu = new MenuPropietario(conexionActiva, idPropietario);

                    menu.setVisible(true);
                    menu.setLocationRelativeTo(null);
                    vista.dispose();
                    break;
                }


                case "productor": {
                JOptionPane.showMessageDialog(vista, "Bienvenido al sistema Productor 🌱");

                Vista.MenuProductor menu = new Vista.MenuProductor(conexionActiva);

                menu.setVisible(true);
                menu.setLocationRelativeTo(null);
                vista.dispose();
                break;
            }
                case "tecnico": {
                    JOptionPane.showMessageDialog(vista, "Bienvenido al sistema Técnico 🛠️");

                    // Obtener el ID del técnico desde LoginDAO
                    idTecnico = modelo.getIdUsuario();  

                    MenuTecnico menu = new MenuTecnico(conexionActiva, idTecnico);

                    menu.setVisible(true);
                    menu.setLocationRelativeTo(null);
                    vista.dispose();
                    break;
                }


                case "administrador": {
                    JOptionPane.showMessageDialog(vista, "Bienvenido al sistema Admin 👑");
                    AdminMenu menu = new AdminMenu(conexionActiva);
                    new ControladorMenuAdministrador(menu,conexionActiva);

                    menu.setVisible(true);
                    vista.dispose();
                    break;
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "💥 Error al abrir ventana: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void cerrarConexion() {
        CConexion.cerrarConexion(conexionActiva);
    }
}
