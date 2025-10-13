package Vista;

/**
 *
 * @author Haider
 */
import Modelado.LoginDAO;
import javax.swing.JOptionPane;
import Controlador.ControladorLogin;
public class vistas extends javax.swing.JFrame {


    public vistas() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Tituloiniciarsesion = new javax.swing.JLabel();
        TXT_contraseñalogin = new javax.swing.JPasswordField();
        Label_ingresecontraseña = new javax.swing.JLabel();
        Label_ingreseusuario = new javax.swing.JLabel();
        TXT_usuariologin = new javax.swing.JTextField();
        Button_ingresar = new javax.swing.JButton();
        Button_Registrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Tituloiniciarsesion.setBackground(new java.awt.Color(153, 204, 255));
        Tituloiniciarsesion.setFont(new java.awt.Font("Serif", 1, 48)); // NOI18N
        Tituloiniciarsesion.setText("INICIAR SESIÓN");

        TXT_contraseñalogin.setBackground(new java.awt.Color(204, 204, 255));
        TXT_contraseñalogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_contraseñaloginActionPerformed(evt);
            }
        });

        Label_ingresecontraseña.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        Label_ingresecontraseña.setText("CONTRASEÑA");

        Label_ingreseusuario.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        Label_ingreseusuario.setText("INGRESE CORREO ELECTRÓNICO:");

        TXT_usuariologin.setBackground(new java.awt.Color(204, 204, 255));
        TXT_usuariologin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_usuariologinActionPerformed(evt);
            }
        });

        Button_ingresar.setBackground(new java.awt.Color(153, 153, 255));
        Button_ingresar.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        Button_ingresar.setText("INGRESAR");
        Button_ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_ingresarActionPerformed(evt);
            }
        });

        Button_Registrar.setBackground(new java.awt.Color(153, 204, 255));
        Button_Registrar.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        Button_Registrar.setText("¿Aún no estás registrado?");
        Button_Registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_RegistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(383, 383, 383)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_ingreseusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXT_usuariologin, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_ingresecontraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXT_contraseñalogin, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Button_ingresar, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(421, 421, 421)
                        .addComponent(Button_Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(383, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Tituloiniciarsesion)
                .addGap(320, 320, 320))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Tituloiniciarsesion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(Label_ingreseusuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TXT_usuariologin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(Label_ingresecontraseña)
                .addGap(18, 18, 18)
                .addComponent(TXT_contraseñalogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Button_ingresar)
                .addGap(18, 18, 18)
                .addComponent(Button_Registrar)
                .addContainerGap(294, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
     public javax.swing.JButton getButtonIngresar() { return Button_ingresar; }
    public javax.swing.JButton getButtonRegistrar() { return Button_Registrar; }
    public javax.swing.JTextField getTxtUsuario() { return TXT_usuariologin; }
    public javax.swing.JPasswordField getTxtContrasena() { return TXT_contraseñalogin; }


    private void TXT_usuariologinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_usuariologinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_usuariologinActionPerformed

    private void Button_ingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_ingresarActionPerformed
      
    }//GEN-LAST:event_Button_ingresarActionPerformed

    private void Button_RegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_RegistrarActionPerformed

    }//GEN-LAST:event_Button_RegistrarActionPerformed

    private void TXT_contraseñaloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_contraseñaloginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_contraseñaloginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            vistas login = new vistas();
            new Controlador.ControladorLogin(login);
            login.setVisible(true);
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_Registrar;
    private javax.swing.JButton Button_ingresar;
    private javax.swing.JLabel Label_ingresecontraseña;
    private javax.swing.JLabel Label_ingreseusuario;
    private javax.swing.JPasswordField TXT_contraseñalogin;
    private javax.swing.JTextField TXT_usuariologin;
    private javax.swing.JLabel Tituloiniciarsesion;
    // End of variables declaration//GEN-END:variables
}
