/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelado;

/**
 *
 * @author Haider
 */

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CultivoDAO {
    
    private final Connection con;

    public CultivoDAO(Connection con) {
        this.con = con;
    }

    public List<String> obtenerCultivos() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT NOMBRE_ESPECIE FROM CULTIVO ORDER BY NOMBRE_ESPECIE";
        
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                lista.add(rs.getString("NOMBRE_ESPECIE"));
            }
        } catch (SQLException e) {
            Logger.getLogger(CultivoDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }
}
