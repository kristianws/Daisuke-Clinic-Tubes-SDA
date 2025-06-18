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
<<<<<<< HEAD
        save("data/doctor.txt");
=======
        save("data/doctor.txt"); 
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
    }

    public void push(Doctor doctor) {
        Node current = top;
        Node prev = null;
        while (current != null) {
            if (current.doctor.getId().equals(doctor.getId())) {
<<<<<<< HEAD
                current.doctor.LoginTime = doctor.getLoginTime();
                return;
            }
            prev = current;
=======
                current.doctor.LoginTime = doctor.getLoginTime(); 
                return;
            }
            prev= current;
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
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
<<<<<<< HEAD
                        loggedOut = new Doctor(id, name, speciality, "", loginTime);
                    } else {
=======
                        // Ini dokter yang logout, jangan dimasukkan ke list
                        loggedOut = new Doctor(id, name, speciality, "", loginTime);
                    } else {
                        // Tambahkan dokter lain ke linked list
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
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
<<<<<<< HEAD
                        d.getId(),
                        d.getName(),
                        d.getSpeciality(),
                        d.getLoginTime().format(TIME_FORMATTER)));
=======
                    d.getId(),
                    d.getName(),
                    d.getSpeciality(),
                    d.getLoginTime().format(TIME_FORMATTER)
                ));
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(Constants.RED + "Error writing to logindoctor.txt: " + e.getMessage() + Constants.RESET);
        }
<<<<<<< HEAD
        return loggedOut;
=======
        return loggedOut; 
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
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
<<<<<<< HEAD
                System.out.println("Doctor ID  : " + current.doctor.getId() + "\n" +
                        "Name       : " + current.doctor.getName() + "\n" +
                        "Speciality : " + current.doctor.getSpeciality());
=======
                System.out.println("Doctor ID  : "+current.doctor.getId()+"\n"+
                                   "Name       : "+current.doctor.getName()+"\n"+
                                   "Speciality : "+current.doctor.getSpeciality());
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
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
<<<<<<< HEAD
                    System.out.println("Doctor ID  : " + regCurrent.doctor.getId() + "\n" +
                            "Name       : " + regCurrent.doctor.getName() + "\n" +
                            "Speciality : " + regCurrent.doctor.getSpeciality());
=======
                    System.out.println("Doctor ID  : "+regCurrent.doctor.getId()+"\n"+
                                       "Name       : "+regCurrent.doctor.getName()+"\n"+
                                       "Speciality : "+regCurrent.doctor.getSpeciality());
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
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

<<<<<<< HEAD
    public void clearLastLoginFile() {
        String filename = "data/logindoctor.txt";
        load(filename);
        try (FileWriter fw = new FileWriter(filename, false)) {
            fw.write("");
            // System.out.println("Login doctor file has been cleared");
=======
    // public boolean isClinicClosed() {
    //     LocalTime now = LocalTime.now();
    //     LocalTime closingTime = LocalTime.of(21, 00); 
    //     return now.isAfter(closingTime);
    // }

    public void clearLastLoginFile() {
        String filename = "data/logindoctor.txt";
        load(filename); 
        try (FileWriter fw = new FileWriter(filename, false)) {
            fw.write("");
            //System.out.println("Login doctor file has been cleared");
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
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
<<<<<<< HEAD
        load("data/doctor.txt");
=======
        load("data/doctor.txt"); 
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
        boolean found = false;
        DoctorNode regCurrent = registeredDoctorsHead;
        while (regCurrent != null) {
            if (regCurrent.doctor.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(Constants.GREEN + "Doctor Name is Found!\n" + Constants.RESET);
<<<<<<< HEAD
                System.out.println("Doctor ID  : " + regCurrent.doctor.getId() + "\n" +
                        "Name       : " + regCurrent.doctor.getName() + "\n" +
                        "Speciality : " + regCurrent.doctor.getSpeciality());
=======
                System.out.println("Doctor ID  : "+regCurrent.doctor.getId()+"\n"+
                                   "Name       : "+regCurrent.doctor.getName()+"\n"+
                                   "Speciality : "+regCurrent.doctor.getSpeciality());
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
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
<<<<<<< HEAD
        System.out.printf("| %-5s | %-20s | %-20s |\n", "ID", "Name", "Speciality");
        System.out.println("|-----------------------------------------------------|");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
=======
        System.out.printf("| %-5s | %-20s | %-20s |\n",  "ID", "Name", "Speciality");
        System.out.println("|-----------------------------------------------------|");
         try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
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
<<<<<<< HEAD
                            id, name, speciality);
=======
                    id, name, speciality);
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
                }
            }
            System.out.println("=======================================================");
        } catch (IOException e) {
<<<<<<< HEAD
            System.out.println(Constants.RED + "Error loading doctors from TXT : " + e.getMessage() + Constants.RESET);
        }
    }

    public void saveLastLogin() {
        Node lastLoginStack = null;
=======
            System.out.println(Constants.RED+"Error loading doctors from TXT : " + e.getMessage()+Constants.RESET);
        }
    }
    
    public void saveLastLogin() {
        Node lastLoginStack = null; 
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727

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
<<<<<<< HEAD
        } catch (IOException ignored) {
        }
=======
        } catch (IOException ignored) {}
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727

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
<<<<<<< HEAD
        if (isLoading)
            return;
=======
        if (isLoading) return;
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
        DateTimeFormatter time = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            DoctorNode regCurrent = registeredDoctorsHead;
            while (regCurrent != null) {
                Doctor doctor = regCurrent.doctor;
<<<<<<< HEAD
                String line = String.format("%s|%s|%s|%s|%s|registered",
                        doctor.getId(),
                        doctor.getName(),
                        doctor.getSpeciality(),
                        doctor.getPassword(),
                        doctor.getLoginTime().format(time));
=======
                String line = String.format("%s|%s|%s|%s|%s|registered", 
                    doctor.getId(),
                    doctor.getName(),
                    doctor.getSpeciality(),
                    doctor.getPassword(),
                    doctor.getLoginTime().format(time));
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
                writer.write(line);
                writer.newLine();
                regCurrent = regCurrent.next;
            }
            Node current = top;
            while (current != null) {
                Doctor doctor = current.doctor;
                String line = String.format("%s|%s|%s|%s|%s|loggedIn",
<<<<<<< HEAD
                        doctor.getId(),
                        doctor.getName(),
                        doctor.getSpeciality(),
                        doctor.getPassword(),
                        doctor.getLoginTime().format(time));
=======
                doctor.getId(),
                doctor.getName(),
                doctor.getSpeciality(),
                doctor.getPassword(),
                doctor.getLoginTime().format(time));
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
                writer.write(line);
                writer.newLine();
                current = current.next;
            }
        } catch (IOException e) {
<<<<<<< HEAD
            System.out.println(Constants.RED + "Error saving doctors to TXT: " + e.getMessage() + Constants.RESET);
=======
            System.out.println(Constants.RED+"Error saving doctors to TXT: " + e.getMessage()+Constants.RESET);
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
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
<<<<<<< HEAD
                    LocalDateTime loginTime = loginTimeStr.equals("Not logged in") ? null
                            : LocalDateTime.parse(loginTimeStr, TIME_FORMATTER);
=======
                    LocalDateTime loginTime = loginTimeStr.equals("Not logged in") ? null : LocalDateTime.parse(loginTimeStr, TIME_FORMATTER);
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
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