package Modelado;

/**
 *
 * @author Haider
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LugarProduccionDAO {

    private CConexion conexion; // Asegúrate de tener tu clase Conexion bien configurada

    public LugarProduccionDAO() {
        conexion = new CConexion();
    }

    // ==============================================
    // 🔹 Registrar un nuevo lugar de producción
    // ==============================================
    public void registrarLugarProduccion(String nombreLugar, String empresaResponsable) {
        String sql = "INSERT INTO LUGAR_PRODUCCION (NUM_REGISTRO_ICA, NOMBRE, EMP_RESPONSABLE) "
                   + "VALUES (SEQ_LUGAR_PRODUCCION.NEXTVAL, ?, ?)";

        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombreLugar);
            ps.setString(2, empresaResponsable);

            ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al registrar el lugar de producción: " + e.getMessage(),
                "Error SQL", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
