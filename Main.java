import java.util.Scanner;

import utility.*;
import patient.*;
import doctor.*;
import appointment.*;
import admin.*;
import schedule.*;
import medicalrecord.*;

public class Main {
    public static int user;
    public static boolean isLoggedIn = false;
    public static String inputId, inputName, inputPassword;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList patientList = new LinkedList();
        Stack doctorStack = new Stack();
        DoctorLinkedList doctorList = new DoctorLinkedList();
        Queue appointmentQueue = new Queue(patientList, doctorStack);
        BST patientBST = new BST();
        AdminList admin = new AdminList();
        AdminRecord adminRole = new AdminRecord();
        PatientManagement patientManage = new PatientManagement();
        DoctorManagement doctorManage = new DoctorManagement();
        AppointmentManagement appointmentManage = new AppointmentManagement();
        MedicalRecordList records = new MedicalRecordList();
        ScheduleList schedule = new ScheduleList();
        MedicalRecordManagement recordsManage = new MedicalRecordManagement();
        ScheduleManagement scheduleManage = new ScheduleManagement();
        int opsi;

        doctorStack.autoClearLoginIfClosed(); // hapus daftar login doctor jika clinic sudah tutup

        loadData(patientList, admin, doctorStack, appointmentQueue, patientBST, schedule);

        ConsoleUtil.clearScreen();
        System.out.print(Constants.BLUE);
        FileHandler.readFile("display/title.txt");
        System.out.print(Constants.RESET);
        while (!isLoggedIn) {
            roleUser(scanner, admin, adminRole, patientManage, doctorStack, doctorManage);
        }

