package appointment;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Scanner;

import utility.*;
import patient.*;
import doctor.*;
import medicalrecord.*;

public class AppointmentManagement {
    public static String filename;
    public static String medication,treatment, roomNumber,diagnosis;
    public void menu(Scanner input,int user, String loginDoc) {
        LinkedList patientList = new LinkedList();
        Stack doctorStack = new Stack();
        Queue appointmentQueue = new Queue(patientList, doctorStack);
        int choice;

        patientList.load("data/patient.json");
        doctorStack.load("data/doctor.txt");

        do {
            ConsoleUtil.clearScreen();
            System.out.println("===============================================");
            System.out.println("----------- Appointment Management ------------");
            System.out.println("===============================================");

            if(user == 1){
                System.out.println("| 1 | Display Upcoming Appointments           |");
                System.out.println("| 0 | Exit From Appointment Management        |");
                System.out.println("-----------------------------------------------");
            }else {
                if(user == 2 ){
                    System.out.println("| 1 | Schedule Appointment                    |");
                    System.out.println("| 2 | Process Appointment                     |");
                    System.out.println("| 3 | Display Upcoming Appointments           |");
                    System.out.println("| 0 | Exit From Appointment Management        |");
                    System.out.println("-----------------------------------------------");
                }else{
                    System.out.println("| 1 | Schedule Appointment                    |");
                    System.out.println("| 2 | Display Upcoming Appointments           |");
                    System.out.println("| 0 | Exit From Appointment Management        |");
                    System.out.println("-----------------------------------------------");
                }
            }
            System.out.print("Choose Appointment Management Menu : ");
            choice = input.nextInt();
            input.nextLine();

            if(user == 1){
                ConsoleUtil.clearScreen();
                switch (choice) {
                    case 0 -> {return;}
                    case 1 -> {
                        displayUpcomingAppointments(input,appointmentQueue);
                        break;
                    }
                    default -> System.out.println(Constants.RED+"Invalid option. Try again"+Constants.RESET);
                }
                ConsoleUtil.waitForEnter(input);
            }else if (user == 2){
                ConsoleUtil.clearScreen();
                switch (choice) {
                    case 0 -> {return;}
                     case 1 -> {
                        scheduleAppointment(input, appointmentQueue, patientList, doctorStack);
                        appointmentQueue.save(filename); 
                        patientList.save("data/patient.json");
                        break;
                    }
                    case 2 -> {
                        processAppointment(input, appointmentQueue,patientList,loginDoc, doctorStack);
                        appointmentQueue.save(filename); 
                        patientList.save("data/patient.json");
                        break;
                    }
                    case 3 ->  {
                        displayUpcomingAppointments(input,appointmentQueue);
                    }
                    default -> System.out.println(Constants.RED + "Invalid option. Try again" + Constants.RESET);
                }
                ConsoleUtil.waitForEnter(input);
            }else {
                ConsoleUtil.clearScreen();
                switch (choice) {
                    case 0 -> {return;}
                    case 1 -> {
                        scheduleAppointment(input, appointmentQueue, patientList, doctorStack);
                        appointmentQueue.save(filename); 
                        patientList.save("data/patient.json");
                        break;
                    }
                    case 2 ->  {
                        displayUpcomingAppointments(input,appointmentQueue);
                    }
                    default -> System.out.println(Constants.RED+"Invalid option. Try again"+Constants.RESET);
                }
                ConsoleUtil.waitForEnter(input);
            }
        } while (choice != 0);
    }

