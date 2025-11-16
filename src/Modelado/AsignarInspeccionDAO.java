package Modelado;

/**
 *
 * @author Haider
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsignarInspeccionDAO {

    // 🔹 Listar técnicos con solicitudes pendientes (o sin asignar)
    public List<Tecnico> listarTecnicos() throws SQLException {
        List<Tecnico> lista = new ArrayList<>();
        String sql = "SELECT IDENTIFICACION, TARJETAPRO, NOMBRE, IDCORREO, IDTELEF, CONTRASENA, TIPO_TECNICO " +
                     "FROM TECNICO ORDER BY NOMBRE";

        try (Connection conn = CConexion.getConnectionPorUsuario("ADMINISTRADOR", "ADMINISTRADOR");
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Tecnico tec = new Tecnico(
                    rs.getInt("IDENTIFICACION"),
                    rs.getLong("TARJETAPRO"),
                    rs.getString("NOMBRE"),
                    "", // telefono
                    "", // correo
                    rs.getString("CONTRASENA"),
                    rs.getString("TIPO_TECNICO")
                );
                tec.setIdCorreo(rs.getInt("IDCORREO"));
                tec.setIdTelefono(rs.getInt("IDTELEF"));
                lista.add(tec);
            }
        }
        return lista;
    }

    // 🔹 Listar lotes "Sin aprobar"
    public List<Lote> listarLotesSinAprobar() throws SQLException {
    List<Lote> lista = new ArrayList<>();
    String sql = "SELECT NUM_LOTE, NOMBRE_LOTE, AREA, ESTADO FROM LOTE WHERE ESTADO = 'SIN APROBAR'";

    try (Connection conn = CConexion.getConnectionPorUsuario("ADMINISTRADOR", "ADMINISTRADOR");
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Lote lote = new Lote();
            lote.setNumLote(rs.getInt("NUM_LOTE"));
            lote.setNombreLote(rs.getString("NOMBRE_LOTE"));
            lote.setArea(rs.getDouble("AREA"));
            lote.setEstado(rs.getString("ESTADO"));
            lista.add(lote);
        }
    }
    return lista;
}


    
    // 🔹 Asignar solicitud de inspección evitando duplicados
public int asignarInspeccionValidada(int numLote, int idTecnico) {

    String validarLoteSql =
        "SELECT COUNT(*) FROM SOLICITUD_INSPECCION " +
        "WHERE NUM_LOTE = ? AND ESTADO = 'Pendiente'";

    String validarTecnicoSql =
        "SELECT COUNT(*) FROM SOLICITUD_INSPECCION " +
        "WHERE NUM_LOTE = ? AND ID_TECNICO = ? " +
        "AND ESTADO = 'Pendiente'";

    String insertSql =
        "INSERT INTO SOLICITUD_INSPECCION " +
        "(NUM_LOTE, ID_TECNICO, FECHA_ASIGNACION, ESTADO) " +
        "VALUES (?, ?, SYSDATE, 'Pendiente')";

    try (Connection conn = CConexion.getConnectionPorUsuario("ADMINISTRADOR", "ADMINISTRADOR")) {

        // 1) Validar si el lote ya está asignado a otro técnico
        PreparedStatement ps1 = conn.prepareStatement(validarLoteSql);
        ps1.setInt(1, numLote);
        ResultSet rs1 = ps1.executeQuery();
        rs1.next();
        if (rs1.getInt(1) > 0) {
            return 1;  // lote ocupado
        }

        // 2) Validar si el técnico ya tiene este lote asignado
        PreparedStatement ps2 = conn.prepareStatement(validarTecnicoSql);
        ps2.setInt(1, numLote);
        ps2.setInt(2, idTecnico);
        ResultSet rs2 = ps2.executeQuery();
        rs2.next();
        if (rs2.getInt(1) > 0) {
            return 2;  // técnico ya tiene lote
        }

        // 3) Insertar nueva solicitud
        PreparedStatement ps3 = conn.prepareStatement(insertSql);
        ps3.setInt(1, numLote);
        ps3.setInt(2, idTecnico);
        int filas = ps3.executeUpdate();

        return filas > 0 ? 0 : 3;

    } catch (SQLException ex) {
        ex.printStackTrace();
        return 3; // error SQL
    }
}




}
