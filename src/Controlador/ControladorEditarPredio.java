package Controlador;

import Modelado.Predio;
import Modelado.PredioDAO;
import Vista.EditarPredio;
import Vista.AdministrarPredios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControladorEditarPredio {

    private final EditarPredio vista;
    private final PredioDAO dao;
    private final AdministrarPredios vistaAnterior;
    private int propietarioOriginal;   // <-- EL DUEÑO REAL (NO CAMBIA)

    private int numRegistroIca; // ID del predio

    public ControladorEditarPredio(
            EditarPredio vista,
            PredioDAO dao,
            AdministrarPredios vistaAnterior,
            int numRegistroIca) {

        this.vista = vista;
        this.dao = dao;
        this.vistaAnterior = vistaAnterior;
        this.numRegistroIca = numRegistroIca;

        cargarCombos();        // primero combos
        cargarDatosPredio();  // luego datos del predio
        iniciarEventos();
    }

    // ------------------------------------------------------------
    //      CARGAR COMBOS
    // ------------------------------------------------------------
    private void cargarCombos() {

        // DEPARTAMENTOS
        vista.getcmbEditarDepartamento().removeAllItems();
        for (String dep : dao.obtenerDepartamentos()) {
            vista.getcmbEditarDepartamento().addItem(dep);
        }

        cargarMunicipiosSegunDepto();

        // PRODUCTORES
        vista.getcmbEditarProdutor().removeAllItems();
        for (String prod : dao.obtenerListaCedulaNombre()) {
            vista.getcmbEditarProdutor().addItem(prod);
        }

        // LUGARES DE PRODUCCIÓN
        vista.getcmbEditarLugarProdu().removeAllItems();
        for (String lugar : dao.obtenerLugaresProduccion()) {
            vista.getcmbEditarLugarProdu().addItem(lugar);
        }
    }

    // ------------------------------------------------------------
    //      CARGAR DATOS DEL PREDIO
    // ------------------------------------------------------------
    private void cargarDatosPredio() {

        Predio p = dao.obtenerPredioPorId(numRegistroIca);

        if (p == null) {
            JOptionPane.showMessageDialog(vista,
                "No se pudo cargar la información del predio.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 🔥 GUARDAR EL PROPIETARIO ACTUAL (NO SE PUEDE CAMBIAR)
        propietarioOriginal = p.getNumeroDocProp();

        // TEXTO
        vista.gettxtEditarnombre().setText(p.getNombre());
        vista.gettxtEditarVereda().setText(p.getVereda());

        // COMBOS
        vista.getcmbEditarDepartamento().setSelectedItem(p.getDepartamento());
        cargarMunicipiosSegunDepto();
        vista.getcmbEditarMunicipio().setSelectedItem(p.getMunicipio());

        // PRODUCTOR → buscar "cedula - nombre"
        seleccionarEnCombo(vista.getcmbEditarProdutor(), p.getNumeroDocProductor());

        // LUGAR → buscar "id - nombre"
        seleccionarEnCombo(vista.getcmbEditarLugarProdu(), p.getNumeroRegIca());
    }

    // Selecciona un item basado en "id"
    private void seleccionarEnCombo(javax.swing.JComboBox combo, Integer id) {
        if (id == null) return;

        String idStr = String.valueOf(id);

        for (int i = 0; i < combo.getItemCount(); i++) {
            String item = combo.getItemAt(i).toString();
            if (item.startsWith(idStr + " ")) { // coincide con "123 - Lugar"
                combo.setSelectedIndex(i);
                return;
            }
        }
    }

    // ------------------------------------------------------------
    //      EVENTOS
    // ------------------------------------------------------------
    private void iniciarEventos() {

        vista.getBtnRegresar().addActionListener(e -> {
            vista.dispose();
            vistaAnterior.setVisible(true);
        });

        vista.getcmbEditarDepartamento().addActionListener(e -> {
            cargarMunicipiosSegunDepto();
        });

        vista.getBtnGuardarCambios().addActionListener(e -> {
            guardarCambios();
        });
    }

    private void cargarMunicipiosSegunDepto() {
        String departamento = (String) vista.getcmbEditarDepartamento().getSelectedItem();
        if (departamento == null) return;

        vista.getcmbEditarMunicipio().removeAllItems();

        for (String mun : dao.obtenerMunicipios(departamento)) {
            vista.getcmbEditarMunicipio().addItem(mun);
        }
    }

    // ------------------------------------------------------------
    //      GUARDAR CAMBIOS EN BD
    // ------------------------------------------------------------
    private void guardarCambios() {

        String nombre = vista.gettxtEditarnombre().getText().trim();
        String vereda = vista.gettxtEditarVereda().getText().trim();
        String departamento = vista.getcmbEditarDepartamento().getSelectedItem().toString();
        String municipio = vista.getcmbEditarMunicipio().getSelectedItem().toString();
        String productor = vista.getcmbEditarProdutor().getSelectedItem().toString();
        String lugar = vista.getcmbEditarLugarProdu().getSelectedItem().toString();

        if (nombre.isEmpty() || vereda.isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                "Por favor completa todos los campos obligatorios.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Predio p = new Predio();
        p.setNumRegistroIca(numRegistroIca);
        p.setNombre(nombre);
        p.setVereda(vereda);
        p.setDepartamento(departamento);
        p.setMunicipio(municipio);

        // PRODUCTOR
        String cedula = productor.split(" - ")[0];
        p.setNumeroDocProductor(Integer.parseInt(cedula));

        // LUGAR
        String idLugar = lugar.split(" - ")[0];
        p.setNumeroRegIca(Integer.parseInt(idLugar));

        // PROPIETARIO (NO CAMBIA)
        p.setNumeroDocProp(propietarioOriginal);

        boolean ok = dao.actualizarPredio(p);

        if (ok) {
            JOptionPane.showMessageDialog(vista,
                "¡Cambios guardados correctamente! 🎉",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

            vista.dispose();
            vistaAnterior.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(vista,
                "No se pudo actualizar el predio.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


