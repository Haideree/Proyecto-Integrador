package Modelado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PropietarioDAO {
    
    private Connection conexion;
    
    public PropietarioDAO(Connection conexion){
        this.conexion = conexion;
    }
    
    public PropietarioDAO(){
        this.conexion = CConexion.getConnection();
    }
    
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
    
    public List<Propietario> obtenerPropietarios(){
        List<Propietario> lista = new ArrayList<>();
        
        String sql = """
        SELECT 
            p.NUMERODOCUMENTO,
            p.NOMBRE,
            p.TELEFONO,
            p.CORREO,
            p.CONTRASENA
        FROM PROPIETARIO p
        """;
        
        try (Connection conn = this.conexion;
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()){
                Propietario pro = new Propietario(
                rs.getInt("NUMERODOCUMENTO"),
                rs.getString("NOMBRE"),
                rs.getString("TELEFONO"),
                rs.getString("CORREO"),
                rs.getString("CONTRASENA")
                );
                lista.add(pro);
            }
        }catch (Exception e) {
            e.printStackTrace();   
    }
    return lista;
    }
}

