
package app.services;

public class UserSession {

    public static String getLevel() {
        return level;
    }
    public static void setLevel(String aLevel) {
        level = aLevel;
    }
    
    private static String userLogin;
    private static int UserId;
    private static String level;
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