package Modelado;

/**
 *
 * @author Haider
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InformeDAO {

    private Connection conexion;

    public InformeDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // ============================================================
    // 1️⃣ INFORME GENERAL DE INSPECCIONES (ADMINISTRADOR)
    // ============================================================
    public String informeInspecciones() throws Exception {

    String sql = """
        SELECT 
            i.id_inspeccion,
            i.fecha_inspeccion,
            t.nombre AS tecnico,
            lp.nombre AS predio,
            l.nombre_lote,
            c.nombre_especie AS cultivo,
            i.resultado,
            i.observaciones
        FROM INSPECCION_SANITARIA i
        JOIN SOLICITUD_INSPECCION s ON i.id_solicitud = s.id_solicitud
        JOIN TECNICO t ON s.id_tecnico = t.identificacion
        JOIN LOTE l ON s.num_lote = l.num_lote
        LEFT JOIN CULTIVO c ON l.idcultivo = c.id
        LEFT JOIN LUGAR_PRODUCCION lp ON l.id_lugarprod = lp.num_registro_ica
        ORDER BY i.fecha_inspeccion DESC
    """;

    PreparedStatement ps = conexion.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();

    StringBuilder sb = new StringBuilder("=== INFORME GENERAL DE INSPECCIONES ===\n\n");

    while (rs.next()) {
        sb.append("ID Inspección: ").append(rs.getInt("id_inspeccion")).append("\n")
                .append("Fecha: ").append(rs.getDate("fecha_inspeccion")).append("\n")
                .append("Técnico: ").append(rs.getString("tecnico")).append("\n")
                .append("Predio (Lugar Prod.): ").append(rs.getString("predio")).append("\n")
                .append("Lote: ").append(rs.getString("nombre_lote")).append("\n")
                .append("Cultivo: ").append(rs.getString("cultivo")).append("\n")
                .append("Resultado: ").append(rs.getString("resultado")).append("\n")
                .append("Observaciones: ").append(rs.getString("observaciones")).append("\n")
                .append("-------------------------------------------\n");
    }

    return sb.toString();
}
    
    // ============================================================
// 3️⃣ INFORME DE TECNICO: Historial de inspecciones
// ============================================================

public String informeEstadisticoTecnico(int idTecnico) throws Exception {

    // 1) Consulta principal: agregados corregidos (UPPER/TRIM)
    String sqlAgg = """
        SELECT 
            COUNT(*) AS total_solicitudes,
            SUM(CASE WHEN UPPER(TRIM(estado)) = 'REALIZADA' THEN 1 ELSE 0 END) AS total_realizadas,
            SUM(CASE WHEN UPPER(TRIM(estado)) = 'PENDIENTE' THEN 1 ELSE 0 END) AS total_pendientes,
            MAX(fecha_asignacion) AS ultima_asignacion
        FROM SOLICITUD_INSPECCION
        WHERE id_tecnico = ?
    """;

    int totalSolicitudes = 0;
    int totalRealizadas = 0;
    int totalPendientes = 0;
    java.sql.Date ultimaAsignacion = null;

    try (PreparedStatement ps1 = conexion.prepareStatement(sqlAgg)) {
        ps1.setInt(1, idTecnico);

        try (ResultSet rs = ps1.executeQuery()) {
            if (rs.next()) {
                totalSolicitudes = rs.getInt("total_solicitudes");
                totalRealizadas = rs.getInt("total_realizadas");
                totalPendientes = rs.getInt("total_pendientes");
                ultimaAsignacion = rs.getDate("ultima_asignacion");
            }
        }
    }

    // 2) Consulta separada: última solicitud REALIZADA del técnico
    String sqlUltRealizada = """
        SELECT MAX(fecha_asignacion) AS ultima_realizada
        FROM SOLICITUD_INSPECCION
        WHERE id_tecnico = ?
          AND UPPER(TRIM(estado)) = 'REALIZADA'
    """;

    java.sql.Date ultimaRealizada = null;

    try (PreparedStatement ps2 = conexion.prepareStatement(sqlUltRealizada)) {
        ps2.setInt(1, idTecnico);

        try (ResultSet rs2 = ps2.executeQuery()) {
            if (rs2.next()) {
                ultimaRealizada = rs2.getDate("ultima_realizada");
            }
        }
    }

    // 3) Armar informe
    StringBuilder sb = new StringBuilder("=== INFORME ESTADÍSTICO DEL TÉCNICO ===\n\n");

    sb.append("Total de solicitudes asignadas: ").append(totalSolicitudes).append("\n");
    sb.append("Solicitudes realizadas: ").append(totalRealizadas).append("\n");
    sb.append("Solicitudes pendientes: ").append(totalPendientes).append("\n");
    sb.append("Última asignación registrada: ").append(ultimaAsignacion).append("\n");
    sb.append("Última solicitud realizada: ").append(ultimaRealizada).append("\n");

    return sb.toString();
}



    // ============================================================
    // 3️⃣ INFORME DE PRODUCTOR: LUGARES + CULTIVOS
    // ============================================================
    public String informeProductor() throws Exception {

    String sql = """
        SELECT 
            lp.nombre AS nombre_lugar,
            c.nombre_especie AS cultivo,
            c.tipo_cultivo AS tipo
        FROM lote l
        LEFT JOIN lugar_produccion lp ON lp.num_registro_ica = l.id_lugarprod
        LEFT JOIN cultivo c ON c.id = l.idcultivo
        ORDER BY lp.nombre
    """;

    PreparedStatement ps = conexion.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();

    StringBuilder sb = new StringBuilder("=== INFORME DE PRODUCCIÓN ===\n\n");

    while (rs.next()) {

        sb.append("Lugar de producción: ")
          .append(rs.getString("nombre_lugar")).append("\n")
          .append("Cultivo: ")
          .append(rs.getString("cultivo") != null ? rs.getString("cultivo") : "Sin cultivo")
          .append("\n")
          .append("Tipo: ")
          .append(rs.getString("tipo") != null ? rs.getString("tipo") : "N/A")
          .append("\n")
          .append("-------------------------------------------\n");
    }

    return sb.toString();
}

    // ============================================================
    // 4️⃣ INFORME DE PROPIETARIO: PREDIOS → LOTES → CULTIVOS
    // ============================================================
    public String informePropietario(int idPropietario) throws Exception {

        String sql = """
            SELECT p.nombre AS predio, l.nombre_lote, c.nombre AS cultivo, c.tipo
            FROM PREDIO p
            LEFT JOIN LOTE l ON l.id_predio = p.id_predio
            LEFT JOIN CULTIVO c ON c.idcultivo = l.idcultivo
            WHERE p.id_propietario = ?
            ORDER BY p.nombre, l.nombre_lote
        """;

        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, idPropietario);
        ResultSet rs = ps.executeQuery();

        StringBuilder sb = new StringBuilder("=== INFORME DE PROPIETARIO ===\n\n");

        while (rs.next()) {
            sb.append("Predio: ").append(rs.getString("predio")).append("\n")
                    .append("Lote: ").append(rs.getString("nombre_lote")).append("\n")
                    .append("Cultivo: ").append(rs.getString("cultivo")).append("\n")
                    .append("Tipo: ").append(rs.getString("tipo")).append("\n")
                    .append("-------------------------------------------\n");
        }

        return sb.toString();
    }

    // ============================================================
    // 5️⃣ INFORME ESTADÍSTICO GLOBAL (ADMIN)
    // ============================================================
    public String informeEstadistico() throws Exception {

        StringBuilder sb = new StringBuilder("=== INFORME ESTADÍSTICO ===\n\n");

        // Total predios
        ResultSet rs = conexion.prepareStatement("SELECT COUNT(*) FROM PREDIO").executeQuery();
        if (rs.next()) sb.append("Total Predios: ").append(rs.getInt(1)).append("\n");

        // Total lotes
        rs = conexion.prepareStatement("SELECT COUNT(*) FROM LOTE").executeQuery();
        if (rs.next()) sb.append("Total Lotes: ").append(rs.getInt(1)).append("\n");

        // Total inspecciones este mes
        rs = conexion.prepareStatement("""
            SELECT COUNT(*) FROM INSPECCION_SANITARIA
            WHERE EXTRACT(MONTH FROM fecha_inspeccion) = EXTRACT(MONTH FROM SYSDATE)
        """).executeQuery();
        if (rs.next()) sb.append("Inspecciones este mes: ").append(rs.getInt(1)).append("\n");

        sb.append("\n--- Técnicos con más inspecciones ---\n");

        // Técnicos líderes
        rs = conexion.prepareStatement("""
            SELECT t.nombre, COUNT(*) AS total
            FROM INSPECCION_SANITARIA i
            JOIN SOLICITUD_INSPECCION s ON i.id_solicitud = s.id_solicitud
            JOIN TECNICO t ON t.id_tecnico = s.id_tecnico
            GROUP BY t.nombre
            ORDER BY total DESC
        """).executeQuery();

        while (rs.next()) {
            sb.append(rs.getString("nombre"))
                    .append(": ").append(rs.getInt("total")).append(" inspecciones\n");
        }

        return sb.toString();
    }
}
