package Modelado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Clase DAO para validar los datos de inicio de sesión
 * y devolver el rol correcto del usuario.
 */
public class LoginDAO {

    private Connection conexionRol; // guarda la conexión activa del usuario logueado

    public Connection getConexionRol() {
        return conexionRol;
    }

    public String validarUsuario(String correo, String contrasena) {
        String rol = null;

        // ✅ Paso 1: Usamos ADMINISTRADOR solo para verificar las credenciales
        try (Connection conn = CConexion.getConnectionPorUsuario("ADMINISTRADOR", "ADMINISTRADOR")) {

            // 🔹 1️⃣ Propietario
            String sqlProp = "SELECT NUMERODOCUMENTO FROM PROPIETARIO WHERE correo = ? AND contrasena = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlProp)) {
                ps.setString(1, correo);
                ps.setString(2, contrasena);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        rol = "propietario";
                    }
                }
            }

            // 🔹 2️⃣ Técnico
            if (rol == null) {
                String sqlTec = "SELECT t.identificacion FROM TECNICO t " +
                                "JOIN CORREO c ON t.idCorreo = c.id_correo " +
                                "WHERE c.correo = ? AND t.contrasena = ?";
                try (PreparedStatement ps = conn.prepareStatement(sqlTec)) {
                    ps.setString(1, correo);
                    ps.setString(2, contrasena);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            rol = "tecnico";
                        }
                    }
                }
            }

            // 🔹 3️⃣ Productor
            if (rol == null) {
                String sqlProd = "SELECT p.numerodocumento FROM PRODUCTOR p " +
                                 "JOIN CORREO c ON p.idCorreo = c.id_correo " +
                                 "WHERE c.correo = ? AND p.contrasena = ?";
                try (PreparedStatement ps = conn.prepareStatement(sqlProd)) {
                    ps.setString(1, correo);
                    ps.setString(2, contrasena);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            rol = "productor";
                        }
                    }
                }
            }

            // 🔹 4️⃣ Administrador
            if (rol == null) {
                String sqlAdm = "SELECT a.cedula FROM ADMINISTRADOR a " +
                                "WHERE a.correo = ? AND a.contrasena = ?";
                try (PreparedStatement ps = conn.prepareStatement(sqlAdm)) {
                    ps.setString(1, correo);
                    ps.setString(2, contrasena);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            rol = "administrador";
                        }
                    }
                }
            }

            // ✅ Paso 2: si se encontró el rol, abrir la conexión con ese usuario Oracle
            if (rol != null) {
                conexionRol = CConexion.getConnectionPorRol(rol);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rol;
    }
}

