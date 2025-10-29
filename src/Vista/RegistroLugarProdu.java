package Vista;

/**
 *
 * @author Usuario
 */
public class RegistroLugarProdu extends javax.swing.JFrame {
    
    public RegistroLugarProdu() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Icon = new javax.swing.JLabel();
        Titulo = new javax.swing.JLabel();
        Button_siguiente = new javax.swing.JButton();
        BtnVolver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Text_nombreLugar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Text_empresaResp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fondo.setBackground(new java.awt.Color(255, 255, 255));
        fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 99, 17));
        jPanel1.setForeground(new java.awt.Color(0, 99, 17));

        Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Ica_logo_2-removebg-preview (2) (1).png"))); // NOI18N

        Titulo.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 255, 255));
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Titulo.setText("Registro de Lugar de Producción");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(Titulo, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Titulo, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
            .addComponent(Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        fondo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 100));

        Button_siguiente.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Button_siguiente.setForeground(new java.awt.Color(0, 99, 17));
        Button_siguiente.setText("Siguiente");
        Button_siguiente.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(55, 165, 87), 1, true));
        Button_siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_siguienteActionPerformed(evt);
            }
        });
        fondo.add(Button_siguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 130, 30));

        BtnVolver.setBackground(new java.awt.Color(55, 165, 87));
        BtnVolver.setForeground(new java.awt.Color(255, 255, 255));
        BtnVolver.setText("⮜ REGRESAR");
        BtnVolver.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        BtnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVolverActionPerformed(evt);
            }
        });
        fondo.add(BtnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 80, 20));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jLabel1.setText("- Nombre del Lugar de producción (*):");
        fondo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 230, -1));

        Text_nombreLugar.setBackground(new java.awt.Color(225, 237, 227));
        Text_nombreLugar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(187, 213, 191)));
        Text_nombreLugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_nombreLugarActionPerformed(evt);
            }
        });
        fondo.add(Text_nombreLugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 310, 20));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jLabel4.setText("- Nombre de la Empresa Responsable (*): ");
        fondo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 360, -1));

        Text_empresaResp.setBackground(new java.awt.Color(225, 237, 227));
        Text_empresaResp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(187, 213, 191)));
        Text_empresaResp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_empresaRespActionPerformed(evt);
            }
        });
        fondo.add(Text_empresaResp, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 310, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/IcaCompleto.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        fondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 320, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Button_siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_siguienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_siguienteActionPerformed

    private void BtnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVolverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnVolverActionPerformed

    private void Text_nombreLugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_nombreLugarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text_nombreLugarActionPerformed

    private void Text_empresaRespActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_empresaRespActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text_empresaRespActionPerformed
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
    private javax.swing.JLabel Icon;
    private javax.swing.JTextField Text_empresaResp;
    private javax.swing.JTextField Text_nombreLugar;
    private javax.swing.JLabel Titulo;
    private javax.swing.JPanel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
