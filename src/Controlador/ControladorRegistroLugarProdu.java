package Controlador;

/**
 *
 * @author Haider
 */
import Modelado.LugarProduccionDAO;
import Vista.RegistroLugarProdu;
import Vista.Predios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControladorRegistroLugarProdu implements ActionListener {

    private RegistroLugarProdu vista;
    private LugarProduccionDAO dao;

    public ControladorRegistroLugarProdu(RegistroLugarProdu vista) {
        this.vista = vista;
        this.dao = new LugarProduccionDAO(); // ← Se instancia aquí dentro

        // Escuchar botones
        this.vista.getButtonSiguiente().addActionListener(this);
        this.vista.getBtnVolver().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // ========================================
        // 🔹 BOTÓN SIGUIENTE
        // ========================================
        if (e.getSource() == vista.getButtonSiguiente()) {
            String nombreLugar = vista.getTextNombreLugar().getText().trim();
            String empresa = vista.getTextEmpresaResp().getText().trim();

            // Validación de campos vacíos
            if (nombreLugar.isEmpty() || empresa.isEmpty()) {
                JOptionPane.showMessageDialog(vista,
                        "⚠️ Todos los campos son obligatorios.",
                        "Campos vacíos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                dao.registrarLugarProduccion(nombreLugar, empresa);

                JOptionPane.showMessageDialog(vista,
                        "✅ Lugar de producción registrado correctamente 🎉",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                // Limpiar campos
                vista.getTextNombreLugar().setText("");
                vista.getTextEmpresaResp().setText("");

                // Regresar a la vista Predios
                vista.dispose();
                Predios ventanaPredios = new Predios();
                new ControladorMostrarPredios(ventanaPredios);
                ventanaPredios.setVisible(true);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista,
                        "❌ Error al registrar el lugar de producción: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }

        // ========================================
        // 🔹 BOTÓN VOLVER
        // ========================================
        if (e.getSource() == vista.getBtnVolver()) {
            vista.dispose();
            Predios ventanaPredios = new Predios();
            new ControladorMostrarPredios(ventanaPredios);
            ventanaPredios.setVisible(true);
        }
    }
}
