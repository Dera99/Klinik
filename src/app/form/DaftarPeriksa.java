package app.form;

import app.configurations.koneksi;
import app.main.Resep;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class DaftarPeriksa extends javax.swing.JPanel {
    ResultSet rs = null;
    Connection CC = new koneksi().connect();;
    PreparedStatement pst = null;
    Statement stt;
    String sql; 
    
    public DaftarPeriksa() {
        initComponents();
        init();
        btnResep.setVisible(false);
    }
    String nama,pelayanan,gender,idMedis,medis,asuransi,diagnosa;
    Date tgl;
    int idPeriksa,idPasien;
    private void init() {
    try {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Periksa");
        model.addColumn("No");
        model.addColumn("ID Pasien");
        model.addColumn("Nama Pasien");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Pelayanan");
        model.addColumn("ID Medis");
        model.addColumn("Diagnosa");
        model.addColumn("Tanggal Periksa");
        stt=CC.createStatement();
        rs = stt.executeQuery("SELECT * From pemeriksaan JOIN pasien ON pasien.id_pasien = pemeriksaan.id_pasien WHERE DATE(pemeriksaan.tanggal) = CURDATE();");
        int no = 1;
        while (rs.next()) {
            idPeriksa = rs.getInt("id_pemeriksaan");
            pelayanan = rs.getString("pelayanan");
            idPasien = rs.getInt("id_pasien");
            idMedis = rs.getString("id_medis");
            diagnosa = rs.getString("diagnosa");
            nama = rs.getString("pasien.nama_pasien");
            gender = rs.getString("jenis_kelamin");
            tgl = rs.getDate("tanggal");
            model.addRow(new Object[]{idPeriksa, no++,idPasien, nama, gender, pelayanan, idMedis, diagnosa, tgl});
             table.setModel(model);
        }
        TableColumn column = table.getColumnModel().getColumn(0);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        btnResep = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setOpaque(false);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "ID Periksa", "ID Pasien", "Nama Pasien", "Jenis Kelamin", "Pelayanan", "ID Medis", "Kode Ausransi", "Tanggal Periksa"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        btnResep.setText("Lihat Resep");
        btnResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResepActionPerformed(evt);
            }
        });

        jLabel1.setText("Cari");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnResep)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 882, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnResep)
                .addGap(29, 29, 29))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseReleased
        // TODO add your handling code here:
        int row = table.getSelectedRow();
        idPeriksa = (int) table.getValueAt(row, 0);
        idPasien = (int) table.getValueAt(row, 2);
        idMedis = (String) table.getValueAt(row, 6);
        btnResep.setVisible(false);
        if(idMedis!=null){
            btnResep.setVisible(true);
        }
    }//GEN-LAST:event_tableMouseReleased

    private void btnResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResepActionPerformed
        // TODO add your handling code here:
        Resep resep = new Resep(idPeriksa,idPasien);
        resep.setVisible(true);
    }//GEN-LAST:event_btnResepActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        String value = txtSearch.getText();
        sorter.setRowFilter(RowFilter.regexFilter(value));
        table.setRowSorter(sorter);
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnResep;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
