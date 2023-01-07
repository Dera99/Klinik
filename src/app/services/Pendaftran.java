
package app.services;

import app.configurations.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Pendaftran {
    ResultSet rs = null;
    Connection CC = new koneksi().connect();;
    PreparedStatement pst = null;
    Statement stt;
    String sql;
    public void insertPasien(){
        sql="INSERT INTO data_pasien (Nama, Tanggal_Lahir, Alamat, Telp, Kode_Asuransi)\n" +
        "SELECT * FROM (SELECT 'Nama', 'Tanggal_Lahir', 'Alamat', 'Telp', 'Kode_Asuransi') AS temp\n" +
        "WHERE NOT EXISTS (\n" +
        "    SELECT Nama, Tanggal_Lahir, Alamat, Telp, Kode_Asuransi FROM data_pasien WHERE Nama = 'Nama' AND Tanggal_Lahir = 'Tanggal_Lahir' AND Alamat = 'Alamat' AND Telp = 'Telp' AND Kode_Asuransi = 'Kode_Asuransi'\n" +
        ") LIMIT 1;\n" +
        "SELECT LAST_INSERT_ID() as IdPasien;";
        
    }
}
