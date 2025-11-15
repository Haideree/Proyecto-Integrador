package Modelado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class PredioDAO {

    private final Connection conexion;

    private static int idPropietarioLogueado;
    
    public Connection getConexion() {
    return conexion;
}


    public static void setIdPropietarioLogueado(int id) {
        idPropietarioLogueado = id;
    }

    public int obtenerIdPropietarioLogueado() {
        return idPropietarioLogueado;
    }

    // ... resto del DAO



    public PredioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // ============================================================
    //  OBTENER PREDIOS DEL PROPIETARIO LOGUEADO
    // ============================================================
    public DefaultTableModel obtenerPredios(int docPropietario) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("NUM_REGISTRO_ICA");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("DEPARTAMENTO");
        modelo.addColumn("MUNICIPIO");
        modelo.addColumn("VEREDA");

        String sql = "SELECT NUM_REGISTRO_ICA, NOMBRE, DEPARTAMENTO, MUNICIPIO, VEREDA "
                   + "FROM PREDIO WHERE NUMERODOCPROP = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, docPropietario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("NUM_REGISTRO_ICA"),
                    rs.getString("NOMBRE"),
                    rs.getString("DEPARTAMENTO"),
                    rs.getString("MUNICIPIO"),
                    rs.getString("VEREDA")
                });
            }

        } catch (Exception e) {
            System.out.println("Error obteniendo predios: " + e.getMessage());
        }

        return modelo;
    }

    // ============================================================
    //  OBTENER PRODUCTORES
    // ============================================================
    public List<String> obtenerProductores() {
    List<String> lista = new ArrayList<>();
    lista.add("-- Seleccione un productor --"); // ← OPCIÓN DEFAULT

    String sql = "SELECT nombre, numerodocumento FROM PRODUCTOR";

    try (PreparedStatement ps = conexion.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            lista.add(rs.getString("nombre") + " - " + rs.getInt("numerodocumento"));
        }

    } catch (SQLException e) {
        System.out.println("Error obteniendo productores: " + e.getMessage());
    }

    return lista;
}
    
    public List<String> obtenerListaCedulaNombre() {
    List<String> lista = new ArrayList<>();

    String sql = """
        SELECT NUMERODOCUMENTO, NOMBRE
        FROM PRODUCTOR
        ORDER BY NOMBRE
    """;

    try (PreparedStatement ps = conexion.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            String ced = rs.getString("NUMERODOCUMENTO");
            String nom = rs.getString("NOMBRE");

            lista.add(ced + " - " + nom);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}



    // ============================================================
    //  OBTENER DEPARTAMENTOS ÚNICOS
    // ============================================================
    public List<String> obtenerDepartamentos() {
    List<String> lista = new ArrayList<>();
    lista.add("-- Seleccione un departamento --"); // ← DEFAULT

    String sql = "SELECT DEPARTAMENTO FROM DEPARTAMENTOS ORDER BY DEPARTAMENTO";

    try (PreparedStatement ps = conexion.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            lista.add(rs.getString("DEPARTAMENTO"));
        }

    } catch (SQLException e) {
        System.out.println("Error obteniendo departamentos: " + e.getMessage());
    }

    return lista;
}

