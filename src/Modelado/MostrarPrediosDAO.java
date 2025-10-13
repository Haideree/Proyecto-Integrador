package Modelado;

/**
 *
 * @author Haider
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MostrarPrediosDAO {

    private Connection conexion;

    public MostrarPrediosDAO() {
        conexion = CConexion.getConnection();
    }

    public List<Object[]> listarPredios() {
    List<Object[]> lista = new ArrayList<>();

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
            Object[] fila = {
                rs.getObject("num_registro_ica"),
                rs.getObject("nombre_predio"),
                rs.getObject("departamento"),
                rs.getObject("municipio"),
                rs.getObject("vereda"),
                rs.getObject("productor"),
                rs.getObject("propietario"),
                rs.getObject("lugar_produccion")
            };

            // Log opcional (puedes quitarlo después)
            System.out.printf("ROW -> %s | %s | %s | %s | %s | %s | %s | %s%n",
                    fila[0], fila[1], fila[2], fila[3], fila[4], fila[5], fila[6], fila[7]);

            lista.add(fila);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al listar predios: " + e.getMessage());
    }

    return lista;
    }

}
