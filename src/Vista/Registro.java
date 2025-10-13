package Vista;
/**
 *
 * @author Haider
 */
import Controlador.ControladorRegistro;
import Modelado.CConexion;   
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import Vista.Registrotec;
import Vista.Registroprop;

public class Registro extends javax.swing.JFrame {
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

        Label_registro = new javax.swing.JLabel();
        Button_Siguiente = new javax.swing.JButton();
        Button_Productor = new javax.swing.JRadioButton();
        Button_Dueno = new javax.swing.JRadioButton();
        Label_registro1 = new javax.swing.JLabel();
        Button_Tecnico = new javax.swing.JRadioButton();
        BtnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Label_registro.setFont(new java.awt.Font("Berlin Sans FB", 0, 36)); // NOI18N
        Label_registro.setText("Selecciona el usuario a registrar:");

        Button_Siguiente.setBackground(new java.awt.Color(0, 204, 204));
        Button_Siguiente.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        Button_Siguiente.setText("Siguiente");
        Button_Siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_SiguienteActionPerformed(evt);
            }
        });

        Button_Productor.setBackground(new java.awt.Color(204, 255, 255));
        Button_Productor.setFont(new java.awt.Font("Berlin Sans FB", 0, 36)); // NOI18N
        Button_Productor.setText("Productor");
        Button_Productor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_ProductorActionPerformed(evt);
            }
        });

        Button_Dueno.setBackground(new java.awt.Color(255, 204, 204));
        Button_Dueno.setFont(new java.awt.Font("Berlin Sans FB", 0, 36)); // NOI18N
        Button_Dueno.setText("Dueño de predio");
        Button_Dueno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_DuenoActionPerformed(evt);
            }
        });

        Label_registro1.setFont(new java.awt.Font("Serif", 1, 48)); // NOI18N
        Label_registro1.setText("Registrar un usuario");

        Button_Tecnico.setBackground(new java.awt.Color(204, 255, 204));
        Button_Tecnico.setFont(new java.awt.Font("Berlin Sans FB", 0, 36)); // NOI18N
        Button_Tecnico.setText("Técnico");
        Button_Tecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_TecnicoActionPerformed(evt);
            }
        });

        BtnVolver.setBackground(new java.awt.Color(0, 204, 204));
        BtnVolver.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        BtnVolver.setText("<-- Volver");
        BtnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(Button_Productor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                .addComponent(Button_Tecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(Button_Dueno)
                .addGap(132, 132, 132))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addComponent(BtnVolver)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Label_registro1))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(262, 262, 262)
                            .addComponent(Label_registro)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(444, 444, 444)
                        .addComponent(Button_Siguiente)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnVolver)
                    .addComponent(Label_registro1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(Label_registro, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button_Productor)
                    .addComponent(Button_Dueno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Button_Tecnico))
                .addGap(90, 90, 90)
                .addComponent(Button_Siguiente)
                .addGap(232, 232, 232))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Button_SiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_SiguienteActionPerformed

    }//GEN-LAST:event_Button_SiguienteActionPerformed

    private void Button_ProductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_ProductorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_ProductorActionPerformed

    private void Button_DuenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_DuenoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_DuenoActionPerformed

    private void Button_TecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_TecnicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_TecnicoActionPerformed

    private void BtnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVolverActionPerformed
 
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
    private javax.swing.JLabel Label_registro;
    private javax.swing.JLabel Label_registro1;
    // End of variables declaration//GEN-END:variables

}