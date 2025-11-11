package Modelado;

import java.sql.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class LoteDAO {
    private Connection conexion;

    public LoteDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // 🔹 Registrar lote (el número se genera automáticamente)
    public boolean registrarLote(double area, String nombreCultivo, String nombreLugar, String nombreLote) {
        boolean resultado = false;

        try {
            // Obtener IDs según los nombres seleccionados
            int idCultivo = obtenerIdCultivo(nombreCultivo);
            int idLugar = obtenerIdLugar(nombreLugar);

            if (idCultivo == -1 || idLugar == -1) {
                JOptionPane.showMessageDialog(null, "⚠️ No se encontró el cultivo o el lugar seleccionado.");
                return false;
            }

            // 🔹 IMPORTANTE: Corrige el nombre de columna y la cantidad de valores
            String sql = "INSERT INTO PROYECTO.LOTE (NUM_LOTE, NOMBRE_LOTE, AREA, IDCULTIVO, ID_LUGARPROD, ESTADO) "
           + "VALUES (PROYECTO.SEQ_NUM_LOTE.NEXTVAL, ?, ?, ?, ?, 'ACTIVO')";

PreparedStatement ps = conexion.prepareStatement(sql);
ps.setString(1, nombreLote);
ps.setDouble(2, area);
ps.setInt(3, idCultivo);
ps.setInt(4, idLugar);

ps.executeUpdate();
ps.close();
            resultado = true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                null,
                "💥 Error SQL al registrar el lote: " + e.getMessage(),
                "Error SQL",
                JOptionPane.ERROR_MESSAGE
            );
        }

        return resultado;
    }

    // 🔹 Obtener lista de cultivos
    public List<String> obtenerCultivos() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT NOMBRE_ESPECIE FROM CULTIVO ORDER BY NOMBRE_ESPECIE";
        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(rs.getString("NOMBRE_ESPECIE"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cultivos: " + e.getMessage());
        }
        return lista;
    }

    // 🔹 Obtener lista de lugares
    public List<String> obtenerLugares() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT NOMBRE FROM LUGAR_PRODUCCION ORDER BY NOMBRE";
        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(rs.getString("NOMBRE"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener lugares: " + e.getMessage());
        }
        return lista;
    }

    // 🔹 Métodos privados: obtener IDs
    private int obtenerIdCultivo(String nombre) throws SQLException {
    String sql = "SELECT ID FROM CULTIVO WHERE NOMBRE_ESPECIE = ?";
    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setString(1, nombre);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt("ID");
        }
    }
    return -1;
}


    private int obtenerIdLugar(String nombre) throws SQLException {
    String sql = "SELECT NUM_REGISTRO_ICA FROM LUGAR_PRODUCCION WHERE NOMBRE = ?";
    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setString(1, nombre);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt("NUM_REGISTRO_ICA");
        }
    }
    return -1;
}

}


