package Modelado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LugarProduccionDAO {

    private final Connection conexion; // 🔹 conexión recibida desde el controlador

    // ✅ Constructor recibe la conexión del rol logueado
    public LugarProduccionDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // ==============================================
    // 🔹 Registrar un nuevo lugar de producción
    // ==============================================
    public void registrarLugarProduccion(String nombreLugar, String empresaResponsable) {
        String sql = """
    INSERT INTO PROYECTO.LUGAR_PRODUCCION (NUM_REGISTRO_ICA, NOMBRE, EMP_RESPONSABLE)
    VALUES (PROYECTO.SEQ_LUGAR_PRODUCCION.NEXTVAL, ?, ?)
""";


        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombreLugar);
            ps.setString(2, empresaResponsable);
            ps.executeUpdate();

            System.out.println("✅ Lugar de producción registrado correctamente en BD.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al registrar el lugar de producción: " + e.getMessage(),
                    "Error SQL", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}

