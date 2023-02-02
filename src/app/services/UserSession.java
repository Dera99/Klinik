
package app.services;

public class UserSession {
    public static String getProfesi() {
        return profesi;
    }
    public static void setProfesi(String aProfesi) {
        profesi = aProfesi;
    }
    public static String getNamaMedis() {
        return namaMedis;
    }
    public static void setNamaMedis(String aNamaMedis) {
        namaMedis = aNamaMedis;
    }
    public static String getIdMedis() {
        return idMedis;
    }
    public static void setIdMedis(String aIdMedis) {
        idMedis = aIdMedis;
    }

    public static String getLevel() {
        return level;
    }
    public static void setLevel(String aLevel) {
        level = aLevel;
    }
    
    private static String userLogin;
    private static int UserId;
    private static String level;
    private static String idMedis;
    private static String namaMedis;
    private static String profesi;
    public static void setUserLogin(String userLogin) {
        UserSession.userLogin = userLogin;
    }
    public static void setUserId(int UserId) {
        UserSession.UserId = UserId;
    }
    
    public static String getUserLogin() {
        return userLogin;
    }
    public static int GetUserId(){
        return UserId;
    }
}