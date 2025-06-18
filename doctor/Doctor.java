package doctor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Doctor {
    String id;
    String name;
    String speciality;
    String password;
    LocalDateTime LoginTime;

    public Doctor(String id, String name, String speciality, String password, LocalDateTime LoginTime) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
        this.password = password;
        this.LoginTime = LoginTime;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getSpeciality() { return speciality; }
    public String getPassword() { return password; }
    public LocalDateTime getLoginTime() { return LoginTime; }

    public void display() {
        System.out.println(
                "Doctor ID  : " + id+"\n"+
                "Name       : " + name + "\n"+
                "Speciality : " + speciality + "\n" +
                "Login Time : " + getLoginTime());
    }

    public String toString() {
        DateTimeFormatter time = DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm:ss)");
        String Time = (LoginTime != null) ? LoginTime.format(time) : "Not logged in";
        return "Name          : " + name +" (ID : " + id + ")\n" + 
               "Speciality    : " + speciality + "\n" +
               "Login Time    : " + Time;
    }
}