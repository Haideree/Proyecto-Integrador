package Controlador;

import Modelado.RegistrotecDAO;
import Modelado.Tecnico;
import Vista.Registrotec;
import Vista.AdminMenu;
import Vista.GestionTecnicos;
import Vista.Registroprod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Haider
 */

public class ControladorGestionTec implements ActionListener {

    private final GestionTecnicos vista;

    public ControladorGestionTec(GestionTecnicos vista) {
        this.vista = vista;

        // Asignar escuchas a los botones
        this.vista.getBtnAgregar().addActionListener(this);
        this.vista.getBtnVolver().addActionListener(this);
        this.vista.getBtnEditar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
        this.vista.getBtnActualizar().addActionListener(this);
    }
        
    private void mostrarTecnicos(){
        try {
            RegistrotecDAO dao = new RegistrotecDAO();
            List<Tecnico> lista = dao.obtenerTecnicos();
            
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Documento");
            modelo.addColumn("Tarjeta Profesional");
            modelo.addColumn("Nombre");
            modelo.addColumn("Correo");
            modelo.addColumn("Telefono");
            modelo.addColumn("Contraseña");
            modelo.addColumn("Tipo tecnico");
            
            for (Tecnico t : lista) {
                modelo.addRow(new Object[]{
                t.getIdentificacion(),
                t.getTarjetapro(),
                t.getNombre(),
                t.getCorreo(),
                t.getTelefono(),
                t.getContrasena(),
                t.getTipoTecnico()       
            });
            }
            vista.getTablaTecnicos().setModel(modelo);
        } catch (Exception e) {
            System.out.println("❌ Error al mostrar productores: " + e.getMessage());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // ➕ BOTÓN AGREGAR: abrir la ventana de registro
        if (source == vista.getBtnAgregar()) {
            Registrotec regTec = new Registrotec();
            new ControladorRegistroTecnico (regTec);
            regTec.setVisible(true);
            regTec.setLocationRelativeTo(null); // Centra la nueva ventana
            vista.dispose(); // Cierra la ventana actual (opcional)
        }

        // ⮜ BOTÓN VOLVER (si tienes un menú anterior)
        else if (source == vista.getBtnVolver()) {
            
            AdminMenu menu = new AdminMenu();
            new ControladorMenuAdministrador(menu);
            menu.setVisible(true);
            menu.setLocationRelativeTo(null);
            vista.dispose();
        }

        // 📝 BOTÓN EDITAR
        else if (source == vista.getBtnEditar()) {
            System.out.println("Función editar aún no implementada");
        }

        // ➖ BOTÓN ELIMINAR
        else if (source == vista.getBtnEliminar()) {
            System.out.println("Función eliminar aún no implementada");
        }

        // 🔃 BOTÓN ACTUALIZAR
        else if (source == vista.getBtnActualizar()) {
            mostrarTecnicos();
            System.out.println("✅ Tabla actualizada correctamente");
        }
    }
}

    

