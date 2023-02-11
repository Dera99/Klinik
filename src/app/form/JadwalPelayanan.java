package app.form;
import app.components.Form;
import app.configurations.koneksi;
import app.main.Resep;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class JadwalPelayanan extends Form {
    ResultSet rs = null;
    Connection CC = new koneksi().connect();;
    PreparedStatement pst = null;
    Statement stt;
    String sql; 
    
    String pelayanan,nama,hari,idMedis;
    Time jamMulai,jamSelesai;
    int idJadwal;
    public JadwalPelayanan() {
            initComponents();
            initTable1();
            initTable2();
            getNama(cbService);      
    }
    private void initTable1(){
       DefaultTableModel model = new DefaultTableModel();
       model.addColumn("ID Jadwal");
       model.addColumn("Nama Dokter");
       model.addColumn("Hari");
       model.addColumn("Jam Pelayanan");
       model.addColumn("Pelayanan");
    try{
        DateFormat sdf = new SimpleDateFormat("H:mm"); 
        stt=CC.createStatement();
        rs = stt.executeQuery("SELECT * FROM tenaga_medis JOIN jadwal_pelayanan ON jadwal_pelayanan.id_medis = tenaga_medis.id_medis WHERE profesi='Dokter Umum'");
        int no = 0;
        while(rs.next()){
            idJadwal = rs.getInt("id_jadwal");
            nama = rs.getString("tenaga_medis.nama");
            hari = rs.getString("hari");
            jamMulai = rs.getTime("jam_mulai");
            jamSelesai =rs.getTime("jam_selesai");
            model.addRow(new Object[]{idJadwal,nama,hari,sdf.format(jamMulai)+" - "+sdf.format(jamSelesai)+" WIB","Umum"});
            table1.setModel(model);
        } 
        TableColumn column = table1.getColumnModel().getColumn(0);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);
    }catch(SQLException e){
        e.printStackTrace();
    }
    }
     private void initTable2(){
       DefaultTableModel model = new DefaultTableModel();
       model.addColumn("ID Jadwal");
       model.addColumn("Nama Bidan");
       model.addColumn("Hari");
       model.addColumn("Jam Pelayanan");
       model.addColumn("Pelayanan");
    try{
        stt=CC.createStatement();
        rs = stt.executeQuery("SELECT * FROM tenaga_medis JOIN jadwal_pelayanan ON jadwal_pelayanan.id_medis = tenaga_medis.id_medis WHERE profesi='Bidan'");
        int no = 0;
        DateFormat sdf = new SimpleDateFormat("H:mm");
        while(rs.next()){
            idJadwal = rs.getInt("id_jadwal");
            nama = rs.getString("tenaga_medis.nama");
            hari = rs.getString("hari");
            jamMulai = rs.getTime("jam_mulai");
            jamSelesai =rs.getTime("jam_selesai");
            model.addRow(new Object[]{idJadwal,nama,hari,sdf.format(jamMulai)+" - "+sdf.format(jamSelesai)+" WIB","Bidan"});
            table2.setModel(model);
        } 
        TableColumn column = table2.getColumnModel().getColumn(0);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);
    }catch(SQLException e){
        e.printStackTrace();
    }
    }
    private void getNama(JComboBox service){
        String profesi = "Bidan";
        if(service.getSelectedItem().equals("Umum")){
            profesi = "Dokter Umum";
        }
        cbNama.removeAllItems();
        try{
            stt=CC.createStatement();
            rs = stt.executeQuery("SELECT * FROM tenaga_medis WHERE tenaga_medis.profesi='"+profesi+"'");
            while(rs.next()){
                nama = rs.getString("nama");  
                cbNama.addItem(nama);
            }
        }catch(SQLException e){
            System.err.println(e);
        } 
    }
    private String getIdMedis(JComboBox name){
        String nama = (String) name.getSelectedItem();
        try{
            stt=CC.createStatement();
            rs = stt.executeQuery("SELECT * FROM tenaga_medis WHERE tenaga_medis.nama='"+nama+"'");
            while(rs.next()){
                idMedis = rs.getString("id_medis");  
                cbNama.addItem(nama);
            }
        }catch(SQLException e){
            System.err.println(e);
        } 
        return idMedis;
    }
    private void addData(){
        nama = (String) cbNama.getSelectedItem();
        hari = txtHari.getText();
        Date start = (Date) mulai.getValue();
        Date end = (Date) selesai.getValue();
        jamMulai = new Time(start.getHours(),0,0);
        jamSelesai = new Time(end.getHours(),0,0);
        idMedis = getIdMedis(cbNama);
        try {
            String query = "INSERT INTO jadwal_pelayanan (id_medis,hari,jam_mulai,jam_selesai) VALUES (?,?,?,?)";
            pst = CC.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, idMedis);
            pst.setString(2, hari);
            pst.setTime(3, jamMulai);
            pst.setTime(4, jamSelesai);
            pst.execute();
            rs.close();
            pst.close();
            JOptionPane.showMessageDialog(this, "Jadwal Berhasil Ditambahkan !");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex);
        }
    }
    private void updateData(){
        nama = (String) cbNama.getSelectedItem();
        hari = txtHari.getText();
        Date start = (Date) mulai.getValue();
        Date end = (Date) selesai.getValue();
        jamMulai = new Time(start.getHours(),0,0);
        jamSelesai = new Time(end.getHours(),0,0);
        idMedis = getIdMedis(cbNama);
        try {
            sql="UPDATE jadwal_pelayanan SET id_medis='"+idMedis+"', hari='"+hari+"', jam_mulai='"+jamMulai+"', jam_selesai='"+jamSelesai+"' WHERE id_Jadwal="+idJadwal+"";
            pst = CC.prepareStatement(sql);
            pst.execute();
        } catch (SQLException ex) {
           ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex);
        }
    }
    private void deleteData(){
         try {
            sql = "DELETE FROM jadwal_pelayanan WHERE id_jadwal = '"+idJadwal+"'";
            pst = CC.prepareStatement(sql);
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnUpdate = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        cbService = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        txtHari = new javax.swing.JTextField();
        cbNama = new javax.swing.JComboBox<>();
        mulai = new javax.swing.JSpinner();
        selesai = new javax.swing.JSpinner();
        jScrollPane3 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();

        btnUpdate.setText("Ubah");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
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
                "Nama Dokter", "Hari ", "Jam", "Pelayanan"
            }
        ));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                table1MouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(table1);

        cbService.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Umum", "Bidan" }));
        cbService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbServiceActionPerformed(evt);
            }
        });

        jLabel2.setText("Jenis Pelayanan");

        jLabel3.setText("Nama");

        jLabel4.setText("Pelayanan Dokter");

        jLabel5.setText("Pelayanan Bidan");

        jLabel6.setText("Hari Kerja");

        jLabel7.setText("Jam Pelayanan");

        jLabel8.setText(":");

        jLabel9.setText(":");

        jLabel10.setText(":");

        jLabel11.setText(":");

        jLabel12.setText("Jam Mulai");

        jLabel13.setText("Jam Selesai");

        btnDelete.setText("Hapus");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnAdd.setText("Simpan");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Jadwal", "Nama Bidan", "Hari ", "Jam", "Pelayanan"
            }
        ));
        jScrollPane3.setViewportView(table2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mulai, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(50, 50, 50)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtHari, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addGap(18, 18, 18)
                                            .addComponent(cbService, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addGap(18, 18, 18)
                                            .addComponent(cbNama, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selesai, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(jLabel4))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jLabel5))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9)
                    .addComponent(cbNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel11)
                    .addComponent(mulai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selesai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate))
                .addGap(40, 40, 40))
        );

        try{
            DateFormat sdf = new SimpleDateFormat("H:mm");
            Date date = sdf.parse("0:00");
            SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.MINUTE);

            mulai.setModel(sm);
            JSpinner.DateEditor de = new JSpinner.DateEditor(mulai, "H:mm");
            de.getTextField().setEditable( false );
            mulai.setEditor(de);
        } catch (ParseException ex) {
            Logger.getLogger(JadwalPelayanan.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            DateFormat sdf = new SimpleDateFormat("H:mm");
            Date date = sdf.parse("0:00");
            SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.MINUTE);

            selesai.setModel(sm);
            JSpinner.DateEditor de = new JSpinner.DateEditor(selesai, "H:mm");
            de.getTextField().setEditable( false );
            selesai.setEditor(de);
        } catch (ParseException ex) {
            Logger.getLogger(JadwalPelayanan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        addData();
        initTable1();
        initTable2();
    }//GEN-LAST:event_btnAddActionPerformed

    private void cbServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbServiceActionPerformed
        // TODO add your handling code here:
        getNama(cbService);
    }//GEN-LAST:event_cbServiceActionPerformed

    private void table1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseReleased
        try {
            // TODO add your handling code here:
            int row = table1.getSelectedRow();
            idJadwal =  (int) table1.getValueAt(row, 0);
            nama = (String) table1.getValueAt(row, 1);
            hari = (String) table1.getValueAt(row, 2);
            String value = (String) table1.getValueAt(row, 3);
            String[] parts = value.split(" - ");
            SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
            jamMulai = new Time(sdf.parse(parts[0]).getTime());
            jamSelesai = new Time(sdf.parse(parts[1]).getTime());
            pelayanan = (String) table1.getValueAt(row, 4);
            cbService.setSelectedItem(pelayanan);
            cbNama.setSelectedItem(nama);
            txtHari.setText(hari);
            mulai.setValue(jamMulai);
            selesai.setValue(jamSelesai);
            System.out.println("ID Jadwal "+idJadwal);
        } catch (ParseException ex) {
            Logger.getLogger(JadwalPelayanan.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_table1MouseReleased

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(this, "Apakah Anda Yakin?", "Konfirmasi Update Data", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response==JOptionPane.YES_OPTION){
        updateData();
        initTable1();
        initTable2();
        }else if(response==JOptionPane.NO_OPTION){
              System.err.println("Failed");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(this, "Apakah Anda Yakin?", "Konfirmasi Hapus Data", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response==JOptionPane.YES_OPTION){
        deleteData();
        initTable1();
        initTable2();
        }else if(response==JOptionPane.NO_OPTION){
              System.err.println("Failed");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbNama;
    private javax.swing.JComboBox<String> cbService;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner mulai;
    private javax.swing.JSpinner selesai;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTextField txtHari;
    // End of variables declaration//GEN-END:variables
}
