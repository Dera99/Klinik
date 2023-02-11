package app.form;

import app.components.Form;
import app.configurations.koneksi;
import app.main.Resep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
public class TenagaMedis extends Form {

    ResultSet rs = null;
    Connection CC = new koneksi().connect();;
    PreparedStatement pst = null;
    Statement stt;
    String sql; 
    public TenagaMedis() {
        initComponents();
        initTable();
    }
    String idMedis,nama,alamat,telp,email,profesi,username;
    int idUser;
    private void initTable(){
       DefaultTableModel model = new DefaultTableModel();
       model.addColumn("ID Tenaga Medis");
       model.addColumn("Nama");
       model.addColumn("Alamat");
       model.addColumn("No Telp");
       model.addColumn("Email");
       model.addColumn("Profesi");
       model.addColumn("User ID");
       model.addColumn("Username");
    try{
        stt=CC.createStatement();
        rs = stt.executeQuery("SELECT * FROM tenaga_medis JOIN accounts ON accounts.id_user = tenaga_medis.id_user");
        int no = 0;
        while(rs.next()){
            idMedis = rs.getString(1);
            nama = rs.getString(2);
            alamat = rs.getString(3);
            telp = rs.getString(4);
            email =rs.getString(5);
            profesi = rs.getString(6);
            idUser = rs.getInt(7);
            username=rs.getString("username");
            model.addRow(new Object[]{idMedis,nama,alamat,telp,email,profesi,idUser,username});
            table.setModel(model);
        } 
        TableColumn column = table.getColumnModel().getColumn(6);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);
        TableColumn column1 = table.getColumnModel().getColumn(7);
        column1.setMinWidth(0);
        column1.setMaxWidth(0);
        column1.setPreferredWidth(0);
    }catch(SQLException e){
        e.printStackTrace();
    }
   }
    private void addAccount(){
        username = txtUser.getText();
        profesi = (String) txtProfesi.getSelectedItem();
        if(profesi.equals("Dokter Umum")){
            profesi = "Dokter";
        }
        try{
            sql="INSERT INTO accounts (username,password,level) VALUES (?,?,?)";
            pst = CC.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1,username);
            pst.setString(2,username);
            pst.setString(3,profesi);
            pst.execute();
            rs = pst.getGeneratedKeys();
            rs.first();
            idUser = rs.getInt(1);
            updateUser();
            rs.close();
            pst.close();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    private  void addData(){
        nama = txtNama.getText();
        alamat = txtAlamat.getText();
        email = txtEmail.getText();
        telp = txtTelp.getText();
        username = txtUser.getText();
        profesi = (String) txtProfesi.getSelectedItem();
        try {
            String query = "INSERT INTO tenaga_medis (id_medis, nama, alamat, nomor_telepon, email, profesi) VALUES (?, ?, ?, ?, ?, ?)";
            pst = CC.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, generateId(profesi));
            pst.setString(2, nama);
            pst.setString(3, alamat);
            pst.setString(4, telp);
            pst.setString(5, email);
            pst.setString(6, profesi);
            pst.execute();
            rs.close();
            pst.close();
            JOptionPane.showMessageDialog(this, "Data Berhasil Ditambahkan !");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal Menambahkan Data !");
            Logger.getLogger(TenagaMedis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void updateUser(){
        try {
            sql="UPDATE tenaga_medis SET id_user = "+idUser+" WHERE id_medis='"+idMedis+"'";
            pst = CC.prepareStatement(sql);
            pst.execute();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }
    private String generateId(String profesi){
         try {
            stt=CC.createStatement(); 
            String countQuery = "SELECT COUNT(id_medis) FROM tenaga_medis";
            rs = stt.executeQuery(countQuery);
            int count = 0;    
            if (rs.next()) {
                count = rs.getInt(1) + 1;
            }
            idMedis = "ADM" + String.format("%04d", count);
            if (profesi.equals("Dokter Umum")) {
                idMedis = "DR" + String.format("%04d", count);
            } else if (profesi.equals("Bidan")){
                idMedis = "BDN" + String.format("%04d", count);
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
           JOptionPane.showMessageDialog(this, ex);
        }
        return idMedis;
    }
    
    private void updateData(){
        nama = txtNama.getText();
        alamat = txtAlamat.getText();
        email = txtEmail.getText();
        telp = txtTelp.getText();
        profesi = (String) txtProfesi.getSelectedItem();
        username = txtUser.getText();
        
        try {
            sql="UPDATE tenaga_medis SET id_medis='"+generateId(profesi)+"' ,nama = '"+nama+"', alamat='"+alamat+"', email='"+email+"', nomor_telepon='"+telp+"', profesi='"+profesi+"' WHERE id_user="+idUser+"";
            pst = CC.prepareStatement(sql);
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
           ex.printStackTrace();
           JOptionPane.showMessageDialog(this, ex);
        }
    }
    private void deleteData(){
         try {
            sql = "DELETE FROM tenaga_medis WHERE id_medis = '"+idMedis+"'";
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

        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        txtNama = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTelp = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtProfesi = new javax.swing.JComboBox<>();
        lblUser = new javax.swing.JLabel();
        titik = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();

        setBackground(new java.awt.Color(241, 253, 243));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Tenaga Medis", "Nama", "Alamat", "No Telp", "Email", "Profesi", "User ID"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(table);

        jLabel3.setText("Nama");

        jLabel7.setText("Email");

        jButton3.setText("Ubah");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel9.setText(":");

        jButton4.setText("Tambah");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel10.setText(":");

        jLabel11.setText(":");

        jButton2.setText("Hapus");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Alamat");

        txtAlamat.setColumns(20);
        txtAlamat.setRows(5);
        jScrollPane3.setViewportView(txtAlamat);

        jLabel12.setText(":");

        jLabel8.setText("No Telp");

        jLabel13.setText("Profesi");

        jLabel14.setText(":");

        txtProfesi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dokter Umum", "Bidan", "Admin" }));

        lblUser.setText("Username");

        titik.setText(":");

        jButton5.setText("Refresh");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel13)
                                    .addComponent(lblUser))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(titik))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtProfesi, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(101, 101, 101))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton4)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton3)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton2)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(txtEmail))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5))))
                    .addComponent(jScrollPane2))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProfesi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUser)
                            .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(titik)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton5))
                .addContainerGap(54, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        addData();
        addAccount();
        initTable();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(this, "Apakah Anda Yakin?", "Konfirmasi Update Data", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response==JOptionPane.YES_OPTION){
        updateData();
        initTable();
         }else if(response==JOptionPane.NO_OPTION){
              System.err.println("Failed");
        }   
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseReleased
        // TODO add your handling code here:
        int row = table.getSelectedRow();
        lblUser.setVisible(false);
        titik.setVisible(false);
        txtUser.setVisible(false);
        idMedis = (String) table.getValueAt(row, 0);
        nama = (String) table.getValueAt(row, 1);
        alamat = (String) table.getValueAt(row, 2);
        telp = (String) table.getValueAt(row, 3);
        email = (String) table.getValueAt(row, 4);
        profesi = (String) table.getValueAt(row, 5);
        idUser = (Integer) table.getValueAt(row, 6);
        username = (String) table.getValueAt(row, 7);
        txtNama.setText(nama);
        txtAlamat.setText(alamat);
        txtTelp.setText(telp);
        txtEmail.setText(email);
        txtProfesi.setSelectedItem(profesi);
        txtUser.setText(username);
    }//GEN-LAST:event_tableMouseReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(this, "Apakah Anda Yakin?", "Konfirmasi Hapus Data", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response==JOptionPane.YES_OPTION){
        deleteData();
        initTable();
         }else if(response==JOptionPane.NO_OPTION){
              System.err.println("Failed");
        }   
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        table.clearSelection();
        lblUser.setVisible(true);
        titik.setVisible(true);
        txtUser.setVisible(true);
        txtNama.setText("");
        txtAlamat.setText("");
        txtTelp.setText("");
        txtEmail.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblUser;
    private javax.swing.JTable table;
    private javax.swing.JLabel titik;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNama;
    private javax.swing.JComboBox<String> txtProfesi;
    private javax.swing.JTextField txtTelp;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
