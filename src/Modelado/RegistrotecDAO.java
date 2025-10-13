package Modelado;
/**
 *
 * @author Haider
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegistrotecDAO {

    public void registrarTecnico(Integer documento, Long tarjeta, String nombre, String telefono, String correo, String contrasena, String tipo) {
        try (Connection conn = CConexion.getConnection()) {
            conn.setAutoCommit(false); // ⬅ importante

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
                            if (rsNewTel.next()) {
                                idTelef = rsNewTel.getInt(1);
                            }
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
                            if (rsNewCorreo.next()) {
                                idCorreo = rsNewCorreo.getInt(1);
                            }
                        }
                    }
                }
            }

            // 3️⃣ Insertar técnico
            String sqlTec = "INSERT INTO tecnico (identificacion, tarjetaPro, nombre, idTelef, idCorreo, contrasena, tipo_tecnico) "
                          + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement psTec = conn.prepareStatement(sqlTec)) {
                psTec.setInt(1, documento);
                if (tarjeta != null) {
                    psTec.setLong(2, tarjeta);
                } else {
                    psTec.setNull(2, java.sql.Types.NUMERIC);
                }
                psTec.setString(3, nombre);
                psTec.setInt(4, idTelef);
                psTec.setInt(5, idCorreo);
                psTec.setString(6, contrasena);
                psTec.setString(7, tipo);
                psTec.executeUpdate();
            }

            conn.commit(); // ⬅ aquí guardamos todo de golpe
            System.out.println("✅ Técnico registrado exitosamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
