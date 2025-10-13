package Modelado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

    /**
     * Valida el usuario y devuelve:
     * - Para propietario: su número de documento (NUMERODOCUMENTO)
     * - Para técnico: "tecnico"
     * - Para productor: "productor"
     * - Null si no existe
     */
    public String validarUsuario(String correo, String contrasena) {
        try (Connection conn = CConexion.getConnection()) {

            // 1️⃣ Validar propietario y traer NUMERODOCUMENTO
            String sqlProp = "SELECT NUMERODOCUMENTO FROM Propietario WHERE correo = ? AND contrasena = ?";
            try (PreparedStatement psProp = conn.prepareStatement(sqlProp)) {
                psProp.setString(1, correo);
                psProp.setString(2, contrasena);
                try (ResultSet rsProp = psProp.executeQuery()) {
                    if (rsProp.next()) {
                        // Devuelve el número de documento real del propietario
                        return rsProp.getString("NUMERODOCUMENTO");
                    }
                }
            }

            // 2️⃣ Validar técnico
            String sqlTec = "SELECT t.identificacion FROM Tecnico t " +
                            "JOIN correo c ON t.idCorreo = c.id_correo " +
                            "WHERE c.correo = ? AND t.contrasena = ?";
            try (PreparedStatement psTec = conn.prepareStatement(sqlTec)) {
                psTec.setString(1, correo);
                psTec.setString(2, contrasena);
                try (ResultSet rsTec = psTec.executeQuery()) {
                    if (rsTec.next()) {
                        return "tecnico";
                    }
                }
            }

            // 3️⃣ Validar productor
            String sqlProdu = "SELECT p.numerodocumento FROM Productor p " +
                              "JOIN correo c ON p.idCorreo = c.id_correo " +
                              "WHERE c.correo = ? AND p.contrasena = ?";
            try (PreparedStatement psProdu = conn.prepareStatement(sqlProdu)) {
                psProdu.setString(1, correo);
                psProdu.setString(2, contrasena);
                try (ResultSet rsProdu = psProdu.executeQuery()) {
                    if (rsProdu.next()) {
                        return "productor";
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Usuario no encontrado
        return null;
    }
}
