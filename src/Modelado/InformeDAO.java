package Modelado;

/**
 *
 * @author Haider
 */

import java.sql.SQLException;
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

    StringBuilder sb = new StringBuilder();

    // ===============================================
    // Encabezado del informe
    // ===============================================
    sb.append("============================================================\n");
    sb.append("               INFORME GENERAL DE INSPECCIONES\n");
    sb.append("============================================================\n");
    sb.append("Generado: ").append(java.time.LocalDateTime.now()).append("\n");
    sb.append("------------------------------------------------------------\n\n");

    boolean hayDatos = false;

    while (rs.next()) {
        hayDatos = true;

        sb.append("🔎 INSPECCIÓN #").append(rs.getInt("id_inspeccion")).append("\n");
        sb.append("------------------------------------------------------------\n");
        sb.append("📅 Fecha: ").append(rs.getDate("fecha_inspeccion")).append("\n");
        sb.append("🧑‍🔧 Técnico: ").append(rs.getString("tecnico")).append("\n");
        sb.append("🏡 Predio / Lugar Prod.: ").append(rs.getString("predio")).append("\n");
        sb.append("🌱 Cultivo: ").append(rs.getString("cultivo")).append("\n");
        sb.append("📦 Lote: ").append(rs.getString("nombre_lote")).append("\n\n");

        sb.append("📊 Resultado: ").append(rs.getString("resultado")).append("\n");
        sb.append("📝 Observaciones:\n");
        sb.append("   ").append(rs.getString("observaciones")).append("\n");

        sb.append("------------------------------------------------------------\n\n");
    }

    if (!hayDatos) {
        sb.append("❗ No se encontraron inspecciones registradas.\n");
    }

    sb.append("============================================================\n");
    sb.append("                     FIN DEL INFORME\n");
    sb.append("============================================================\n");

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
// 3️⃣ INFORME DE PRODUCTOR: LUGARES + CULTIVOS (VERSIÓN PRO)
// ============================================================
public String informeProductor() throws Exception {

    String sql = """
        SELECT 
            lp.nombre AS nombre_lugar,
            c.nombre_especie AS cultivo,
            c.tipo_cultivo AS tipo
        FROM lugar_produccion lp
        LEFT JOIN lote l ON l.id_lugarprod = lp.num_registro_ica
        LEFT JOIN cultivo c ON c.id = l.idcultivo
        ORDER BY lp.nombre, c.nombre_especie
    """;

    PreparedStatement ps = conexion.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();

    StringBuilder sb = new StringBuilder();

    sb.append("\n===== INFORME GENERAL DE PRODUCCIÓN =====\n");

    String lugarActual = null;

    while (rs.next()) {
        String lugar = rs.getString("nombre_lugar");
        String cultivo = rs.getString("cultivo");
        String tipo = rs.getString("tipo");

        if (lugarActual == null || !lugar.equals(lugarActual)) {
            sb.append("\n🏠 Lugar de Producción: ").append(lugar).append("\n");
            sb.append("----------------------------------------\n");
            lugarActual = lugar;
        }

        sb.append(" • Cultivo: ").append(cultivo != null ? cultivo : "Ninguno")
          .append(" | Tipo: ").append(tipo != null ? tipo : "N/A")
          .append("\n");
    }

    sb.append("\n=========== FIN DEL INFORME ===========\n");

    return sb.toString();
}




    // ============================================================
    // 4️⃣ INFORME DE PROPIETARIO: PREDIOS → LOTES → CULTIVOS
    // ============================================================
    public String informePropietario(long documento) throws SQLException {

    String sql =
        "SELECT p.nombre AS predio, " +
        "       lp.nombre AS lugarProd, " +
        "       l.nombre_lote AS lote, " +
        "       c.nombre_especie AS especie, " +
        "       c.tipo_cultivo AS tipo " +
        "FROM predio p " +
        "JOIN lugar_produccion lp " +
        "     ON lp.num_registro_ica = p.numero_reg_ica " +
        "LEFT JOIN lote l " +
        "     ON l.id_lugarprod = lp.num_registro_ica " +
        "LEFT JOIN cultivo c " +
        "     ON c.id = l.idcultivo " +
        "WHERE p.numerodocprop = ? " +
        "ORDER BY p.nombre, lp.nombre, l.nombre_lote";

    PreparedStatement ps = conexion.prepareStatement(sql);
    ps.setLong(1, documento);
    ResultSet rs = ps.executeQuery();

    StringBuilder sb = new StringBuilder();

    sb.append("============================================================\n");
    sb.append("                 INFORME DEL PROPIETARIO\n");
    sb.append("============================================================\n");
    sb.append("Documento del propietario: ").append(documento).append("\n");
    sb.append("Generado: ").append(java.time.LocalDateTime.now()).append("\n");
    sb.append("------------------------------------------------------------\n\n");

    boolean hayDatos = false;

    String ultimoPredio = null;
    String ultimoLugar = null;

    while (rs.next()) {
        hayDatos = true;

        String predio = rs.getString("predio");
        String lugar = rs.getString("lugarProd");

        // Nueva sección por predio
        if (!predio.equals(ultimoPredio)) {
            sb.append("\n🏡 PREDIO: ").append(predio).append("\n");
            sb.append("------------------------------------------------------------\n");
            ultimoPredio = predio;
            ultimoLugar = null;
        }

        // Nueva sección por lugar de producción
        if (lugar != null && !lugar.equals(ultimoLugar)) {
            sb.append("   📍 Lugar de Producción: ").append(lugar).append("\n");
            sb.append("   ---------------------------------------------------------\n");
            ultimoLugar = lugar;
        }

        sb.append("      • Lote: ").append(rs.getString("lote")).append("\n");
        sb.append("        → Especie: ").append(rs.getString("especie")).append("\n");
        sb.append("        → Tipo de Cultivo: ").append(rs.getString("tipo")).append("\n");
        sb.append("\n");
    }

    if (!hayDatos) {
        sb.append("❗ No se encontraron registros para este propietario.\n");
    }

    sb.append("\n============================================================\n");
    sb.append("                   FIN DEL INFORME\n");
    sb.append("============================================================\n");

    return sb.toString();
}



    // ============================================================
    // 5️⃣ INFORME ESTADÍSTICO GLOBAL (ADMIN)
    // ============================================================
    public String informeEstadistico() throws Exception {

    StringBuilder sb = new StringBuilder();

    sb.append("============================================================\n");
    sb.append("                  INFORME ESTADÍSTICO GLOBAL\n");
    sb.append("============================================================\n");
    sb.append("Generado: ").append(java.time.LocalDateTime.now()).append("\n");
    sb.append("------------------------------------------------------------\n\n");

    // ============================
    //  MÉTRICAS PRINCIPALES
    // ============================
    sb.append("📊 MÉTRICAS GENERALES\n");
    sb.append("------------------------------------------------------------\n");

    // Total predios
    ResultSet rs = conexion.prepareStatement("SELECT COUNT(*) FROM PREDIO").executeQuery();
    if (rs.next()) sb.append("🏡 Total de Predios: ").append(rs.getInt(1)).append("\n");

    // Total lotes
    rs = conexion.prepareStatement("SELECT COUNT(*) FROM LOTE").executeQuery();
    if (rs.next()) sb.append("📦 Total de Lotes: ").append(rs.getInt(1)).append("\n");

    // Total inspecciones este mes
    rs = conexion.prepareStatement("""
        SELECT COUNT(*) 
        FROM INSPECCION_SANITARIA
        WHERE EXTRACT(MONTH FROM fecha_inspeccion) = EXTRACT(MONTH FROM SYSDATE)
    """).executeQuery();
    if (rs.next()) sb.append("📝 Inspecciones este mes: ").append(rs.getInt(1)).append("\n");

    sb.append("\n");

    // ============================
    //  TÉCNICOS LÍDERES
    // ============================
    sb.append("⭐ TÉCNICOS CON MÁS INSPECCIONES\n");
    sb.append("------------------------------------------------------------\n");

    rs = conexion.prepareStatement("""
        SELECT t.nombre, COUNT(*) AS total
        FROM INSPECCION_SANITARIA i
        JOIN SOLICITUD_INSPECCION s ON i.id_solicitud = s.id_solicitud
        JOIN TECNICO t ON t.identificacion = s.id_tecnico
        GROUP BY t.nombre
        ORDER BY total DESC
    """).executeQuery();

    boolean hayTec = false;

    while (rs.next()) {
        hayTec = true;
        sb.append("🔹 ").append(rs.getString("nombre"))
          .append(" → ").append(rs.getInt("total"))
          .append(" inspecciones\n");
    }

    if (!hayTec) {
        sb.append("❗ No hay registros de inspecciones para técnicos.\n");
    }

    sb.append("\n============================================================\n");
    sb.append("                        FIN DEL INFORME\n");
    sb.append("============================================================\n");

    return sb.toString();
}

}
