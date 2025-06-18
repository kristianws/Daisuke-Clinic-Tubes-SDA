package appointment;

import java.io.*;
import java.time.format.DateTimeFormatter;

import utility.*;
import patient.*;
import doctor.*;

public class Queue {
    public class Node {
        Appointment appointment;
        Node next;

        Node(Appointment appointment) {
            this.appointment = appointment;
            this.next = null;
        }
    }

    Node front;
    private Node rear;
    private LinkedList patientList;
    private Stack doctorStack;

    public Queue(LinkedList patientList, Stack doctorStack) {
        this.front = null;
        this.rear = null;
        this.patientList = patientList;
        this.doctorStack = doctorStack;
    }

    public void load(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            front = rear = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 6) {
                    String appointmentId = parts[0];
                    String patientId = parts[1];
                    String doctorId = parts[2];
                    String speciality= parts[3];
                    String complaint = parts[4];
                    String[] dateTimeParts = parts[5].split(" ");
                    if (dateTimeParts.length == 2) {
                        String date = dateTimeParts[0];
                        String time = dateTimeParts[1];
                        Appointment appointment = new Appointment(appointmentId, patientId, doctorId, speciality, complaint,date, time);
                        enqueue(filename,appointment);
                    }else {
                        System.out.println(Constants.RED + "Invalid date-time format in line : " + line + Constants.RESET);
                    }
                }
            }
            save(filename);
        } catch (IOException e) {
            System.out.println(Constants.RED+"Error loading appointments from TXT : " + e.getMessage()+Constants.RESET);
        }
    }

    public void save(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            Node current = front;
            while (current != null) {
                Appointment appt = current.appointment;
                String dateTime = appt.getDate() + " " + appt.getTime();
                String line = String.format("%s|%s|%s|%s|%s|%s",
                        appt.getAppointmentId(),
                        appt.getPatientId(),
                        appt.getDoctorId(),
                        appt.getSpeciality(),
                        appt.getComplaint(),
                        dateTime);
                writer.write(line);
                writer.newLine();
                current = current.next;
            }
        } catch (IOException e) {
            System.out.println(Constants.RED + "Error saving appointments to TXT : " + e.getMessage() + Constants.RESET);
        }
    }

    public void enqueue(String filename,Appointment appointment) {
        Node newNode = new Node(appointment);
        if (front == null|| appointment.getDateTime().isBefore(front.appointment.getDateTime())) {
            newNode.next = front;
            front = newNode;
            if (rear == null) rear = newNode; 
            save(filename); 
            return;
        } 
        
        Node current = front;
        while (current.next != null && current.next.appointment.getDateTime().isBefore(appointment.getDateTime())) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
        if (newNode.next == null) rear = newNode; 
        save(filename);
    }

    public Appointment dequeue(String filename) {
        if (front == null) return null;

        Appointment appointment = front.appointment;
        front = front.next;
        if (front == null) rear = null;
        save(filename);
        return appointment;
    }

    public boolean isAppointmentIdUnique(String id) {
        String fullId = id.startsWith("A") ? id : "A" + id;
        Node current = front;
        while (current != null) {
            if (current.appointment.getAppointmentId().equals(fullId)) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

<<<<<<< HEAD
    public Appointment getTopAppointment() {
        return front != null ? front.appointment : null;
    }

=======
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
    public void displayQueue(String filename) {
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        load(filename);
        Node current = front;
        int number= 1;
        System.out.println("_______________________________________________________________________________________________________________________________________________");
        System.out.println("|                                                         APPOINTMENTS INFORMATION                                                             |");
        System.out.println("===============================================================================================================================================|");
        System.out.printf("| %-3s | %-13s | %-30s | %-20s | %-10s | %-28s | %-15s    |\n", "No","ID", "Doctor (ID)", "Patient (ID)", "Speciality", "Complaint", "Date");
        System.out.println("|----------------------------------------------------------------------------------------------------------------------------------------------|");

        while (current != null) {
            Appointment a = current.appointment;
            System.out.printf("| %-3s | %-13s | %-30s | %-20s | %-10s | %-28s | %-15s |\n",
                    number++, a.getAppointmentId(), a.getDoctorName(doctorStack)+ " (ID : " + a.getDoctorId() + " )", a.getPatientName(patientList) + " (ID : " + a.getPatientId() + ")",a.getSpeciality(),a.getComplaint(),a.getDateTime().format(formatTime));
            current = current.next;
        }
        System.out.println("================================================================================================================================================");
    }
}