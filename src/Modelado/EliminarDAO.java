package Modelado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EliminarDAO {

    // ===========================================================
    // 🧨 ELIMINAR TÉCNICO
    // ===========================================================
    public boolean eliminarTecnico(int idTecnico, int idCorreo, int idTelefono) {
        String sqlDeleteTecnico  = "DELETE FROM TECNICO WHERE IDENTIFICACION = ?";
        String sqlDeleteCorreo   = "DELETE FROM CORREO WHERE ID_CORREO = ?";
        String sqlDeleteTelefono = "DELETE FROM TELEFONO WHERE ID_TELEFONO = ?";

        try (Connection conn = CConexion.getConnectionPorUsuario("ADMINISTRADOR", "ADMINISTRADOR")) {
            conn.setAutoCommit(false);

            try (PreparedStatement psTec = conn.prepareStatement(sqlDeleteTecnico)) {
                psTec.setInt(1, idTecnico);
                psTec.executeUpdate();
            }

            try (PreparedStatement psCorreo = conn.prepareStatement(sqlDeleteCorreo)) {
                psCorreo.setInt(1, idCorreo);
                psCorreo.executeUpdate();
            }

            try (PreparedStatement psTel = conn.prepareStatement(sqlDeleteTelefono)) {
                psTel.setInt(1, idTelefono);
                psTel.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ===========================================================
    // 🧨 ELIMINAR PRODUCTOR
    // ===========================================================
    public boolean eliminarProductor(int idProductor, int idCorreo, int idTelefono) {
        String sqlDeleteLotes    = "DELETE FROM LOTE WHERE IDCULTIVO = ?";
        String sqlDeletePredios  = "DELETE FROM PREDIO WHERE NUMERO_DOC_PRODUCTOR = ?";
        String sqlDeleteProductor = "DELETE FROM PRODUCTOR WHERE NUMERODOCUMENTO = ?";
        String sqlDeleteCorreo    = "DELETE FROM CORREO WHERE ID_CORREO = ?";
        String sqlDeleteTelefono  = "DELETE FROM TELEFONO WHERE ID_TELEFONO = ?";

        try (Connection conn = CConexion.getConnectionPorUsuario("ADMINISTRADOR", "ADMINISTRADOR")) {
            conn.setAutoCommit(false);

            // 1️⃣ Eliminar lotes del productor
            try (PreparedStatement psLote = conn.prepareStatement(sqlDeleteLotes)) {
                psLote.setInt(1, idProductor);
                psLote.executeUpdate();
            }

            // 2️⃣ Eliminar predios del productor
            try (PreparedStatement psPredio = conn.prepareStatement(sqlDeletePredios)) {
                psPredio.setInt(1, idProductor);
                psPredio.executeUpdate();
            }

            // 3️⃣ Eliminar productor
            try (PreparedStatement psProd = conn.prepareStatement(sqlDeleteProductor)) {
                psProd.setInt(1, idProductor);
                psProd.executeUpdate();
            }

            // 4️⃣ Eliminar correo
            try (PreparedStatement psCorreo = conn.prepareStatement(sqlDeleteCorreo)) {
                psCorreo.setInt(1, idCorreo);
                psCorreo.executeUpdate();
            }

            // 5️⃣ Eliminar teléfono
            try (PreparedStatement psTel = conn.prepareStatement(sqlDeleteTelefono)) {
                psTel.setInt(1, idTelefono);
                psTel.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ===========================================================
    // 🧨 ELIMINAR PROPIETARIO
    // ===========================================================
    public boolean eliminarPropietario(int idPropietario) {
        String sqlDeleteLotes   = "DELETE FROM LOTE WHERE ID_LUGARPROD IN (SELECT NUM_REGISTRO_ICA FROM LUGAR_PRODUCCION WHERE EMP_RESPONSABLE = ?)";
        String sqlDeletePredios = "DELETE FROM PREDIO WHERE NUMERO_DOC_PROPIETARIO = ?";
        String sqlDeleteProp    = "DELETE FROM PROPIETARIO WHERE NUMERODOCUMENTO = ?";

        try (Connection conn = CConexion.getConnectionPorUsuario("ADMINISTRADOR", "ADMINISTRADOR")) {
            conn.setAutoCommit(false);

            // 1️⃣ Eliminar lotes asociados a los predios del propietario
            try (PreparedStatement psLote = conn.prepareStatement(sqlDeleteLotes)) {
                psLote.setInt(1, idPropietario);
                psLote.executeUpdate();
            }

            // 2️⃣ Eliminar predios del propietario
            try (PreparedStatement psPredio = conn.prepareStatement(sqlDeletePredios)) {
                psPredio.setInt(1, idPropietario);
                psPredio.executeUpdate();
            }

            // 3️⃣ Eliminar propietario
            try (PreparedStatement psProp = conn.prepareStatement(sqlDeleteProp)) {
                psProp.setInt(1, idPropietario);
                psProp.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
