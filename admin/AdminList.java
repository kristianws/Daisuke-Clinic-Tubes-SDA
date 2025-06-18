package admin;

import java.util.Iterator;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdminList implements Iterable<Admin> {
    public static class Node {
        public Admin data;
        public Node next;

        public Node(Admin data) {
            this.data = data;
            this.next = null;
        }
    }

    public Node head;

    public AdminList() {
        head = null;
    }

    public void add(Admin data) {
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

    public Iterator<Admin> iterator() {
        return new Iterator<Admin>() {
            private Node current = head;

            public boolean hasNext() {
                return current != null;
            }

            public Admin next() {
                Admin data = current.data;
                current = current.next;
                return data;
            }
        };
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

    public void load(String filename) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String id = parts[0].trim();
                    String username = parts[1].trim();
                    String password = parts[2].trim();
                    String loginTimeStr = parts[3].trim();
                    LocalDateTime loginTime = LocalDateTime.parse(loginTimeStr, formatter);

                    Admin adm = new Admin(id, username, password, loginTime);
                    add(adm);
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Format data salah: " + e.getMessage());
        }
    }

    public void loadRegister(String filename) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length >= 3) { 
                    String id = parts[0].trim();
                    String username = parts[1].trim();
                    String password = parts[2].trim();

                    LocalDateTime loginTime = null;
                    if (parts.length == 4) {
                        String loginTimeStr = parts[3].trim();
                        loginTime = LocalDateTime.parse(loginTimeStr, formatter);
                    }

                    Admin adm = new Admin(id, username, password, loginTime);
                    add(adm);
                }
            }

        } catch (IOException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Format data salah: " + e.getMessage());
        }
    }

    public void save(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            Node current = head;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            while (current != null) {
                Admin admin = current.data;
                String loginTime = admin.getLoginTime().format(formatter);
                String line = admin.getId() + "|" + admin.getName() + "|" + admin.getPassword() + "|" + loginTime;
                bw.write(line);
                bw.newLine();
                current = current.next;
            }
            //System.out.println("Data admin berhasil disimpan ke " + filename);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file: " + e.getMessage());
        }
    }

    public void saveRegister(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            Node current = head;
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            while (current != null) {
                Admin admin = current.data;
                //String loginTime = admin.getLoginTime().format(formatter);
                String line = admin.getId() + "|" + admin.getName() + "|" + admin.getPassword();
                bw.write(line);
                bw.newLine();
                current = current.next;
            }
            //System.out.println("Data admin berhasil disimpan ke " + filename);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file: " + e.getMessage());
        }
    }
}