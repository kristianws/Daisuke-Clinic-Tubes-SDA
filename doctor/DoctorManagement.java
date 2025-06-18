package doctor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;

import utility.*;

public class DoctorManagement {
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public static Doctor currentLoggedInDoctor = null;

    public void menu(Scanner input, Stack doctorStack,int user) {
        int choice;

        do {
            ConsoleUtil.clearScreen();
            System.out.println("==============================================");
            System.out.println("------------- Doctor Management --------------");
            System.out.println("==============================================");
            if(user == 1 || user == 3){
                System.out.println("| 1 | View Last Logged-in Doctor             |");
                System.out.println("| 2 | View All Logged-in Doctor              |");
                System.out.println("| 3 | Display All Doctors                    |"); 
                System.out.println("| 4 | Search Doctor by Name                  |");  
                System.out.println("| 5 | Search Doctor by ID                    |");  
                System.out.println("| 0 | Exit From Doctor Management            |");
                System.out.println("----------------------------------------------");
            }else if (user == 2){
                System.out.println("| 1 | Doctor Logout                          |");
                System.out.println("| 2 | View Last Logged-in Doctor             |");
                System.out.println("| 3 | View All Logged-in Doctor              |");
                System.out.println("| 4 | Display All Doctors                    |");  
                System.out.println("| 5 | Search Doctor by Name                  |");  
                System.out.println("| 6 | Search Doctor by ID                    |");  
                System.out.println("| 0 | Exit From Doctor Management            |");
                System.out.println("----------------------------------------------");
            }
            System.out.print("Choose Doctor Management Menu : ");
            choice = input.nextInt();
            input.nextLine();

            if(user == 1 || user == 3){
                ConsoleUtil.clearScreen();
                switch (choice) {
                    case 0 -> {return;}
                    case 1 -> lastLogin(1);
                    case 2 -> lastLogin(2);
                    case 3 -> displayAllDoctors(doctorStack);
                    case 4 -> searchDoctorByName(input, doctorStack);
                    case 5 -> searchDoctorByID(input, doctorStack);
                    default -> System.out.println(Constants.RED+"Invalid option. Try again"+Constants.RESET);
                }
                ConsoleUtil.waitForEnter(input);
            }else{
                ConsoleUtil.clearScreen();
                switch (choice) {
                    case 0 -> {return;}
                    case 1 -> {
                        doctorLogout(doctorStack);
                        System.out.println(Constants.PURPLE);
                        FileHandler.readFile("display/end.txt");
                        System.out.println(Constants.RESET);
                        System.exit(0);
                    }
                    case 2 -> lastLogin(1);
                    case 3 -> lastLogin(2);
                    case 4 -> displayAllDoctors(doctorStack);
                    case 5 -> searchDoctorByName(input, doctorStack);
                    case 6 -> searchDoctorByID(input, doctorStack);
                    default -> System.out.println(Constants.RED+"Invalid option. Try again"+Constants.RESET);
                }
                ConsoleUtil.waitForEnter(input);
            }
        } while (choice != 0);
        return;
    }

    public void registerDoctor(Scanner sc, Stack doctorStack) {
        ConsoleUtil.clearScreen();
        String docId = generateDoctorID(doctorStack);
        System.out.println("\nDoctor ID         : " + docId);

        System.out.print("Enter Doctor Name : ");
        String docName = sc.nextLine();
        if (docName == null || docName.trim().isEmpty() || !docName.matches("[a-zA-Z\\.\\s\\-']+")) {
            System.out.println(Constants.RED+"Error : Name must contain only letters and spaces"+Constants.RESET);
            return;
        }
        System.out.print("Enter Speciality  : ");
        String speciality = sc.nextLine();
        if (speciality == null || speciality.trim().isEmpty()) {
            System.out.println(Constants.RED+"Error : Speciality cannot be empty"+Constants.RESET);
            return;
        }
        System.out.print("Enter Password    : ");
        String password = sc.nextLine();
        if (password == null || password.trim().isEmpty()) {
            System.out.println(Constants.RED+"Error : Password cannot be empty"+Constants.RESET);
            return;
        }
        Doctor doctor = new Doctor(docId, docName, speciality, password, LocalDateTime.now());
        doctorStack.registerDoctor(doctor);
        ConsoleUtil.clearScreen();
        System.out.println(Constants.GREEN+"Doctor registered successfully\n" + Constants.RESET);
          System.out.println("Doctor ID  : "+doctor.getId()+"\n"+
                             "Name       : "+doctor.getName()+"\n"+
                             "Speciality : "+doctor.getSpeciality());
        ConsoleUtil.waitForEnter(sc);
        ConsoleUtil.clearScreen();
    }

