package Modelado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MostrarLugaresDAO {

    private final Connection conexion;

    public MostrarLugaresDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public List<Object[]> listarLugares() {
        List<Object[]> lista = new ArrayList<>();
        if (conexion == null) return lista;

        String sql = """
            SELECT NUM_REGISTRO_ICA, NOMBRE, EMP_RESPONSABLE
            FROM LUGAR_PRODUCCION
            ORDER BY NUM_REGISTRO_ICA
        """;

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3)
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "💥 Error al listar:\n" + e.getMessage());
        }

        return lista;
    }

    // 🔥 ELIMINAR LUGAR SIN BORRAR PREDIOS NI LOTES
public boolean eliminarLugar(int numRegIca) {

    String sqlDesasociarPredios =
        "UPDATE PREDIO SET NUMERO_REG_ICA = NULL WHERE NUMERO_REG_ICA = ?";

    String sqlDesasociarLotes =
        "UPDATE LOTE SET ID_LUGARPROD = NULL WHERE ID_LUGARPROD = ?";

    String sqlEliminarLugar =
        "DELETE FROM LUGAR_PRODUCCION WHERE NUM_REGISTRO_ICA = ?";

    try {
        // 1️⃣ Iniciar transacción
        conexion.setAutoCommit(false);

        // 2️⃣ Desasociar predios
        try (PreparedStatement ps1 = conexion.prepareStatement(sqlDesasociarPredios)) {
            ps1.setInt(1, numRegIca);
            ps1.executeUpdate();
        }

        // 3️⃣ Desasociar lotes
        try (PreparedStatement ps2 = conexion.prepareStatement(sqlDesasociarLotes)) {
            ps2.setInt(1, numRegIca);
            ps2.executeUpdate();
        }

        // 4️⃣ Eliminar el lugar
        int filasAfectadas = 0;
        try (PreparedStatement ps3 = conexion.prepareStatement(sqlEliminarLugar)) {
            ps3.setInt(1, numRegIca);
            filasAfectadas = ps3.executeUpdate();
        }

        // 5️⃣ Confirmar transacción
        conexion.commit();
        conexion.setAutoCommit(true);

        return filasAfectadas > 0;

    } catch (SQLException e) {

        try {
            // ❌ Revertir transacción en caso de error
            conexion.rollback();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en rollback: " + ex.getMessage());
        }

        JOptionPane.showMessageDialog(null,
                "💥 Error al eliminar lugar:\n" + e.getMessage());
        return false;
    }
}



    // 🔥 ACTUALIZAR
    public boolean actualizarLugar(int numRegIca, String nombre, String empresa) {
        String sql = """
            UPDATE LUGAR_PRODUCCION
            SET NOMBRE = ?, EMP_RESPONSABLE = ?
            WHERE NUM_REGISTRO_ICA = ?
        """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, empresa);
            ps.setInt(3, numRegIca);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "💥 Error al actualizar:\n" + e.getMessage());
            return false;
        }
    }
}