        if (user == 1) {
            boolean isRunning = true;
            do {
                ConsoleUtil.clearScreen();
                System.out.println(Constants.BLUE);
                FileHandler.readFile("display/menuPatient.txt");
                System.out.print(Constants.RESET);
                System.out.print("Choose option : ");
                opsi = scanner.nextInt();
                scanner.nextLine();

                switch (opsi) {
                    case 1 -> {
                        doctorManage.menu(scanner, doctorStack, user);
                        break;
                    }
                    case 2 -> {
                        appointmentManage.menu(scanner, user, inputId);
                        break;
                    }
                    case 3 -> {
                        recordsManage.menu(scanner);
                        break;
                    }
                    case 4 -> {
                        scheduleManage.menu(scanner);
                        break;
                    }
                    case 5 -> {
                        ConsoleUtil.clearScreen();
                        System.out.println(Constants.PURPLE);
                        FileHandler.readFile("display/end.txt");
                        System.out.println(Constants.RESET);
                        return;
                    }
                    default -> System.out.println(Constants.RED + "Invalid option. Try again" + Constants.RESET);
                }
            } while (isRunning);
        } else if (user == 2 || user == 3) {
            ConsoleUtil.clearScreen();
            if (user == 2) {
                do {
                    ConsoleUtil.clearScreen();
                    System.out.println(Constants.BLUE);
                    FileHandler.readFile("display/menuDoctor.txt");
                    System.out.print(Constants.RESET);
                    System.out.print("Choose menu : ");
                    opsi = scanner.nextInt();
                    scanner.nextLine();

                    ConsoleUtil.clearScreen();
                    switch (opsi) {
                        case 1 -> {
                            patientManage.menu(scanner, user);
                            break;
                        }
                        case 2 -> {
                            doctorManage.menu(scanner, doctorStack, user);
                            break;
                        }
                        case 3 -> {
                            appointmentManage.menu(scanner, user, inputId);
                            break;
                        }
                        case 4 -> {
                            recordsManage.menu(scanner);
                            break;
                        }
                        case 5 -> {
                            scheduleManage.menu(scanner);
                            break;
                        }
                        default -> System.out.println(Constants.RED + "Invalid option. Try again" + Constants.RESET);
                    }
                } while (true);
            } else {
                do {
                    ConsoleUtil.clearScreen();
                    System.out.println(Constants.BLUE);
                    FileHandler.readFile("display/menuAdmin.txt");
                    System.out.print(Constants.RESET);
                    System.out.print("Choose menu : ");
                    opsi = scanner.nextInt();
                    scanner.nextLine();

                    ConsoleUtil.clearScreen();
                    switch (opsi) {
                        case 1 -> {
                            patientManage.menu(scanner, user);
                            break;
                        }
                        case 2 -> {
                            doctorManage.menu(scanner, doctorStack, user);
                            break;
                        }
                        case 3 -> {
                            appointmentManage.menu(scanner, user, inputId);
                            break;
                        }
                        case 4 -> {
                            recordsManage.menu(scanner);
                        }
                        case 5 -> {
                            scheduleManage.menu(scanner);
                            break;
                        }
                        case 6 -> {
                            ConsoleUtil.clearScreen();
                            adminRole.adminLogout(inputId, inputName);
                            System.out.println(Constants.PURPLE);
                            FileHandler.readFile("display/end.txt");
                            System.out.println(Constants.RESET);
                            return;
                        }
                        default -> System.out.println(Constants.RED + "Invalid option. Try again" + Constants.RESET);
                    }
                } while (true);
            }
        } else {
            System.out.println("INVALID INPUT");
            return;
        }
    }

    static void loadData(LinkedList patientList, AdminList adm, Stack doctorStack, Queue appointmentQueue,
            BST patientBST, ScheduleList schedule) {
        patientList.load("data/patient.json");
        doctorStack.load("data/doctor.txt");
        // adm.load("data/admin.txt");
        schedule.load("data/schedule.txt");

        LinkedList.Node current = patientList.head;
        while (current != null) {
            patientBST.insertPatient(current.patient);
            current = current.next;
        }
    }

    static void roleUser(Scanner sc, AdminList adm, AdminRecord adminRole,
            PatientManagement patientManage, Stack doctorRole,
            DoctorManagement doctorManage) {
        int opsi;
        System.out.println(Constants.BLUE);
        FileHandler.readFile("display/loginregister.txt");
        System.out.print(Constants.RESET + "Choose option : ");
        opsi = sc.nextInt();
        sc.nextLine();

        ConsoleUtil.clearScreen();

        if (opsi == 1) {
            System.out.print("Enter ID       : ");
            inputId = sc.nextLine();

            if (inputId == null || inputId.trim().isEmpty()) {
                System.out.println(Constants.RED + "Error: ID cannot be empty" + Constants.RESET);
                return;
            }

            String prefix = inputId.toUpperCase().substring(0, 1);

            switch (prefix) {
                case "A":
                    if (!inputId.toUpperCase().startsWith("ADM"))
                        break;

                    System.out.print("Enter Username : ");
                    inputName = sc.nextLine();
                    System.out.print("Enter Password : ");
                    inputPassword = sc.nextLine();

                    if (inputName.trim().isEmpty() || inputPassword.trim().isEmpty()) {
                        System.out.println(
                                Constants.RED + "Error: Username or Password cannot be empty" + Constants.RESET);
                        return;
                    }

                    isLoggedIn = true;
                    user = 3;
                    adminRole.adminLogin(sc, inputId, inputName, inputPassword);
                    ConsoleUtil.waitForEnter(sc);
                    ConsoleUtil.clearScreen();
                    break;
                case "D":
                    boolean loginSuccess = false;

                    while (!loginSuccess) {
                        System.out.print("Enter Username : ");
                        inputName = sc.nextLine();
                        System.out.print("Enter Password : ");
                        inputPassword = sc.nextLine();

                        if (inputName.trim().isEmpty() || inputPassword.trim().isEmpty()) {
                            System.out.println(
                                    Constants.RED + "Error: Username or Password cannot be empty" + Constants.RESET);
                            continue;
                        }
                        loginSuccess = doctorManage.doctorLogin(sc, doctorRole, inputId, inputName, inputPassword);

                        if (!loginSuccess) {
                            // System.out.println(Constants.RED + "Login failed. Please try again." +
                            // Constants.RESET);
                        }
                        ConsoleUtil.waitForEnter(sc);
                        ConsoleUtil.clearScreen();
                    }
                    isLoggedIn = true;
                    user = 2;
                    break;
                case "P":
                    System.out.print("Enter Username : ");
                    inputName = sc.nextLine();

                    if (inputName.trim().isEmpty()) {
                        System.out.println(Constants.RED + "Error: Username cannot be empty" + Constants.RESET);
                        return;
                    }

                    isLoggedIn = true;
                    user = 1;
                    patientManage.patientLogin(sc, inputId, inputName);
                    ConsoleUtil.waitForEnter(sc);
                    ConsoleUtil.clearScreen();
                    break;
                default:
                    System.out.println(Constants.RED + "User role not recognized. ID should start with ADM, D, or P."
                            + Constants.RESET);
                    ConsoleUtil.waitForEnter(sc);
                    ConsoleUtil.clearScreen();
            }
        } else if (opsi == 2) {
            System.out.print(Constants.CYAN);
            FileHandler.readFile("display/user.txt");
            System.out.print(Constants.RESET + "Choose option user : ");
            int opsi2 = sc.nextInt();
            sc.nextLine();

            if (opsi2 == 1) {
                doctorManage.registerDoctor(sc, doctorRole);
                user = 2;
            } else if (opsi2 == 2) {
                adm.load("data/admin.txt");
                adminRole.registerAdmin(sc, adm);
                user = 3;
            } else {
                System.out.println(Constants.RED + "INVALID INPUT" + Constants.RESET);
            }
        } else if (opsi == 3) {
            isLoggedIn = true;
            user = 1;
            return;
        } else {
            ConsoleUtil.clearScreen();
            System.out.println(Constants.PURPLE);
            FileHandler.readFile("display/end.txt");
            System.out.println(Constants.RESET);
            System.exit(0);
        }
    }
}
