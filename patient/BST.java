package patient;

import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import utility.*;

// AVL Tree implementation for managing patient data
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
        if (patient == null || !isValidPatientId(patient.getId())) {
            System.out.println(Constants.RED + "Invalid patient ID: " + (patient == null ? "null" : patient.getId())
                    + Constants.RESET);
            return;
        }
        root = insert(root, patient);
    }

    public Patient searchByIdPatient(String id) {
        if (!isValidPatientId(id)) {
            System.out.println(Constants.RED + "Invalid ID format: " + id + Constants.RESET);
            return null;
        }
        AVLNode node = search(root, id);
        return node == null ? null : node.data;
    }

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
            System.out.println(Constants.RED + "Duplicate patient ID: " + patient.getId()
                    + ". Updating existing patient." + Constants.RESET);
            node.data = patient;
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        // Left Left
        if (balance > 1 && Integer.parseInt(patient.getId().substring(1)) < Integer
                .parseInt(node.left.data.getId().substring(1))) {
            return rightRotate(node);
        }
        // Right Right
        if (balance < -1 && Integer.parseInt(patient.getId().substring(1)) > Integer
                .parseInt(node.right.data.getId().substring(1))) {
            return leftRotate(node);
        }
        // Left Right
        if (balance > 1 && Integer.parseInt(patient.getId().substring(1)) > Integer
                .parseInt(node.left.data.getId().substring(1))) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // Right Left
        if (balance < -1 && Integer.parseInt(patient.getId().substring(1)) < Integer
                .parseInt(node.right.data.getId().substring(1))) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private AVLNode search(AVLNode node, String id) {
        if (node == null || node.data.getId().equals(id)) {
            return node;
        }
        int searchNum = Integer.parseInt(id.substring(1));
        int currentNum = Integer.parseInt(node.data.getId().substring(1));
        if (searchNum < currentNum) {
            return search(node.left, id);
        }
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

        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    public void removePatient(String id) {
        if (!isValidPatientId(id)) {
            System.out.println(Constants.RED + "Invalid patient ID: " + id + Constants.RESET);
            return;
        }
        root = delete(root, id);
    }

    private AVLNode delete(AVLNode node, String id) {
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
            } else {
                AVLNode temp = getMinValueNode(node.right);
                node.data = temp.data;
                node.right = delete(node.right, temp.data.getId());
            }
        }

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

    private void printTableHeader(String traversalType) {
        System.out.println("____________________________________________________________________________");
        System.out.println("|                         PATIENTS INFORMATION                              |");
        System.out.println("|                          (BST " + traversalType.toUpperCase()
                + ")                                    |");
        System.out.println("============================================================================|");
        System.out.printf("| %-3s | %-5s | %-12s | %-3s | %-15s | %-20s |\n", "No", "ID", "Name", "Age", "Phone Number",
                "Address");
        System.out.println("|---------------------------------------------------------------------------|");
    }

    public void inOrderDisplay() {
        if (root == null) {
            System.out.println(Constants.RED + "No patients in BST" + Constants.RESET);
            return;
        }
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
    }

    public void preOrderDisplay() {
        if (root == null) {
            System.out.println(Constants.RED + "No patients in BST" + Constants.RESET);
            return;
        }
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
    }

    public void postOrderDisplay() {
        if (root == null) {
            System.out.println(Constants.RED + "No patients in BST" + Constants.RESET);
            return;
        }
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
        }
    }

    public void load(String filename) {
        BST tempTree = new BST();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            Gson gson = new Gson();
            Patient[] patients = gson.fromJson(reader, Patient[].class);
            if (patients != null) {
                for (Patient p : patients) {
                    if (p != null && isValidPatientId(p.getId())) {
                        tempTree.insertPatient(p);
                    } else {
                        System.out.println(Constants.RED + "Skipping invalid patient data: "
                                + (p == null ? "null" : p.getId()) + Constants.RESET);
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
        }
    }
}