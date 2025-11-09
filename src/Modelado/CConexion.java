package Modelado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CConexion {

    // ==============================================
    // 🔧 CONFIGURACIÓN GENERAL DE LA BASE DE DATOS
    // ==============================================
    private static final String HOST = "localhost";
    private static final String PUERTO = "1521";
    private static final String SERVICIO = "xe"; // puede ser SID o SERVICE_NAME

    // ==============================================
    // 🔹 1️⃣ Conexión base (por defecto)
    //      -> se conecta como ADMINISTRADOR
    // ==============================================
    public static Connection getConnection() {
        // Esta conexión solo debe usarse para tareas generales o pruebas
        return getConnectionPorUsuario("ADMINISTRADOR", "ADMINISTRADOR");
    }

    // ==============================================
    // 🔹 2️⃣ Conexión según ROL
    //      -> se conecta automáticamente con el usuario correcto
    // ==============================================
    public static Connection getConnectionPorRol(String rol) {
        String usuario;
        String contrasena;

        switch (rol.toLowerCase()) {
            case "productor":
                usuario = "PRODUCTOR";
                contrasena = "PRODUCTOR";
                break;
            case "tecnico":
                usuario = "TECNICO";
                contrasena = "TECNICO";
                break;
            case "administrador":
                usuario = "ADMINISTRADOR";
                contrasena = "ADMINISTRADOR";
                break;
            default: // propietario
                usuario = "PROPIETARIO";
                contrasena = "PROPIETARIO";
                break;
        }

        return getConnectionPorUsuario(usuario, contrasena);
    }

    // ==============================================
    // 🔹 3️⃣ Conexión directa por usuario y contraseña
    //      -> se usa internamente o en el LoginDAO
    // ==============================================
    public static Connection getConnectionPorUsuario(String usuario, String contrasena) {
        Connection conexion = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 💡 Usa esta URL si trabajas con SID (por ejemplo "xe")
            String url = "jdbc:oracle:thin:@" + HOST + ":" + PUERTO + ":" + SERVICIO;

            // 💡 O esta si trabajas con SERVICE_NAME:
            // String url = "jdbc:oracle:thin:@//" + HOST + ":" + PUERTO + "/" + SERVICIO;

            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("🔥 Conectado a Oracle como: " + usuario);

        } catch (ClassNotFoundException e) {
            System.out.println("💥 Error: No se encontró el driver JDBC de Oracle.");
        } catch (SQLException e) {
            System.out.println("💥 Error de conexión con " + usuario + ": " + e.getMessage());
        }

        return conexion;
    }

    // ==============================================
    // 🔹 4️⃣ Cerrar conexión
    // ==============================================
    public static void cerrarConexion(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("🔒 Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("💥 Error al cerrar conexión: " + e.getMessage());
            }
        }
    }
}
