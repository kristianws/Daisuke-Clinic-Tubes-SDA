package patient;

import java.io.*;

import utility.*;
import com.google.gson.*;

public class LinkedList {
    public static class Node {
        public Patient patient;
        public Node next;

        public Node(Patient patient) {
            this.patient = patient;
            this.next = null;
        }
    }

    public Node head;

    public LinkedList() {
        head = null;
    }

    public boolean isIdUnique(String id) {
        String fullId = id.startsWith("P") ? id : "P" + id;
        Node current = head;
        while (current != null) {
            if (current.patient.getId().equals(fullId)) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    public void addPatient(Patient patient) {
        Node newNode = new Node(patient);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        save("data/patient.json");
    }

    public boolean removePatientById(String id) {
        if (head == null) {
            return false;
        }
        if (head.patient.getId().equals(id)) {
            head = head.next;
            save("data/patient.json");
            return true;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.patient.getId().equals(id)) {
                current.next = current.next.next;
                save("data/patient.json");
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void findPatientByName(String name) {
        Node current = head;
        boolean found = false;
        while (current != null) {
            if (current.patient.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(Constants.GREEN+"Patient Name is Found !\n"+Constants.RESET);
                System.out.println(current.patient);
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println(Constants.RED+"No patient found with name : " + name+ Constants.RESET);
        }
    }

    public void findPatientByID(String id) {
        Node current = head;
        boolean found = false;
        while (current != null) {
            if (current.patient.getId().equals(id)) {
                System.out.println(current.patient);
                found = true; 
            }
            current = current.next;
        }
        if (!found) {
            System.out.println(Constants.RED + "No patient found with name : " + id + Constants.RESET);
        }
    }

    public Patient getPatientById(String id) {
        Node current = head;
        while (current != null) {
            if (current.patient.getId().equals(id)) {
                return current.patient;
            }
            current = current.next;
        }
        return null;
    }

    public void displayAllPatients() {
        if (head == null) {
            System.out.println(Constants.RED + "No patients registered!" + Constants.RESET);
            return;
        }
        Node current = head;
        int number= 1;
        System.out.println("____________________________________________________________________________");
        System.out.println("|                             PATIENTS INFORMATION                          |");
        System.out.println("============================================================================|");
        System.out.printf("| %-3s | %-5s | %-12s | %-3s | %-15s | %-20s |\n", "No", "ID", "Name", "Age", "Phone Number", "Address");
        System.out.println("|---------------------------------------------------------------------------|");

        while (current != null) {
            Patient p = current.patient;
            System.out.printf("| %-3d | %-5s | %-12s | %-3d | %-15s | %-20s |\n",
                    number++, p.getId(), p.getName(), p.getAge(), p.getPhoneNumber(), p.getAddress());
            current = current.next;
        }
        System.out.println("=============================================================================");
    }

<<<<<<< HEAD
=======
    // public void save(String filename) {
    //     try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
    //         writer.write("[\n");
    //         Node current = head;
    //         while (current != null) {
    //             writer.write(current.patient.toJson());
    //             if (current.next != null) writer.write(",\n");
    //             current = current.next;
    //         }
    //         writer.write("\n]");
    //     } catch (IOException e) {
    //         System.out.println("Error saving to JSON: " + e.getMessage());
    //     }
    // }

>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
    public String toJson() {
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
        return gson.toJson(this);
    }

    public Patient[] toArray() {
<<<<<<< HEAD
=======
        // Hitung jumlah elemen
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
        int size = 0;
        Node curr = head;
        while (curr != null) {
            size++;
            curr = curr.next;
        }
<<<<<<< HEAD
=======

        // Pindahkan isi ke array
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
        Patient[] array = new Patient[size];
        curr = head;
        int index = 0;
        while (curr != null) {
            array[index++] = curr.patient;
            curr = curr.next;
        }
        return array;
    }

    public void save(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Patient[] array = toArray(); 
            gson.toJson(array, writer);  
        } catch (Exception e) {
            System.out.println("Failed to save patients: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addPatientSilently(Patient patient) {
        Node newNode = new Node(patient);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }
    
    public LinkedList getAllPatients() {
        LinkedList patients = new LinkedList();
        Node current = head;
        
        while (current != null) {
            patients.addPatient(current.patient); 
            current = current.next;
        }
        
        return patients;
    }

    public void load(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Patient[] patients = gson.fromJson(reader, Patient[].class);

            for (int i = 0; i < patients.length; i++) {
                addPatientSilently(patients[i]);
            }

            System.out.println("Patient data loaded successfully.");
        } catch (Exception e) {
            System.out.println("Failed to load patients: " + e.getMessage());
            e.printStackTrace();
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
