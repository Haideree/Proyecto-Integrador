package Vista;

/**
 *
 * @author Haider
 */

import Controlador.ControladorRegistroLugarProdu;
import Controlador.ControladorMostrarPredios;
public class Predios extends javax.swing.JFrame {

    private ControladorMostrarPredios controlador;
    public Predios() {
       initComponents();
        controlador = new ControladorMostrarPredios(this);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        buttonRegistrarP = new javax.swing.JButton();
        BtnConsultar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPredio = new javax.swing.JTable();
        BtnVolver = new javax.swing.JButton();
        buttonRegistrarLugarProdu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Serif", 1, 48)); // NOI18N
        jLabel1.setText("Menú Productor");

        buttonRegistrarP.setBackground(new java.awt.Color(0, 204, 204));
        buttonRegistrarP.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        buttonRegistrarP.setText("Registrar un nuevo predio");
        buttonRegistrarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistrarPActionPerformed(evt);
            }
        });

        BtnConsultar.setBackground(new java.awt.Color(0, 204, 204));
        BtnConsultar.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        BtnConsultar.setText("Consultar un predio");
        BtnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnConsultarActionPerformed(evt);
            }
        });

        tablaPredio.setBackground(new java.awt.Color(204, 255, 255));
        tablaPredio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N° Registro ICA", "Nombre", "Departamento", "Municipio", "Vereda", "Productor", "Propietario", "Lugar Producción"
            }
        ));
        jScrollPane1.setViewportView(tablaPredio);

        BtnVolver.setBackground(new java.awt.Color(0, 204, 204));
        BtnVolver.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        BtnVolver.setText("<-- Volver");
        BtnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVolverActionPerformed(evt);
            }
        });

        buttonRegistrarLugarProdu.setBackground(new java.awt.Color(0, 204, 204));
        buttonRegistrarLugarProdu.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        buttonRegistrarLugarProdu.setText("Registrar un lugar de produccion");
        buttonRegistrarLugarProdu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistrarLugarProduActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(buttonRegistrarLugarProdu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonRegistrarP)
                .addGap(164, 164, 164))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnConsultar)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BtnVolver)
                                .addGap(226, 226, 226)
                                .addComponent(jLabel1)))
                        .addGap(0, 362, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(BtnVolver)))
                .addGap(29, 29, 29)
                .addComponent(BtnConsultar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonRegistrarP)
                    .addComponent(buttonRegistrarLugarProdu))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonRegistrarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarPActionPerformed
    Vista.RegistroPredio vista = new Vista.RegistroPredio();
    Controlador.ControladorRegistroPredio controlador = new Controlador.ControladorRegistroPredio(vista);
    vista.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_buttonRegistrarPActionPerformed
    
    private void BtnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnConsultarActionPerformed
        
    }//GEN-LAST:event_BtnConsultarActionPerformed

    private void BtnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVolverActionPerformed
    Vista.vistas login = new Vista.vistas();
    Controlador.ControladorLogin controlador = new Controlador.ControladorLogin(login);
    login.setVisible(true);
    this.dispose();
        
    }//GEN-LAST:event_BtnVolverActionPerformed

    private void buttonRegistrarLugarProduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarLugarProduActionPerformed
      Vista.RegistroLugarProdu vista = new Vista.RegistroLugarProdu();
        Controlador.ControladorRegistroLugarProdu controlador = new Controlador.ControladorRegistroLugarProdu(vista);
         vista.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_buttonRegistrarLugarProduActionPerformed

   
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
            java.util.logging.Logger.getLogger(Predios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Predios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Predios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Predios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Predios().setVisible(true);
            }
        });
    }
    
  
   public javax.swing.JButton getBtnConsultar() { 
       return BtnConsultar;
   }
    public javax.swing.JTable getTablaPredios() { 
        return tablaPredio; }
    
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnConsultar;
    private javax.swing.JButton BtnVolver;
    private javax.swing.JButton buttonRegistrarLugarProdu;
    private javax.swing.JButton buttonRegistrarP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPredio;
    // End of variables declaration//GEN-END:variables
}
