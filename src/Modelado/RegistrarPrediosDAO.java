package Modelado;
/**
 *
 * @author Haider
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RegistrarPrediosDAO {

    private Connection conexion;

    public RegistrarPrediosDAO() {
        try {
            conexion = CConexion.getConnection(); // Guarda la conexión en el atributo
            if (conexion == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos ⚠️");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos ⚠️");
        }
    }

    // ==============================================================
    // 1️⃣ Obtener productores
    // ==============================================================
    public List<String> obtenerProductores() {
        List<String> productores = new ArrayList<>();
        String sql = "SELECT NUMERODOCUMENTO, NOMBRE FROM PRODUCTOR";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String item = rs.getString("NOMBRE") + " - " + rs.getString("NUMERODOCUMENTO");
                productores.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar productores.");
        }

        return productores;
    }

    // ==============================================================
    // 2️⃣ Obtener propietarios
    // ==============================================================
    public List<String> obtenerPropietarios() {
        List<String> propietarios = new ArrayList<>();
        String sql = "SELECT NUMERODOCUMENTO, NOMBRE FROM PROPIETARIO";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String item = rs.getString("NOMBRE") + " - " + rs.getString("NUMERODOCUMENTO");
                propietarios.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar propietarios.");
        }

        return propietarios;
    }

    // ==============================================================
    // 3️⃣ Obtener lugares de producción
    // ==============================================================
    public List<String> obtenerLugaresProduccion() {
        List<String> lugares = new ArrayList<>();
        String sql = "SELECT NUM_REGISTRO_ICA, NOMBRE FROM LUGAR_PRODUCCION";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int numIca = rs.getInt("NUM_REGISTRO_ICA");
                String nombre = rs.getString("NOMBRE");
                lugares.add(numIca + " - " + nombre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar lugares de producción.");
        }

        return lugares;
    }

    // ==============================================================
    // 4️⃣ Registrar predio con FK NUM_REGISTRO_ICA
    // ==============================================================
    public boolean registrarPredio(String nombre, String departamento, String municipio, String vereda,
                                   int idProductor, int idPropietario, int numRegistroIca) {

        String sql = "INSERT INTO PREDIO (NUM_REGISTRO_ICA, NOMBRE, DEPARTAMENTO, MUNICIPIO, VEREDA, "
           + "NUMERO_DOC_PRODUCTOR, NUMERODOCPROP, NUMERO_REG_ICA) "
           + "VALUES (SEQ_PREDIO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, departamento);
            ps.setString(3, municipio);
            ps.setString(4, vereda);
            ps.setInt(5, idProductor);
            ps.setInt(6, idPropietario);
            ps.setInt(7, numRegistroIca);

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al registrar el predio 😓");
            return false;
        }
    }
}

