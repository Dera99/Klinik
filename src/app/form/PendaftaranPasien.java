
package app.form;

import app.components.Form;
import app.configurations.koneksi;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PendaftaranPasien extends Form {
    ResultSet rs = null;
    Connection CC = new koneksi().connect();;
    PreparedStatement pst = null;
    Statement stt;
    String sql;        
    public PendaftaranPasien() {
        initComponents();  
        radioBPJS.setSelected(true);
        radioDokter.setSelected(true);
    }
    String nama;
    private Date ttl;
    String alamat;
    String telp;
    String gender;
    String kode;
    int idPasien,idPeriksa;
    String pelayanan = "Umum";
    private void cariPasien(String kode) throws SQLException{
        sql="SELECT * FROM pasien WHERE kode_asuransi = '"+kode+"'";
        pst = CC.prepareStatement(sql);
        rs = pst.executeQuery();
        if(rs.next()){
            String nama = rs.getString("nama_pasien");
            String alamat = rs.getString("alamat");
            String no_telp = rs.getString("no_telp");
            String jenis_kelamin = rs.getString("jenis_kelamin");
            String ttl = rs.getString("ttl");
            String asuransi = rs.getString("kode_asuransi");
            txtNama.setText(nama);
            txtAlamat.setText(alamat);
            txtTelp.setText(no_telp);
            cbGender.setSelectedItem(jenis_kelamin);
            txtTanggal.setText(ttl);
            JOptionPane.showMessageDialog(this, "Pasien Ditemukan !");
        }else{
            JOptionPane.showMessageDialog(this, "Pasien Tidak Ditemukan !");
        }
    }
    private void daftarPasien() throws SQLException, ParseException{
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        nama = txtNama.getText();
        ttl = sdf.parse(txtTanggal.getText());
        java.sql.Date sqlDate = new java.sql.Date(ttl.getTime());
        alamat = txtAlamat.getText();
        telp = txtTelp.getText();
        gender = (String) cbGender.getSelectedItem();
        if(radioPribadi.isSelected()){
        kode=null;
        }else{
        kode=txtKode.getText();
        }
            sql = "SELECT id_pasien FROM pasien WHERE nama_pasien=? AND alamat=? AND no_telp=? AND jenis_kelamin=? AND ttl=? AND kode_asuransi=?";
            pst = CC.prepareStatement(sql);
            pst.setString(1, nama);
            pst.setString(2, alamat);
            pst.setString(3, telp);
            pst.setString(4, gender);
            pst.setDate(5, sqlDate);
            pst.setString(6, kode);
            rs = pst.executeQuery();
            if(rs.next()) {
            idPasien = rs.getInt("id_pasien");
        } else {
            sql = "INSERT INTO pasien (nama_pasien,alamat,no_telp,jenis_kelamin,ttl,kode_asuransi)VALUES(?,?,?,?,?,?)";
            pst = CC.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, nama);
            pst.setString(2, alamat);
            pst.setString(3, telp);
            pst.setString(4, gender);
            pst.setDate(5, sqlDate);
            pst.setString(6, kode);
            pst.execute();
            rs = pst.getGeneratedKeys();
            rs.first();
            idPasien = rs.getInt(1);
        }
        rs.close();
        pst.close();
    }
    private void daftarPemeriksaan() throws SQLException{
        if(radioDokter.isSelected()){
           pelayanan="Umum";
        }else{
           pelayanan="Bidan";
        }
        sql="INSERT INTO pemeriksaan(id_pasien,pelayanan) VALUES (?,?)";
        pst = CC.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, idPasien);
        pst.setString(2, pelayanan);
        pst.execute();
        rs = pst.getGeneratedKeys();
        rs.first();
        idPeriksa = rs.getInt(1);
        rs.close();
        pst.close();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        txtNama = new javax.swing.JTextField();
        txtAlamat = new javax.swing.JTextField();
        txtTanggal = new javax.swing.JTextField();
        txtTelp = new javax.swing.JTextField();
        radioPribadi = new javax.swing.JRadioButton();
        radioBPJS = new javax.swing.JRadioButton();
        txtKode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        radioBidan = new javax.swing.JRadioButton();
        radioDokter = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cbGender = new javax.swing.JComboBox<>();
        btnCek = new javax.swing.JButton();
        lblKode = new javax.swing.JLabel();

        dateChooser1.setDateFormat("yyyy-MM-dd");
        dateChooser1.setTextRefernce(txtTanggal);

        txtNama.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        txtAlamat.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        txtTanggal.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        txtTelp.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        radioPribadi.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        radioPribadi.setText("Pribadi");
        radioPribadi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioPribadiActionPerformed(evt);
            }
        });

        radioBPJS.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        radioBPJS.setText("BPJS");
        radioBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBPJSActionPerformed(evt);
            }
        });

        txtKode.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setText("Nama");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setText("Tanggal Lahir");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel4.setText("Alamat");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel5.setText("No. Telpon");

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel6.setText("Jenis Pembayaran");

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel7.setText("Jenis Pelayanan");

        radioBidan.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        radioBidan.setText("Bidan");
        radioBidan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBidanActionPerformed(evt);
            }
        });

        radioDokter.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        radioDokter.setText("Umum");
        radioDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioDokterActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(196, 229, 236));
        jButton1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jButton1.setText("Batal");

        jButton2.setBackground(new java.awt.Color(196, 229, 236));
        jButton2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jButton2.setText("Simpan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel8.setText("Jenis Kelamin");

        cbGender.setBackground(new java.awt.Color(196, 229, 236));
        cbGender.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-Laki", "Perempuan" }));

        btnCek.setBackground(new java.awt.Color(196, 229, 236));
        btnCek.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnCek.setText("Cek");
        btnCek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekActionPerformed(evt);
            }
        });

        lblKode.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblKode.setText("Kode BPJS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(lblKode, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioPribadi)
                    .addComponent(radioBPJS)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCek))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNama)
                                .addComponent(txtAlamat)
                                .addComponent(txtTanggal)
                                .addComponent(txtTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(124, 124, 124)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioBidan)
                            .addComponent(radioDokter)
                            .addComponent(jLabel7))))
                .addContainerGap(92, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(48, 48, 48))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(radioDokter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioBidan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioPribadi)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioBPJS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCek)
                    .addComponent(lblKode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(45, 45, 45))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void radioBidanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBidanActionPerformed
        // TODO add your handling code here:
        radioBidan.setSelected(true);
        radioDokter.setSelected(false);
    }//GEN-LAST:event_radioBidanActionPerformed

    private void radioPribadiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPribadiActionPerformed
        // TODO add your handling code here:
        radioBPJS.setSelected(false);
        radioPribadi.setSelected(true);
        txtKode.setVisible(false);
        btnCek.setVisible(false);
        lblKode.setVisible(false);
    }//GEN-LAST:event_radioPribadiActionPerformed

    private void radioBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBPJSActionPerformed
        // TODO add your handling code here:
        radioPribadi.setSelected(false);
        radioBPJS.setSelected(true);
        txtKode.setVisible(true);
        btnCek.setVisible(true);
        lblKode.setVisible(true);
    }//GEN-LAST:event_radioBPJSActionPerformed

    private void radioDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioDokterActionPerformed
        // TODO add your handling code here:
        radioDokter.setSelected(true);
        radioBidan.setSelected(false);
    }//GEN-LAST:event_radioDokterActionPerformed

    private void btnCekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekActionPerformed
        try {
            // TODO add your handling code here:
            cariPasien(txtKode.getText());
        } catch (SQLException ex) {
            Logger.getLogger(PendaftaranPasien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCekActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:
            daftarPasien();
            daftarPemeriksaan();
            JOptionPane.showMessageDialog(this, "Pendaftaran Pasien Berhasil !");
        } catch (SQLException ex) {
            Logger.getLogger(PendaftaranPasien.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PendaftaranPasien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCek;
    private javax.swing.JComboBox<String> cbGender;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblKode;
    private javax.swing.JRadioButton radioBPJS;
    private javax.swing.JRadioButton radioBidan;
    private javax.swing.JRadioButton radioDokter;
    private javax.swing.JRadioButton radioPribadi;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtTanggal;
    private javax.swing.JTextField txtTelp;
    // End of variables declaration//GEN-END:variables
}