    private String generateDoctorID(Stack doctorStack) {
        int maxId = 0;
        Stack.DoctorNode current = doctorStack.registeredDoctorsHead;
        while (current != null) {
            String id = current.doctor.getId();
            if (id.startsWith("D")) {
                try {
<<<<<<< HEAD
                    int num = Integer.parseInt(id.substring(1)); 
=======
                    int num = Integer.parseInt(id.substring(1)); // Extract number after "D"
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
                    maxId = Math.max(maxId, num);
                } catch (NumberFormatException e) {}
            }
            current = current.next;
        }
        int newId = maxId + 1;
        return String.format("D%d", newId); 
    }

<<<<<<< HEAD
    public static boolean doctorLogin(Scanner sc, Stack doctorStack,String doctorId,String docName,String password) {
=======
    public void doctorLogin(Scanner sc, Stack doctorStack,String doctorId,String docName,String password) {
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
        if (doctorStack.validateDoctor(doctorId, docName, password)) {
            Doctor doctor = doctorStack.getDoctorById(doctorId);
            Doctor updatedDoctor = new Doctor(doctor.getId(), doctor.getName(), doctor.getSpeciality(),doctor.getPassword(), LocalDateTime.now());
            doctorStack.push(updatedDoctor);
            doctorStack.saveLastLogin(); 

            currentLoggedInDoctor = updatedDoctor;

            ConsoleUtil.clearScreen();
            System.out.println(Constants.GREEN+"\nDoctor logged in successfully\n"+Constants.RESET);
            System.out.println("Doctor ID   : "+updatedDoctor.getId());
            System.out.println("Doctor Name : "+updatedDoctor.getName());
            System.out.println("Speciality  : "+updatedDoctor.getSpeciality());
<<<<<<< HEAD
            return true;
        } else {
            System.out.println(Constants.RED+"\nLogin failed : Invalid name or password"+Constants.RESET);
            return false;
=======
        } else {
            System.out.println(Constants.RED+"Login failed : Invalid ID, name, or password"+Constants.RESET);
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
        }
    }

    public static boolean doctorLogout(Stack doctorStack) {
        String docName = currentLoggedInDoctor.getName();
        if (docName == null || docName.trim().isEmpty() || !docName.matches("[a-zA-Z\\.\\s\\-']+")) {
            System.out.println(Constants.RED + "Invalid name. Name must contain only letters and spaces" + Constants.RESET);
            return false;
        }
        String doctorId = currentLoggedInDoctor.getId();

        Doctor loggedOut = doctorStack.outDoctor(doctorId,docName);
        if (loggedOut == null) {
            System.out.println(Constants.RED + "Doctor with ID " + doctorId + " and name " + docName + " not found or not logged in" + Constants.RESET);
            return false;
        }

        DateTimeFormatter time = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime currentDate = LocalDateTime.now();
        ConsoleUtil.clearScreen();
        System.out.println(Constants.GREEN + "Successfully logout\n" + Constants.RESET);
        System.out.println("Name         : " + loggedOut.getName() + " (ID : " + loggedOut.getId() + ")\n" +
                           "Speciality   : " + loggedOut.getSpeciality() + "\n" +
                           "Log Out Time : " + currentDate.format(time));
        currentLoggedInDoctor = null;
        return true;
    }

    public void lastLogin(int display) {
        DoctorLinkedList doctorList = new DoctorLinkedList();
        try (BufferedReader reader = new BufferedReader(new FileReader("data/logindoctor.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String id = parts[0];
                    String name = parts[1];
                    String speciality = parts[2];
                    LocalDateTime loginTime = LocalDateTime.parse(parts[3], TIME_FORMATTER);
                    doctorList.addDoctor(new Doctor(id, name, speciality, "", loginTime));
                }
            }
        } catch (IOException e) {
            System.out.println(Constants.RED + "Error reading last login : " + e.getMessage() + Constants.RESET);
            return;
        }

        if (doctorList.isEmpty()) {
            System.out.println(Constants.RED + "No doctors logged in yet" + Constants.RESET);
            return;
        }

        if (display == 1) {
            System.out.println(Constants.GREEN + "Last Login Doctor :\n" + Constants.RESET);
            System.out.println(doctorList.get(0));
        } else if (display == 2) {
            DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            System.out.println("____________________________________________________________________________");
            System.out.println("|                                   HISTORY                                 |");
            System.out.println("|                            LAST LOGIN DOCTOR                              |");
            System.out.println("|===========================================================================|");
            System.out.printf("| %-5s | %-20s | %-20s | %-19s |\n", "ID", "Name", "Speciality", "Login Time");
            System.out.println("|---------------------------------------------------------------------------|");
            for (int i = 0; i < doctorList.size(); i++) {
                Doctor doctor = doctorList.get(i);
                System.out.printf("| %-5s | %-20s | %-20s | %-19s |\n",
                        doctor.getId(),
                        doctor.getName(),
                        doctor.getSpeciality(),
                        doctor.getLoginTime().format(displayFormat));
            }

            System.out.println("=============================================================================");
        }
    }

    static void searchDoctorByName(Scanner sc, Stack doctorStack) {
        System.out.print("Enter name to search : ");
        String searchName = sc.nextLine();
        if (searchName.trim().isEmpty()) {
            System.out.println(Constants.RED + "Name cannot be empty" + Constants.RESET);
            return;
        }
        ConsoleUtil.clearScreen();
        doctorStack.findDoctorByName(searchName);
    }

    static void searchDoctorByID(Scanner sc, Stack doctorStack) {
        System.out.print("Enter ID to search (numbers only): ");
        String searchName = sc.nextLine();
        if (searchName.trim().isEmpty()) {
            System.out.println(Constants.RED + "ID cannot be empty" + Constants.RESET);
            return;
        }
        ConsoleUtil.clearScreen();
        doctorStack.findDoctorById(searchName);
    }

    static void displayAllDoctors(Stack doctorStack) {
        doctorStack.displayAllDoctors("data/doctor.txt");
    }
}
