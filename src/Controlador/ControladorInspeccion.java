package Controlador;

import java.sql.Connection;
import Vista.RealizarInspeccionSanitaria;
import Vista.VerInspeccion;
import Modelado.InspeccionDAO;
import Vista.MenuTecnico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControladorInspeccion implements ActionListener {

    private Connection conexionActiva;

    private VerInspeccion vista;
    private InspeccionDAO dao;
    private int idTecnico;

    public ControladorInspeccion(VerInspeccion vista, int idTecnico, Connection conexionActiva) {
    this.vista = vista;
    this.idTecnico = idTecnico;
    this.conexionActiva = conexionActiva;
    this.dao = new InspeccionDAO(conexionActiva); // ⬅ usa conexión real

    vista.getBtnRealizar().addActionListener(this);
    vista.getBtnActualizar().addActionListener(this);
    vista.getBtnRegresar().addActionListener(this);

    cargarSolicitudes();
}


    // Cargar solicitudes en la tabla
    private void cargarSolicitudes() {
        vista.getTablaSolicitudes().setModel(dao.listarSolicitudesParaTecnico(idTecnico));
    }

    @Override
public void actionPerformed(ActionEvent e) {

    if (e.getSource() == vista.getBtnActualizar()) {
        cargarSolicitudes();
    }

    if (e.getSource() == vista.getBtnRealizar()) {

        int fila = vista.getTablaSolicitudes().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona una solicitud");
            return;
        }

        // 🔹 Obtenemos el ID de la solicitud seleccionada
        int idSolicitud = Integer.parseInt(
            vista.getTablaSolicitudes().getValueAt(fila, 0).toString()
        );

        // 🔹 Abrimos la nueva ventana
        RealizarInspeccionSanitaria nuevaVentana = new RealizarInspeccionSanitaria(idSolicitud, conexionActiva);

        // 🔹 Creamos su controlador
        new ControladorRealizarInspeccion(nuevaVentana, idSolicitud, idTecnico, conexionActiva);

        // 🔹 Mostramos
        nuevaVentana.setVisible(true);
        nuevaVentana.setLocationRelativeTo(null);

        // 🔹 Cerramos esta ventana
        vista.dispose();
    }

    if (e.getSource() == vista.getBtnRegresar()) {
               MenuTecnico menu = new MenuTecnico(conexionActiva, idTecnico);
               new ControladorMenuTecnico(menu, idTecnico, conexionActiva);
                menu.setVisible(true);
                menu.setLocationRelativeTo(null);
                vista.dispose();
    }
    }

}
