package patient;

import medicalrecord.*;

public class Patient {
    private String id;
    private String name;
    private int age;
    private String address;
    private String phoneNumber;
    private MedicalRecordList medicalrecord ;

    public Patient(String id, String name, int age, String address, String phoneNumber) {
        this.id = id; 
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.medicalrecord = new MedicalRecordList();
    }

    public String getId() {
        if (!id.startsWith("P")) {
            return "P" + id;
        } else {
            return id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public MedicalRecordList getMedicalrecord() {
        return medicalrecord;
    }

    public void addMedicalRecord(MedicalRecord record) {
        if (record != null) {
            medicalrecord.add(record);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Patient ID     : ").append(id).append("\n");
        sb.append("Name           : ").append(name).append("\n");
        sb.append("Age            : ").append(age).append("\n");
        sb.append("Address        : ").append(address).append("\n");
        sb.append("Phone Number   : ").append(phoneNumber).append("\n");
        if (medicalrecord == null || medicalrecord.isEmpty()) {
            sb.append("Medical Record : - ");
        } else {
            sb.append(medicalrecord.toString());
        }
        return sb.toString();
    }
}
