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
import javax.swing.table.TableColumn;


public class Resep extends javax.swing.JFrame {

    int idPeriksa, idPasien,resep;
    ResultSet rs = null;
    Connection CC = new koneksi().connect();;
    PreparedStatement pst = null;
    Statement stt;
    String sql; 
    public Resep(int idPeriksa,int idPasien) {
        initComponents();
        this.idPeriksa=idPeriksa;
        this.idPasien = idPasien;
        getObat(cbObat);
        System.out.println("id Periksa : "+idPeriksa);
        txtResep.setEnabled(false);
        txtPeriksa.setEnabled(false);
        txtPasien.setEnabled(false);
        initTable();
        txtPeriksa.setText(String.valueOf(idPeriksa));
        txtPasien.setText(String.valueOf(idPasien));
        txtResep.setText(String.valueOf(getResep()));
    }
    int jumlah,idDetail;
    String nama,aturan,idObat;
    private Resep(){}
    
    private void initTable(){
       DefaultTableModel model = new DefaultTableModel();
       model.addColumn("Detail ID");
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
            idDetail = rs.getInt("id_detail");
            resep = rs.getInt("id_resep");
            idObat = rs.getString("id_obat");
            nama = rs.getString("obat.nama");
            aturan = rs.getString("detail_resep.aturan");
            jumlah = rs.getInt("detail_resep.jumlah");
            model.addRow(new Object[]{idDetail,no,idObat,nama,aturan,jumlah});
            table.setModel(model);
        }
        TableColumn column = table.getColumnModel().getColumn(0);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);
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
            rs = stt.executeQuery("SELECT * FROM obat WHERE Jumlah>0");
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
            rs = stt.executeQuery("SELECT * FROM obat WHERE obat.nama='"+obat+"'");
            if(rs.next()){
                idObat = rs.getString("id_obat");  
            }
        }catch(SQLException e){
            System.err.println(e);
        } 
        return idObat;
    }

    private void tambahObat(){
      resep = Integer.parseInt(txtResep.getText());
      aturan = txtAturan.getText();
      jumlah = (int) spinJumlah.getValue();
      idObat = getIdObat(cbObat.getSelectedItem().toString());
      try{
        sql = "SELECT id_obat, aturan, jumlah FROM detail_resep WHERE id_resep = ?";
        pst = CC.prepareStatement(sql);
        pst.setInt(1, resep);
        rs = pst.executeQuery();
        if (rs.next()) {
            String existingIdObat = rs.getString("id_obat");
            String existingAturan = rs.getString("aturan");
            Integer existingJumlah = rs.getInt("jumlah");
            if (existingIdObat == null|| existingAturan == null || existingJumlah == null) {
                String sqlUpdate = "UPDATE detail_resep SET id_obat = COALESCE(?, id_obat), aturan = COALESCE(?, aturan), jumlah = COALESCE(?, jumlah) WHERE id_resep = ?";
                PreparedStatement pstUpdate = CC.prepareStatement(sqlUpdate);
                pstUpdate.setInt(4, resep);
                pstUpdate.setString(1, idObat);
                pstUpdate.setString(2, aturan);
                pstUpdate.setInt(3, jumlah);
                pstUpdate.executeUpdate();
                pstUpdate.close();
            } else {
            String sqlInsert = "INSERT INTO detail_resep (id_resep, id_obat, aturan, jumlah) VALUES (?, ?, ?, ?)";
            PreparedStatement pstInsert = CC.prepareStatement(sqlInsert);
            pstInsert.setInt(1, resep);
            pstInsert.setString(2, idObat);
            pstInsert.setString(3, aturan);
            pstInsert.setInt(4, jumlah);
            pstInsert.executeUpdate();
            pstInsert.close();
            }
        }
        rs.close();
        }catch(SQLException e){
          e.printStackTrace();
        }
    }
    private void updateObat(){
       System.out.println("ID Detail "+idDetail);
      aturan = txtAturan.getText();
      jumlah = (int) spinJumlah.getValue();
      idObat = getIdObat(cbObat.getSelectedItem().toString());
        try {
            sql="UPDATE detail_resep SET id_obat = ?,aturan = ?,jumlah=? WHERE id_detail= ?";
            pst = CC.prepareStatement(sql);
            pst.setString(1, idObat);
            pst.setString(2, aturan);
            pst.setInt(3, jumlah);
            pst.setInt(4, idDetail);
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Resep.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void deleteObat(){
        System.out.println("ID Detail "+idDetail);
        try {
            sql = "DELETE FROM detail_resep WHERE id_detail = "+idDetail+"";
            pst = CC.prepareStatement(sql);
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Resep.class.getName()).log(Level.SEVERE, null, ex);
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
        cbObat = new javax.swing.JComboBox<>();

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
                "Detail ID", "Kode Obat", "Nama Obat", "Aturan", "Jumlah"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMinWidth(0);
            table.getColumnModel().getColumn(0).setPreferredWidth(0);
            table.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jLabel1.setText("No Resep");

        jLabel2.setText("Nama Obat");

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
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Hapus");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

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
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                                        .addComponent(cbObat, 0, 123, Short.MAX_VALUE)))
                                .addGap(0, 145, Short.MAX_VALUE)))))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        tambahObat();
        initTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        updateObat();
        initTable();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseReleased
        int row = table.getSelectedRow();
        idDetail= (Integer)(table.getModel().getValueAt(row,0));
        idObat = (String) (table.getModel().getValueAt(row,2));
        resep = Integer.parseInt(txtResep.getText());
        aturan = (String)(table.getModel().getValueAt(row,4));
        jumlah = (Integer) (table.getModel().getValueAt(row,5));
        nama = (String)(table.getModel().getValueAt(row,3));
        cbObat.setSelectedItem(nama);
        txtAturan.setText(aturan);
        spinJumlah.setValue(jumlah);
    }//GEN-LAST:event_tableMouseReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        deleteObat();
        initTable();
    }//GEN-LAST:event_jButton4ActionPerformed

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
