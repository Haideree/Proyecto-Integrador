package Vista;
/**
 *
 * @author Haider
 */
import Controlador.ControladorRegistroTecnico;
import Controlador.ControladorRegistro;
import javax.swing.JOptionPane; 
import Modelado.RegistrotecDAO;

public class Registrotec extends javax.swing.JFrame {
;

    public Registrotec() {
        initComponents();
        grupoTipo = new javax.swing.ButtonGroup();
        grupoTipo.add(rbtIca);
        grupoTipo.add(rbtParticular);

        // Por defecto, seleccionamos “Técnico del ICA”
        rbtIca.setSelected(true);

        // Comportamiento: activar/desactivar campo de tarjeta profesional
        rbtIca.addActionListener(e -> Text_numtarjtec.setEnabled(true));
        rbtParticular.addActionListener(e -> Text_numtarjtec.setEnabled(false));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoTipo = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Text_numdoctec = new javax.swing.JTextField();
        Text_numtarjtec = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Text_nombretec = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Text_correotec = new javax.swing.JTextField();
        Text_correoaltertec = new javax.swing.JTextField();
        Text_telefonoaltertec = new javax.swing.JTextField();
        Button_siguiente = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        TXT_contrasena = new javax.swing.JTextField();
        BtnVolver = new javax.swing.JButton();
        rbtIca = new javax.swing.JRadioButton();
        rbtParticular = new javax.swing.JRadioButton();
        Text_telefonotec = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Serif", 1, 48)); // NOI18N
        jLabel1.setText("Registro Técnico");

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel2.setText("•Número de documento(*): ");

        Text_numdoctec.setBackground(new java.awt.Color(204, 255, 204));
        Text_numdoctec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_numdoctecActionPerformed(evt);
            }
        });

        Text_numtarjtec.setBackground(new java.awt.Color(204, 255, 204));
        Text_numtarjtec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_numtarjtecActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel3.setText("•Número de tarjeta profesional(Solo aplica si es del ICA): ");

        jLabel4.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel4.setText("•Nombre(*): ");

        jLabel5.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel5.setText("•Teléfono(*): ");

        jLabel6.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel6.setText("•Contraseña(*): ");

        jLabel7.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel7.setText("•Correo electrónico alternativo (opcional):");

        Text_nombretec.setBackground(new java.awt.Color(204, 255, 204));
        Text_nombretec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_nombretecActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel8.setText("•Teléfono alternativo (opcional):");

        Text_correotec.setBackground(new java.awt.Color(204, 255, 204));

        Text_correoaltertec.setEditable(false);
        Text_correoaltertec.setBackground(new java.awt.Color(204, 255, 204));
        Text_correoaltertec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_correoaltertecActionPerformed(evt);
            }
        });

        Text_telefonoaltertec.setBackground(new java.awt.Color(204, 255, 204));
        Text_telefonoaltertec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_telefonoaltertecActionPerformed(evt);
            }
        });

        Button_siguiente.setBackground(new java.awt.Color(102, 255, 102));
        Button_siguiente.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        Button_siguiente.setText("Siguiente");
        Button_siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_siguienteActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel9.setText("•Correo electrónico(*): ");

        TXT_contrasena.setBackground(new java.awt.Color(204, 255, 204));

        BtnVolver.setBackground(new java.awt.Color(102, 255, 102));
        BtnVolver.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        BtnVolver.setText("<-- Volver");
        BtnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVolverActionPerformed(evt);
            }
        });

        rbtIca.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        rbtIca.setText("Técnico del ICA");

        rbtParticular.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        rbtParticular.setText("Técnico Particular");

        Text_telefonotec.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(448, 448, 448)
                .addComponent(Button_siguiente)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnVolver)
                        .addGap(152, 152, 152)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Text_telefonotec, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Text_numtarjtec)
                                    .addComponent(Text_numdoctec)
                                    .addComponent(jLabel5)
                                    .addComponent(Text_nombretec))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(rbtIca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rbtParticular, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TXT_contrasena)
                            .addComponent(Text_telefonoaltertec)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(Text_correotec, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 24, Short.MAX_VALUE))
                            .addComponent(Text_correoaltertec))
                        .addGap(57, 57, 57))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(BtnVolver))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Text_numdoctec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Text_correotec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtIca)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rbtParticular)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(TXT_contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Text_numtarjtec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Text_telefonoaltertec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Text_correoaltertec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Text_nombretec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(Text_telefonotec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(Button_siguiente)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Text_numdoctecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_numdoctecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text_numdoctecActionPerformed

    private void Text_numtarjtecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_numtarjtecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text_numtarjtecActionPerformed

    private void Text_nombretecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_nombretecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text_nombretecActionPerformed

    private void Button_siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_siguienteActionPerformed
    
    }//GEN-LAST:event_Button_siguienteActionPerformed

    private void Text_correoaltertecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_correoaltertecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text_correoaltertecActionPerformed

    private void Text_telefonoaltertecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_telefonoaltertecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text_telefonoaltertecActionPerformed

    private void BtnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVolverActionPerformed
   
    }//GEN-LAST:event_BtnVolverActionPerformed

    //Getters
    public javax.swing.JButton getButton_siguiente() { return Button_siguiente; }
    public javax.swing.JButton getBtnVolver() { return BtnVolver; }
    public javax.swing.JTextField getText_numdoctec() { return Text_numdoctec; }
    public javax.swing.JTextField getText_numtarjtec() { return Text_numtarjtec; }
    public javax.swing.JTextField getText_nombretec() { return Text_nombretec; }
    public javax.swing.JTextField getText_telefonotec() { return Text_telefonotec; }
    public javax.swing.JTextField getText_correotec() { return Text_correotec; }
    public javax.swing.JTextField getTXT_contrasena() { return TXT_contrasena; }
    public javax.swing.JRadioButton getRadioICA() { return rbtIca; }
    public javax.swing.JRadioButton getRadioParticular() { return rbtParticular; }

    public static void main(String args[]) {
     java.awt.EventQueue.invokeLater(() -> {
        Registrotec vista = new Registrotec(); // Creamos la vista
        new ControladorRegistroTecnico(vista); // Creamos el controlador y conectamos eventos
        vista.setVisible(true); // Mostramos la ventana
    });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnVolver;
    private javax.swing.JButton Button_siguiente;
    private javax.swing.JTextField TXT_contrasena;
    private javax.swing.JTextField Text_correoaltertec;
    private javax.swing.JTextField Text_correotec;
    private javax.swing.JTextField Text_nombretec;
    private javax.swing.JTextField Text_numdoctec;
    private javax.swing.JTextField Text_numtarjtec;
    private javax.swing.JTextField Text_telefonoaltertec;
    private javax.swing.JTextField Text_telefonotec;
    private javax.swing.ButtonGroup grupoTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton rbtIca;
    private javax.swing.JRadioButton rbtParticular;
    // End of variables declaration//GEN-END:variables
}
