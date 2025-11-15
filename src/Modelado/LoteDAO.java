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

    // 🔹 Registrar lote con IDs correctos
    public boolean registrarLote(double area, String nombreCultivo, String nombreLugar, String nombreLote) {
        boolean resultado = false;

        try {
            // Obtener IDs reales
            int idCultivo = obtenerIdCultivo(nombreCultivo);  // <-- CULTIVO.ID
            int idLugar = obtenerIdLugar(nombreLugar);        // <-- LUGAR.NUM_REGISTRO_ICA

            if (idCultivo == -1 || idLugar == -1) {
                JOptionPane.showMessageDialog(null, "⚠️ No se encontró el cultivo o el lugar seleccionado.");
                return false;
            }

            String sql = "INSERT INTO LOTE (NUM_LOTE, NOMBRE_LOTE, AREA, IDCULTIVO, ID_LUGARPROD, ESTADO) "
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

    // 🔹 Editar lote
    public boolean editarLote(int numLote, double area, String nombre, int idCultivo, int idLugar) throws SQLException {
        String sql = "UPDATE LOTE SET NOMBRE_LOTE=?, AREA=?, IDCULTIVO=?, ID_LUGARPROD=? WHERE NUM_LOTE=?";

        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, nombre);
        ps.setDouble(2, area);
        ps.setInt(3, idCultivo);
        ps.setInt(4, idLugar);
        ps.setInt(5, numLote);

        return ps.executeUpdate() > 0;
    }

    // 🔹 Listar cultivos
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

    // 🔹 Listar lugares
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

    // 🔹 Obtener ID real de CULTIVO
    public int obtenerIdCultivo(String nombre) throws SQLException {
        String sql = "SELECT ID FROM CULTIVO WHERE NOMBRE_ESPECIE = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("ID");
            }
        }
        return -1;
    }

    // 🔹 Obtener ID real de LUGAR_PRODUCCION
    public int obtenerIdLugar(String nombre) throws SQLException {
        String sql = "SELECT NUM_REGISTRO_ICA FROM LUGAR_PRODUCCION WHERE NOMBRE = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("NUM_REGISTRO_ICA");
            }
        }
        return -1;
    }
    
    public static class DatosLote {
    public String nombre;
    public double area;
    public String nombreCultivo;
    public String nombreLugar;
}

public DatosLote obtenerDatosLote(int numLote) throws SQLException {
    String sql = """
        SELECT L.NOMBRE_LOTE, L.AREA,
               C.NOMBRE_ESPECIE,
               LP.NOMBRE
        FROM LOTE L
        JOIN CULTIVO C ON C.ID = L.IDCULTIVO
        JOIN LUGAR_PRODUCCION LP ON LP.NUM_REGISTRO_ICA = L.ID_LUGARPROD
        WHERE L.NUM_LOTE = ?
    """;

    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setInt(1, numLote);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                DatosLote d = new DatosLote();
                d.nombre = rs.getString(1);
                d.area = rs.getDouble(2);
                d.nombreCultivo = rs.getString(3);
                d.nombreLugar = rs.getString(4);
                return d;
            }
        }
    }
    return null;
}

}


