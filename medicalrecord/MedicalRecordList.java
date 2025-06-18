package medicalrecord;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class MedicalRecordList implements Iterable<MedicalRecord> {

    public static class Node {
        public MedicalRecord data;
        public Node next;

        public Node(MedicalRecord data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    public MedicalRecordList() {
        head = null;
    }

    public void add(MedicalRecord data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int count = 0;
        Node curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    public void clear() {
        head = null;
    }

    public Node getHead() {
        return head;
    }

    @Override
    public Iterator<MedicalRecord> iterator() {
        return new Iterator<MedicalRecord>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public MedicalRecord next() {
                MedicalRecord data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node curr = head;
        while (curr != null) {
            sb.append("\n- ").append(curr.data.toString());
            curr = curr.next;
        }
        return sb.toString();
    }

    public void save(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            Node current = head;
            while (current != null) {
                MedicalRecord record = current.data;
                String line = record.getComplaint() + "|" +
                            record.getDiagnosis() + "|" +
                            record.getMedication() + "|" +
                            record.getTreatment() + "|" +
                            record.getRoomNumber();
                bw.write(line);
                bw.newLine();
                current = current.next;
            }
            System.out.println("Data rekam medis berhasil disimpan ke " + filename);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data rekam medis: " + e.getMessage());
        }
    }

    public void load(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 10) {
                    String appointmentId = parts[0];
                    String patientId = parts[1];
                    String doctorId = parts[2];
                    String speciality = parts[3];
                    String complaint = parts[4].trim();
                    String diagnosis = parts[5].trim();
                    String medication = parts[6].trim();
                    String roomNumber = parts[7].trim();

                    MedicalRecord record = new MedicalRecord(complaint, diagnosis, medication, "", roomNumber);
                    add(record);
                }
            }
            //System.out.println("Data rekam medis berhasil dimuat.");
        } catch (IOException e) {
            //System.out.println("Gagal memuat data rekam medis: " + e.getMessage());
        }
    }

}
