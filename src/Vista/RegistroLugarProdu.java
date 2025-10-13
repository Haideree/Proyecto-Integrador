package Vista;

/**
 *
 * @author Haider
 */
public class RegistroLugarProdu extends javax.swing.JFrame {

   
    public RegistroLugarProdu() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Text_nombreLugar = new javax.swing.JTextField();
        Text_empresaResp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Button_siguiente = new javax.swing.JButton();
        BtnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Serif", 0, 48)); // NOI18N
        jLabel1.setText("Registro de lugar de producción");

        Text_nombreLugar.setBackground(new java.awt.Color(204, 255, 255));
        Text_nombreLugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_nombreLugarActionPerformed(evt);
            }
        });

        Text_empresaResp.setBackground(new java.awt.Color(204, 255, 255));
        Text_empresaResp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_empresaRespActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel2.setText("•Empresa responsable(*): ");

        jLabel3.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel3.setText("•Nombre del lugar(*): ");

        Button_siguiente.setBackground(new java.awt.Color(0, 204, 204));
        Button_siguiente.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        Button_siguiente.setText("Siguiente");
        Button_siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_siguienteActionPerformed(evt);
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
                .addContainerGap(290, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(245, 245, 245))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Text_empresaResp, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Text_nombreLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnVolver)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(490, 490, 490)
                        .addComponent(Button_siguiente)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(BtnVolver))
                .addGap(67, 67, 67)
                .addComponent(jLabel3)
                .addGap(27, 27, 27)
                .addComponent(Text_nombreLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(Text_empresaResp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(Button_siguiente)
                .addGap(185, 185, 185))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Text_nombreLugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_nombreLugarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text_nombreLugarActionPerformed

    private void Text_empresaRespActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_empresaRespActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text_empresaRespActionPerformed

    private void Button_siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_siguienteActionPerformed

    }//GEN-LAST:event_Button_siguienteActionPerformed

    private void BtnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVolverActionPerformed

    }//GEN-LAST:event_BtnVolverActionPerformed

 
//  Getters para que el controlador acceda a los campos
    public javax.swing.JButton getButtonSiguiente() {
    return Button_siguiente;
}

public javax.swing.JButton getBtnVolver() {
    return BtnVolver;
}

public javax.swing.JTextField getTextNombreLugar() {
    return Text_nombreLugar;
}

public javax.swing.JTextField getTextEmpresaResp() {
    return Text_empresaResp;
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
            java.util.logging.Logger.getLogger(RegistroLugarProdu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroLugarProdu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroLugarProdu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroLugarProdu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroLugarProdu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnVolver;
    private javax.swing.JButton Button_siguiente;
    private javax.swing.JTextField Text_empresaResp;
    private javax.swing.JTextField Text_nombreLugar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
