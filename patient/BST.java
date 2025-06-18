package patient;

<<<<<<< HEAD
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import utility.*;

// AVL Tree implementation for managing patient data
=======
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;

import utility.*;

//Struktur data BST dengan AVL
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
public class BST {
    public static class AVLNode {
        Patient data;
        AVLNode left, right;
        int height;

        public AVLNode(Patient patient) {
            this.data = patient;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    public AVLNode root;

    public BST() {
        this.root = null;
    }

    public void insertPatient(Patient patient) {
<<<<<<< HEAD
        if (patient == null || !isValidPatientId(patient.getId())) {
            System.out.println(Constants.RED + "Invalid patient ID: " + (patient == null ? "null" : patient.getId()) + Constants.RESET);
            return;
        }
=======
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
        root = insert(root, patient);
    }

    public Patient searchByIdPatient(String id) {
<<<<<<< HEAD
        if (!isValidPatientId(id)) {
            System.out.println(Constants.RED + "Invalid ID format: " + id + Constants.RESET);
            return null;
        }
=======
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
        AVLNode node = search(root, id);
        return node == null ? null : node.data;
    }

<<<<<<< HEAD
    private boolean isValidPatientId(String id) {
        return id != null && id.matches("P\\d+");
    }

    private AVLNode insert(AVLNode node, Patient patient) {
        if (node == null) {
            return new AVLNode(patient);
        }

        // Extract numeric part and compare
        int currentNum = Integer.parseInt(node.data.getId().substring(1));
        int newNum = Integer.parseInt(patient.getId().substring(1));
        if (newNum < currentNum) {
            node.left = insert(node.left, patient);
        } else if (newNum > currentNum) {
            node.right = insert(node.right, patient);
        } else {
            System.out.println(Constants.RED + "Duplicate patient ID: " + patient.getId() + ". Updating existing patient." + Constants.RESET);
            node.data = patient;
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        // Left Left
        if (balance > 1 && Integer.parseInt(patient.getId().substring(1)) < Integer.parseInt(node.left.data.getId().substring(1))) {
            return rightRotate(node);
        }
        // Right Right
        if (balance < -1 && Integer.parseInt(patient.getId().substring(1)) > Integer.parseInt(node.right.data.getId().substring(1))) {
            return leftRotate(node);
        }
        // Left Right
        if (balance > 1 && Integer.parseInt(patient.getId().substring(1)) > Integer.parseInt(node.left.data.getId().substring(1))) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // Right Left
        if (balance < -1 && Integer.parseInt(patient.getId().substring(1)) < Integer.parseInt(node.right.data.getId().substring(1))) {
            node.right = rightRotate(node.right);
=======
    private AVLNode insert(AVLNode node, Patient patient) {
        if (node == null) return new AVLNode(patient);

        if (patient.getId().compareTo(node.data.getId()) < 0) {
            node.left = insert(node.left, patient);
        } else if (patient.getId().compareTo(node.data.getId()) > 0) {
            node.right = insert(node.right, patient);
        } else {
            return node; 
        }

        node.height = 1+Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        if (balance > 1 && patient.getId().compareTo(node.left.data.getId()) < 0) {
            return rightRotate(node); 
        }
        if (balance < -1 && patient.getId().compareTo(node.right.data.getId()) > 0) {
            return leftRotate(node); 
        }
        if (balance > 1 && patient.getId().compareTo(node.left.data.getId()) > 0) {
            node.left = leftRotate(node.left); 
            return rightRotate(node);
        }
        if (balance < -1 && patient.getId().compareTo(node.right.data.getId()) < 0) {
            node.right = rightRotate(node.right); 
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
            return leftRotate(node);
        }
        return node;
    }

    private AVLNode search(AVLNode node, String id) {
<<<<<<< HEAD
        if (node == null || node.data.getId().equals(id)) {
            return node;
        }
        int searchNum = Integer.parseInt(id.substring(1));
        int currentNum = Integer.parseInt(node.data.getId().substring(1));
        if (searchNum < currentNum) {
            return search(node.left, id);
        }
=======
        if (node == null || node.data.getId().equals(id)) return node;
        if (id.compareTo(node.data.getId()) < 0) return search(node.left, id);
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
        return search(node.right, id);
    }

    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

<<<<<<< HEAD
        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));
=======
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

<<<<<<< HEAD
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
=======
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
        return y;
    }

    public void removePatient(String id) {
<<<<<<< HEAD
        if (!isValidPatientId(id)) {
            System.out.println(Constants.RED + "Invalid patient ID: " + id + Constants.RESET);
            return;
        }
=======
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
        root = delete(root, id);
    }

    private AVLNode delete(AVLNode node, String id) {
<<<<<<< HEAD
        if (node == null) {
            return null;
        }

        int compareResult = Integer.parseInt(id.substring(1)) - Integer.parseInt(node.data.getId().substring(1));
        if (compareResult < 0) {
            node.left = delete(node.left, id);
        } else if (compareResult > 0) {
            node.right = delete(node.right, id);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
=======
        if (node == null) return null;

        if (id.compareTo(node.data.getId()) < 0) {
            node.left = delete(node.left, id);
        } else if (id.compareTo(node.data.getId()) > 0) {
            node.right = delete(node.right, id);
            } else {
            // Node ditemukan - 3 kasus penghapusan
            if (node.left == null || node.right == null) {
                AVLNode temp = (node.left != null) ? node.left : node.right;
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
            } else {
                AVLNode temp = getMinValueNode(node.right);
                node.data = temp.data;
                node.right = delete(node.right, temp.data.getId());
            }
        }
<<<<<<< HEAD

        if (node == null) {
            return null;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        // Left Left
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }
        // Left Right
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // Right Right
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }
        // Right Left
=======
        if (node == null) return null;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node); 
        }
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left); 
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private AVLNode getMinValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

<<<<<<< HEAD
    private void printTableHeader(String traversalType) {
        System.out.println("____________________________________________________________________________");
        System.out.println("|                         PATIENTS INFORMATION                              |");
        System.out.println(  "|                          (BST " + traversalType.toUpperCase() + ")                                    |");
        System.out.println("============================================================================|");
        System.out.printf("| %-3s | %-5s | %-12s | %-3s | %-15s | %-20s |\n", "No", "ID", "Name", "Age", "Phone Number", "Address");
        System.out.println("|---------------------------------------------------------------------------|");
    }

=======
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
    public void inOrderDisplay() {
        if (root == null) {
            System.out.println(Constants.RED + "No patients in BST" + Constants.RESET);
            return;
        }
<<<<<<< HEAD
        printTableHeader("Inorder");
        int counter = 1;
        counter = inOrder(root, counter);
        System.out.println("=============================================================================");
    }

