package Modelado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class ProductorDAO {

    // =========================================================
    // 🔹 Atributo de conexión (puede ser de cualquier rol)
    // =========================================================
    private Connection conexion;

    // 🔹 Constructor que recibe una conexión (por ejemplo, del login)
    public ProductorDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // 🔹 Constructor por defecto (usa ADMINISTRADOR si no se pasa nada)
    public ProductorDAO() {
        this.conexion = CConexion.getConnection();
    }

    // =========================================================
    // 🔹 Registrar Productor
    // =========================================================
    public boolean registrarProductor(int documento, String nombre, String telefono, String correo, String contrasena) {
        try (Connection conn = this.conexion) {

            // 0️⃣ Verificar si el documento ya está registrado
            String sqlCheckDoc = "SELECT NUMERODOCUMENTO FROM PRODUCTOR WHERE NUMERODOCUMENTO = ?";
            try (PreparedStatement psCheckDoc = conn.prepareStatement(sqlCheckDoc)) {
                psCheckDoc.setInt(1, documento);
                ResultSet rsDoc = psCheckDoc.executeQuery();
                if (rsDoc.next()) {
                    System.out.println("⚠ El productor ya está registrado.");
                    return false;
                }
            }

            // 1️⃣ Obtener o insertar ID de teléfono y correo
            int idTelef = obtenerIdOTelefonoNuevo(conn, telefono);
            int idCorreo = obtenerIdOCorreoNuevo(conn, correo);

            // 2️⃣ Insertar productor
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
    // 🔹 Obtener todos los productores
    // =========================================================
    public List<Productor> obtenerProductores() {
        List<Productor> lista = new ArrayList<>();

        String sql = """
            SELECT p.NUMERODOCUMENTO, p.NOMBRE, t.TELEFONO, c.CORREO, p.CONTRASENA
            FROM PRODUCTOR p
            JOIN TELEFONO t ON p.IDTELEFONO = t.ID_TELEFONO
            JOIN CORREO c ON p.IDCORREO = c.ID_CORREO
        """;

        try (Connection conn = this.conexion;
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Productor p = new Productor(
                    rs.getInt("NUMERODOCUMENTO"),
                    rs.getString("NOMBRE"),
                    rs.getString("TELEFONO"),
                    rs.getString("CORREO"),
                    rs.getString("CONTRASENA")
                );
                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // =========================================================
    // 🔹 Métodos auxiliares
    // =========================================================
    private int obtenerIdOTelefonoNuevo(Connection conn, String telefono) throws Exception {
        int idTelef = 0;
        try (PreparedStatement psCheckTel = conn.prepareStatement("SELECT id_telefono FROM telefono WHERE telefono = ?")) {
            psCheckTel.setString(1, telefono);
            ResultSet rsTel = psCheckTel.executeQuery();

            if (rsTel.next()) {
                idTelef = rsTel.getInt(1);
            } else {
                try (PreparedStatement psTel = conn.prepareStatement(
                        "INSERT INTO telefono (id_telefono, telefono) VALUES (seq_telefono.NEXTVAL, ?)")) {
                    psTel.setString(1, telefono);
                    psTel.executeUpdate();
                }

                try (PreparedStatement psGetTel = conn.prepareStatement("SELECT seq_telefono.CURRVAL FROM dual");
                     ResultSet rsNewTel = psGetTel.executeQuery()) {
                    if (rsNewTel.next()) {
                        idTelef = rsNewTel.getInt(1);
                    }
                }
            }
        }
        return idTelef;
    }

    private int obtenerIdOCorreoNuevo(Connection conn, String correo) throws Exception {
        int idCorreo = 0;
        try (PreparedStatement psCheckCorreo = conn.prepareStatement("SELECT id_correo FROM correo WHERE correo = ?")) {
            psCheckCorreo.setString(1, correo);
            ResultSet rsCorreo = psCheckCorreo.executeQuery();

            if (rsCorreo.next()) {
                idCorreo = rsCorreo.getInt(1);
            } else {
                try (PreparedStatement psCorreo = conn.prepareStatement(
                        "INSERT INTO correo (id_correo, correo) VALUES (seq_correo.NEXTVAL, ?)")) {
                    psCorreo.setString(1, correo);
                    psCorreo.executeUpdate();
                }

                try (PreparedStatement psGetCorreo = conn.prepareStatement("SELECT seq_correo.CURRVAL FROM dual");
                     ResultSet rsNewCorreo = psGetCorreo.executeQuery()) {
                    if (rsNewCorreo.next()) {
                        idCorreo = rsNewCorreo.getInt(1);
                    }
                }
            }
        }
        return idCorreo;
    }
}


