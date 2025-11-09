package Vista;

/**
 *
 * @author Usuario
 */
import java.sql.Connection;
import Controlador.ControladorRegistroLugarProdu;
import Controlador.ControladorMostrarPredios;

public class Predios extends javax.swing.JFrame {
    
    private final Connection conexionActiva; // 🔹 guardamos la conexión activa del usuario
    private ControladorMostrarPredios controlador;

    public Predios(Connection conexionActiva) {
        initComponents();
        this.conexionActiva = conexionActiva;
        controlador = new ControladorMostrarPredios(this, conexionActiva);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Icon = new javax.swing.JLabel();
        Titulo = new javax.swing.JLabel();
        BtnConsultar = new javax.swing.JButton();
        BtnVolver = new javax.swing.JButton();
        buttonRegistrarP = new javax.swing.JButton();
        buttonRegistrarLugarProdu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPredio = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fondo.setBackground(new java.awt.Color(255, 255, 255));
        fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 99, 17));
        jPanel1.setForeground(new java.awt.Color(0, 99, 17));

        Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/ICAlogo90.png"))); // NOI18N

        Titulo.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 255, 255));
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Titulo.setText("Menú Productor");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149)
                .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        fondo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 80));

        BtnConsultar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        BtnConsultar.setForeground(new java.awt.Color(0, 99, 17));
        BtnConsultar.setText("Consultar predios");
        BtnConsultar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(55, 165, 87), 1, true));
        BtnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnConsultarActionPerformed(evt);
            }
        });
        fondo.add(BtnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 130, 30));

        BtnVolver.setBackground(new java.awt.Color(55, 165, 87));
        BtnVolver.setForeground(new java.awt.Color(255, 255, 255));
        BtnVolver.setText("⮜ REGRESAR");
        BtnVolver.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        BtnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVolverActionPerformed(evt);
            }
        });
        fondo.add(BtnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 80, 20));

        buttonRegistrarP.setBackground(new java.awt.Color(225, 225, 225));
        buttonRegistrarP.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        buttonRegistrarP.setForeground(new java.awt.Color(0, 99, 17));
        buttonRegistrarP.setText("Registrar un nuevo predio");
        buttonRegistrarP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(55, 165, 87), 1, true));
        buttonRegistrarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistrarPActionPerformed(evt);
            }
        });
        fondo.add(buttonRegistrarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 370, 240, 30));

        buttonRegistrarLugarProdu.setBackground(new java.awt.Color(225, 225, 225));
        buttonRegistrarLugarProdu.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        buttonRegistrarLugarProdu.setForeground(new java.awt.Color(0, 99, 17));
        buttonRegistrarLugarProdu.setText("Registrar un lugar de producción");
        buttonRegistrarLugarProdu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(55, 165, 87), 1, true));
        buttonRegistrarLugarProdu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistrarLugarProduActionPerformed(evt);
            }
        });
        fondo.add(buttonRegistrarLugarProdu, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, 240, 30));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(55, 165, 87)));

        tablaPredio.setAutoCreateRowSorter(true);
        tablaPredio.setBackground(new java.awt.Color(204, 204, 204));
        tablaPredio.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(55, 165, 87), 1, true));
        tablaPredio.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        tablaPredio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N° Registro ICA", "Nombre Predio", "Departamento", "Municipio", "Vereda", "Productor", "Propietario", "Lugar Produccion"
            }
        ));
        tablaPredio.setGridColor(new java.awt.Color(111, 165, 87));
        jScrollPane1.setViewportView(tablaPredio);

        fondo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 730, 120));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Ica Completo 100.png"))); // NOI18N
        fondo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, -1, -1));

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

    private void BtnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnConsultarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnConsultarActionPerformed

    private void BtnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVolverActionPerformed
    Vista.vistas login = new Vista.vistas();
    Controlador.ControladorLogin controlador = new Controlador.ControladorLogin(login);
    login.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_BtnVolverActionPerformed

    private void buttonRegistrarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarPActionPerformed
    Vista.RegistroPredio vista = new Vista.RegistroPredio(conexionActiva);//eror en esta linea
    Controlador.ControladorRegistroPredio controlador = new Controlador.ControladorRegistroPredio(vista, conexionActiva);
    vista.setVisible(true);   
        this.dispose();    }//GEN-LAST:event_buttonRegistrarPActionPerformed

    private void buttonRegistrarLugarProduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarLugarProduActionPerformed
    Vista.RegistroLugarProdu vista = new Vista.RegistroLugarProdu(conexionActiva);
    Controlador.ControladorRegistroLugarProdu controlador = new Controlador.ControladorRegistroLugarProdu(vista, conexionActiva);
    vista.setVisible(true);
    this.dispose();

    }//GEN-LAST:event_buttonRegistrarLugarProduActionPerformed

    public static void main(String args[]) {
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(Predios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }

    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            // 💡 Aquí se conecta como PRODUCTOR (puedes cambiar el rol)
            java.sql.Connection conexion = Modelado.CConexion.getConnectionPorRol("productor");

            // 💡 Pasa la conexión al formulario (si tu constructor la recibe)
            new Vista.Predios(conexion).setVisible(true);
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
    private javax.swing.JLabel Icon;
    private javax.swing.JLabel Titulo;
    private javax.swing.JButton buttonRegistrarLugarProdu;
    private javax.swing.JButton buttonRegistrarP;
    private javax.swing.JPanel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPredio;
    // End of variables declaration//GEN-END:variables
}
