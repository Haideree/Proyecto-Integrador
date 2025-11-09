package Controlador;

import java.sql.Connection;
import Modelado.MostrarPrediosDAO;
import Vista.Predios;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ControladorMostrarPredios {

    private final Predios vista;
    private final MostrarPrediosDAO dao;
    private final Connection conexion; // 🔹 guardamos la conexión activa

    public ControladorMostrarPredios(Predios vista, Connection conexionRol) {
        this.vista = vista;
        this.conexion = conexionRol;
        this.dao = new MostrarPrediosDAO(conexionRol);

        // Acción del botón
        this.vista.getBtnConsultar().addActionListener(e -> {
            System.out.println("✅ Botón 'Consultar un predio' presionado — Controlador activo");
            mostrarPredios();
        });
    }

    public void mostrarPredios() {
        if (conexion == null) {
            System.err.println("⚠️ No hay conexión activa con la base de datos.");
            return;
        }

        try {
            cargarTabla();
            System.out.println("✅ Datos cargados correctamente en la tabla.");
        } catch (Exception ex) {
            System.err.println("💥 Error al mostrar predios: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void cargarTabla() {
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

        if (lista == null || lista.isEmpty()) {
            System.out.println("⚠️ No se encontraron registros de predios.");
        } else {
            for (Object[] fila : lista) {
                modeloTabla.addRow(fila);
            }
        }

        vista.getTablaPredios().setModel(modeloTabla);

        // Ajustar anchos
        vista.getTablaPredios().getColumnModel().getColumn(0).setPreferredWidth(100);
        vista.getTablaPredios().getColumnModel().getColumn(1).setPreferredWidth(150);
        vista.getTablaPredios().getColumnModel().getColumn(7).setPreferredWidth(150);

        System.out.println("📋 Tabla actualizada con " + (lista != null ? lista.size() : 0) + " registros.");
    }
}

