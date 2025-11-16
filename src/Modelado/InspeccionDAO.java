package Modelado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class InspeccionDAO {

    private Connection conexion;

    public InspeccionDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // ============================================================
    //   GUARDAR INSPECCIÓN + MARCAR SOLICITUD + ACTUALIZAR LOTE
    // ============================================================
    public boolean guardarInspeccionCompleta(int idSolicitud, String fecha, String observaciones, String resultado) {

        String insertarInspeccion = """
            INSERT INTO INSPECCION_SANITARIA
            (ID_INSPECCION, ID_SOLICITUD, FECHA_INSPECCION, OBSERVACIONES, RESULTADO)
            VALUES (INSPECCION_SEQ.NEXTVAL, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?)
        """;

        String marcarSolicitud = """
            UPDATE SOLICITUD_INSPECCION
            SET ESTADO = 'REALIZADA'
            WHERE ID_SOLICITUD = ?
        """;

        // Ajusta ESTADO y columna según tu BD
        String actualizarLote = """
    UPDATE LOTE
    SET ESTADO = CASE 
                    WHEN UPPER(?) = 'APROBADO' THEN 'APROBADO'
                    ELSE 'SIN APROBAR'
                 END
    WHERE NUM_LOTE = (
        SELECT NUM_LOTE FROM  SOLICITUD_INSPECCION WHERE ID_SOLICITUD = ?
    )
""";


        try {
            conexion.setAutoCommit(false);

            // 1️⃣ Insertar inspección
            try (PreparedStatement ps = conexion.prepareStatement(insertarInspeccion)) {
                ps.setInt(1, idSolicitud);
                ps.setString(2, fecha);
                ps.setString(3, observaciones);
                ps.setString(4, resultado);
                ps.executeUpdate();
            }

            // 2️⃣ Marcar solicitud como realizada
            try (PreparedStatement ps = conexion.prepareStatement(marcarSolicitud)) {
                ps.setInt(1, idSolicitud);
                ps.executeUpdate();
            }

            // 3️⃣ Actualizar estado del lote
            try (PreparedStatement ps = conexion.prepareStatement(actualizarLote)) {
                ps.setString(1, resultado);
                ps.setInt(2, idSolicitud);
                ps.executeUpdate();
            }

            conexion.commit();
            return true;

        } catch (Exception e) {
            try { conexion.rollback(); } catch (Exception ex) {}
            System.out.println("Error en transacción de inspección: " + e.getMessage());
            return false;

        } finally {
            try { conexion.setAutoCommit(true); } catch (Exception ex) {}
        }
    }

    // ============================================================
    // LISTAR SOLICITUDES PARA TÉCNICO
    // ============================================================
    public DefaultTableModel listarSolicitudesParaTecnico(int idTecnico) {
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Fecha Asignación");
        modelo.addColumn("Lote");
        modelo.addColumn("Estado");

        String sql = """
            SELECT s.id_solicitud,
                   s.fecha_asignacion,
                   l.nombre_lote AS nombre_lote,
                   s.estado
            FROM SOLICITUD_INSPECCION s
            JOIN LOTE l ON s.num_lote = l.num_lote
            WHERE s.id_tecnico = ?
              AND s.estado <> 'REALIZADA'
        """;

        try (Connection cn = CConexion.getConnectionPorRol("tecnico");
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idTecnico);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id_solicitud"),
                    rs.getDate("fecha_asignacion"),
                    rs.getString("nombre_lote"),
                    rs.getString("estado")
                });
            }

        } catch (Exception e) {
            System.out.println("Error listar solicitudes: " + e.getMessage());
        }

        return modelo;
    }

    // ============================================================
    // OBTENER NOMBRE DEL LOTE
    // ============================================================
    public String obtenerNombreLotePorSolicitud(int idSolicitud) {
        String nombre = "";

        String sql = """
            SELECT l.nombre_lote
            FROM SOLICITUD_INSPECCION s
            JOIN LOTE l ON s.num_lote = l.num_lote
            WHERE s.id_solicitud = ?
        """;

        try (Connection cn = CConexion.getConnectionPorRol("tecnico");
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idSolicitud);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nombre = rs.getString("nombre_lote");
            }

        } catch (Exception e) {
            System.out.println("Error obteniendo lote: " + e.getMessage());
        }

        return nombre;
    }

}
