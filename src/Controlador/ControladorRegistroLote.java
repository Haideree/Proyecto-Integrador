package Controlador;


import Modelado.LoteDAO;
import Vista.RegistroLote;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class ControladorRegistroLote {

    private final RegistroLote vista;
    private final LoteDAO dao;
    private final Connection conexion;
    private int idPropietario;

     public ControladorRegistroLote(RegistroLote vista, Connection conexionActiva, int idPropietario) {
        this.vista = vista;
        this.conexion = conexionActiva;
        this.dao = new LoteDAO(conexionActiva);
        this.idPropietario = idPropietario;

        cargarCombos();
        this.vista.getbtnRegresar().addActionListener((ActionEvent e)-> regresar());
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

        // =============================
        // 🔹 Validación de campos vacíos
        // =============================
        if (nombre.isEmpty() || areaTxt.isEmpty() || cultivo == null || lugar == null) {
            JOptionPane.showMessageDialog(vista,
                    "⚠️ Debes completar todos los campos obligatorios.");
            return;
        }

        // =============================
        // 🔹 Validación del nombre del lote
        // =============================

        // Evitar caracteres no permitidos
        if (!nombre.matches("[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ.-]+")) {
            JOptionPane.showMessageDialog(vista,
                    "❌ El nombre contiene caracteres no permitidos.");
            return;
        }

        // Debe tener mínimo 2 caracteres
        if (nombre.length() < 2) {
            JOptionPane.showMessageDialog(vista,
                    "❌ El nombre del lote debe tener al menos 2 caracteres.");
            return;
        }

        // Si es solo números → máximo 8 dígitos
        if (nombre.matches("\\d+") && nombre.length() > 8) {
            JOptionPane.showMessageDialog(vista,
                    "❌ Si el nombre del lote es solo numérico, debe tener máximo 8 dígitos.");
            return;
        }

        // Normalizar múltiples espacios
        nombre = nombre.replaceAll("\\s+", " ");

        // =============================
        // 🔹 Validación del área
        // =============================

        double area;
        try {
            area = Double.parseDouble(areaTxt);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista,
                    "⚠️ Ingresa un valor numérico válido en el área.");
            return;
        }

        if (area <= 0) {
            JOptionPane.showMessageDialog(vista,
                    "❌ El área debe ser mayor que cero.");
            return;
        }

        // =============================
        // 🔹 Registrar lote
        // =============================
        boolean exito = dao.registrarLote(area, cultivo, lugar, nombre);

        if (exito) {
            JOptionPane.showMessageDialog(vista,
                    "✅ Lote registrado correctamente 🎉");
            limpiarCampos();
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(vista,
                "💥 Error al registrar el lote: " + e.getMessage());
    }
}



    private void limpiarCampos() {
        vista.getTxtNombre().setText("");
        vista.getTxtArea().setText("");
        vista.getcomboCultivo().setSelectedIndex(0);
        vista.getcomboLugar().setSelectedIndex(0);
    }
    
    private void regresar(){
        Vista.AdministrarLotes menu = new Vista.AdministrarLotes(conexion,idPropietario);
        Controlador.ControladorAdministrarLotes controlador =new Controlador.ControladorAdministrarLotes(menu, conexion, idPropietario);
        menu.setVisible(true);
        vista.dispose();
    }
}



