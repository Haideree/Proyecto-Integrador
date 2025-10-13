package Modelado;

/**
 *
 * @author Haider
 */

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class MostrarTecnicos {
    public DefaultTableModel mostrarTecnicos() {
        DefaultTableModel modelo = new DefaultTableModel();
        //modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        //modelo.addColumn("Correo");

        CConexion conn = new CConexion(); // tu clase de conexión

        try (
            Connection conexion = conn.getConnection();
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Nombre FROM TECNICO")
        ) {
            while (rs.next()) {
                modelo.addRow(new Object[]{
                   // rs.getInt("id_cliente"),
                    rs.getString("nombre")
                    //rs.getString("correo")
                });
            }

        } catch (Exception e) {
            System.out.println("❌ Error al consultar: " + e.getMessage());
        }

        return modelo;
    }
}
 
 