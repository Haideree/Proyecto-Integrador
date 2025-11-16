package Controlador;

import Modelado.InspeccionDAO;
import Vista.RealizarInspeccionSanitaria;
import Vista.VerInspeccion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class ControladorRealizarInspeccion implements ActionListener {

    private final RealizarInspeccionSanitaria vista;
    
    private final int idSolicitud;
private final int idTecnico;   // <-- AGREGAR
private final Connection conexion;

public ControladorRealizarInspeccion(RealizarInspeccionSanitaria vista,
                                     int idSolicitud,
                                     int idTecnico,     // <-- AGREGAR
                                     Connection conexion) {

    this.vista = vista;
    this.idSolicitud = idSolicitud;
    this.idTecnico = idTecnico;   // <-- AGREGAR
    this.conexion = conexion;

    this.vista.getBtnGuardar().addActionListener(this);
    this.vista.getBtnCancelar().addActionListener(this);
}

    

    @Override
public void actionPerformed(ActionEvent e) {

    if (e.getSource() == vista.getBtnGuardar()) {

        String fecha = vista.getTxtFecha().getText();
        String observaciones = vista.getTxtObservaciones().getText();
        String resultado = vista.getComboResultado().getSelectedItem().toString();

        try {
            InspeccionDAO dao = new InspeccionDAO(conexion);

            // 🔥 NUEVO — Un solo método para todo:
            boolean ok = dao.guardarInspeccionCompleta(
                    idSolicitud,
                    fecha,
                    observaciones,
                    resultado
            );

            if (ok) {
                JOptionPane.showMessageDialog(null, 
                    "Inspección registrada correctamente.\nSolicitud marcada como realizada.\nEstado del lote actualizado.");

                // Volver a la vista de inspecciones
                VerInspeccion menu = new VerInspeccion();
                new ControladorInspeccion(menu, idTecnico, conexion);

                menu.setVisible(true);
                menu.setLocationRelativeTo(null);
                vista.dispose();

            } else {
                JOptionPane.showMessageDialog(null, 
                    "Hubo un error registrando la inspección.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, 
                "Error guardando inspección: " + ex.getMessage());
        }
    }

    if (e.getSource() == vista.getBtnCancelar()) {

        VerInspeccion menu = new VerInspeccion();
        new ControladorInspeccion(menu, idTecnico, conexion);

        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
        vista.dispose();
    }
}


}

