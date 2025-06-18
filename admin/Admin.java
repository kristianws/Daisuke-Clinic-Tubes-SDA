package admin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Admin {
    String id;
    String username;
    String password;
    LocalDateTime LoginTime;

    public Admin(String id, String username, String password, LocalDateTime LoginTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.LoginTime = LoginTime;
    }

    public String getId() {
        if (!id.startsWith("ADM")) {
            return "ADM" + id;
        } else {
            return id;
        }
    }

    public static LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }

    public String getName() { return username; }
    public String getPassword() { return password; }
    public LocalDateTime getLoginTime() { return LoginTime; }

    public void display() {
        System.out.println(
                "Admin ID   : " + id + "\n"+
                "Username   : " + username + "\n" +
                "Login Time : " + getDateTime());
    }

    public String toString() {
        DateTimeFormatter time = DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm:ss)");
        String Time = (LoginTime != null) ? LoginTime.format(time) : "Not logged in";
        return "Admin ID        : " +  id + "\n" +
               "Username        : " + username + "\n" +
               "Registered Time : " + Time;
    }
}
