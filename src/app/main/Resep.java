package app.main;

import app.configurations.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;


public class Resep extends javax.swing.JFrame {

    static int idPeriksa, idPasien,resep;
    ResultSet rs = null;
    Connection CC = new koneksi().connect();;
    PreparedStatement pst = null;
    Statement stt;
    String sql; 
    public Resep(int idPeriksa,int idPasien) {
        initComponents();
        this.idPeriksa=idPeriksa;
        this.idPasien = idPasien;
        System.out.println("id Periksa : "+idPeriksa);
        txtResep.setEnabled(false);
        txtPeriksa.setEnabled(false);
        txtPasien.setEnabled(false);
        getObat(cbObat);
        kode = getIdObat(cbObat.getSelectedItem().toString());
        initTable();
        txtPeriksa.setText(String.valueOf(idPeriksa));
        txtPasien.setText(String.valueOf(idPasien));
        txtResep.setText(String.valueOf(getResep()));
    }
    int jumlah;
    String nama,aturan,kode;
    private Resep(){}
    
    private void initTable(){
       DefaultTableModel model = new DefaultTableModel();
       model.addColumn("No");
       model.addColumn("Kode Obat");
       model.addColumn("Nama Obat");
       model.addColumn("Aturan Pakai");
       model.addColumn("Jumlah");
    try{
        stt=CC.createStatement();
        rs = stt.executeQuery("SELECT * FROM resep JOIN detail_resep ON detail_resep.id_resep = resep.id_resep JOIN obat ON obat.id_obat = detail_resep.id_obat WHERE resep.id_pemeriksaan="+idPeriksa+"");
        int no = 0;
        while(rs.next()){
            no++;
            resep = rs.getInt("id_resep");
            kode = rs.getString("id_obat");
            nama = rs.getString("obat.nama");
            aturan = rs.getString("detail_resep.aturan");
            jumlah = rs.getInt("detail_resep.jumlah");
            model.addRow(new Object[]{no,kode,nama,aturan,jumlah});
            table.setModel(model);
        }
    }catch(SQLException e){
        e.printStackTrace();
    }
}
    private int getResep(){
        idPeriksa = Integer.parseInt(txtPeriksa.getText());
        try{
            stt=CC.createStatement();
            rs = stt.executeQuery("SELECT * FROM resep WHERE resep.id_pemeriksaan = "+idPeriksa+"");
            if(rs.next()){
                resep=rs.getInt("id_resep");
            }
        }catch(SQLException e){
        
        }
        return resep;
    }
    
    private void getObat(JComboBox paket){
         try{
            stt=CC.createStatement();
            rs = stt.executeQuery("SELECT * FROM obat WHERE jumlah != 0 OR jumlah IS NOT NULL;");
            while(rs.next()){
                paket.addItem(rs.getString("nama"));  
            }
        }catch(SQLException e){
            System.err.println(e);
        } 
    }
    private String getIdObat(String obat){
        try{
            stt=CC.createStatement();
            rs = stt.executeQuery("SELECT * FROM detail_resep JOIN obat ON obat.id_obat = detail_resep.id_obat WHERE nama='"+obat+"'");
            if(rs.next()){
                kode = rs.getString("id_obat");  
            }
        }catch(SQLException e){
            System.err.println(e);
        } 
        return kode;
    }

    private void tambahObat(){
      resep = Integer.parseInt(txtResep.getText());
      kode = getIdObat(cbObat.getSelectedItem().toString());
        System.out.println("Kode "+kode);
      aturan = txtAturan.getText();
      jumlah = (int) spinJumlah.getValue();
      try{
        String sqlCheck = "SELECT id_obat, aturan, jumlah FROM detail_resep WHERE id_resep = ? limit 1";
        PreparedStatement pstCheck = CC.prepareStatement(sqlCheck);
        pstCheck.setInt(1, resep);
        ResultSet rs = pstCheck.executeQuery();
        if (rs.next()) {
            Integer existingIdObat = rs.getInt("id_obat");
            String existingAturan = rs.getString("aturan");
            Integer existingJumlah = rs.getInt("jumlah");
            if (existingIdObat == null || existingAturan == null || existingJumlah == null) {
                String sqlUpdate = "UPDATE detail_resep SET id_obat = COALESCE(?, id_obat), aturan = COALESCE(?, aturan), jumlah = COALESCE(?, jumlah) WHERE id_resep = ? limit 1";
                PreparedStatement pstUpdate = CC.prepareStatement(sqlUpdate);
                pstUpdate.setInt(4, resep);
                pstUpdate.setString(1, kode);
                pstUpdate.setString(2, aturan);
                pstUpdate.setInt(3, jumlah);
                pstUpdate.executeUpdate();
            }
        } else {
            String sqlInsert = "INSERT INTO detail_resep (id_resep, id_obat, aturan, jumlah) VALUES (?, ?, ?, ?)";
            PreparedStatement pstInsert = CC.prepareStatement(sqlInsert);
            pstInsert.setInt(1, resep);
            pstInsert.setString(2, kode);
            pstInsert.setString(3, aturan);
            pstInsert.setInt(4, jumlah);
            pstInsert.executeUpdate();
        }
        }catch(SQLException e){
            System.err.println(e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        txtResep = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbObat = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtAturan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        spinJumlah = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtPeriksa = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPasien = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "Kode Obat", "Nama Obat", "Aturan", "Jumlah"
            }
        ));
        jScrollPane1.setViewportView(table);

        jLabel1.setText("No Resep");

        jLabel2.setText("Nama Obat");

        cbObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbObatActionPerformed(evt);
            }
        });

        jLabel3.setText("Aturan Pakai");

        jLabel4.setText("Jumlah");

        spinJumlah.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        jButton1.setText("Tambah");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cetak");

        jButton3.setText("Ubah");

        jButton4.setText("Hapus");

        jLabel5.setText("ID Periksa");

        jLabel6.setText("ID Pasien");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtResep, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 351, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtAturan, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                                            .addComponent(spinJumlah)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbObat, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtResep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(cbObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtAturan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(spinJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton3)
                            .addComponent(jButton4))
                        .addGap(12, 12, 12)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbObatActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbObatActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        tambahObat();
        initTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Resep().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbObat;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spinJumlah;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtAturan;
    private javax.swing.JTextField txtPasien;
    private javax.swing.JTextField txtPeriksa;
    private javax.swing.JTextField txtResep;
    // End of variables declaration//GEN-END:variables
}
