package schedule;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import admin.Admin;

public class ScheduleList implements Iterable<Schedule> {
    public static class Node {
        public Schedule data;
        public Node next;

        public Node(Schedule data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    public ScheduleList() {
        head = null;
    }

    public void add(Schedule data) {
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

    public Iterator<Schedule> iterator() {
        return new Iterator<Schedule>() {
            private Node current = head;

            public boolean hasNext() {
                return current != null;
            }

            public Schedule next() {
                Schedule data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    public void load(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 7) {
                    String doctorId = parts[0].trim();
                    String doctorName = parts[1].trim();
                    String speciality = parts[2].trim();
                    String day = parts[3].trim();
                    String shift = parts[4].trim();
                    String startTime = parts[5].trim();
                    String endTime = parts[6].trim();

                    Schedule schedule = new Schedule(
                        doctorId, doctorName, speciality, day, shift, startTime, endTime
                    );
                    add(schedule);
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to open txt : " + e.getMessage());
        }
    }

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
                Schedule schedule = current.data;
                String line = schedule.getDoctorId() + "|" +
                            schedule.getDoctorName() + "|" +
                            schedule.getSpeciality() + "|" +
                            schedule.getDay() + "|" +
                            schedule.getShift() + "|" +
                            schedule.getStartTime() + "|" +
                            schedule.getEndTime();
                bw.write(line);
                bw.newLine();
                current = current.next;
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file: " + e.getMessage());
        }
    }
}
