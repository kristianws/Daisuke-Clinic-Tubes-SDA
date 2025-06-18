package medicalrecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import utility.ConsoleUtil;
import utility.Constants;

public class MedicalRecordManagement {
    public void menu(Scanner input) {
        int choice;
        do {
            ConsoleUtil.clearScreen();
            System.out.println("=================================================");
            System.out.println("----------- Medical Record Management -----------");
            System.out.println("=================================================");
            System.out.println("| 1 | Search Medical Record By Name Patient     |");
            System.out.println("| 2 | Display All Medical Record                |");
            System.out.println("| 0 | Exit From Appointment Management          |");
            System.out.println("-------------------------------------------------");
            System.out.print("Choose Appointment Management Menu : ");
            choice = input.nextInt();
            input.nextLine();

            ConsoleUtil.clearScreen();
            switch (choice) {
                case 0 -> {return;}
                case 1 -> {
                    searchRecordsByName(input);
                    ConsoleUtil.waitForEnter(input);
                    break;
                }
                case 2 -> {
                    displayAllMedicalRecords();
                    ConsoleUtil.waitForEnter(input);
                    break;
                }
                default -> System.out.println(Constants.RED+"Invalid option. Try again"+Constants.RESET);
            }
        } while (choice != 0);
    }

    public void searchRecordsByName(Scanner input) {
        System.out.print("Name Patient : ");
        String name = input.nextLine();

        ConsoleUtil.clearScreen();
        try (BufferedReader recordReader = new BufferedReader(new FileReader("data/MedicalRecord.txt"))) {
            String recordLine;
            boolean found = false;

            while ((recordLine = recordReader.readLine()) != null) {
                String[] recordParts = recordLine.split("\\|");
                if (recordParts.length >= 10) {
                    String patientIdInRecord = recordParts[1];
                    String[] patientInfo = getPatientInfoById(patientIdInRecord);

                    if (patientInfo != null && patientInfo[1].equalsIgnoreCase(name)) {
                        String doctorId = recordParts[2];
                        String[] doctorInfo = getDoctorInfoById(doctorId);

                        System.out.println("================ Medical Record ================");
                        System.out.println("Appointment ID : " + recordParts[0]);
                        System.out.println("Patient ID     : " + patientInfo[0]);
                        System.out.println("Name           : " + patientInfo[1]);
                        System.out.println("Age            : " + patientInfo[2]);
                        System.out.println("Address        : " + patientInfo[3]);
                        System.out.println("Phone Number   : " + patientInfo[4]);
                        System.out.println("Doctor ID      : " + doctorId);
                        if (doctorInfo != null) {
                            System.out.println("Doctor Name    : " + doctorInfo[1]);
                            System.out.println("Specialist     : " + doctorInfo[2]);
                        } else {
                            System.out.println("Doctor Name    : Cannot find");
                            System.out.println("Specialist     : " + recordParts[3]);
                        }
                        System.out.println("Complaint      : " + recordParts[4]);
                        System.out.println("Diagnosis      : " + recordParts[5]);
                        System.out.println("Medication     : " + recordParts[6]);
                        System.out.println("Treatment      : " + recordParts[7]);
                        System.out.println("Room Number    : " + recordParts[8]);
                        System.out.println("Time           : " + recordParts[9]);
                        System.out.println();
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println(Constants.RED+"There is no medical record for patient " + name+Constants.RESET);
            }

        } catch (IOException e) {
            System.out.println(Constants.RED+"Failed to open MedicalRecord.txt : " + e.getMessage()+Constants.RESET);
        }
    }

    private String[] getPatientInfoById(String patientId) {
        try (FileReader reader = new FileReader("data/patient.json")) {
            JsonArray patients = JsonParser.parseReader(reader).getAsJsonArray();
            
            for (int i = 0; i < patients.size(); i++) {
                JsonObject patient = patients.get(i).getAsJsonObject();
                if (patient.get("id").getAsString().equals(patientId)) {
                    return new String[]{
                        patient.get("id").getAsString(),
                        patient.get("name").getAsString(),
                        patient.get("age").getAsString(),
                        patient.get("address").getAsString(),
                        patient.get("phoneNumber").getAsString(),
                        patient.has("medicalrecord") ? 
                            patient.get("medicalrecord").toString() : "No medical record"
                    };
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    private String[] getDoctorInfoById(String doctorId) {
        try (BufferedReader doctorReader = new BufferedReader(new FileReader("data/doctor.txt"))) {
            String line;
            while ((line = doctorReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 3 && parts[0].equals(doctorId)) {
                    return parts; 
                }
            }
        } catch (IOException e) {
            System.out.println(Constants.RED+"Failed to open doctor.txt: " + e.getMessage()+Constants.RESET);
        }
        return null;
    }

     public void displayAllMedicalRecords() {
        System.out.println("____________________________________________________________________________________________________________________________________________________");
        System.out.println("|                                                         MEDICAL RECORDS INFORMATION                                                               |");
        System.out.println("|===================================================================================================================================================|");
        System.out.printf("| %-15s | %-10s | %-10s | %-15s | %-15s | %-15s | %-15s | %-10s | %-16s |\n",
                "Appointment ID", "Patient ID", "Doctor ID", "Specialist", "Diagnosis", "Medication", "Treatment", "Room", "Time");
        System.out.println("====================================================================================================================================================|");

        try (BufferedReader reader = new BufferedReader(new FileReader("data/MedicalRecord.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 9) {
                    System.out.printf("| %-15s | %-10s | %-10s | %-15s | %-15s | %-15s | %-15s | %-10s | %-16s |\n",
                            parts[0], parts[1], parts[2], parts[3], parts[4],
                            parts[5], parts[6], parts[7], parts[8]);
                }
            }
        } catch (IOException e) {
            System.out.println(Constants.RED + "Failed to read MedicalRecord.txt: " + e.getMessage() + Constants.RESET);
        }

        System.out.println("=====================================================================================================================================================");
    }
}
