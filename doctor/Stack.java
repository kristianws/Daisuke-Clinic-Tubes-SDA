package doctor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.io.*;
import utility.*;

public class Stack {
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private boolean isLoading = false;

    public class Node {
        Doctor doctor;
        Node next;

        Node(Doctor doctor) {
            this.doctor = doctor;
            this.next = null;
        }
    }

    public class DoctorNode {
        String id;
        Doctor doctor;
        DoctorNode next;

        DoctorNode(String id, Doctor doctor) {
            this.id = id;
            this.doctor = doctor;
            this.next = null;
        }
    }

    Node top;
    DoctorNode registeredDoctorsHead;

    public Stack() {
        this.top = null;
        this.registeredDoctorsHead = null;
    }

    public void registerDoctor(Doctor doctor) {
        DoctorNode newNode = new DoctorNode(doctor.getId(), doctor);

        if (registeredDoctorsHead == null) {
            registeredDoctorsHead = newNode;
        } else {
            DoctorNode current = registeredDoctorsHead;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        save("data/doctor.txt");
    }

    public void push(Doctor doctor) {
        Node current = top;
        Node prev = null;
        while (current != null) {
            if (current.doctor.getId().equals(doctor.getId())) {
                current.doctor.LoginTime = doctor.getLoginTime();
                return;
            }
            prev = current;
            current = current.next;
        }
        Node newNode = new Node(doctor);
        newNode.next = top;
        top = newNode;
        saveLastLogin();
    }

    public Doctor outDoctor(String doctorId, String docName) {
        Doctor loggedOut = null;
        DoctorLinkedList remainingDoctors = new DoctorLinkedList();

        try (BufferedReader reader = new BufferedReader(new FileReader("data/logindoctor.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String id = parts[0];
                    String name = parts[1];
                    String speciality = parts[2];
                    LocalDateTime loginTime = LocalDateTime.parse(parts[3], TIME_FORMATTER);

                    if (id.equals(doctorId) && name.equals(docName)) {
                        loggedOut = new Doctor(id, name, speciality, "", loginTime);
                    } else {
                        remainingDoctors.addLast(new Doctor(id, name, speciality, "", loginTime));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(Constants.RED + "Error reading logindoctor.txt: " + e.getMessage() + Constants.RESET);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/logindoctor.txt"))) {
            for (int i = 0; i < remainingDoctors.size(); i++) {
                Doctor d = remainingDoctors.get(i);
                writer.write(String.format("%s|%s|%s|%s",
                        d.getId(),
                        d.getName(),
                        d.getSpeciality(),
                        d.getLoginTime().format(TIME_FORMATTER)));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(Constants.RED + "Error writing to logindoctor.txt: " + e.getMessage() + Constants.RESET);
        }
        return loggedOut;
    }

    public Doctor peek() {
        if (isEmpty()) {
            return null;
        }
        return top.doctor;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public Doctor getDoctorById(String id) {
        Node current = top;
        while (current != null) {
            if (current.doctor.getId().equals(id)) {
                return current.doctor;
            }
            current = current.next;
        }
        return getRegisteredDoctor(id);
    }

    public void findDoctorById(String id) {
        load("data/doctor.txt");
        boolean found = false;

        id = id.trim().toUpperCase();
        if (!id.startsWith("D")) {
            id = "D" + id;
        }
        Node current = top;
        while (current != null) {
            if (current.doctor.getId().equalsIgnoreCase(id)) {
                System.out.println(Constants.GREEN + "Doctor ID is Found!\n" + Constants.RESET);
                System.out.println("Doctor ID  : " + current.doctor.getId() + "\n" +
                        "Name       : " + current.doctor.getName() + "\n" +
                        "Speciality : " + current.doctor.getSpeciality());
                found = true;
                break;
            }
            current = current.next;
        }
        if (!found) {
            DoctorNode regCurrent = registeredDoctorsHead;
            while (regCurrent != null) {
                if (regCurrent.doctor.getId().equalsIgnoreCase(id)) {
                    System.out.println(Constants.GREEN + "Doctor ID is Found!\n" + Constants.RESET);
                    System.out.println("Doctor ID  : " + regCurrent.doctor.getId() + "\n" +
                            "Name       : " + regCurrent.doctor.getName() + "\n" +
                            "Speciality : " + regCurrent.doctor.getSpeciality());
                    found = true;
                    break;
                }
                regCurrent = regCurrent.next;
            }
        }
        if (!found) {
            System.out.println(Constants.RED + "No doctor found with ID: " + id + Constants.RESET);
        }
    }

    public static boolean isClinicClosed() {
        LocalTime now = LocalTime.now();
        return now.isBefore(LocalTime.of(7, 0)) || now.isAfter(LocalTime.of(21, 0));
    }

    public void clearLastLoginFile() {
        String filename = "data/logindoctor.txt";
        load(filename);
        try (FileWriter fw = new FileWriter(filename, false)) {
            fw.write("");
            // System.out.println("Login doctor file has been cleared");
        } catch (IOException e) {
            System.out.println("Error clearing last login file: " + e.getMessage());
        }
        saveLastLogin();
    }

    public void autoClearLoginIfClosed() {
        if (isClinicClosed()) {
            clearLastLoginFile();
        }
    }

    public void findDoctorByName(String name) {
        load("data/doctor.txt");
        boolean found = false;
        DoctorNode regCurrent = registeredDoctorsHead;
        while (regCurrent != null) {
            if (regCurrent.doctor.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(Constants.GREEN + "Doctor Name is Found!\n" + Constants.RESET);
                System.out.println("Doctor ID  : " + regCurrent.doctor.getId() + "\n" +
                        "Name       : " + regCurrent.doctor.getName() + "\n" +
                        "Speciality : " + regCurrent.doctor.getSpeciality());
                found = true;
            }
            regCurrent = regCurrent.next;
        }

        Node current = top;
        while (current != null) {
            if (current.doctor.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(Constants.GREEN + "Doctor Name is Found!\n" + Constants.RESET);
                System.out.println(current.doctor);
                found = true;
            }
            current = current.next;
        }

        if (!found) {
            System.out.println(Constants.RED + "No doctor found with name: " + name + Constants.RESET);
        }
    }

    public boolean validateDoctor(String id, String name, String password) {
        Doctor doctor = getRegisteredDoctor(id);
        if (doctor == null) {
            return false;
        }
        return doctor.getName().equals(name) && doctor.getPassword().equals(password);
    }

    public Doctor getRegisteredDoctor(String id) {
        DoctorNode current = registeredDoctorsHead;
        while (current != null) {
            if (current.id.equals(id)) {
                return current.doctor;
            }
            current = current.next;
        }
        return null;
    }

    public void displayAllDoctors(String filename) {
        System.out.println("_______________________________________________________");
        System.out.println("|                  DOCTORS INFORMATION                |");
        System.out.println("======================================================|");
        System.out.printf("| %-5s | %-20s | %-20s |\n", "ID", "Name", "Speciality");
        System.out.println("|-----------------------------------------------------|");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            top = null;
            registeredDoctorsHead = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 6) {
                    String id = parts[0];
                    String name = parts[1];
                    String speciality = parts[2];
                    System.out.printf("| %-5s | %-20s | %-20s |\n",
                            id, name, speciality);
                }
            }
            System.out.println("=======================================================");
        } catch (IOException e) {
            System.out.println(Constants.RED + "Error loading doctors from TXT : " + e.getMessage() + Constants.RESET);
        }
    }

    public void saveLastLogin() {
        Node lastLoginStack = null;

        try (BufferedReader reader = new BufferedReader(new FileReader("data/logindoctor.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String id = parts[0];
                    String name = parts[1];
                    String speciality = parts[2];
                    LocalDateTime loginTime = LocalDateTime.parse(parts[3], TIME_FORMATTER);

                    boolean isSameId = false;
                    Node temp = top;
                    while (temp != null) {
                        if (temp.doctor.getId().equals(id)) {
                            isSameId = true;
                            break;
                        }
                        temp = temp.next;
                    }

                    if (!isSameId) {
                        Doctor doctor = new Doctor(id, name, speciality, "", loginTime);
                        Node newNode = new Node(doctor);
                        newNode.next = lastLoginStack;
                        lastLoginStack = newNode;
                    }
                }
            }
        } catch (IOException ignored) {
        }

        Node temp = top;
        while (temp != null) {
            Node newNode = new Node(temp.doctor);
            newNode.next = lastLoginStack;
            lastLoginStack = newNode;
            temp = temp.next;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/logindoctor.txt"))) {
            Node current = lastLoginStack;
            while (current != null) {
                Doctor doctor = current.doctor;
                String line = String.format("%s|%s|%s|%s",
                        doctor.getId(),
                        doctor.getName(),
                        doctor.getSpeciality(),
                        doctor.getLoginTime().format(TIME_FORMATTER));
                writer.write(line);
                writer.newLine();
                current = current.next;
            }
        } catch (IOException e) {
            System.out.println(Constants.RED + "Error saving last login: " + e.getMessage() + Constants.RESET);
        }
    }

    public void save(String filename) {
        if (isLoading)
            return;
        DateTimeFormatter time = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            DoctorNode regCurrent = registeredDoctorsHead;
            while (regCurrent != null) {
                Doctor doctor = regCurrent.doctor;
                String line = String.format("%s|%s|%s|%s|%s|registered",
                        doctor.getId(),
                        doctor.getName(),
                        doctor.getSpeciality(),
                        doctor.getPassword(),
                        doctor.getLoginTime().format(time));
                writer.write(line);
                writer.newLine();
                regCurrent = regCurrent.next;
            }
            Node current = top;
            while (current != null) {
                Doctor doctor = current.doctor;
                String line = String.format("%s|%s|%s|%s|%s|loggedIn",
                        doctor.getId(),
                        doctor.getName(),
                        doctor.getSpeciality(),
                        doctor.getPassword(),
                        doctor.getLoginTime().format(time));
                writer.write(line);
                writer.newLine();
                current = current.next;
            }
        } catch (IOException e) {
            System.out.println(Constants.RED + "Error saving doctors to TXT: " + e.getMessage() + Constants.RESET);
        }
    }

    public void load(String filename) {
        isLoading = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            top = null;
            registeredDoctorsHead = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 6) {
                    String id = parts[0];
                    String name = parts[1];
                    String speciality = parts[2];
                    String password = parts[3];
                    String loginTimeStr = parts[4];
                    LocalDateTime loginTime = loginTimeStr.equals("Not logged in") ? null
                            : LocalDateTime.parse(loginTimeStr, TIME_FORMATTER);
                    String type = parts[5];
                    Doctor doctor = new Doctor(id, name, speciality, password, loginTime);
                    if (type.equals("registered")) {
                        DoctorNode newNode = new DoctorNode(doctor.getId(), doctor);
                        if (registeredDoctorsHead == null) {
                            registeredDoctorsHead = newNode;
                        } else {
                            DoctorNode current = registeredDoctorsHead;
                            while (current.next != null) {
                                current = current.next;
                            }
                            current.next = newNode;
                        }
                    } else if (type.equals("loggedIn")) {
                        push(doctor);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(Constants.RED + "Error loading doctors from TXT: " + e.getMessage() + Constants.RESET);
        } finally {
            isLoading = false;
        }
    }
}