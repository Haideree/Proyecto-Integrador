package Modelado;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PropietarioDAO {
    public void registrarPropietario(int documento, String nombre, long telefono, String correo, String contrasena) {
        try (Connection conn = CConexion.getConnection()) {

            String sql = "INSERT INTO Propietario (Numerodocumento, Nombre, telefono, correo, contrasena) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, documento);
            
            // Si el nombre viene vacío (es opcional), lo ponemos null
            if (nombre == null || nombre.isEmpty()) {
                ps.setNull(2, java.sql.Types.VARCHAR);
            } else {
                ps.setString(2, nombre);
            }
            
            ps.setLong(3, telefono);  
            ps.setString(4, correo);
            ps.setString(5, contrasena);

            ps.executeUpdate();

            System.out.println(" Propietario registrado con éxito.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
}


