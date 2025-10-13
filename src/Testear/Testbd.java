package Testear;

import java.sql.Connection;
import Modelado.CConexion;

public class Testbd {
    public static void main(String[] args) {
        Connection conn = CConexion.getConnection();
        if (conn != null) {
            System.out.println("🔥 Todo bien, conexión lista para usar.");
            CConexion.cerrarConexion(conn);
        } else {
            System.out.println("💥 No se pudo conectar a la base de datos.");
        }
    }
}
