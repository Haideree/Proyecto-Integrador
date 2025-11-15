package Modelado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RegistrotecDAO {

    private Connection conexion;

    public RegistrotecDAO(Connection conexion){
        this.conexion = conexion;
    }

    public RegistrotecDAO() {
        this.conexion = CConexion.getConnection();
    }

    // =========================================================
    // 🔹 Registrar Técnico
    // =========================================================
    public void registrarTecnico(Integer documento, Long tarjeta, String nombre, String telefono, String correo, String contrasena, String tipo) {
        try (Connection conn = CConexion.getConnection()) {
            conn.setAutoCommit(false);

            int idTelef = 0;
            int idCorreo = 0;

            // 1️⃣ Teléfono
            String sqlCheckTel = "SELECT id_telefono FROM telefono WHERE telefono = ?";
            try (PreparedStatement psCheckTel = conn.prepareStatement(sqlCheckTel)) {
                psCheckTel.setString(1, telefono);
                try (ResultSet rsTel = psCheckTel.executeQuery()) {
                    if (rsTel.next()) {
                        idTelef = rsTel.getInt(1);
                    } else {
                        String sqlTel = "INSERT INTO telefono (id_telefono, telefono) VALUES (seq_telefono.NEXTVAL, ?)";
                        try (PreparedStatement psTel = conn.prepareStatement(sqlTel)) {
                            psTel.setString(1, telefono);
                            psTel.executeUpdate();
                        }
                        try (PreparedStatement psGetTel = conn.prepareStatement("SELECT seq_telefono.CURRVAL FROM dual");
                             ResultSet rsNewTel = psGetTel.executeQuery()) {

                            if (rsNewTel.next()) idTelef = rsNewTel.getInt(1);
                        }
                    }
                }
            }

            // 2️⃣ Correo
            String sqlCheckCorreo = "SELECT id_correo FROM correo WHERE correo = ?";
            try (PreparedStatement psCheckCorreo = conn.prepareStatement(sqlCheckCorreo)) {
                psCheckCorreo.setString(1, correo);
                try (ResultSet rsCorreo = psCheckCorreo.executeQuery()) {
                    if (rsCorreo.next()) {
                        idCorreo = rsCorreo.getInt(1);
                    } else {
                        String sqlCorreo = "INSERT INTO correo (id_correo, correo) VALUES (seq_correo.NEXTVAL, ?)";
                        try (PreparedStatement psCorreo = conn.prepareStatement(sqlCorreo)) {
                            psCorreo.setString(1, correo);
                            psCorreo.executeUpdate();
                        }
                        try (PreparedStatement psGetCorreo = conn.prepareStatement("SELECT seq_correo.CURRVAL FROM dual");
                             ResultSet rsNewCorreo = psGetCorreo.executeQuery()) {

                            if (rsNewCorreo.next()) idCorreo = rsNewCorreo.getInt(1);
                        }
                    }
                }
            }

            // 3️⃣ Insertar técnico
            String sqlTec = """
                INSERT INTO tecnico 
                (identificacion, tarjetapro, nombre, idtelef, idcorreo, contrasena, tipo_tecnico)
                VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

            try (PreparedStatement psTec = conn.prepareStatement(sqlTec)) {

                psTec.setInt(1, documento);

                if (tarjeta != null) psTec.setLong(2, tarjeta);
                else psTec.setNull(2, java.sql.Types.NUMERIC);

                psTec.setString(3, nombre);
                psTec.setInt(4, idTelef);
                psTec.setInt(5, idCorreo);
                psTec.setString(6, contrasena);
                psTec.setString(7, tipo);

                psTec.executeUpdate();
            }

            conn.commit();
            System.out.println("✅ Técnico registrado exitosamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================================================
    // 🔹 Obtener todos los técnicos
    // =========================================================
    public List<Tecnico> obtenerTecnicos() {
        List<Tecnico> lista = new ArrayList<>();

        String sql = """
            SELECT 
                t.IDENTIFICACION,
                t.TARJETAPRO,
                t.NOMBRE,
                te.TELEFONO,
                c.CORREO,
                t.CONTRASENA,
                t.TIPO_TECNICO
            FROM TECNICO t
            JOIN TELEFONO te ON t.IDTELEF = te.ID_TELEFONO
            JOIN CORREO c ON t.IDCORREO = c.ID_CORREO
        """;

        try (Connection conn = CConexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
            Tecnico tec = new Tecnico(
    rs.getInt("IDENTIFICACION"),
    rs.getLong("TARJETAPRO"),
    rs.getString("NOMBRE"),
    rs.getString("TELEFONO"),
    rs.getString("CORREO"),
    rs.getString("CONTRASENA"),
    rs.getString("TIPO_TECNICO")
);

               

       

                lista.add(tec);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}

