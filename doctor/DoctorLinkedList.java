package doctor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import utility.Constants;

public class DoctorLinkedList {
    private static class Node {
        public Doctor doctor;
        public Node next;

        public Node(Doctor doctor) {
            this.doctor = doctor;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public DoctorLinkedList() {
        head = null;
        size = 0;
    }

    public void addLast(Doctor doctor) {
        Node newNode = new Node(doctor);
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        size++;
    }

    public void registerDoctor(Doctor doctor) {
        Node newNode = new Node(doctor);
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        size++;
        save("data/doctor.txt");
    }

    public void addDoctor(Doctor doctor) {
        addLast(doctor);
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public Doctor get(int index) {
        if (index < 0 || index >= size) return null;

        Node current = head;
        int count = 0;
        while (current != null) {
            if (count == index) {
                return current.doctor;
            }
            current = current.next;
            count++;
        }
        return null;
    }

    public Doctor getLastLogin() {
        return head != null ? head.doctor : null;
    }

    public void save(String filename) {
        DateTimeFormatter time = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            Node current = head;
            while (current != null) {
                Doctor doctor = current.doctor;
                String line = String.format("%s|%s|%s|%s|%s|registered", 
                    doctor.getId(),
                    doctor.getName(),
                    doctor.getSpeciality(),
                    doctor.getPassword(),
                    doctor.getLoginTime().format(time));
                bw.write(line);
                bw.newLine();
                current = current.next;
            }
        } catch (IOException e) {
            System.out.println(Constants.RED+"Error saving doctors to TXT: " + e.getMessage()+Constants.RESET);
        }
    }

    public void clear() {
        head = null;
        size = 0;
    }

    
}
