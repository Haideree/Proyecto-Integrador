package Controlador;

import Modelado.EliminarDAO;
import Modelado.RegistrotecDAO;
import Modelado.Tecnico;
import Vista.Registrotec;
import Vista.AdminMenu;
import Vista.GestionTecnicos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ControladorGestionTec implements ActionListener {

    private final GestionTecnicos vista;

    public ControladorGestionTec(GestionTecnicos vista) {
        this.vista = vista;

        this.vista.getBtnAgregar().addActionListener(this);
        this.vista.getBtnVolver().addActionListener(this);
        this.vista.getBtnEditar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
        this.vista.getBtnActualizar().addActionListener(this);

        mostrarTecnicos();
    }
        
    private void mostrarTecnicos() {
        try {
            RegistrotecDAO dao = new RegistrotecDAO();
            List<Tecnico> lista = dao.obtenerTecnicos();
            
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Documento");
            modelo.addColumn("Tarjeta Profesional");
            modelo.addColumn("Nombre");
            modelo.addColumn("Correo");
            modelo.addColumn("Teléfono");
            modelo.addColumn("Contraseña");
            modelo.addColumn("Tipo Técnico");

            // Los IDs (se ocultarán)
            modelo.addColumn("ID_CORREO");
            modelo.addColumn("ID_TELEFONO");
            
            for (Tecnico t : lista) {
                modelo.addRow(new Object[]{
                    t.getIdentificacion(),
                    t.getTarjetapro(),
                    t.getNombre(),
                    t.getCorreo(),
                    t.getTelefono(),
                    t.getContrasena(),
                    t.getTipoTecnico(),
                    t.getIdCorreo(),
                    t.getIdTelefono()
                });
            }

            vista.getTablaTecnicos().setModel(modelo);

            // Ocultar columnas ID_CORREO y ID_TELEFONO
            vista.getTablaTecnicos().getColumnModel().getColumn(7).setMinWidth(0);
            vista.getTablaTecnicos().getColumnModel().getColumn(7).setMaxWidth(0);
            vista.getTablaTecnicos().getColumnModel().getColumn(7).setWidth(0);

            vista.getTablaTecnicos().getColumnModel().getColumn(8).setMinWidth(0);
            vista.getTablaTecnicos().getColumnModel().getColumn(8).setMaxWidth(0);
            vista.getTablaTecnicos().getColumnModel().getColumn(8).setWidth(0);

        } catch (Exception e) {
            System.out.println("❌ Error al mostrar técnicos: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // ➕ ABRIR REGISTRO
        if (source == vista.getBtnAgregar()) {
            Registrotec regTec = new Registrotec();
            new ControladorRegistroTecnico(regTec);
            regTec.setVisible(true);
            regTec.setLocationRelativeTo(null);
            vista.dispose();
        }

        // ⮜ VOLVER AL MENÚ
        else if (source == vista.getBtnVolver()) {
            AdminMenu menu = new AdminMenu();
            new ControladorMenuAdministrador(menu);
            menu.setVisible(true);
            menu.setLocationRelativeTo(null);
            vista.dispose();
        }

        // 📝 EDITAR
        else if (source == vista.getBtnEditar()) {
            System.out.println("Función editar aún no implementada");
        }

        // ❌ ELIMINAR
        else if (source == vista.getBtnEliminar()) {

            int fila = vista.getTablaTecnicos().getSelectedRow();

            if (fila == -1) {
                javax.swing.JOptionPane.showMessageDialog(null, "Seleccione un técnico para eliminar.");
                return;
            }

            int documento = Integer.parseInt(vista.getTablaTecnicos().getValueAt(fila, 0).toString());
            int idCorreo = Integer.parseInt(vista.getTablaTecnicos().getValueAt(fila, 7).toString());
            int idTelefono = Integer.parseInt(vista.getTablaTecnicos().getValueAt(fila, 8).toString());

            int confirm = javax.swing.JOptionPane.showConfirmDialog(null,
                    "¿Está seguro de eliminar al técnico con documento " + documento + "?",
                    "Confirmar eliminación",
                    javax.swing.JOptionPane.YES_NO_OPTION);

            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                try {
                    EliminarDAO dao = new EliminarDAO();
                    boolean eliminado = dao.eliminarTecnico(documento, idCorreo, idTelefono);

                    if (eliminado) {
                        javax.swing.JOptionPane.showMessageDialog(null, "Técnico eliminado correctamente.");
                        mostrarTecnicos();
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(null, "No se pudo eliminar el técnico.");
                    }

                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage());
                }
            }
        }

        // 🔄 ACTUALIZAR TABLA
        else if (source == vista.getBtnActualizar()) {
            mostrarTecnicos();
        }
    }
}



 /*       // 📝 BOTÓN EDITAR
else if (source == vista.getBtnEditar()) {
    int filaSeleccionada = vista.getTablaTecnicos().getSelectedRow();

    if (filaSeleccionada == -1) {
        javax.swing.JOptionPane.showMessageDialog(null, "Seleccione un técnico para editar.");
    } else {
        // Obtener datos de la fila
        String documento = vista.getTablaTecnicos().getValueAt(filaSeleccionada, 0).toString();
        String tarjeta = vista.getTablaTecnicos().getValueAt(filaSeleccionada, 1).toString();
        String nombre = vista.getTablaTecnicos().getValueAt(filaSeleccionada, 2).toString();
        String correo = vista.getTablaTecnicos().getValueAt(filaSeleccionada, 3).toString();
        String telefono = vista.getTablaTecnicos().getValueAt(filaSeleccionada, 4).toString();
        String contrasena = vista.getTablaTecnicos().getValueAt(filaSeleccionada, 5).toString();
        String tipo = vista.getTablaTecnicos().getValueAt(filaSeleccionada, 6).toString();

        // Abre la ventana de registro con los datos precargados
        Registrotec regTec = new Registrotec();
        regTec.getTxtDocumento().setText(documento);
        regTec.getTxtTarjetaPro().setText(tarjeta);
        regTec.getTxtNombre().setText(nombre);
        regTec.getTxtCorreo().setText(correo);
        regTec.getTxtTelefono().setText(telefono);
        regTec.getTxtContrasena().setText(contrasena);
        regTec.getComboTipoTec().setSelectedItem(tipo);

        // Bloquear campo de documento para evitar modificarlo
        regTec.getTxtDocumento().setEnabled(false);

        new ControladorRegistroTecnico(regTec);
        regTec.setVisible(true);
        regTec.setLocationRelativeTo(null);
        vista.dispose();
    }
}
*/
    


    