    private int inOrder(AVLNode root, int counter) {
        if (root != null && root.data != null) {
            counter = inOrder(root.left, counter);
            Patient p = root.data;
            System.out.printf("| %-3d | %-5s | %-12s | %-3d | %-15s | %-20s |\n",
                    counter++, p.getId(), p.getName(), p.getAge(), p.getPhoneNumber(), p.getAddress());
            counter = inOrder(root.right, counter);
        }
        return counter;
=======
        System.out.println("_______________________________________________________________________");
        System.out.println("|                         PATIENTS INFORMATION                        |");
        System.out.println("|                            (BST INORDER)                            |");
        System.out.println("======================================================================|");
        System.out.printf("| %-5s | %-12s | %-3s | %-15s | %-20s |\n", "ID", "Name", "Age", "Phone Number", "Address");
        System.out.println("|---------------------------------------------------------------------|");
        inOrder(root, new int[]{1});

        System.out.println("=======================================================================");
    }

    private void inOrder(AVLNode root, int[] counter) {
        if (root != null) {
            inOrder(root.left, counter);

            Patient p = root.data;
            System.out.printf("| %-5s | %-12s | %-3d | %-15s | %-20s |\n",
                   p.getId(), p.getName(), p.getAge(), p.getPhoneNumber(), p.getAddress());

            inOrder(root.right, counter);
        }
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
    }

    public void preOrderDisplay() {
        if (root == null) {
            System.out.println(Constants.RED + "No patients in BST" + Constants.RESET);
            return;
        }
<<<<<<< HEAD
        printTableHeader("Preorder");
        int counter = 1;
        counter = preOrder(root, counter);
       System.out.println("=============================================================================");
    }

