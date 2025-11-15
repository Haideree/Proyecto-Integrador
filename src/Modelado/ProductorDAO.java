package Modelado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class ProductorDAO {

    private Connection conexion;

    public ProductorDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public ProductorDAO() {
        this.conexion = CConexion.getConnection();
    }

    // =========================================================
    // REGISTRAR PRODUCTOR
    // =========================================================
    public boolean registrarProductor(int documento, String nombre, String telefono, String correo, String contrasena) {
        try {
            Connection conn = this.conexion;

            // Verificar documento
            String sqlCheckDoc = "SELECT NUMERODOCUMENTO FROM PRODUCTOR WHERE NUMERODOCUMENTO = ?";
            try (PreparedStatement psCheckDoc = conn.prepareStatement(sqlCheckDoc)) {
                psCheckDoc.setInt(1, documento);
                ResultSet rsDoc = psCheckDoc.executeQuery();
                if (rsDoc.next()) {
                    System.out.println("⚠ El productor ya está registrado.");
                    return false;
                }
            }

            // Obtener id teléfono/correo
            int idTelef = obtenerIdOTelefonoNuevo(conn, telefono);
            int idCorreo = obtenerIdOCorreoNuevo(conn, correo);

            // Insertar Productor
            String sqlProd = """
                INSERT INTO PRODUCTOR (NUMERODOCUMENTO, NOMBRE, IDTELEFONO, IDCORREO, CONTRASENA)
                VALUES (?, ?, ?, ?, ?)
            """;

            try (PreparedStatement psProd = conn.prepareStatement(sqlProd)) {
                psProd.setInt(1, documento);
                psProd.setString(2, nombre);
                psProd.setInt(3, idTelef);
                psProd.setInt(4, idCorreo);
                psProd.setString(5, contrasena);
                psProd.executeUpdate();
            }

            System.out.println("✅ Productor registrado correctamente.");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // =========================================================
    // OBTENER LISTA DE PRODUCTORES
    // =========================================================
    public List<Productor> obtenerProductores() {
        List<Productor> lista = new ArrayList<>();

        String sql = """
            SELECT 
                p.NUMERODOCUMENTO,
                p.NOMBRE,
                t.TELEFONO,
                c.CORREO,
                p.CONTRASENA,
                p.IDTELEFONO,
                p.IDCORREO
            FROM PRODUCTOR p
            JOIN TELEFONO t ON p.IDTELEFONO = t.ID_TELEFONO
            JOIN CORREO   c ON p.IDCORREO = c.ID_CORREO
        """;

        try {
            Connection conn = this.conexion;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Productor p = new Productor(
                    rs.getInt("NUMERODOCUMENTO"),
                    rs.getString("NOMBRE"),
                    rs.getString("TELEFONO"),
                    rs.getString("CORREO"),
                    rs.getString("CONTRASENA"),
                    rs.getInt("IDTELEFONO"),
                    rs.getInt("IDCORREO")
                );
                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public List<String> obtenerListaCedulas() {
    List<String> lista = new ArrayList<>();

    String sql = "SELECT NUMERODOCUMENTO FROM PRODUCTOR ORDER BY NUMERODOCUMENTO";

    try (PreparedStatement ps = conexion.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            lista.add(rs.getString("NUMERODOCUMENTO"));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}


    // =========================================================
    // MÉTODOS AUXILIARES
    // =========================================================
    private int obtenerIdOTelefonoNuevo(Connection conn, String telefono) throws Exception {
        int idTelef = 0;

        try (PreparedStatement psCheck = conn.prepareStatement(
                "SELECT ID_TELEFONO FROM TELEFONO WHERE TELEFONO = ?")) {
            psCheck.setString(1, telefono);
            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        // Insert
        try (PreparedStatement psInsert = conn.prepareStatement(
                "INSERT INTO TELEFONO (ID_TELEFONO, TELEFONO) VALUES (SEQ_TELEFONO.NEXTVAL, ?)")) {
            psInsert.setString(1, telefono);
            psInsert.executeUpdate();
        }

        // Obtener nuevo id
        try (PreparedStatement psGet = conn.prepareStatement("SELECT SEQ_TELEFONO.CURRVAL FROM DUAL");
             ResultSet rsNew = psGet.executeQuery()) {

            if (rsNew.next()) {
                idTelef = rsNew.getInt(1);
            }
        }

        return idTelef;
    }

    private int obtenerIdOCorreoNuevo(Connection conn, String correo) throws Exception {
        int idCorreo = 0;

        try (PreparedStatement psCheck = conn.prepareStatement(
                "SELECT ID_CORREO FROM CORREO WHERE CORREO = ?")) {
            psCheck.setString(1, correo);
            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        try (PreparedStatement psInsert = conn.prepareStatement(
                "INSERT INTO CORREO (ID_CORREO, CORREO) VALUES (SEQ_CORREO.NEXTVAL, ?)")) {
            psInsert.setString(1, correo);
            psInsert.executeUpdate();
        }

        try (PreparedStatement psGet = conn.prepareStatement("SELECT SEQ_CORREO.CURRVAL FROM DUAL");
             ResultSet rsNew = psGet.executeQuery()) {

            if (rsNew.next()) {
                idCorreo = rsNew.getInt(1);
            }
        }

        return idCorreo;
    }
}



