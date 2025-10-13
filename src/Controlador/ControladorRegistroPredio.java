package Controlador;

/**
 *
 * @author Haider
 */
import Modelado.RegistrarPrediosDAO;
import Vista.Predios;
import Vista.RegistroPredio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControladorRegistroPredio implements ActionListener {

    private RegistroPredio vista;
    private RegistrarPrediosDAO modelo;

    public ControladorRegistroPredio(RegistroPredio vista) {
        this.vista = vista;
        this.modelo = new RegistrarPrediosDAO();

        // Cargar combos al iniciar la vista
        cargarCombos();

        // Escuchar botones
        this.vista.getBtnRegistrar().addActionListener(this);
        this.vista.getBtnVolver().addActionListener(this);
    }

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
        vista.getCmbPropietario().removeAllItems();
        vista.getCmbLugarProdu().removeAllItems();

        // Productores
        for (String productor : modelo.obtenerProductores()) {
            vista.getCmbProductor().addItem(productor);
        }

        // Propietarios
        for (String propietario : modelo.obtenerPropietarios()) {
            vista.getCmbPropietario().addItem(propietario);
        }

        // Lugares de producción (NUM_REGISTRO_ICA - NOMBRE)
        for (String lugar : modelo.obtenerLugaresProduccion()) {
            vista.getCmbLugarProdu().addItem(lugar);
        }
    }

    // ==============================================================
    // 🔹 Registrar predio
    // ==============================================================
    private void registrarPredio() {
        try {
            String nombre = vista.getTxtNombre().getText().trim();
            String departamento = vista.getTxtDepartamento().getText().trim();
            String municipio = vista.getTxtMunicipio().getText().trim();
            String vereda = vista.getTxtVereda().getText().trim();

            String productorSeleccionado = (String) vista.getCmbProductor().getSelectedItem();
            String propietarioSeleccionado = (String) vista.getCmbPropietario().getSelectedItem();
            String lugarSeleccionado = (String) vista.getCmbLugarProdu().getSelectedItem();

            // Validaciones básicas
            if (nombre.isEmpty() || departamento.isEmpty() || municipio.isEmpty() || vereda.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (productorSeleccionado == null || propietarioSeleccionado == null || lugarSeleccionado == null) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar productor, propietario y lugar de producción.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Extraer los IDs o números de cada combo (usando formato "NOMBRE - ID")
            int idProductor = Integer.parseInt(productorSeleccionado.split(" - ")[1]);
            int idPropietario = Integer.parseInt(propietarioSeleccionado.split(" - ")[1]);
            int numRegistroIca = Integer.parseInt(lugarSeleccionado.split(" - ")[0]); // NUM_REGISTRO_ICA

            // Registrar en BD
            boolean registrado = modelo.registrarPredio(nombre, departamento, municipio, vereda, idProductor, idPropietario, numRegistroIca);

            if (registrado) {
                JOptionPane.showMessageDialog(null, "✅ Predio registrado correctamente 🎉");
                limpiarCampos();
                volverAPredios();
            } else {
                JOptionPane.showMessageDialog(null, "❌ No se pudo registrar el predio.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener los valores seleccionados.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado al registrar el predio.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ==============================================================
    // 🔹 Limpiar campos
    // ==============================================================
    private void limpiarCampos() {
        vista.getTxtNombre().setText("");
        vista.getTxtDepartamento().setText("");
        vista.getTxtMunicipio().setText("");
        vista.getTxtVereda().setText("");
        vista.getCmbProductor().setSelectedIndex(-1);
        vista.getCmbPropietario().setSelectedIndex(-1);
        vista.getCmbLugarProdu().setSelectedIndex(-1);
    }

    // ==============================================================
    // 🔹 Volver a la vista de predios
    // ==============================================================
    private void volverAPredios() {
        vista.dispose();
        Predios ventanaPredios = new Predios();
        new ControladorMostrarPredios(ventanaPredios);
        ventanaPredios.setVisible(true);
    }
}

