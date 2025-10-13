package Controlador;

/**
 *
 * @author Haider
 */
import Modelado.MostrarPrediosDAO;
import Vista.Predios;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ControladorMostrarPredios {

    private final Predios vista;
    private final MostrarPrediosDAO dao;

    public ControladorMostrarPredios(Predios vista) {
        this.vista = vista;
        this.dao = new MostrarPrediosDAO();

        // Acción del botón
        this.vista.getBtnConsultar().addActionListener(e -> {
            System.out.println("✅ Botón 'Consultar un predio' presionado — Controlador activo");
            mostrarPredios();
        });
    }

    public void mostrarPredios() {
        try {
            cargarTabla();
            System.out.println("✅ Datos cargados correctamente en la tabla.");
        } catch (Exception ex) {
            System.err.println("⚠️ Error al mostrar predios: " + ex.getMessage());
        }
    }

    private void cargarTabla() {
        // ✅ Los títulos deben coincidir en orden con la consulta del DAO
        String[] columnas = {
            "N° Registro ICA",
            "Nombre del Predio",
            "Departamento",
            "Municipio",
            "Vereda",
            "Productor",
            "Propietario",
            "Lugar de Producción"
        };

        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Object[]> lista = dao.listarPredios();

        if (lista.isEmpty()) {
            System.out.println("⚠️ No se encontraron registros de predios.");
        }

        for (Object[] fila : lista) {
            modeloTabla.addRow(fila);
        }

        vista.getTablaPredios().setModel(modeloTabla);

        // Opcional: ajustar anchos para mejor presentación
        vista.getTablaPredios().getColumnModel().getColumn(0).setPreferredWidth(100); // N° Registro
        vista.getTablaPredios().getColumnModel().getColumn(1).setPreferredWidth(150); // Nombre del Predio
        vista.getTablaPredios().getColumnModel().getColumn(7).setPreferredWidth(150); // Lugar de Producción

        System.out.println("📋 Tabla actualizada con " + lista.size() + " registros.");
    }
}

