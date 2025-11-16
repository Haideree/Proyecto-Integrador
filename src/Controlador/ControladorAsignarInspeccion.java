package Controlador;

/**
 *
 * @author Haider
 */

import java.sql.Connection;
import Modelado.AsignarInspeccionDAO;
import Vista.AsignarTecnico;
import Vista.AdminMenu;
import Modelado.Lote;
import Modelado.Tecnico;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class ControladorAsignarInspeccion implements ActionListener {
    
    private Connection conexion;
    private final AsignarTecnico vista;
    private final AsignarInspeccionDAO dao;

    public ControladorAsignarInspeccion(AsignarTecnico vista, Connection conexion) {
        this.vista = vista;
        this.dao = new AsignarInspeccionDAO();
        this.conexion=conexion;
        // Botón asignar
        vista.getBtnAsignar().addActionListener(this);
        vista.getBtnVolver().addActionListener(this);


        // Cargar tablas
        cargarLotes();
        cargarTecnicos();
    }

    private void cargarLotes() {
    try {
        List<Lote> lotes = dao.listarLotesSinAprobar();
        DefaultTableModel modelo = (DefaultTableModel) vista.getTablaLotesPendientes().getModel();
        modelo.setRowCount(0);

        for (Lote l : lotes) {
            modelo.addRow(new Object[]{
                l.getNumLote(),
                l.getNombreLote(),
                l.getArea(),     // 🟩 Agregado
                l.getEstado()
            });
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    private void cargarTecnicos() {
        try {
            List<Tecnico> tecnicos = dao.listarTecnicos();
            DefaultTableModel modelo = (DefaultTableModel) vista.getTablaTecnicos().getModel();
            modelo.setRowCount(0);

            for (Tecnico t : tecnicos) {
                modelo.addRow(new Object[]{t.getIdentificacion(), t.getNombre(), t.getTipoTecnico()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
@Override
public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();

    if (source == vista.getBtnAsignar()) {

        int filaLote = vista.getTablaLotesPendientes().getSelectedRow();
        int filaTec = vista.getTablaTecnicos().getSelectedRow();

        if (filaLote == -1 || filaTec == -1) {
            JOptionPane.showMessageDialog(vista, 
                    "Seleccione un lote y un técnico.");
            return;
        }

        int numLote = Integer.parseInt(
                vista.getTablaLotesPendientes().getValueAt(filaLote, 0).toString());
        int idTec = Integer.parseInt(
                vista.getTablaTecnicos().getValueAt(filaTec, 0).toString());

        // ⚠ Nuevo método que retorna códigos de estado
        int resultado = dao.asignarInspeccionValidada(numLote, idTec);

        switch (resultado) {

            case 1:
                JOptionPane.showMessageDialog(vista, 
                        "❌ El lote ya está asignado a otro técnico.\n" +
                        "Solo puede haber una solicitud pendiente por lote.");
                break;

            case 2:
                JOptionPane.showMessageDialog(vista, 
                        "❌ El técnico ya tiene este lote asignado.\n" +
                        "No se puede asignar el mismo lote dos veces al mismo técnico.");
                break;

            case 3:
                JOptionPane.showMessageDialog(vista, 
                        "❌ Error al registrar la asignación.\n" +
                        "Verifique la conexión o contacte al administrador.");
                break;

            case 0:
                JOptionPane.showMessageDialog(vista, 
                        "✅ Inspección asignada correctamente.");
                cargarLotes();
                break;
        }
    }
    else if (source == vista.getBtnVolver()) {
        AdminMenu menu = new AdminMenu(conexion);
        new ControladorMenuAdministrador(menu, conexion);
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
        vista.dispose();
    }
}

}
