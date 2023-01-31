package app.form;

import app.components.Form;
import app.configurations.koneksi;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
public class Pemeriksaan extends Form {
    ResultSet rs = null;
    Connection CC = new koneksi().connect();;
    PreparedStatement pst = null;
    Statement stt;
    String sql;  
    public Pemeriksaan() {
        initComponents();
        initTablePemeriksaan();
    }
    int idPeriksa,idPasien,idDokter,idBidan;
    String diagnosa,pelayanan,nama,gender,dokter,bidan;
    Date tgl;
    private void initTable(int idPasien){
       DefaultTableModel model = new DefaultTableModel();
       model.addColumn("ID Pemeriksaan");
       model.addColumn("ID Pasien");
       model.addColumn("Nama Pasien");
       model.addColumn("Jenis Kelamin");
       model.addColumn("Pelayanan");
       model.addColumn("Pasien");
       model.addColumn("Diagnosa");
       model.addColumn("Tanggal Periksa");
       try{
            stt=CC.createStatement();
            rs = stt.executeQuery("SELECT * From pemeriksaan JOIN pasien ON pasien.id_pasien = pemeriksaan.id_pasien JOIN dokter ON dokter.id_dokter = pemeriksaan.id_dokter JOIN bidan ON bidan.id_bidan=pemeriksaan.id_bidan WHERE "
                    + " pemeriksaan.id_pasien="+idPasien+"");
            while(rs.next()){
              idPeriksa = rs.getInt("id_pemeriksaan");
              pelayanan = rs.getString("pelayanan");
              idPasien = rs.getInt("id_pasien");
              idDokter = rs.getInt("id_dokter");
              idBidan= rs.getInt("id_bidan");
              diagnosa = rs.getString("diagnosa");
              nama = rs.getString("nama");
              gender = rs.getString("jenis_kelamin");
              tgl = rs.getDate("tanggal");
              String pasien;
              if(pelayanan.equals("Bidan")){
                  pasien = rs.getString("bidan.nama_bidan");
              }else{
                  pasien = rs.getString("dokter.nama");
              }
             model.addRow(new Object[]{idPeriksa,idPasien,nama,gender,pelayanan,pasien,diagnosa,tgl});
             table.setModel(model);
            }
        }catch(SQLException e){
            e.printStackTrace();
        } 
    }
    private void initTablePemeriksaan(){
       DefaultTableModel model = new DefaultTableModel();
       model.addColumn("ID Pasien");
       model.addColumn("Nama Pasien");
       model.addColumn("Jenis Kelamin");
       model.addColumn("Pelayanan");   
       try{
            stt=CC.createStatement();
            rs = stt.executeQuery("SELECT * From pemeriksaan JOIN pasien ON pasien.id_pasien = pemeriksaan.id_pasien where pemeriksaan.tanggal = CURDATE()");
            while(rs.next()){
              idPeriksa = rs.getInt("id_pemeriksaan");
              pelayanan = rs.getString("pelayanan");
              idPasien = rs.getInt("id_pasien");
              idDokter = rs.getInt("id_dokter");
              idBidan= rs.getInt("id_bidan");
              diagnosa = rs.getString("diagnosa");
              nama = rs.getString("nama");
              gender = rs.getString("jenis_kelamin");
              tgl = rs.getDate("tanggal");
             model.addRow(new Object[]{idPasien,nama,gender,pelayanan});
             table1.setModel(model);
            }
        }catch(SQLException e){
            e.printStackTrace();
        } 
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtPasien = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDokter = new javax.swing.JTextField();
        txtDokter1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Pemeriksaan", "Pelayanan", "Nama Pasien", "Diagnosa", "Tanggal"
            }
        ));
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setHeaderValue("ID Pemeriksaan");
            table.getColumnModel().getColumn(3).setHeaderValue("Diagnosa");
            table.getColumnModel().getColumn(4).setHeaderValue("Tanggal");
        }

        jLabel1.setText("ID Pemeriksaan");

        jLabel2.setText("ID Pasien");

        jLabel3.setText("ID Dokter");

        jLabel4.setText("Diagnosa");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Pasien", "Pelayanan", "Nama Pasien", "Jenis Kelamin"
            }
        ));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                table1MouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(table1);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Rekam Medis Pasien");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Daftar Pemeriksaan Pasien");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(txtDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(50, 50, 50)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDokter1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 449, Short.MAX_VALUE)
                            .addComponent(jButton1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDokter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void table1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseReleased
        // TODO add your handling code here:
        int row = table1.getSelectedRow();
        idPasien = Integer.parseInt((table1.getModel().getValueAt(row,0)).toString());
        System.out.println(idPasien);
        initTable(idPasien);
    }//GEN-LAST:event_table1MouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTable table;
    private javax.swing.JTable table1;
    private javax.swing.JTextField txtDokter;
    private javax.swing.JTextField txtDokter1;
    private javax.swing.JTextField txtPasien;
    // End of variables declaration//GEN-END:variables
}
