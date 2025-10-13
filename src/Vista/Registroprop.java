package Vista;

/**
 *
 * @author Haider
 */
import Controlador.ControladorRegistro;
import javax.swing.JOptionPane;  
import Modelado.PropietarioDAO;
public class Registroprop extends javax.swing.JFrame {

    
    public Registroprop() {
        initComponents();
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Text_Numdocprop = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Text_Nomprop = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Text_Telefonoprop = new javax.swing.JTextField();
        Text_Correoprop = new javax.swing.JTextField();
        Button_siguiente = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        TXT_contrasena = new javax.swing.JTextField();
        BtnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setBackground(new java.awt.Color(255, 204, 204));
        jLabel1.setFont(new java.awt.Font("Serif", 1, 48)); // NOI18N
        jLabel1.setText("Registro Dueño de predio");

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel2.setText("•Número de documento(*): ");

        Text_Numdocprop.setBackground(new java.awt.Color(255, 204, 204));
        Text_Numdocprop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_NumdocpropActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel4.setText("•Nombre: ");

        Text_Nomprop.setBackground(new java.awt.Color(255, 204, 204));
        Text_Nomprop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_NompropActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel5.setText("•Teléfono(*): ");

        jLabel6.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel6.setText("•Correo electrónico(*): ");

        Text_Telefonoprop.setBackground(new java.awt.Color(255, 204, 204));
        Text_Telefonoprop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_TelefonopropActionPerformed(evt);
            }
        });

        Text_Correoprop.setBackground(new java.awt.Color(255, 204, 204));

        Button_siguiente.setBackground(new java.awt.Color(255, 102, 102));
        Button_siguiente.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        Button_siguiente.setText("Siguiente");
        Button_siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_siguienteActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel7.setText("•Contraseña(*): ");

        TXT_contrasena.setBackground(new java.awt.Color(255, 204, 204));

        BtnVolver.setBackground(new java.awt.Color(255, 102, 102));
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
            .addGroup(layout.createSequentialGroup()
                .addGap(439, 439, 439)
                .addComponent(Button_siguiente)
                .addContainerGap(489, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2)
                            .addComponent(Text_Nomprop, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                            .addComponent(Text_Numdocprop)
                            .addComponent(Text_Correoprop))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 279, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(Text_Telefonoprop, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                            .addComponent(TXT_contrasena))
                        .addContainerGap(173, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnVolver)
                        .addGap(77, 77, 77)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(BtnVolver))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Text_Numdocprop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Text_Telefonoprop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Text_Nomprop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXT_contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(Text_Correoprop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(Button_siguiente)
                .addGap(112, 112, 112))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Text_NumdocpropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_NumdocpropActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text_NumdocpropActionPerformed

    private void Text_NompropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_NompropActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text_NompropActionPerformed

    private void Text_TelefonopropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_TelefonopropActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text_TelefonopropActionPerformed

    private void Button_siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_siguienteActionPerformed

    }//GEN-LAST:event_Button_siguienteActionPerformed

    private void BtnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVolverActionPerformed

    }//GEN-LAST:event_BtnVolverActionPerformed

    public javax.swing.JButton getButtonSiguiente() {
        return Button_siguiente;
    }

    public javax.swing.JButton getBtnVolver() {
        return BtnVolver;
    }

    public javax.swing.JTextField getTextNumdocprop() {
        return Text_Numdocprop;
    }

    public javax.swing.JTextField getTextNomprop() {
        return Text_Nomprop;
    }

    public javax.swing.JTextField getTextTelefonoprop() {
        return Text_Telefonoprop;
    }

    public javax.swing.JTextField getTextCorreoprop() {
        return Text_Correoprop;
    }

public javax.swing.JTextField getTXTContrasena() {
    return TXT_contrasena;
}

    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registroprop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registroprop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registroprop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registroprop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registroprop().setVisible(true);
            }
        });}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnVolver;
    private javax.swing.JButton Button_siguiente;
    private javax.swing.JTextField TXT_contrasena;
    private javax.swing.JTextField Text_Correoprop;
    private javax.swing.JTextField Text_Nomprop;
    private javax.swing.JTextField Text_Numdocprop;
    private javax.swing.JTextField Text_Telefonoprop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