    private int preOrder(AVLNode root, int counter) {
        if (root != null && root.data != null) {
            Patient p = root.data;
            System.out.printf("| %-3d | %-5s | %-12s | %-3d | %-15s | %-20s |\n",
                    counter++, p.getId(), p.getName(), p.getAge(), p.getPhoneNumber(), p.getAddress());
            counter = preOrder(root.left, counter);
            counter = preOrder(root.right, counter);
        }
        return counter;
=======
        System.out.println("_______________________________________________________________________");
        System.out.println("|                         PATIENTS INFORMATION                        |");
        System.out.println("|                            (BST PREORDER)                           |");
        System.out.println("======================================================================|");
        System.out.printf("| %-5s | %-12s | %-3s | %-15s | %-20s |\n", "ID", "Name", "Age", "Phone Number", "Address");
        System.out.println("|---------------------------------------------------------------------|");

       preOrder(root, new int[]{1});

        System.out.println("=======================================================================");
    }

    private void preOrder(AVLNode root, int[] counter) {
        if (root != null) {
            Patient p = root.data;
            System.out.printf("| %-5s | %-12s | %-3d | %-15s | %-20s |\n",
                     p.getId(), p.getName(), p.getAge(), p.getPhoneNumber(), p.getAddress());

           preOrder(root.left, counter);
           preOrder(root.right, counter);
        }
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
    }

    public void postOrderDisplay() {
        if (root == null) {
            System.out.println(Constants.RED + "No patients in BST" + Constants.RESET);
            return;
        }
<<<<<<< HEAD
        printTableHeader("Postorder");
        int counter = 1;
        counter = postOrder(root, counter);
       System.out.println("=============================================================================");
    }

    private int postOrder(AVLNode root, int counter) {
        if (root != null && root.data != null) {
            counter = postOrder(root.left, counter);
            counter = postOrder(root.right, counter);
            Patient p = root.data;
            System.out.printf("| %-3d | %-5s | %-12s | %-3d | %-15s | %-20s |\n",
                    counter++, p.getId(), p.getName(), p.getAge(), p.getPhoneNumber(), p.getAddress());
        }
        return counter;
    }

    public void save(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Patient[] patients = toArray();
            gson.toJson(patients, writer);
            System.out.println("Patient data saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Failed to save patients: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Patient[] toArray() {
        java.util.List<Patient> patientList = new java.util.ArrayList<>();
        collectPatientsInOrder(root, patientList);
        return patientList.toArray(new Patient[0]);
    }

    private void collectPatientsInOrder(AVLNode node, java.util.List<Patient> patientList) {
        if (node != null && node.data != null) {
            collectPatientsInOrder(node.left, patientList);
            patientList.add(node.data);
            collectPatientsInOrder(node.right, patientList);
=======
        System.out.println("_______________________________________________________________________");
        System.out.println("|                         PATIENTS INFORMATION                        |");
        System.out.println("|                            (BST POSTORDER)                          |");
        System.out.println("======================================================================|");
        System.out.printf("| %-5s | %-12s | %-3s | %-15s | %-20s |\n", "ID", "Name", "Age", "Phone Number", "Address");
        System.out.println("|---------------------------------------------------------------------|");

        postOrder(root, new int[]{1});

        System.out.println("=======================================================================");
    }

    private void postOrder(AVLNode root, int[] counter) {
        if (root != null) {
            postOrder(root.left, counter);
            postOrder(root.right, counter);

            Patient p = root.data;
            System.out.printf("| %-5s | %-12s | %-3d | %-15s | %-20s |\n",
                    p.getId(), p.getName(), p.getAge(), p.getPhoneNumber(), p.getAddress());
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
        }
    }

    public void load(String filename) {
<<<<<<< HEAD
        BST tempTree = new BST();
=======
        root = null; // Kosongkan tree lama
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            Gson gson = new Gson();
            Patient[] patients = gson.fromJson(reader, Patient[].class);
            if (patients != null) {
                for (Patient p : patients) {
<<<<<<< HEAD
                    if (p != null && isValidPatientId(p.getId())) {
                        tempTree.insertPatient(p);
                    } else {
                        System.out.println(Constants.RED + "Skipping invalid patient data: " + (p == null ? "null" : p.getId()) + Constants.RESET);
                    }
                }
                root = tempTree.root; 
                System.out.println("Patient data loaded successfully from " + filename);
            } else {
                System.out.println("No patient data found in " + filename);
            }
        } catch (IOException e) {
            System.out.println("Error reading from JSON: " + e.getMessage());
            e.printStackTrace();
=======
                    insertPatient(p); // Masukkan ke BST
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from JSON: " + e.getMessage());
>>>>>>> ad4db4028402c9539efbe24912e83d678c3af727
        }
    }
}