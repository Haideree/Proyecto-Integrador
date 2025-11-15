/*package Modelado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditarDAO {

    // ============================
    // 🔹 EDITAR TÉCNICO
    // ============================
    public boolean editarTecnico(Tecnico t) {
        String sql = "UPDATE TECNICOS SET TARJETAPRO = ?, NOMBRE = ?, CORREO = ?, TELEFONO = ?, CONTRASENA = ?, TIPOTECNICO = ? "
                   + "WHERE IDENTIFICACION = ?";

        try (Connection con = CConexion.getConnectionPorRol("administrador");
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t.getTarjetapro());
            ps.setString(2, t.getNombre());
            ps.setString(3, t.getCorreo());
            ps.setString(4, t.getTelefono());
            ps.setString(5, t.getContrasena());
            ps.setString(6, t.getTipoTecnico());
            ps.setString(7, t.getIdentificacion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al editar técnico: " + e.getMessage());
            return false;
        }
    }

    // ============================
    // 🔹 EDITAR PROPIETARIO
    // ============================
    public boolean editarPropietario(Propietario p) {
        String sql = "UPDATE PROPIETARIOS SET NOMBRE = ?, CORREO = ?, TELEFONO = ?, CONTRASENA = ? "
                   + "WHERE IDENTIFICACION = ?";

        try (Connection con = CConexion.getConnectionPorRol("administrador");
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCorreo());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getContrasena());
            ps.setString(5, p.getIdentificacion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al editar propietario: " + e.getMessage());
            return false;
        }
    }

    // ============================
    // 🔹 EDITAR PRODUCTOR
    // ============================
    public boolean editarProductor(Productor pr) {
        String sql = "UPDATE PRODUCTORES SET NOMBRE = ?, CORREO = ?, TELEFONO = ?, CONTRASENA = ?, FINCA = ? "
                   + "WHERE IDENTIFICACION = ?";

        try (Connection con = CConexion.getConnectionPorRol("administrador");
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pr.getNombre());
            ps.setString(2, pr.getCorreo());
            ps.setString(3, pr.getTelefono());
            ps.setString(4, pr.getContrasena());
            ps.setString(5, pr.getFinca());
            ps.setString(6, pr.getIdentificacion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al editar productor: " + e.getMessage());
            return false;
        }
    }
}
*/

