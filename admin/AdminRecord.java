package admin;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDateTime;

import utility.*;

public class AdminRecord {
    public static void registerAdmin(Scanner sc,AdminList adm) {
        adm.load("data/admin.txt");
        ConsoleUtil.clearScreen();
        System.out.println("======= ADMIN REGISTER =======");

        String admId = generateAdminID(adm);
        System.out.println("\nAdmin ID       : ADM" + admId);
        String adminId = "ADM" + admId;
        System.out.print("Enter Username : ");
        String username = sc.nextLine();
        if (username == null || username.trim().isEmpty()) {
            System.out.println(Constants.RED+"Error : Name must contain only letters and spaces"+Constants.RESET);
            return;
        }

        System.out.print("Enter Password : ");
        String password = sc.nextLine();
        if (password == null || password.trim().isEmpty()) {
            System.out.println(Constants.RED+"Error : Password cannot be empty"+Constants.RESET);
            return;
        }
        DateTimeFormatter time = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String currentTime = LocalDateTime.now().format(time);
        try (FileWriter fw = new FileWriter("data/admin.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.println(adminId +"|"+username + "|" + password + "|" + currentTime);

            out.flush();
            ConsoleUtil.clearScreen();
            //System.out.println(Constants.GREEN+"Admin registered successfully" + Constants.RESET);
        } catch (IOException e) {
            System.out.println(Constants.RED+"Error : " + e.getMessage() + Constants.RESET);
        }
        //adm.save("data/admin.txt");
    }

    public static boolean validateAdmin(String adminId, String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/admin.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 3 && parts[0].equals(adminId) && parts[1].equals(username) && parts[2].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error : " + e.getMessage());
        }
        return false;
    }

    static String generateAdminID(AdminList adminData) {
        int maxId = 0;
        AdminList.Node current = adminData.head;
        while (current != null) {
            String id = current.data.getId();
            if (id.startsWith("ADM")) {
                try {
                    int num = Integer.parseInt(id.substring(3));
                    maxId = Math.max(maxId, num);
                } catch (NumberFormatException e) {
                    // Bisa log error kalau perlu
                }
            }
            current = current.next;
        }
        int newId = maxId + 1;
        return String.valueOf(newId);
    }


    public static void adminLogin(Scanner sc, String adminId, String username, String password) {
        boolean isLogin = false;

        do {
            ConsoleUtil.clearScreen();
            if (validateAdmin(adminId, username, password)) {
                System.out.println("======= ADMIN LOGIN =======");
                System.out.println("Admin ID : " + adminId);
                System.out.println("Username : " + username);
                System.out.println(Constants.GREEN + "\nAdmin logged in successfully\n" + Constants.RESET);
                isLogin = true;
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/loginadmin.txt", true))) {
                    String loginTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
                    String line = String.format("%s|%s|%s|%s", adminId, username, password, loginTime);
                    writer.write(line);
                    writer.newLine();
                } catch (IOException e) {
                    System.out.println(Constants.RED + "Error writing to TXT : " + e.getMessage() + Constants.RESET);
                }
            } else {
                System.out.print(Constants.RED + "Login failed: Invalid admin id, username or password" + Constants.RESET);
                System.out.print("");
                sc.nextLine();
            }
        } while (!isLogin);
    }

    public static void adminLogout(String adminId, String username) {
        String logoutTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/logoutadmin.txt", true))) {
            String line = String.format("%s|%s|%s", adminId, username, logoutTime);
            writer.write(line);
            writer.newLine();
            System.out.println("Admin ID      : "+adminId);
            System.out.println("Username      : "+username);
            System.out.println("Log out Time  :"+logoutTime);
            System.out.println(Constants.GREEN + "\nAdmin logged out successfully\n" + Constants.RESET);
        } catch (IOException e) {
            System.out.println(Constants.RED + "Error writing logout info: " + e.getMessage() + Constants.RESET);
        }
    }
}