   static void scheduleAppointment(Scanner sc, Queue appointmentQueue, LinkedList patientList, Stack doctorStack) {
        boolean isAvailable = false;
        String speciality, apptId, patientId,doctorId,complaint,date, time;

        // patientList.load("data/patient.json");
        do{
            ConsoleUtil.clearScreen();
            System.out.print("Speciality               : ");
            speciality = sc.nextLine();
            filename = "data/Appointment_"+speciality+".txt";

            FileHandler.createFile("data/Appointment_"+speciality+".txt");
            appointmentQueue.load("data/Appointment_"+speciality+".txt");

            apptId = generateAppointmentID(appointmentQueue);
            System.out.println("Appointment ID           : " + apptId);

            System.out.print("Patient ID (numbers only): ");
            patientId = sc.nextLine();
            if (!patientId.matches("\\d+")) {
                System.out.println(Constants.RED + "Invalid Patient ID. Must be a number" + Constants.RESET);
                return;
            }
            Patient patient = patientList.getPatientById("P" + patientId);
            if (patient == null) {
                System.out.println(Constants.RED + "Error: Patient with ID P" + patientId + " does not exist" + Constants.RESET);
                return;
            }

            System.out.print("Doctor ID (numbers only): ");
            doctorId = sc.nextLine();
            if (!doctorId.matches("\\d+")) {
                System.out.println(Constants.RED + "Invalid Doctor ID. Must be a number" + Constants.RESET);
                return;
            }
            Doctor doctor = doctorStack.getDoctorById("D" + doctorId);
            if (doctor == null) {
                System.out.println(Constants.RED + "Error: Doctor with ID D" + doctorId + " does not exist" + Constants.RESET);
                return;
            }

            System.out.print("Complaint                : ");
            complaint = sc.nextLine();

            System.out.print("Date (DD-MM-YYYY)        : ");
            date = sc.nextLine();
            System.out.print("Time (HH:mm)             : ");
            time = sc.nextLine();
            if(ScheduleDoctorAvailable(sc,doctorId,date,time)){
                isAvailable = true;
            } else {
                System.out.println(Constants.RED + "\nDoctor is not available at that time. Please choose another slot" + Constants.RESET);
                ConsoleUtil.waitForEnter(sc);;
            }
        }while(!isAvailable);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime appointmentDateTime;
        try {
            String datetimeStr = date + " " + time;
            appointmentDateTime = LocalDateTime.parse(datetimeStr, formatter);
            if (appointmentDateTime.isBefore(LocalDateTime.now())) {
                System.out.println(Constants.RED + "Appointment must be in the future!" + Constants.RESET);
                return;
            }
        } catch (DateTimeParseException e) {
            System.out.println(Constants.RED + "Invalid date/time format!" + Constants.RESET);
            return;
        }
        Appointment appointment = new Appointment(apptId, "P" + patientId, "D" + doctorId, speciality, complaint, date, time);
        appointmentQueue.enqueue(filename,appointment);

        ConsoleUtil.clearScreen();
        System.out.println(Constants.GREEN + "Appointment scheduled successfully\n" + Constants.RESET + appointment.toString(patientList, doctorStack));
    }

   public static boolean ScheduleDoctorAvailable(Scanner input, String docId, String date, String time) {
        LocalDate inputDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        DayOfWeek dayOfWeek = inputDate.getDayOfWeek(); 
        String dayName = dayOfWeek.toString(); 

        try (BufferedReader scheduleReader = new BufferedReader(new FileReader("data/schedule.txt"))) {
            String scheduleLine;
            boolean found = false;

            while ((scheduleLine = scheduleReader.readLine()) != null) {
                String[] scheduleParts = scheduleLine.split("\\|");

                if (scheduleParts.length >= 7) {
                    String Id = scheduleParts[0];
                    String day = scheduleParts[3].toUpperCase(); 

                    if (Id.equalsIgnoreCase("D"+docId) && day.equals(dayName)) {
                        LocalTime checkTime = LocalTime.parse(time);
                        LocalTime startTime = LocalTime.parse(scheduleParts[5]); 
                        LocalTime endTime = LocalTime.parse(scheduleParts[6]);  

                        if (!checkTime.isBefore(startTime) && !checkTime.isAfter(endTime)) {
                            return true;
                        }
                    }
                }
            }
           // System.out.println(Constants.RED + "There is no schedule available at that time" + Constants.RESET);

        } catch (IOException e) {
            System.out.println(Constants.RED + "Failed to open schedule.txt : " + e.getMessage() + Constants.RESET);
        }

        return false;
    }

    private static String generateAppointmentID(Queue appointmentQueue) {
        int maxId = 0;
        Queue.Node current = appointmentQueue.front;
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        while (current != null) {
            String id = current.appointment.getAppointmentId();
            if (id.startsWith("A" + today + "-")) {
                try {
                    String numberPart = id.substring(("A" + today + "-").length());
                    int num = Integer.parseInt(numberPart);
                    maxId = Math.max(maxId, num);
                } catch (NumberFormatException e) {}
            }
            current = current.next;
        }
        int newId = maxId + 1;
        return String.format("A%s-%03d", today, newId); 
    }

