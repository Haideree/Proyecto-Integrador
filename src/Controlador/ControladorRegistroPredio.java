package Controlador;

import Modelado.PredioDAO;
import Vista.AdministrarPredios;
import Vista.MenuProductor;
import Vista.RegistroPredio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.util.List;
import java.sql.Connection;

public class ControladorRegistroPredio implements ActionListener {

    private final RegistroPredio vista;
    private final PredioDAO modelo;
    private final Connection conexionActiva;
    private final int idPropietario; // <<--- AGREGADO

    public ControladorRegistroPredio(RegistroPredio vista, Connection conexionActiva, int idPropietario) {
        this.vista = vista;
        this.conexionActiva = conexionActiva;
        this.idPropietario = idPropietario; // <<--- GUARDADO
        this.modelo = new PredioDAO(conexionActiva);

        cargarCombos();

        this.vista.getBtnRegistrar().addActionListener(this);
        this.vista.getBtnVolver().addActionListener(this);}
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnRegistrar()) {
            registrarPredio();
        } else if (e.getSource() == vista.getBtnVolver()) {
            volverAPredios();
        }
    }

    // ==============================================================
    // 🔹 Cargar JComboBox con datos desde la BD
    // ==============================================================
    private void cargarCombos() {
        vista.getCmbProductor().removeAllItems();
        vista.getCmbLugarProdu().removeAllItems();
        vista.getCmbDepartamento().removeAllItems();
        vista.getCmbMunicipio().removeAllItems();

        // Productores
        for (String productor : modelo.obtenerProductores()) {
            vista.getCmbProductor().addItem(productor);
        }

        // Departamentos
        for (String departamento : modelo.obtenerDepartamentos()) {
            vista.getCmbDepartamento().addItem(departamento);
        }

        // Listener para cargar municipios dinámicamente
        vista.getCmbDepartamento().addActionListener(e -> {
            vista.getCmbMunicipio().removeAllItems();

            String departamentoSeleccionado = (String) vista.getCmbDepartamento().getSelectedItem();

            if (departamentoSeleccionado != null) {
                List<String> municipios = modelo.obtenerMunicipios(departamentoSeleccionado);
                for (String municipio : municipios) {
                    vista.getCmbMunicipio().addItem(municipio);
                }
            }
        });

        // Lugares de producción
        for (String lugar : modelo.obtenerLugaresProduccion()) {
            vista.getCmbLugarProdu().addItem(lugar);
        }
    }

    // ==============================================================
    // 🔹 Registrar predio
    // ==============================================================
    private void registrarPredio() {
    try {

        // ============================
        // 🔹 Validación de ComboBoxes
        // ============================
        
        
        if (vista.getCmbDepartamento().getSelectedItem() == null ||
    vista.getCmbMunicipio().getSelectedItem() == null ||
    vista.getCmbProductor().getSelectedItem() == null ||
    vista.getCmbLugarProdu().getSelectedItem() == null) {

    JOptionPane.showMessageDialog(null,
        "Debe seleccionar todos los campos de la lista.",
        "Error", JOptionPane.ERROR_MESSAGE);
    return;
}

        // ============================
        // 🔹 Validación de campos texto
        // ============================
        String nombre = vista.getTxtNombre().getText().trim();
        String vereda = vista.getTxtVereda().getText().trim();
        System.out.println("Nombre: '" + nombre + "'");
        System.out.println("Vereda: '" + vereda + "'");

        if (nombre.isEmpty() || vereda.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Por favor complete todos los campos.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ============================
        // 🔹 Extraer IDs reales
        // ============================
        String productorSeleccionado = vista.getCmbProductor().getSelectedItem().toString();
        String lugarSeleccionado = vista.getCmbLugarProdu().getSelectedItem().toString();

        int docProductor = Integer.parseInt(productorSeleccionado.split(" - ")[1]);
        int lugarProdICA = Integer.parseInt(lugarSeleccionado.split(" - ")[0]);
        int docPropietario = this.idPropietario;

        PredioDAO predioDAO = new PredioDAO(conexionActiva);

        boolean exito = predioDAO.insertarPredio(
                nombre,
                vista.getCmbDepartamento().getSelectedItem().toString(),
                vista.getCmbMunicipio().getSelectedItem().toString(),
                vereda,
                docProductor,
                docPropietario,
                lugarProdICA
        );

        if (exito) {
            JOptionPane.showMessageDialog(null, "✅ Predio registrado correctamente 🎉");
            limpiarCampos();
            volverAPredios();
        } else {
            JOptionPane.showMessageDialog(null,
                "❌ No se pudo registrar el predio.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null,
            "Ocurrió un error inesperado al registrar el predio.",
            "Error", JOptionPane.ERROR_MESSAGE);
    }
}


private void limpiarCampos() {
    vista.getTxtNombre().setText("");
    vista.getCmbDepartamento().setSelectedIndex(0);
    vista.getCmbMunicipio().setSelectedIndex(0);
    vista.getTxtVereda().setText("");
    vista.getCmbProductor().setSelectedIndex(0);
    vista.getCmbLugarProdu().setSelectedIndex(0);
}

    // ==============================================================
    // 🔹 Volver a la vista de predios
    // ==============================================================
    private void volverAPredios() {
        vista.dispose();
        AdministrarPredios vista = new AdministrarPredios(conexionActiva,idPropietario);
        ControladorAdministrarPredio controlador = new ControladorAdministrarPredio(vista, conexionActiva, idPropietario);

        vista.setVisible(true);
        
    }
}


