package Modelado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MostrarPrediosDAO {

    private final Connection conexion;

    public MostrarPrediosDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public List<Object[]> listarPredios() {
        List<Object[]> lista = new ArrayList<>();

        if (conexion == null) {
            JOptionPane.showMessageDialog(null, "❌ Conexión no inicializada.");
            return lista;
        }

        String sql = """
            SELECT 
                p.NUM_REGISTRO_ICA AS num_registro_ica,
                p.NOMBRE AS nombre_predio,
                p.DEPARTAMENTO AS departamento,
                p.MUNICIPIO AS municipio,
                p.VEREDA AS vereda,
                prod.NOMBRE AS productor,
                prop.NOMBRE AS propietario,
                NVL(lp.NOMBRE, 'Sin asignar') AS lugar_produccion
            FROM 
                PREDIO p
                LEFT JOIN PRODUCTOR prod ON p.NUMERO_DOC_PRODUCTOR = prod.NUMERODOCUMENTO
                LEFT JOIN PROPIETARIO prop ON p.NUMERODOCPROP = prop.NUMERODOCUMENTO
                LEFT JOIN LUGAR_PRODUCCION lp ON p.NUMERO_REG_ICA = lp.NUM_REGISTRO_ICA
            ORDER BY 
                p.NUM_REGISTRO_ICA
        """;

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getObject("num_registro_ica"),
                    rs.getObject("nombre_predio"),
                    rs.getObject("departamento"),
                    rs.getObject("municipio"),
                    rs.getObject("vereda"),
                    rs.getObject("productor"),
                    rs.getObject("propietario"),
                    rs.getObject("lugar_produccion")
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "💥 Error al listar predios:\n" + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }
}


