
package app.main;

import app.form.Beranda;
import app.form.Data_Pasien;
import app.form.Jadwal_Pelayanan_admin;
import app.form.Rekam_medis;
import app.form.jadwal_dokter;
import app.menu.EventMenu;


public class Dashboard extends javax.swing.JFrame {

  
    public Dashboard() {
        initComponents();
        init();
    }
    private void init(){
        menu.addEventMenu(new EventMenu() {
            @Override
            public void selectedMenu(int index) {
                if(index==0){
                    mainBody.displayForm(new Beranda(),"Beranda");
                }else if(index==1){
                    mainBody.displayForm(new jadwal_dokter(),"Jadwal Dokter");
                }else if(index==2){
                    mainBody.displayForm(new Jadwal_Pelayanan_admin(),"Jadwal Pelayanan");
                }else if (index==3){
                    mainBody.displayForm(new Data_Pasien(),"Data Pasien");
                }else if (index==4){
                    mainBody.displayForm(new Rekam_medis(),"Rekam Medis");
                }else if (index==5){
                     Login a = new Login();
                    a.setVisible(true);
//                    UserSession.setUserLogin("");
//                    UserSession.setUserId(0);
                    dispose();  
                }
//                else if(index == 2){
//                    Login a = new Login();
//                    a.setVisible(true);
////                    UserSession.setUserLogin("");
////                    UserSession.setUserId(0);
//                    dispose();  
//                }    
            }
        });
        mainBody.displayForm(new Beranda(),"Beranda");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBackground1 = new app.swing.PanelBackground();
        menu = new app.menu.Menu();
        mainBody = new app.components.MainBody();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panelBackground1Layout = new javax.swing.GroupLayout(panelBackground1);
        panelBackground1.setLayout(panelBackground1Layout);
        panelBackground1Layout.setHorizontalGroup(
            panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackground1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainBody, javax.swing.GroupLayout.DEFAULT_SIZE, 937, Short.MAX_VALUE))
        );
        panelBackground1Layout.setVerticalGroup(
            panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainBody, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

   
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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.components.MainBody mainBody;
    private app.menu.Menu menu;
    private app.swing.PanelBackground panelBackground1;
    // End of variables declaration//GEN-END:variables
}
