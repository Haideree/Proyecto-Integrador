package Vista;
/**
 *
 * @author Usuario
 */
import Controlador.ControladorRegistro;
import Modelado.CConexion;   
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import Vista.Registrotec;
import Vista.Registroprop;


public class Registro extends javax.swing.JPanel {
    public Registro() {
        initComponents();
        javax.swing.ButtonGroup grupoUsuarios = new javax.swing.ButtonGroup();
        grupoUsuarios.add(Button_Tecnico);
        grupoUsuarios.add(Button_Dueno);
        grupoUsuarios.add(Button_Productor);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondo = new javax.swing.JPanel();
        Icono1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Icon = new javax.swing.JLabel();
        Titulo = new javax.swing.JLabel();
        Texto = new javax.swing.JLabel();
        Button_Tecnico = new javax.swing.JRadioButton();
        Button_Productor = new javax.swing.JRadioButton();
        Button_Dueno = new javax.swing.JRadioButton();
        Button_Siguiente = new javax.swing.JButton();
        BtnVolver = new javax.swing.JButton();

        fondo.setBackground(new java.awt.Color(255, 255, 255));
        fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Icono1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Icono1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/icono.png"))); // NOI18N
        fondo.add(Icono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 290, 160, 140));

        jPanel1.setBackground(new java.awt.Color(0, 99, 17));
        jPanel1.setForeground(new java.awt.Color(0, 99, 17));

        Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Logo109.png"))); // NOI18N

        Titulo.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 255, 255));
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Titulo.setText("Registro de personal");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addComponent(Icon))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fondo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 110));

        Texto.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        Texto.setText("- Seleccione el tipo de usuario a crear:");
        fondo.add(Texto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 420, 40));

        Button_Tecnico.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        Button_Tecnico.setSelected(true);
        Button_Tecnico.setText("Asistente tecnico");
        Button_Tecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_TecnicoActionPerformed(evt);
            }
        });
        fondo.add(Button_Tecnico, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 200, 30));

        Button_Productor.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        Button_Productor.setText("Productor");
        Button_Productor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_ProductorActionPerformed(evt);
            }
        });
        fondo.add(Button_Productor, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 140, 30));

        Button_Dueno.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        Button_Dueno.setText("Dueño de Predio");
        Button_Dueno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_DuenoActionPerformed(evt);
            }
        });
        fondo.add(Button_Dueno, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 180, 30));

        Button_Siguiente.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Button_Siguiente.setForeground(new java.awt.Color(0, 99, 17));
        Button_Siguiente.setText("Siguiente");
        Button_Siguiente.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(55, 165, 87), 1, true));
        Button_Siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_SiguienteActionPerformed(evt);
            }
        });
        fondo.add(Button_Siguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, 130, 30));

        BtnVolver.setBackground(new java.awt.Color(55, 165, 87));
        BtnVolver.setForeground(new java.awt.Color(255, 255, 255));
        BtnVolver.setText("⮜ REGRESAR");
        BtnVolver.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        BtnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVolverActionPerformed(evt);
            }
        });
        fondo.add(BtnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 80, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Button_TecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_TecnicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_TecnicoActionPerformed

    private void Button_ProductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_ProductorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_ProductorActionPerformed

    private void Button_DuenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_DuenoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_DuenoActionPerformed

    private void Button_SiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_SiguienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_SiguienteActionPerformed

    private void BtnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVolverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnVolverActionPerformed

// Getters para que el controlador acceda a los botones
    public javax.swing.JButton getButtonSiguiente() {
        return Button_Siguiente;
    }

    public javax.swing.JButton getBtnVolver() {
        return BtnVolver;
    }

    public javax.swing.JRadioButton getButtonTecnico() {
        return Button_Tecnico;
    }

    public javax.swing.JRadioButton getButtonDueno() {
        return Button_Dueno;
    }

    public javax.swing.JRadioButton getButtonProductor() {
        return Button_Productor;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(() -> {
        Registro vista = new Registro();
        Controlador.ControladorRegistro controlador = new Controlador.ControladorRegistro(vista);
        vista.setVisible(true);
    });
}  
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnVolver;
    private javax.swing.JRadioButton Button_Dueno;
    private javax.swing.JRadioButton Button_Productor;
    private javax.swing.JButton Button_Siguiente;
    private javax.swing.JRadioButton Button_Tecnico;
    private javax.swing.JLabel Icon;
    private javax.swing.JLabel Icono1;
    private javax.swing.JLabel Texto;
    private javax.swing.JLabel Titulo;
    private javax.swing.JPanel fondo;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
