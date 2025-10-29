package Vista;

/**
 *
 * @author Usuario
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

        jLabel5 = new javax.swing.JLabel();
        fondo = new javax.swing.JPanel();
        Panel1 = new javax.swing.JPanel();
        ICAlogo = new javax.swing.JLabel();
        Panel2 = new javax.swing.JPanel();
        Icono1 = new javax.swing.JLabel();
        Titulo = new javax.swing.JLabel();
        CorreoElec = new javax.swing.JLabel();
        TXT_usuariologin = new javax.swing.JTextField();
        Contraseña = new javax.swing.JLabel();
        TXT_contraseñalogin = new javax.swing.JPasswordField();
        Button_ingresar = new javax.swing.JButton();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fondo.setBackground(new java.awt.Color(255, 255, 255));
        fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Panel1.setBackground(new java.awt.Color(75, 133, 82));

        ICAlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Ica_logo_2-removebg-preview (2) (1).png"))); // NOI18N

        javax.swing.GroupLayout Panel1Layout = new javax.swing.GroupLayout(Panel1);
        Panel1.setLayout(Panel1Layout);
        Panel1Layout.setHorizontalGroup(
            Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel1Layout.createSequentialGroup()
                .addComponent(ICAlogo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Panel1Layout.setVerticalGroup(
            Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel1Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(ICAlogo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
        );

        fondo.add(Panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 440));

        Panel2.setBackground(new java.awt.Color(55, 165, 87));

        javax.swing.GroupLayout Panel2Layout = new javax.swing.GroupLayout(Panel2);
        Panel2.setLayout(Panel2Layout);
        Panel2Layout.setHorizontalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        Panel2Layout.setVerticalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        fondo.add(Panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, 600, 80));

        Icono1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/icono.png"))); // NOI18N
        Icono1.setText("jLabel1");
        fondo.add(Icono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, -20, 150, 140));

        Titulo.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        Titulo.setForeground(new java.awt.Color(0, 99, 17));
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo.setText("INICIO SESION");
        fondo.add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 90));

        CorreoElec.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        CorreoElec.setForeground(new java.awt.Color(0, 99, 17));
        CorreoElec.setText("CORREO ELECTRONICO:");
        fondo.add(CorreoElec, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, -1, -1));

        TXT_usuariologin.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        TXT_usuariologin.setToolTipText("");
        TXT_usuariologin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        TXT_usuariologin.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        TXT_usuariologin.setDragEnabled(true);
        TXT_usuariologin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_usuariologinActionPerformed(evt);
            }
        });
        fondo.add(TXT_usuariologin, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 420, 30));

        Contraseña.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        Contraseña.setForeground(new java.awt.Color(0, 99, 17));
        Contraseña.setText("CONTRASEÑA:");
        fondo.add(Contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, -1, -1));

        TXT_contraseñalogin.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        TXT_contraseñalogin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        fondo.add(TXT_contraseñalogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 420, 30));

        Button_ingresar.setBackground(new java.awt.Color(225, 217, 238));
        Button_ingresar.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        Button_ingresar.setForeground(new java.awt.Color(0, 51, 0));
        Button_ingresar.setText("Ingresar");
        Button_ingresar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        fondo.add(Button_ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 150, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TXT_usuariologinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_usuariologinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_usuariologinActionPerformed
    
     public javax.swing.JButton getButtonIngresar() { return Button_ingresar; }
    public javax.swing.JTextField getTxtUsuario() { return TXT_usuariologin; }
    public javax.swing.JPasswordField getTxtContrasena() { return TXT_contraseñalogin; }
    
      
    
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
    private javax.swing.JButton Button_ingresar;
    private javax.swing.JLabel Contraseña;
    private javax.swing.JLabel CorreoElec;
    private javax.swing.JLabel ICAlogo;
    private javax.swing.JLabel Icono1;
    private javax.swing.JPanel Panel1;
    private javax.swing.JPanel Panel2;
    private javax.swing.JPasswordField TXT_contraseñalogin;
    private javax.swing.JTextField TXT_usuariologin;
    private javax.swing.JLabel Titulo;
    private javax.swing.JPanel fondo;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
