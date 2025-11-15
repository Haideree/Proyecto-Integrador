package Modelado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EliminarDAO {

    // ===========================================================
    // 🧨 ELIMINAR TECNICO
    // ===========================================================
    public boolean eliminarTecnico(int idTecnico, int idCorreo, int idTelefono) {

        String sqlDeleteTecnico  = "DELETE FROM TECNICO WHERE IDENTIFICACION = ?";
        String sqlDeleteCorreo   = "DELETE FROM CORREO WHERE ID_CORREO = ?";
        String sqlDeleteTelefono = "DELETE FROM TELEFONO WHERE ID_TELEFONO = ?";

        try (Connection conn = CConexion.getConnectionPorUsuario("ADMINISTRADOR", "ADMINISTRADOR")) {

            conn.setAutoCommit(false); // ⭐ INICIO TRANSACCIÓN

            // 1️⃣ Primero eliminar técnico (TIENE las FK)
            try (PreparedStatement ps = conn.prepareStatement(sqlDeleteTecnico)) {
                ps.setInt(1, idTecnico);
                ps.executeUpdate();
            }

            // 2️⃣ Luego eliminar correo
            try (PreparedStatement ps = conn.prepareStatement(sqlDeleteCorreo)) {
                ps.setInt(1, idCorreo);
                ps.executeUpdate();
            }

            // 3️⃣ Luego eliminar teléfono
            try (PreparedStatement ps = conn.prepareStatement(sqlDeleteTelefono)) {
                ps.setInt(1, idTelefono);
                ps.executeUpdate();
            }

            conn.commit(); // ⭐ CONFIRMAR TRANSACCIÓN
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar técnico: " + e.getMessage());
            return false;
        }
    }

    // ===========================================================
    // 🧨 ELIMINAR PRODUCTOR
    // ===========================================================
    public boolean eliminarProductor(int idProductor, int idCorreo, int idTelefono) {

        String sqlDeleteProductor = "DELETE FROM PRODUCTOR WHERE NUMERODOCUMENTO = ?";
        String sqlDeleteCorreo    = "DELETE FROM CORREO WHERE ID_CORREO = ?";
        String sqlDeleteTelefono  = "DELETE FROM TELEFONO WHERE ID_TELEFONO = ?";

        try (Connection conn = CConexion.getConnectionPorUsuario("ADMINISTRADOR", "ADMINISTRADOR")) {

            conn.setAutoCommit(false); // ⭐ INICIO TRANSACCIÓN

            // 1️⃣ Primero borrar productor
            try (PreparedStatement ps = conn.prepareStatement(sqlDeleteProductor)) {
                ps.setInt(1, idProductor);
                ps.executeUpdate();
            }

            // 2️⃣ Luego borrar correo
            try (PreparedStatement ps = conn.prepareStatement(sqlDeleteCorreo)) {
                ps.setInt(1, idCorreo);
                ps.executeUpdate();
            }

            // 3️⃣ Luego borrar teléfono
            try (PreparedStatement ps = conn.prepareStatement(sqlDeleteTelefono)) {
                ps.setInt(1, idTelefono);
                ps.executeUpdate();
            }

            conn.commit(); // ⭐ CONFIRMAR TRANSACCIÓN
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar productor: " + e.getMessage());
            return false;
        }
    }

    // ===========================================================
    // 🧨 ELIMINAR PROPIETARIO
    // ===========================================================
    public boolean eliminarPropietario(int idPropietario) {

        String sqlDeleteProp = "DELETE FROM PROPIETARIO WHERE NUMERODOCUMENTO = ?";

        try (Connection conn = CConexion.getConnectionPorUsuario("ADMINISTRADOR", "ADMINISTRADOR");
             PreparedStatement ps = conn.prepareStatement(sqlDeleteProp)) {

            ps.setInt(1, idPropietario);
            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar propietario: " + e.getMessage());
            return false;
        }
    }
}
