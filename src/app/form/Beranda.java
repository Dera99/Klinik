/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package app.form;

import app.chart.ModelChart;
import app.components.Form;
import app.configurations.koneksi;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Beranda extends Form {
    int idJadwal;
    String nama,hari,profesi;
    Time jamMulai,jamSelesai;
    public Beranda() {
        initComponents();
        txtUmum.setText(getUmum());
        txtBidan.setText(getBidan());
        initChart();
        initTable();
        

    }
    ResultSet rs = null;
    Connection CC = new koneksi().connect();
    PreparedStatement pst = null;
    Statement stt;
    String sql; 
    private void initChart(){
        try {
        chart.addLegend("Pasien Non-BPJS", new Color(12, 84, 175), new Color(0, 108, 247));
        chart.addLegend("Pasien BPJS", new Color(5, 125, 0), new Color(95, 209, 69));
         List<ModelChart> datas = getDataChart();
            for (int i = datas.size() - 1; i >= 0; i--) {
                chart.addData(datas.get(i));
            }
            chart.start();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    private List<ModelChart> getDataChart() throws SQLException {
        List<ModelChart> list = new ArrayList<>();
        sql = "SELECT DATE_FORMAT(tanggal,'%M') as M, SUM(pasien.kode_asuransi IS NULL) as Subtotal1, SUM(pasien.kode_asuransi IS NOT NULL) as Subtotal2 FROM pemeriksaan JOIN pasien ON pemeriksaan.id_pasien=pasien.id_pasien WHERE pemeriksaan.diagnosa IS NOT NULL GROUP BY DATE_FORMAT(tanggal,'%Y-%M') ORDER BY pemeriksaan.tanggal DESC LIMIT 6";
        pst = CC.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        rs = pst.executeQuery();
        while (rs.next()) {
            String month = rs.getString(1);
            double total = rs.getDouble(2); // non-bpjs
            double total2 = rs.getDouble(3); // bpjs
            list.add(new ModelChart(month, new double[]{total,total2}));
        }
        rs.close();
        pst.close();
        return list;
    }
    private void initTable(){
       DefaultTableModel model = new DefaultTableModel();
       model.addColumn("ID Jadwal");
       model.addColumn("Nama");
       model.addColumn("Hari");
       model.addColumn("Jam Praktik");
       model.addColumn("Pelayanan");
    try{
        DateFormat sdf = new SimpleDateFormat("H:mm"); 
        stt=CC.createStatement();
        rs = stt.executeQuery("SELECT * FROM tenaga_medis JOIN jadwal_pelayanan ON jadwal_pelayanan.id_medis = tenaga_medis.id_medis");
        int no = 0;
        while(rs.next()){
            idJadwal = rs.getInt("id_jadwal");
            nama = rs.getString("tenaga_medis.nama");
            hari = rs.getString("hari");
            jamMulai = rs.getTime("jam_mulai");
            jamSelesai =rs.getTime("jam_selesai");
            profesi = rs.getString("tenaga_medis.profesi");
            if(profesi.equals("Dokter Umum")){
                profesi="Umum";
            }
            model.addRow(new Object[]{idJadwal,nama,hari,sdf.format(jamMulai)+" - "+sdf.format(jamSelesai)+" WIB","Umum"});
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
    private String getUmum(){
      int total=0;
      try{
        sql="SELECT COUNT(*) as JumlahPasien FROM pemeriksaan WHERE pemeriksaan.pelayanan='Umum' AND DATE(pemeriksaan.tanggal) = CURDATE()";
        pst = CC.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        rs = pst.executeQuery();
        while (rs.next()) {
            total = rs.getInt(1);
        }
        rs.close();
        pst.close();
      }catch(SQLException ex){
        ex.printStackTrace();
      }
        return String.valueOf(total);
    }
    private String getBidan(){
      int total=0;
      try{
        sql="SELECT COUNT(*) as JumlahPasien FROM pemeriksaan WHERE pemeriksaan.pelayanan='Bidan' AND DATE(pemeriksaan.tanggal) = CURDATE()";
        pst = CC.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        rs = pst.executeQuery();
        while (rs.next()) {
            total = rs.getInt(1);
        }
        rs.close();
        pst.close();
      }catch(SQLException ex){
        ex.printStackTrace();
      }
        return String.valueOf(total);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel3 = new app.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        roundPanel2 = new app.swing.RoundPanel();
        jLabel7 = new javax.swing.JLabel();
        txtUmum = new javax.swing.JLabel();
        roundPanel4 = new app.swing.RoundPanel();
        jLabel8 = new javax.swing.JLabel();
        txtBidan = new javax.swing.JLabel();
        panelShadow1 = new app.swing.PanelShadow();
        chart = new app.chart.CurveChart();

        setBackground(new java.awt.Color(219, 232, 208));

        roundPanel3.setBackground(new java.awt.Color(54, 149, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nama Dokter", "Jenis Pelayanan", "Tanggal", "Jam Praktek"
            }
        ));
        jScrollPane1.setViewportView(table);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Jadwal Praktik");

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                        .addGap(21, 21, 21))
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        roundPanel2.setBackground(new java.awt.Color(54, 149, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Pasien Umum Hari Ini");

        txtUmum.setBackground(new java.awt.Color(255, 255, 255));
        txtUmum.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtUmum.setForeground(new java.awt.Color(255, 255, 255));
        txtUmum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtUmum.setText("jLabel1");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(txtUmum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(28, 28, 28)
                .addComponent(txtUmum)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        roundPanel4.setBackground(new java.awt.Color(54, 149, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Pasien Bidan Hari Ini");

        txtBidan.setBackground(new java.awt.Color(255, 255, 255));
        txtBidan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtBidan.setForeground(new java.awt.Color(255, 255, 255));
        txtBidan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtBidan.setText("jLabel1");

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBidan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(28, 28, 28)
                .addComponent(txtBidan)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        panelShadow1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.chart.CurveChart chart;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private app.swing.PanelShadow panelShadow1;
    private app.swing.RoundPanel roundPanel2;
    private app.swing.RoundPanel roundPanel3;
    private app.swing.RoundPanel roundPanel4;
    private javax.swing.JTable table;
    private javax.swing.JLabel txtBidan;
    private javax.swing.JLabel txtUmum;
    // End of variables declaration//GEN-END:variables
}