public Predio obtenerPredioPorId(int id) {
    Predio p = null;
    String sql = "SELECT * FROM predio WHERE num_registro_ica = ?";

    try (PreparedStatement ps = conexion.prepareStatement(sql)) {

        ps.setInt(1, id);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                p = new Predio();

                p.setNumRegistroIca(rs.getInt("num_registro_ica"));
                p.setNombre(rs.getString("nombre"));
                p.setDepartamento(rs.getString("departamento"));
                p.setMunicipio(rs.getString("municipio"));
                p.setVereda(rs.getString("vereda"));

                // Valores que pueden ser NULL
                p.setNumeroDocProductor(rs.getObject("numero_doc_productor", Integer.class));
                p.setNumeroRegIca(rs.getObject("numero_reg_ica", Integer.class));
                p.setNumeroDocProp(rs.getObject("numerodocprop", Integer.class));
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return p;
}


public boolean actualizarPredio(Predio p) {
    String sql = "UPDATE predio SET nombre=?, departamento=?, municipio=?, vereda=?, "
               + "numero_doc_productor=?, numero_reg_ica=?, numerodocprop=? "
               + "WHERE num_registro_ica=?";

    try (PreparedStatement ps = conexion.prepareStatement(sql)) {

        ps.setString(1, p.getNombre());
        ps.setString(2, p.getDepartamento());
        ps.setString(3, p.getMunicipio());
        ps.setString(4, p.getVereda());

        // numero_doc_productor (nullable)
        if (p.getNumeroDocProductor() != null) {
            ps.setInt(5, p.getNumeroDocProductor());
        } else {
            ps.setNull(5, java.sql.Types.INTEGER);
        }

        // numero_reg_ica (nullable)
        if (p.getNumeroRegIca() != null) {
            ps.setInt(6, p.getNumeroRegIca());
        } else {
            ps.setNull(6, java.sql.Types.INTEGER);
        }

        // numerodocprop (nullable)
        if (p.getNumeroDocProp() != null) {
            ps.setInt(7, p.getNumeroDocProp());
        } else {
            ps.setNull(7, java.sql.Types.INTEGER);
        }

        ps.setInt(8, p.getNumRegistroIca());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}



    // ============================================================
    //  OBTENER MUNICIPIOS POR DEPARTAMENTO
    // ============================================================
    public List<String> obtenerMunicipios(String nombreDepto) {
    List<String> lista = new ArrayList<>();
    lista.add("-- Seleccione un municipio --"); // ← DEFAULT

    String sql = """
        SELECT m.MUNICIPIO
        FROM MUNICIPIOS m
        JOIN DEPARTAMENTOS d ON m.DEPARTAMENTO = d.IDDEPART
        WHERE d.DEPARTAMENTO = ?
        ORDER BY m.MUNICIPIO
    """;

    try (PreparedStatement ps = conexion.prepareStatement(sql)) {

        ps.setString(1, nombreDepto);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            lista.add(rs.getString("MUNICIPIO"));
        }

    } catch (SQLException e) {
        System.out.println("Error obteniendo municipios: " + e.getMessage());
    }

    return lista;
}



    // ============================================================
    //  OBTENER LUGARES DE PRODUCCIÓN (NUM_REGISTRO_ICA)
    // ============================================================
    public List<String> obtenerLugaresProduccion() {
    List<String> lista = new ArrayList<>();
    lista.add("-- Seleccione un lugar --"); // ← DEFAULT

    String sql = "SELECT NUM_REGISTRO_ICA, NOMBRE FROM LUGAR_PRODUCCION";

    try (PreparedStatement ps = conexion.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            lista.add(rs.getInt("NUM_REGISTRO_ICA") + " - " + rs.getString("NOMBRE"));
        }

    } catch (SQLException e) {
        System.out.println("Error obteniendo lugares de producción: " + e.getMessage());
    }

    return lista;
}


    
    // ============================================================
    //  INSERTAR UN PREDIO
    // ============================================================
    public boolean insertarPredio(
    String nombre,
    String departamento,
    String municipio,
    String vereda,
    int idProductor,
    int idPropietario,
    int idLugarProduccion) {
    System.out.println("Propietario logueado: " + idPropietario);

    String sql = """
        INSERT INTO PREDIO (
            NUM_REGISTRO_ICA,
            NOMBRE,
            DEPARTAMENTO,
            MUNICIPIO,
            VEREDA,
            NUMERO_DOC_PRODUCTOR,
            NUMERODOCPROP,
            NUMERO_REG_ICA
        )
        VALUES (PROYECTO.SEQ_NUM_REGISTRO_ICA.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)
    """;

    try (PreparedStatement ps = conexion.prepareStatement(sql)) {

        ps.setString(1, nombre);
        ps.setString(2, departamento);
        ps.setString(3, municipio);
        ps.setString(4, vereda);
        ps.setInt(5, idProductor);
        ps.setInt(6, idPropietario);
        ps.setInt(7, idLugarProduccion);

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error insertando predio: " + e.getMessage());
        return false;
    }
}



    // ============================================================
    //  ELIMINAR PREDIO
    // ============================================================
    public boolean eliminarPredio(int numICA) {
        String sql = "DELETE FROM PREDIO WHERE NUM_REGISTRO_ICA = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, numICA);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminando predio: " + e.getMessage());
            return false;
        }
    }

  
}

