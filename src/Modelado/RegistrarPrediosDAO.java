package Modelado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RegistrarPrediosDAO {

    private final Connection conexion; // ✅ Conexión inyectada desde el controlador

    // Constructor que recibe la conexión activa
    public RegistrarPrediosDAO(Connection conexion) {
        this.conexion = conexion;
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
            JOptionPane.showMessageDialog(null, "Error al cargar productores: " + e.getMessage(),
                    "Error SQL", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
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
            JOptionPane.showMessageDialog(null, "Error al cargar propietarios: " + e.getMessage(),
                    "Error SQL", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return propietarios;
    }

    // ==============================================================
    // 3️⃣ Obtener departamentos
    // ==============================================================
    public List<String> obtenerDepartamentos() {
        List<String> departamentos = new ArrayList<>();
        String sql = "SELECT DEPARTAMENTO FROM DEPARTAMENTOS";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                departamentos.add(rs.getString("DEPARTAMENTO"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar departamentos: " + e.getMessage(),
                    "Error SQL", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return departamentos;
    }

    // ==============================================================
    // 4️⃣ Obtener municipios por departamento
    // ==============================================================
    public List<String> obtenerMunicipios(String nombreDepartamento) {
        List<String> municipios = new ArrayList<>();
        String sql = """
            SELECT M.MUNICIPIO 
            FROM MUNICIPIOS M 
            JOIN DEPARTAMENTOS D ON M.DEPARTAMENTO = D.IDDEPART 
            WHERE D.DEPARTAMENTO = ?
        """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombreDepartamento);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    municipios.add(rs.getString("MUNICIPIO"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar municipios del departamento " + nombreDepartamento + ": " + e.getMessage(),
                    "Error SQL", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return municipios;
    }

    // ==============================================================
    // 5️⃣ Obtener lugares de producción
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
            JOptionPane.showMessageDialog(null, "Error al cargar lugares de producción: " + e.getMessage(),
                    "Error SQL", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return lugares;
    }

    // ==============================================================
    // 6️⃣ Registrar predio
    // ==============================================================
    public boolean registrarPredio(String nombre, String departamento, String municipio, String vereda,
                                   int idProductor, int idPropietario, int numRegistroIca) {

        String sql = """
            INSERT INTO PREDIO (NUM_REGISTRO_ICA, NOMBRE, DEPARTAMENTO, MUNICIPIO, VEREDA, 
            NUMERO_DOC_PRODUCTOR, NUMERODOCPROP, NUMERO_REG_ICA)
            VALUES (SEQ_PREDIO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, departamento);
            ps.setString(3, municipio);
            ps.setString(4, vereda);
            ps.setInt(5, idProductor);
            ps.setInt(6, idPropietario);
            ps.setInt(7, numRegistroIca);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el predio: " + e.getMessage(),
                    "Error SQL", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }
}


