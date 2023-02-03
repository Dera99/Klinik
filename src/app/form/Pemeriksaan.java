package app.form;

import app.components.Form;
import app.configurations.koneksi;
import app.main.Resep;
import app.services.UserSession;
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
        idMedis =UserSession.getIdMedis();
        txtMedis.setText(UserSession.getIdMedis());
        txtMedis.setEditable(false);
        initDaftarPeriksa();
    }
    int idPasien,idPeriksa;
    String diagnosa,pelayanan,nama,gender,idMedis,medis,profesi;
    Date tgl;
    String level = UserSession.getLevel();
    private void rekamMedis(int id){     
        try{
        DefaultTableModel model = new DefaultTableModel();
            System.out.println("Pelayanan "+pelayanan);
        String header;
        if(pelayanan.equals("Umum")){
            header="Dokter";
        }else{
            header="Bidan";
        }
                model.addColumn("ID Pemeriksaan");
                model.addColumn("ID Pasien");
                model.addColumn("Nama Pasien");
                model.addColumn("Jenis Kelamin");
                model.addColumn("Pelayanan");
                model.addColumn(header);
                model.addColumn("Diagnosa");
                model.addColumn("Tanggal Periksa"); 
                rs = stt.executeQuery("SELECT * From pemeriksaan JOIN pasien ON pasien.id_pasien = pemeriksaan.id_pasien JOIN tenaga_medis ON tenaga_medis.id_medis = pemeriksaan.id_medis WHERE "
                        + "pemeriksaan.id_pasien="+id+"");
                    while(rs.next()){
                      idPeriksa = rs.getInt("id_pemeriksaan");
                      pelayanan = rs.getString("pelayanan");
                      idPasien = rs.getInt("id_pasien");
                      idMedis = rs.getString("tenaga_medis.id_medis");
                      diagnosa = rs.getString("diagnosa");
                      nama = rs.getString("pasien.nama");
                      gender = rs.getString("jenis_kelamin");
                      tgl = rs.getDate("tanggal");
                      medis = rs.getString("tenaga_medis.nama");
                      model.addRow(new Object[]{idPeriksa,idPasien,nama,gender,pelayanan,medis,diagnosa,tgl});
                      table.setModel(model);
                    } 
                    table.setModel(model);
            
        }catch(SQLException e){
            e.printStackTrace();
        }    
    }
    private void initDaftarPeriksa(){
       DefaultTableModel model = new DefaultTableModel();
       model.addColumn("ID Periksa");
       model.addColumn("ID Pasien");
       model.addColumn("Nama Pasien");
       model.addColumn("Jenis Kelamin");
       model.addColumn("Pelayanan");   
       profesi=UserSession.getProfesi();
       try{
            stt=CC.createStatement();
            if(profesi.equals("Dokter Umum")){
                rs = stt.executeQuery("SELECT * From pemeriksaan JOIN pasien ON pasien.id_pasien = pemeriksaan.id_pasien where pemeriksaan.pelayanan='Umum' AND DATE(tanggal) = CURDATE() ");
            }else{
               rs = stt.executeQuery("SELECT * From pemeriksaan JOIN pasien ON pasien.id_pasien = pemeriksaan.id_pasien where pemeriksaan.pelayanan='Bidan' AND DATE(tanggal) = CURDATE() "); 
            }
            while(rs.next()){
              idPeriksa = rs.getInt("id_pemeriksaan");
              pelayanan = rs.getString("pelayanan");
              idPasien = rs.getInt("id_pasien");
              diagnosa = rs.getString("diagnosa");
              nama = rs.getString("nama");
              gender = rs.getString("jenis_kelamin");
              tgl = rs.getDate("tanggal");
             model.addRow(new Object[]{idPeriksa,idPasien,nama,gender,pelayanan});
             table1.setModel(model);
            }
        }catch(SQLException e){
            e.printStackTrace();
        } 
           
    }
    
    private void updateDiagnosa(int idperiksa){
        try{
        sql= "Update pemeriksaan Set id_medis=?, diagnosa=? WHERE id_pemeriksaan="+idperiksa+" limit 1";
        pst = CC.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, idMedis);
        pst.setString(2, txtDiagnosa.getText());
        pst.execute();
        rs.close();
        pst.close();   
        }catch(SQLException e){
            System.err.println(e);
        }
    }
    private void getResep(int periksaID){
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtPeriksa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPasien = new javax.swing.JTextField();
        txtMedis = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDiagnosa = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnResep = new javax.swing.JButton();

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
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setHeaderValue("ID Pemeriksaan");
            table.getColumnModel().getColumn(3).setHeaderValue("Diagnosa");
            table.getColumnModel().getColumn(4).setHeaderValue("Tanggal");
        }

        jLabel1.setText("ID Periksa");

        jLabel2.setText("ID Pasien");

        jLabel3.setText("ID Tenaga Medis");

        jLabel4.setText("Diagnosa");

        txtDiagnosa.setColumns(20);
        txtDiagnosa.setRows(5);
        jScrollPane2.setViewportView(txtDiagnosa);

        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Periksa", "ID Pasien", "Pelayanan", "Nama Pasien", "Jenis Kelamin"
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

        btnResep.setText("Resep");
        btnResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnResep)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(17, 17, 17)
                                        .addComponent(txtPeriksa))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMedis, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMedis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel2))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jButton1)
                    .addComponent(btnResep))
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
        idPeriksa = Integer.parseInt(txtPeriksa.getText());
        idPasien=Integer.parseInt(txtPasien.getText());
        updateDiagnosa(idPeriksa);
        initDaftarPeriksa();
        rekamMedis(idPasien);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void table1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseReleased
        // TODO add your handling code here:
        int row = table1.getSelectedRow();
        table.clearSelection();
        pelayanan = (String)(table1.getModel().getValueAt(row,4)).toString();
        idPasien = Integer.parseInt((table1.getModel().getValueAt(row,1)).toString());
        idPeriksa = Integer.parseInt((table1.getModel().getValueAt(row,0)).toString());
        txtPeriksa.setText(String.valueOf(idPeriksa));
        txtPasien.setText(String.valueOf(idPasien));
        txtDiagnosa.setText("");
        rekamMedis(idPasien); 
    }//GEN-LAST:event_table1MouseReleased

    private void tableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseReleased
        // TODO add your handling code here:
        btnResep.setVisible(true);
        table1.clearSelection();
        int row = table.getSelectedRow();
        idPeriksa = Integer.parseInt((table.getModel().getValueAt(row,0)).toString());
        idPasien = Integer.parseInt((table.getModel().getValueAt(row,1)).toString());
        diagnosa = (String)(table.getModel().getValueAt(row,6)).toString();
        txtPeriksa.setText(String.valueOf(idPeriksa));
        txtPasien.setText(String.valueOf(idPasien));
        txtDiagnosa.setText(diagnosa);
       
    }//GEN-LAST:event_tableMouseReleased

    private void btnResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResepActionPerformed
        // TODO add your handling code here:
        idPeriksa = Integer.parseInt(txtPeriksa.getText());
        idPasien = Integer.parseInt(txtPasien.getText());
        Resep resep = new Resep(idPeriksa,idPasien);
        resep.setVisible(true);
    }//GEN-LAST:event_btnResepActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnResep;
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
    private javax.swing.JTable table;
    private javax.swing.JTable table1;
    private javax.swing.JTextArea txtDiagnosa;
    private javax.swing.JTextField txtMedis;
    private javax.swing.JTextField txtPasien;
    private javax.swing.JTextField txtPeriksa;
    // End of variables declaration//GEN-END:variables
}
