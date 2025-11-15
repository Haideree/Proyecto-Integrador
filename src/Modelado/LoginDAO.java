package Modelado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Clase DAO para validar los datos de inicio de sesión
 * y devolver el rol correcto del usuario.
 */
public class LoginDAO {
    
    private int idUsuario;
    public int getIdUsuario() { return idUsuario; }

    private Connection conexionRol; // guarda la conexión activa del usuario logueado

    public Connection getConexionRol() {
        return conexionRol;
    }

    public String validarUsuario(String correo, String contrasena) {
    String rol = null;

    try (Connection conn = CConexion.getConnectionPorUsuario("ADMINISTRADOR", "ADMINISTRADOR")) {

        // 1) PROPIETARIO
        String sqlProp = "SELECT NUMERODOCUMENTO FROM PROPIETARIO WHERE correo = ? AND contrasena = ?";
        try (PreparedStatement ps = conn.prepareStatement(sqlProp)) {
            ps.setString(1, correo);
            ps.setString(2, contrasena);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    rol = "propietario";
                    idUsuario = rs.getInt(1);
                }
            }
        }

        // 2) TÉCNICO
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
                        idUsuario = rs.getInt(1);
                    }
                }
            }
        }

        // 3) PRODUCTOR
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
                        idUsuario = rs.getInt(1);
                    }
                }
            }
        }

        // 4) ADMINISTRADOR
        if (rol == null) {
            String sqlAdm = "SELECT a.cedula FROM ADMINISTRADOR WHERE correo = ? AND contrasena = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlAdm)) {
                ps.setString(1, correo);
                ps.setString(2, contrasena);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        rol = "administrador";
                        idUsuario = rs.getInt(1);
                    }
                }
            }
        }

        // Abrir conexión según rol
        if (rol != null) {
            conexionRol = CConexion.getConnectionPorRol(rol);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return rol;
}

}

