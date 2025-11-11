package Controlador;

import Modelado.CConexion;
import Modelado.LoteDAO;
import Vista.RegistroLote;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class ControladorRegistroLote {

    private final RegistroLote vista;
    private final LoteDAO dao;
    private final Connection conexion;

     public ControladorRegistroLote(RegistroLote vista, Connection conexionActiva) {
        this.vista = vista;
        this.conexion = conexionActiva;
        this.dao = new LoteDAO(conexionActiva);

        cargarCombos();

        this.vista.getbtnRegistrar().addActionListener((ActionEvent e) -> registrarLote());
    }

    private void cargarCombos() {
        try {
            // Limpiar y cargar cultivos
            vista.getcomboCultivo().removeAllItems();
            for (String cultivo : dao.obtenerCultivos()) {
                vista.getcomboCultivo().addItem(cultivo);
            }

            // Limpiar y cargar lugares
            vista.getcomboLugar().removeAllItems();
            for (String lugar : dao.obtenerLugares()) {
                vista.getcomboLugar().addItem(lugar);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista,
                    "💥 Error al cargar los datos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarLote() {
        try {
            String nombre = vista.getTxtNombre().getText().trim();
            String areaTxt = vista.getTxtArea().getText().trim();
            String cultivo = (String) vista.getcomboCultivo().getSelectedItem();
            String lugar = (String) vista.getcomboLugar().getSelectedItem();

            // Validaciones básicas
            if (nombre.isEmpty() || areaTxt.isEmpty() || cultivo == null || lugar == null) {
                JOptionPane.showMessageDialog(vista, "⚠️ Debes completar todos los campos obligatorios.");
                return;
            }

            double area = Double.parseDouble(areaTxt);

            // Registrar usando secuencia automática
            boolean exito = dao.registrarLote(area, cultivo, lugar, nombre);

            if (exito) {
                JOptionPane.showMessageDialog(vista, "✅ Lote registrado correctamente.");
                limpiarCampos();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "⚠️ Ingresa un valor numérico válido en el campo de área.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "💥 Error al registrar el lote: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        vista.getTxtNombre().setText("");
        vista.getTxtArea().setText("");
        vista.getcomboCultivo().setSelectedIndex(0);
        vista.getcomboLugar().setSelectedIndex(0);
    }
}



