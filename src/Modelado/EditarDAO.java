package Modelado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditarDAO {

    // ===========================================================
    // ✏️ EDITAR TÉCNICO
    // ===========================================================
    public boolean editarTecnico(int idTecnico, String nombre, String contrasena, String tipoTecnico,
                                 int idCorreo, String nuevoCorreo, int idTelefono, String nuevoTelefono) {
        String sqlUpdateTec    = "UPDATE TECNICO SET NOMBRE = ?, CONTRASENA = ?, TIPO_TECNICO = ? WHERE IDENTIFICACION = ?";
        String sqlUpdateCorreo = "UPDATE CORREO SET CORREO = ? WHERE ID_CORREO = ?";
        String sqlUpdateTel    = "UPDATE TELEFONO SET TELEFONO = ? WHERE ID_TELEFONO = ?";

        try (Connection conn = CConexion.getConnectionPorUsuario("ADMINISTRADOR", "ADMINISTRADOR")) {
            conn.setAutoCommit(false);

            try (PreparedStatement psTec = conn.prepareStatement(sqlUpdateTec)) {
                psTec.setString(1, nombre);
                psTec.setString(2, contrasena);
                psTec.setString(3, tipoTecnico);
                psTec.setInt(4, idTecnico);
                psTec.executeUpdate();
            }

            try (PreparedStatement psCorreo = conn.prepareStatement(sqlUpdateCorreo)) {
                psCorreo.setString(1, nuevoCorreo);
                psCorreo.setInt(2, idCorreo);
                psCorreo.executeUpdate();
            }

            try (PreparedStatement psTel = conn.prepareStatement(sqlUpdateTel)) {
                psTel.setString(1, nuevoTelefono);
                psTel.setInt(2, idTelefono);
                psTel.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ===========================================================
    // ✏️ EDITAR PRODUCTOR
    // ===========================================================
    public boolean editarProductor(int idProductor, String nombre, String contrasena,
                                   String nuevoCorreo, String nuevoTelefono,
                                   int idCorreo, int idTelefono) {

        String sqlUpdateProd   = "UPDATE PRODUCTOR SET NOMBRE = ?, CONTRASENA = ? WHERE NUMERODOCUMENTO = ?";
        String sqlUpdateCorreo = "UPDATE CORREO SET CORREO = ? WHERE ID_CORREO = ?";
        String sqlUpdateTel    = "UPDATE TELEFONO SET TELEFONO = ? WHERE ID_TELEFONO = ?";

        try (Connection conn = CConexion.getConnectionPorUsuario("ADMINISTRADOR", "ADMINISTRADOR")) {
            conn.setAutoCommit(false); // inicio transacción

            // 1️⃣ Actualizar PRODUCTOR
            try (PreparedStatement psProd = conn.prepareStatement(sqlUpdateProd)) {
                psProd.setString(1, nombre);
                psProd.setString(2, contrasena);
                psProd.setInt(3, idProductor);
                psProd.executeUpdate();
            }

            // 2️⃣ Actualizar CORREO si hay ID y valor
            if (idCorreo > 0 && nuevoCorreo != null && !nuevoCorreo.trim().isEmpty()) {
                try (PreparedStatement psCorreo = conn.prepareStatement(sqlUpdateCorreo)) {
                    psCorreo.setString(1, nuevoCorreo);
                    psCorreo.setInt(2, idCorreo);
                    psCorreo.executeUpdate();
                }
            }

            // 3️⃣ Actualizar TELEFONO si hay ID y valor
            if (idTelefono > 0 && nuevoTelefono != null && !nuevoTelefono.trim().isEmpty()) {
                try (PreparedStatement psTel = conn.prepareStatement(sqlUpdateTel)) {
                    psTel.setString(1, nuevoTelefono);
                    psTel.setInt(2, idTelefono);
                    psTel.executeUpdate();
                }
            }

            conn.commit(); // confirmar cambios
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    // ===========================================================
// ✏️ EDITAR PROPIETARIO
// ===========================================================
public boolean editarPropietario(int idPropietario, String nombre, String contrasena,
                                 String correo, String telefono) {

    // SQL para actualizar propietario
    String sqlUpdateProp = "UPDATE PROPIETARIO SET NOMBRE = ?, CONTRASENA = ?, CORREO = ?, TELEFONO = ? WHERE NUMERODOCUMENTO = ?";

    try (Connection conn = CConexion.getConnectionPorUsuario("ADMINISTRADOR", "ADMINISTRADOR")) {

        // Empezamos transacción
        conn.setAutoCommit(false);

        try (PreparedStatement ps = conn.prepareStatement(sqlUpdateProp)) {

            // Asignamos parámetros
            ps.setString(1, nombre);
            ps.setString(2, contrasena);
            ps.setString(3, correo);
            ps.setString(4, telefono);
            ps.setInt(5, idPropietario);

            int filas = ps.executeUpdate();

            // Confirmamos transacción si todo salió bien
            conn.commit();

            return filas > 0;

        } catch (SQLException e) {
            // Revertimos transacción si falla
            conn.rollback();
            e.printStackTrace();
            return false;
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

}
