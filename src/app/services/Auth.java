package app.services;

import app.configurations.koneksi;
import app.main.Dashboard;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Auth {
    ResultSet rs = null;
    Connection CC = new koneksi().connect();
    PreparedStatement pst = null;
    Statement stt;
    String sql;
     public void Auth(String Username, String Password, JFrame frame){
        try{
            sql="SELECT * FROM accounts JOIN tenaga_medis ON tenaga_medis.id_user = accounts.id_user WHERE username = '"+Username+
            "' and password = '"+Password+"'";
            pst = CC.prepareStatement(sql);
            rs = pst.executeQuery();
            
        if(rs.next()){
                String user = rs.getString("username");
                String pass = rs.getString("password");
                String level = rs.getString("level");
                String idMedis = rs.getString("id_medis");
                String nama = rs.getString("nama");
                String profesi = rs.getString("profesi");
                int idUser = rs.getInt("accounts.id_user");
         if (Password.equals(pass) && Username.equals(user)){
                    JOptionPane.showMessageDialog(frame, "Login Berhasil");
                    UserSession.setLevel(level);
                    UserSession.setUserLogin(user); 
                    UserSession.setIdMedis(idMedis);
                    UserSession.setNamaMedis(nama);
                    UserSession.setProfesi(profesi);
                    UserSession.setUserId(idUser);
                        Dashboard a = new Dashboard();
                        a.setVisible(true); 
                        frame.dispose();
                
         }else{
                JOptionPane.showMessageDialog(frame, "Username atau Password anda salah");  
                //err.showNotification();
            }
        }else{
             JOptionPane.showMessageDialog(frame, "Username atau Password anda salah");  
                //err.showNotification();
        }
        }catch(SQLException e){
            System.err.println(e);
            JOptionPane.showMessageDialog(frame, "Username atau Password anda salah");
        }
    }
}
