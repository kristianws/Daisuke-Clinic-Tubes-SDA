package medicalrecord;

public class MedicalRecord {
    private String diagnosis;
    private String medication;
    private String treatment;
    private String roomNumber;
    private String complaint;

    public MedicalRecord() {
        
    }

    public MedicalRecord(String complaint, String diagnosis, String medication, String treatment, String roomNumber) {
        this.diagnosis = diagnosis;
        this.medication = medication;
        this.treatment = treatment;
        this.roomNumber = roomNumber;
        this.complaint = complaint;
    }
    
    public String getComplaint() { return complaint; };
    public void setComplaint(String complaint) { this.complaint = complaint; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getMedication() { return medication; }
    public void setMedication(String medication) { this.medication = medication; }

    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public String toJson() {
        return String.format("{\"complaint\':\"%s\",\"diagnosis\":\"%s\", \"medication\":\"%s\", \"treatment\":\"%s\", \"room number\":\"%s\"}",
                diagnosis, medication, treatment, roomNumber);
    }

    public String toString() {
        return "Medical Records :"+"\n"+
               "- Complaint   : "+complaint +"\n"+
               "- Diagnosis   : " + diagnosis + "\n" +
               "- Medication  : "+medication+"\n"+
               "- Treatment   : " + treatment + "\n" +
               "- Room number : " + roomNumber + "\n";
    }
}
