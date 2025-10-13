package Modelado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Haider
 */
public class ProductorDAO {
    public void registrarProductor(int documento, String nombre, String telefono, String correo, String contrasena) {
        try (Connection conn = CConexion.getConnection()) {

            int idTelef = 0;
            int idCorreo = 0;

            // 1. Buscar si el teléfono ya existe
            String sqlCheckTel = "SELECT id_telefono FROM telefono WHERE telefono = ?";
            PreparedStatement psCheckTel = conn.prepareStatement(sqlCheckTel);
            psCheckTel.setString(1, telefono);
            ResultSet rsTel = psCheckTel.executeQuery();

            if (rsTel.next()) {
                idTelef = rsTel.getInt(1); // ya existe
            } else {
                // si no existe, lo insertamos
                String sqlTel = "INSERT INTO telefono (id_telefono, telefono) VALUES (seq_telefono.NEXTVAL, ?)";
                PreparedStatement psTel = conn.prepareStatement(sqlTel);
                psTel.setString(1, telefono);
                psTel.executeUpdate();

                PreparedStatement psGetTel = conn.prepareStatement("SELECT seq_telefono.CURRVAL FROM dual");
                ResultSet rsNewTel = psGetTel.executeQuery();
                if (rsNewTel.next()) {
                    idTelef = rsNewTel.getInt(1);
                }
            }

            // 2. Buscar si el correo ya existe
            String sqlCheckCorreo = "SELECT id_correo FROM correo WHERE correo = ?";
            PreparedStatement psCheckCorreo = conn.prepareStatement(sqlCheckCorreo);
            psCheckCorreo.setString(1, correo);
            ResultSet rsCorreo = psCheckCorreo.executeQuery();

            if (rsCorreo.next()) {
                idCorreo = rsCorreo.getInt(1); // ya existe
            } else {
                // si no existe, lo insertamos
                String sqlCorreo = "INSERT INTO correo (id_correo, correo) VALUES (seq_correo.NEXTVAL, ?)";
                PreparedStatement psCorreo = conn.prepareStatement(sqlCorreo);
                psCorreo.setString(1, correo);
                psCorreo.executeUpdate();

                PreparedStatement psGetCorreo = conn.prepareStatement("SELECT seq_correo.CURRVAL FROM dual");
                ResultSet rsNewCorreo = psGetCorreo.executeQuery();
                if (rsNewCorreo.next()) {
                    idCorreo = rsNewCorreo.getInt(1);
                }
            }

            // 3. Insertar productor con los IDs ya verificados
            String sqlProd = "INSERT INTO PRODUCTOR (NUMERODOCUMENTO, NOMBRE, IDTELEFONO, IDCORREO, CONTRASENA) VALUES (?, ?, ?, ?, ?)";


            PreparedStatement psTec = conn.prepareStatement(sqlProd);
            psTec.setInt(1, documento);
            psTec.setString(2, nombre);
            psTec.setInt(3, idTelef);
            psTec.setInt(4, idCorreo);
            psTec.setString(5, contrasena);
            psTec.executeUpdate();

            System.out.println(" Productor registrado");

        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
}
