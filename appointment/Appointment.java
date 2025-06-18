package appointment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import doctor.*;
import utility.*;
import patient.*;

public class Appointment {
    String appointmentId;
    String patientId;
    String doctorId;
    String speciality;
    String complaint;
    String date;
    String timeAppointment;

    public Appointment(String appointmentId, String patientId, String doctorId, String speciality, String complaint, String date, String timeAppointment) {
        this.appointmentId = appointmentId.startsWith("A") ? appointmentId : "A" + appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.speciality = speciality;
        this.complaint = complaint;
        this.date = date;
        this.timeAppointment = timeAppointment;
    }

    public String getPatientName(LinkedList patientList) {
        if (patientList == null) return Constants.RED + "Patient list not available" + Constants.RESET;
        Patient patient = patientList.getPatientById(patientId);
        return patient != null ? patient.getName() : Constants.RED + "No patient found with ID : " + patientId + Constants.RESET;
    }
    
    public int getAgePatient(LinkedList patientList) {
        if (patientList == null) return -1;
        Patient patient = patientList.getPatientById(patientId);
        return (patient != null) ? patient.getAge() : -1;
    }

    public String getPatientAddress(LinkedList patientList) {
        if (patientList == null) return Constants.RED + "Patient list not available" + Constants.RESET;
        Patient patient = patientList.getPatientById(patientId);
        return patient != null ? patient.getAddress() : Constants.RED + "No patient found with ID : " + patientId + Constants.RESET;
    }

    public String getPatientPhoneNumber(LinkedList patientList) {
        if (patientList == null) return Constants.RED + "Patient list not available" + Constants.RESET;
        Patient patient = patientList.getPatientById(patientId);
        return patient != null ? patient.getPhoneNumber() : Constants.RED + "No patient found with ID : " + patientId + Constants.RESET;
    }

    public String getDoctorName(Stack doctorStack) {
        if (doctorStack == null) return Constants.RED + "Doctor stack not available" + Constants.RESET;
        Doctor doctor = doctorStack.getDoctorById(doctorId);
        return doctor != null ? doctor.getName() : Constants.RED+"No doctor found with ID : " + doctorId + Constants.RESET;
    }

    public LocalDateTime getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(this.date + " " + this.timeAppointment, formatter);
    }

    public String getAppointmentId() { return appointmentId; }   
    public String getPatientId() { return patientId; }
    public String getDoctorId() { return doctorId; }
    public String getSpeciality() { return speciality; }
    public String getComplaint() { return complaint; }
    public String getDate() { return date; }
    public String getTime() { return timeAppointment; }


    public String toString(LinkedList patientList, Stack doctorStack) {
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "\nAppointment ID : " + appointmentId + "\n" +
               "Patient        : " + getPatientName(patientList) + " (ID : " + patientId + ")" + "\n" +
                "Doctor         : " + getDoctorName(doctorStack) + " (ID : " + doctorId + ")" + "\n" +
               "Speciality     : " + getSpeciality() + "\nComplaint      : "+getComplaint()+"\n"+
               "Date           : " + getDateTime().format(formatTime);
    }
}