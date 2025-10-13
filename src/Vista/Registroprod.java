package Vista;

import Controlador.ControladorRegistro;
import javax.swing.JOptionPane; 
import Modelado.ProductorDAO;
/**
 *
 * @author Haider
 */
public class Registroprod extends javax.swing.JFrame {

    public Registroprod() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Text_numdocprod = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Text_nombreprod = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Text_telefonoprod = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Text_correoprod = new javax.swing.JTextField();
        Button_siguiente = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        TXT_contrasena = new javax.swing.JTextField();
        BtnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Serif", 1, 48)); // NOI18N
        jLabel1.setText("Registro Productor");

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel2.setText("•Número de documento(*): ");

        Text_numdocprod.setBackground(new java.awt.Color(204, 255, 255));
        Text_numdocprod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_numdocprodActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel4.setText("•Nombre(*): ");

        Text_nombreprod.setBackground(new java.awt.Color(204, 255, 255));
        Text_nombreprod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_nombreprodActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel5.setText("•Teléfono(*): ");

        Text_telefonoprod.setBackground(new java.awt.Color(204, 255, 255));
        Text_telefonoprod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_telefonoprodActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel9.setText("•Correo electrónico(*): ");

        Text_correoprod.setBackground(new java.awt.Color(204, 255, 255));

        Button_siguiente.setBackground(new java.awt.Color(0, 204, 204));
        Button_siguiente.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        Button_siguiente.setText("Siguiente");
        Button_siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_siguienteActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel6.setText("•Contraseña(*): ");

        TXT_contrasena.setBackground(new java.awt.Color(204, 255, 255));

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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Text_numdocprod, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Text_nombreprod, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(Text_telefonoprod, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(255, 255, 255)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(TXT_contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Text_correoprod, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BtnVolver)
                                .addGap(111, 111, 111)
                                .addComponent(jLabel1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(451, 451, 451)
                        .addComponent(Button_siguiente)))
                .addContainerGap(209, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(BtnVolver))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Text_numdocprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Text_correoprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Text_nombreprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Text_telefonoprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(Button_siguiente)
                .addGap(165, 165, 165))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Text_numdocprodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_numdocprodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text_numdocprodActionPerformed

    private void Text_nombreprodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_nombreprodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text_nombreprodActionPerformed

    private void Text_telefonoprodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_telefonoprodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text_telefonoprodActionPerformed

    private void Button_siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_siguienteActionPerformed

    }//GEN-LAST:event_Button_siguienteActionPerformed

    private void BtnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVolverActionPerformed
       
    }//GEN-LAST:event_BtnVolverActionPerformed
    
//Getters
    public javax.swing.JButton getBtnVolver() { return BtnVolver; }
    public javax.swing.JButton getBtnSiguiente() { return Button_siguiente; }
    public javax.swing.JTextField getTxtDocumento() { return Text_numdocprod; }
    public javax.swing.JTextField getTxtNombre() { return Text_nombreprod; }
    public javax.swing.JTextField getTxtTelefono() { return Text_telefonoprod; }
    public javax.swing.JTextField getTxtCorreo() { return Text_correoprod; }
    public javax.swing.JTextField getTxtContrasena() { return TXT_contrasena; }

    // 🔹 Método rápido para mostrar mensajes
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
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
            java.util.logging.Logger.getLogger(Registroprod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registroprod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registroprod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registroprod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registroprod().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnVolver;
    private javax.swing.JButton Button_siguiente;
    private javax.swing.JTextField TXT_contrasena;
    private javax.swing.JTextField Text_correoprod;
    private javax.swing.JTextField Text_nombreprod;
    private javax.swing.JTextField Text_numdocprod;
    private javax.swing.JTextField Text_telefonoprod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables

}
