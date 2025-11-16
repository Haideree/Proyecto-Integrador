package Controlador;

import Modelado.InformeDAO;
import java.sql.Connection;
import Modelado.MostrarLugaresDAO;
import Vista.MenuProductor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class ControladorMostrarLugares {

    private final MenuProductor vista;
    private final MostrarLugaresDAO dao;
    private final Connection conexion;

    public ControladorMostrarLugares(MenuProductor vista, Connection conexionRol) {
        this.vista = vista;
        this.conexion = conexionRol;
        this.dao = new MostrarLugaresDAO(conexionRol);
        
        this.vista.getBtnInforme().addActionListener(e -> generarInforme() );
        this.vista.getBtnConsultar().addActionListener(e -> mostrarLugares());
        this.vista.getBtnEliminar().addActionListener(e -> eliminarLugar());
        this.vista.getBtnEditar().addActionListener(e -> editarLugar());
    }

    public void mostrarLugares() {
        cargarTabla();
    }

    private void cargarTabla() {
        String[] columnas = {
            "N° Registro ICA",
            "Nombre del Lugar",
            "Empresa Responsable"
        };

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };

        List<Object[]> lista = dao.listarLugares();
        for (Object[] fila : lista) modelo.addRow(fila);

        vista.getTablaPredios().setModel(modelo);
    }

    // 🔥 ELIMINAR
    private void eliminarLugar() {
        int fila = vista.getTablaPredios().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "⚠ Selecciona un lugar para eliminar.");
            return;
        }

        int numReg = (int) vista.getTablaPredios().getValueAt(fila, 0);

        int confirm = JOptionPane.showConfirmDialog(
                vista,
                "¿Eliminar el lugar con registro " + numReg + "?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            if (dao.eliminarLugar(numReg)) {
                JOptionPane.showMessageDialog(vista, "✔ Lugar eliminado.");
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "❌ No se pudo eliminar.");
            }
        }
    }

    // 🔥 EDITAR (abre un mini formulario)
    private void editarLugar() {
        int fila = vista.getTablaPredios().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "⚠ Selecciona un lugar para editar.");
            return;
        }

        int numReg = (int) vista.getTablaPredios().getValueAt(fila, 0);
        String nombre = (String) vista.getTablaPredios().getValueAt(fila, 1);
        String empresa = (String) vista.getTablaPredios().getValueAt(fila, 2);

        String nuevoNombre = JOptionPane.showInputDialog(vista, "Nuevo nombre:", nombre);
        String nuevaEmpresa = JOptionPane.showInputDialog(vista, "Nueva empresa:", empresa);

        if (nuevoNombre != null && nuevaEmpresa != null) {
            if (dao.actualizarLugar(numReg, nuevoNombre, nuevaEmpresa)) {
                JOptionPane.showMessageDialog(vista, "✔ Lugar actualizado.");
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "❌ No se pudo actualizar.");
            }
        }
    }
    
    // Generar Informe
private void generarInforme() {

    try {
        InformeDAO dao = new InformeDAO(conexion);
        String informe = dao.informeProductor();

        // ===== ÁREA DE TEXTO ESTILIZADA =====
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.setText(informe);
        area.setMargin(new Insets(18, 25, 18, 25));
        area.setBackground(new Color(250, 250, 250));
        area.setLineWrap(false);     // Mantiene alineación del informe
        area.setWrapStyleWord(true);

        // ===== SCROLL BONITO =====
        JScrollPane scroll = new JScrollPane(area);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ===== DIÁLOGO PROFESIONAL =====
        JDialog dialogo = new JDialog(vista, "Informe de Producción", true);
        dialogo.setSize(750, 600);
        dialogo.setLocationRelativeTo(vista);
        dialogo.setLayout(new BorderLayout());
        dialogo.setResizable(true);

        dialogo.add(scroll, BorderLayout.CENTER);
        dialogo.setVisible(true);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                vista,
                "Error al generar el informe: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}



}