    static void processAppointment(Scanner sc, Queue appointmentQueue, LinkedList patientList,String loginDoc, Stack doctorStack) {
        System.out.print("Speciality Appointment : ");
        String speciality = sc.nextLine();
        filename = "data/Appointment_"+speciality+".txt";

        // patientList.load("data/patient.json");

        FileHandler.createFile("data/Appointment_"+speciality+".txt");
        appointmentQueue.load("data/Appointment_"+speciality+".txt");
<<<<<<< HEAD
        Appointment top = appointmentQueue.getTopAppointment();

        if (top != null) {
            System.out.println("\nAppointment ID   : " + top.getAppointmentId());
            System.out.println("Patient ID       : " + top.getPatientId());
            System.out.println("Doctor ID        : " + top.getDoctorId());
            System.out.println("Date             : " + top.getDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
            System.out.println("Complaint        : " + top.getComplaint()+"\n");
            ConsoleUtil.waitForEnter(sc);
        } else {
            System.out.println(Constants.RED+"No appointments available"+Constants.RESET);
        }

        if (!top.getDoctorId().equals(loginDoc)) {
            System.out.println(Constants.RED + "This appointment is not assigned to you\n" + Constants.RESET);
            ConsoleUtil.waitForEnter(sc);
            return;
        }

        if (!isDoctorLoggedIn(top.getDoctorId())) {
            System.out.println(Constants.RED + "Doctor " + top.getDoctorId() + " is not logged in\n" + Constants.RESET);
            ConsoleUtil.waitForEnter(sc);
            return;
        }

        System.out.println(Constants.GREEN+"\nDetail Processed appointment\n"+Constants.RESET);
        Appointment processed = appointmentQueue.dequeue(filename);

=======
        Appointment processed = appointmentQueue.dequeue(filename);

        if (processed == null) {
            System.out.println(Constants.RED+"No appointments to process"+Constants.RESET);
            return;
        }
        ConsoleUtil.clearScreen();
        System.out.println(Constants.GREEN+"\nDetail Processed appointment\n"+Constants.RESET);
        System.out.println(processed.toString(patientList, doctorStack));
        ConsoleUtil.waitForEnter(sc);

        ConsoleUtil.clearScreen();
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
        System.out.println("Appointment ID   : "+processed.getAppointmentId());
        System.out.println("Patient ID       : "+processed.getPatientId());
        String patientId = processed.getPatientId();
        System.out.println("Doctor ID        : "+processed.getDoctorId());
<<<<<<< HEAD
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        System.out.println("Date             : " + processed.getDateTime().format(formatter));  
        System.out.println("Complaint        : "+processed.getComplaint());
        String complaint = processed.getComplaint();
=======
        System.out.println("Date             : "+processed.getDateTime());
        System.out.println("Complaint        : "+processed.getComplaint());
        String complaint = processed.getComplaint();
        if(!processed.getDoctorId().equals(loginDoc)){
            System.out.println(Constants.RED+"You can not process this appointment\n"+Constants.RESET);
            ConsoleUtil.waitForEnter(sc);
            return;
        }
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
        System.out.print("Diagnosis        : ");
        diagnosis = sc.nextLine();
        System.out.print("Medication       : ");
        medication = sc.nextLine(); 
        System.out.print("Treatment Type   : ");
        treatment = sc.nextLine();
        System.out.print("Room Number      : ");
        roomNumber = sc.nextLine();
        updateCapacityRoom("data/roomNumber.txt", roomNumber);

<<<<<<< HEAD
        Patient p = patientList.getPatientById(processed.getPatientId()); 

        if (p != null) { 
            p.addMedicalRecord( new MedicalRecord(complaint,diagnosis, medication, treatment, roomNumber)); 

=======
        Patient p = patientList.getPatientById(processed.getPatientId()); // Dapatkan pasien yang ada berdasarkan ID

        if (p != null) { // Pastikan pasien ditemukan
            p.addMedicalRecord( new MedicalRecord(complaint,diagnosis, medication, treatment, roomNumber)); // Tambahkan medical record ke pasien yang *ada*

            //patientList.save("data/patient.json"); // Simpan perubahan ke file
            //System.out.println(Constants.GREEN + "Data berhasil disimpan!" + Constants.RESET);
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
        } else {
            System.out.println(Constants.RED + "Pasien dengan ID " + processed.getPatientId() + " tidak ditemukan." + Constants.RESET);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/MedicalRecord.txt",true))) {
            String line = String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s",
                processed.getAppointmentId(),
                processed.getPatientId(),
                processed.getDoctorId(),
                speciality, 
                complaint,
                diagnosis,
                medication,
                treatment,
                roomNumber,
                processed.getDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                writer.write(line);
                writer.newLine();
        } catch (IOException e) {
            System.out.println(Constants.RED+"Error to TXT : " + e.getMessage()+Constants.RESET);
        }
<<<<<<< HEAD
=======
        // MedicalRecord records = new MedicalRecord(diagnosis,medication,treatment, roomNumber);
        //ConsoleUtil.waitForEnter(sc);
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
        ConsoleUtil.clearScreen();
        DisplayMedicalRecord(sc,patientId,processed,patientList,doctorStack);
    }

<<<<<<< HEAD
    public static boolean isDoctorLoggedIn(String doctorId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/logindoctor.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 1 && parts[0].equalsIgnoreCase(doctorId)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println(Constants.RED + "Error reading logindoctor.txt : " + e.getMessage() + Constants.RESET);
        }
        return false;
    }

=======
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
    public static void DisplayMedicalRecord(Scanner sc,  String patientId, Appointment appointment, LinkedList patientList, Stack doctorStack) {
        File inputFile = new File("data/MedicalRecord.txt");
        Doctor doctor = doctorStack.getDoctorById(appointment.getDoctorId());
        Patient patient = patientList.getPatientById(patientId);
        if (patient == null) {
            System.out.println(Constants.RED + "Error: Patient with ID"+ patientId + " does not exist" + Constants.RESET);
            return;
        }
        
        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        ){
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] parts = currentLine.split("\\|");
                if (parts.length >= 10 && parts[1].equals(patientId)) {
                    System.out.println("________________________________________________________________");
                    System.out.println("|                        DAISUKE CLINIC                         |");
                    System.out.println("|                    MEDICAL RECORD PATIENT                     |");
                    System.out.println("|===============================================================|");
                    System.out.println("|```````````````````````````````````````````````````````````````|");
                    System.out.printf("| "+ "%-15s" + "  : %-42s |\n", "Appointment ID", appointment.getAppointmentId());
                    System.out.printf("| "+ "%-15s" + " : %-42s |\n", "Appointment Date", parts[9]);
                    System.out.println("|---------------------------------------------------------------|");
                    System.out.println("| "+Constants.BLUE + "Doctor Information   " + Constants.RESET + "                                         |");
                    System.out.println("|---------------------------------------------------------------|");
                    System.out.printf("| " +"%-15s" +" : %-43s |\n", "Doctor ID", doctor.getId());
                    System.out.printf("| " +"%-15s" +" : %-43s |\n", "Doctor Name", doctor.getName());
                    System.out.printf("| " +"%-15s" +" : %-43s |\n", "Speciality", doctor.getSpeciality());
                    System.out.println("|---------------------------------------------------------------|");

                    System.out.println("| "+Constants.CYAN+ "Patient Information  " + Constants.RESET + "                                         |");

                    System.out.println("|---------------------------------------------------------------|");
                    System.out.printf("| "+ "%-15s" + " : %-43s |\n", "Patient ID", patient.getId());
                    System.out.printf("| "+ "%-15s" + " : %-43s |\n", "Patient Name", patient.getName());
                    System.out.printf("| "+ "%-15s" + " : %-43s |\n", "Age", patient.getAge());
                    System.out.printf("| "+ "%-15s" + " : %-43s |\n", "Address", patient.getAddress());
                    System.out.printf("| "+ "%-15s" + " : %-43s |\n", "Phone Number", patient.getPhoneNumber());
                    System.out.println("|---------------------------------------------------------------|");

                    System.out.println("| "+Constants.RED + "Medical Details  " + Constants.RESET + "                                             |");
                    System.out.println("|---------------------------------------------------------------|");
                    System.out.printf("| " + "%-15s" + " : %-43s |\n", "Complaint", parts[4]);
                    System.out.printf("| " + "%-15s" + " : %-43s |\n", "Diagnosis", parts[5]);
                    System.out.printf("| " + "%-15s" + " : %-43s |\n", "Medication", parts[6]);
                    System.out.printf("| " + "%-15s" + " : %-43s |\n", "Treatment Type", parts[7]);
                    System.out.printf("| " + "%-15s" + " : %-43s |\n", "Room Number", parts[8]);
                    System.out.println("|---------------------------------------------------------------|");
                    System.out.println("|               Thank you for trusting us :)                    |");
                    System.out.println("|              Get well soon and stay healthy!                  |");
                    System.out.println("=================================================================");
                }
            }
        } catch (IOException e) {
            System.out.println("Error updating patient record: " + e.getMessage());
        }
    }

    static void displayUpcomingAppointments(Scanner sc,Queue appointmentQueue) {
        System.out.print("Speciality Appointment : ");
        String speciality = sc.nextLine();
        filename = "data/Appointment_"+speciality+".txt";

        FileHandler.createFile("data/Appointment_"+speciality+".txt");
        appointmentQueue.load("data/Appointment_"+speciality+".txt");
        appointmentQueue.displayQueue(filename);
    }

    public static void updateCapacityRoom (String filename, String room) {
        StringBuilder updatedContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String roomNumber = parts[0];
                int capacity = Integer.parseInt(parts[1]);

                if (room.equals(roomNumber) && capacity > 0) {
                    capacity -= 1; 
                }
                updatedContent.append(roomNumber).append("|").append(capacity).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(updatedContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
