package Modelado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductorDAO {

    public boolean registrarProductor(int documento, String nombre, String telefono, String correo, String contrasena) {
        try (Connection conn = CConexion.getConnection()) {

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

            int idTelef = obtenerIdOTelefonoNuevo(conn, telefono);
            int idCorreo = obtenerIdOCorreoNuevo(conn, correo);

            // 3️⃣ Insertar productor (contraseña sin encriptar)
            String sqlProd = "INSERT INTO PRODUCTOR (NUMERODOCUMENTO, NOMBRE, IDTELEFONO, IDCORREO, CONTRASENA) VALUES (?, ?, ?, ?, ?)";
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
                try (PreparedStatement psTel = conn.prepareStatement("INSERT INTO telefono (id_telefono, telefono) VALUES (seq_telefono.NEXTVAL, ?)")) {
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
                try (PreparedStatement psCorreo = conn.prepareStatement("INSERT INTO correo (id_correo, correo) VALUES (seq_correo.NEXTVAL, ?)")) {
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

