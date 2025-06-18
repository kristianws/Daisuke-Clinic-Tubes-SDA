package patient;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.google.gson.Gson;

import utility.*;

public class PatientManagement {
    public void menu(Scanner input, int user) {
        LinkedList patientList = new LinkedList();
        BST patientBST = new BST();

        patientList.load("data/patient.json");
        patientBST.load("data/patient.json");
        int choice;

        do {
            ConsoleUtil.clearScreen();
            System.out.println("===============================================");
            System.out.println("------------- Patient Management --------------");
            System.out.println("===============================================");

            if(user == 2){
                System.out.println("| 1 | Search Patient by Name                  |");
                System.out.println("| 2 | Display All Patients                    |");
                System.out.println("| 3 | Search Patient by ID (BST)              |");
                System.out.println("| 4 | Display All Patients (BST Inorder)      |");
                System.out.println("| 5 | Display All Patients (BST Preorder)     |");
                System.out.println("| 6 | Display All Patients (BST Postorder)    |");
                System.out.println("| 0 | Exit From Patient Management            |");
                System.out.println("----------------------------------------------");
            }else if(user == 3){
                System.out.println("| 1 | Add New Patient                         |");
                System.out.println("| 2 | Remove Patient by ID                    |");
                System.out.println("| 3 | Search Patient by Name                  |");
                System.out.println("| 4 | Display All Patients                    |");
                System.out.println("| 5 | Search Patient by ID (BST)              |");
                System.out.println("| 6 | Display All Patients (BST Inorder)      |");
                System.out.println("| 7 | Display All Patients (BST Preorder)     |");
                System.out.println("| 8 | Display All Patients (BST Postorder)    |");
                System.out.println("| 0 | Exit From Patient Management            |");
                System.out.println("----------------------------------------------");
            }else{
                System.out.println(Constants.RED+"You cannot acces this management"+Constants.RESET);
            }
            System.out.print("Choose Patient Management Menu : ");
            choice = input.nextInt();
            input.nextLine();

            if(user == 2){
                ConsoleUtil.clearScreen();
                switch (choice) {
                    case 0 -> {
                        return; 
                    }
                    case 1 -> {
                        searchPatientByName(input, patientList);
                        patientList.save("data/patient.json");
                        break;
                    }
                    case 2 -> {
                        displayAllPatients(patientList);
                        patientList.save("data/patient.json");
                        break;
                    }
                    case 3 -> {
                        searchPatientById(input, patientBST);
                        patientList.save("data/patient.json");
                        break;
                    }
                    case 4 -> {
                        displayAllPatientsBSTInorder(patientBST);
                        patientList.save("data/patient.json");
                        break;
                    }
                    case 5 -> {
                        displayAllPatientsBSTPreorder(patientBST);
                        patientList.save("data/patient.json");
                        break;
                    }case 6 -> {
                        displayAllPatientsBSTPostorder(patientBST);
                        patientList.save("data/patient.json");
                        break;
                    }
                    default -> System.out.println(Constants.RED + "Invalid option. Try again" + Constants.RESET);
                }
                ConsoleUtil.waitForEnter(input);
            }else if (user == 3){
                ConsoleUtil.clearScreen();
                switch (choice) {
                    case 0 -> {
                        return; 
                    }
                    case 1 -> {
                        addPatient(input, patientList, patientBST);
                        patientList.save("data/patient.json");
                        break;
                    }
                    case 2 -> {
                        removePatient(input, patientList, patientBST);
                        patientList.save("data/patient.json");
                        break;
                    }
                    case 3 -> {
                        searchPatientByName(input, patientList);
                        patientList.save("data/patient.json");
                        break;
                    }
                    case 4 -> {
                        displayAllPatients(patientList);
                        patientList.save("data/patient.json");
                        break;
                    }
                    case 5 -> {
                        searchPatientById(input, patientBST);
                        patientList.save("data/patient.json");
                        break;
                    }
                    case 6 -> {
                        displayAllPatientsBSTInorder(patientBST);
                        patientList.save("data/patient.json");
                        break;
                    }
                    case 7 -> {
                        displayAllPatientsBSTPreorder(patientBST);
                        patientList.save("data/patient.json");
                        break;
                    }case 8 -> {
                        displayAllPatientsBSTPostorder(patientBST);
                        patientList.save("data/patient.json");
                        break;
                    }
                    default -> System.out.println(Constants.RED + "Invalid option. Try again" + Constants.RESET);
                }
                ConsoleUtil.waitForEnter(input);
            }else{
                System.out.println("INVALID OPTION");
            }
        }while(choice != 0);
    }

<<<<<<< HEAD
    public static void registerPatient(Scanner sc,LinkedList patientList, String username,String password) {
=======
      public static void registerPatient(Scanner sc,LinkedList patientList, String username,String password) {
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
        ConsoleUtil.clearScreen();
        System.out.println("======= PATIENT REGISTER =======");

        String Id = generatePatientID(patientList);
        System.out.println("\nPatient ID     : P" + Id);
        String patientId = "ADM" + patientList;
        System.out.print("Enter Username : ");
        System.out.print("Enter Password    : ");
        try (FileWriter fw = new FileWriter("data/userPatient.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.println(patientId +"|"+username + "|" + password);
            ConsoleUtil.clearScreen();
            System.out.println(Constants.GREEN+"Patient registered successfully\n" + Constants.RESET);
        } catch (IOException e) {
            System.out.println(Constants.RED+"Error : " + e.getMessage() + Constants.RESET);
        }
    }

    public static boolean validateAdmin(String id, String username) {
        try (FileReader reader = new FileReader("data/patient.json")) {
            Gson gson = new Gson();
            Patient[] patients = gson.fromJson(reader, Patient[].class);
            for (Patient p : patients) {
                if (p.getId().equalsIgnoreCase(id) && p.getName().equalsIgnoreCase(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading patient data: " + e.getMessage());
        }
        return false;
    }

    static String generateID(LinkedList patientList) {
        int maxId = 0;
        LinkedList.Node current = patientList.head;
        while (current != null) {
            String id = current.patient.getId();
            if (id.startsWith("P")) {
                try {
                    int num = Integer.parseInt(id.substring(1)); 
                    maxId = Math.max(maxId, num);
                } catch (NumberFormatException e) {}
            }
            current = current.next;
        }
        int newId = maxId + 1;
        return String.format("%d", newId);
    }

    public static void patientLogin(Scanner sc, String adminId, String username) {
        boolean isLogin = false;

        do{
            ConsoleUtil.clearScreen();
            if (validateAdmin(adminId, username)) {
                System.out.println("======= PATIENT LOGIN =======");
                System.out.println("Patient ID : "+adminId);
                System.out.println("Username   : "+username);
                System.out.println(Constants.GREEN+"\nPatient logged in successfully\n"+Constants.RESET);
                isLogin = true;
            } else {
                System.out.print(Constants.RED+"Login failed : Invalid patient id, username or password"+Constants.RESET);
                System.out.print("");
                sc.nextLine();
            }
        }while (!isLogin);
    }

    static void addPatient(Scanner sc, LinkedList patientList, BST patientBST) {
        String patientID = generatePatientID(patientList);
        System.out.println("Patient ID       : P" + patientID);

        System.out.print("Enter Name       : ");
        String name = sc.nextLine();
        if (name == null || name.trim().isEmpty() || !name.matches("[a-zA-Z\\s]+")) {
            System.out.println(Constants.RED+"Error : Name must contain only letters and spaces"+Constants.RESET);
            return;
        }
        System.out.print("Enter Age        : ");
        int age;
        try {
            age = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(Constants.RED+"Invalid age. Must be a number"+Constants.RESET);
            return;
        }
        System.out.print("Enter Address    : ");
        String address = sc.nextLine();
        if (address == null || address.trim().isEmpty()) {
            System.out.println(Constants.RED+"Error : Address cannot be empty"+Constants.RESET);
            return;
        }
        System.out.print("Enter Phone Number : ");
        String phoneNumber = sc.nextLine();
        if (!phoneNumber.matches("\\d{10,13}")) {
            System.out.println(Constants.RED+"Error : Phone number must be 10-13 digits"+Constants.RESET);
            return;
        }
        Patient patient = new Patient(patientID, name, age, address, phoneNumber);
        patientList.addPatient(patient);
        patientBST.insertPatient(patient);
        ConsoleUtil.clearScreen();
        System.out.println(Constants.GREEN+"Patient added successfully\n"+Constants.RESET);
        System.out.println(patient);
    }

    static String generatePatientID(LinkedList patientList) {
        int maxId = 0;
        LinkedList.Node current = patientList.head;
        while (current != null) {
            String id = current.patient.getId();
            if (id.startsWith("P")) {
                try {
                    int num = Integer.parseInt(id.substring(1)); 
                    maxId = Math.max(maxId, num);
                } catch (NumberFormatException e) {}
            }
            current = current.next;
        }
        int newId = maxId + 1;
        return String.format("%d", newId);
    } 

    static void removePatient(Scanner sc, LinkedList patientList, BST patientBST) {
        System.out.print("Enter Patient ID (numbers only) : ");
        String removeId = sc.nextLine();
        if (!removeId.matches("\\d+")){
            System.out.println(Constants.RED + "Invalid ID. Must be a number" + Constants.RESET);
            return;
        }
        String patientId = "P" + removeId;
        Patient foundPatient = patientBST.searchByIdPatient(patientId); 
        if (foundPatient != null) {
            System.out.println("\n"+foundPatient+"\n");
        } 

        if (patientList.removePatientById(patientId)) {
            patientBST.removePatient(patientId);
            System.out.println(Constants.GREEN+"Patient with ID " + patientId + " removed successfully"+Constants.RESET);
        } else {
            System.out.println(Constants.RED+"Patient with ID " + patientId + " not found"+Constants.RESET);
        }
    }

    static void searchPatientByName(Scanner sc, LinkedList patientList) {
        System.out.print("Enter name to search : ");
        String searchName = sc.nextLine();
        if (searchName.trim().isEmpty()) {
            System.out.println(Constants.RED+"Name cannot be empty" + Constants.RESET);
            return;
        }
        ConsoleUtil.clearScreen();
        patientList.findPatientByName(searchName);
    }

    static void displayAllPatients(LinkedList patientList) {
        patientList.displayAllPatients();
    }

    static void searchPatientById(Scanner sc, BST patientBST) {
        System.out.print("Enter Patient ID (numbers only): ");
        String searchId = sc.nextLine();
        if (!searchId.matches("\\d+")){
            System.out.println(Constants.RED + "Invalid ID. Must be a number" + Constants.RESET);
            return;
        }
        String patientId = "P" + searchId;
     
        Patient foundPatient = patientBST.searchByIdPatient(patientId); 
        if (foundPatient == null) {
            System.out.println(Constants.RED+"Patient with ID " + patientId + " not found"+Constants.RESET);
        } else {
            ConsoleUtil.clearScreen();
            System.out.println(Constants.GREEN+"Patient ID is Found !\n\n"+Constants.RESET+foundPatient);
        }
    }

    static void displayAllPatientsBSTInorder(BST patientBST) {
        patientBST.inOrderDisplay();
    }

    static void displayAllPatientsBSTPostorder(BST patientBST) {
        patientBST.postOrderDisplay();
    }

    static void displayAllPatientsBSTPreorder(BST patientBST) {
        patientBST.preOrderDisplay();
    }
}
