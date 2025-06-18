package utility;

import java.io.*; 
import java.util.Scanner; 

public class FileHandler {
    public static void readFile(String nameFile) {
        try {
            File obj = new File(nameFile);
            Scanner reader = new Scanner(obj);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(Constants.RED + "An error has occurred" + Constants.RESET);
            // e.printStackTrace();
        }
    }

    public static void createFile(String filename){
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();  
            }
        } catch (IOException e) {
            System.out.println(Constants.RED + "Error creating file: " + e.getMessage() + Constants.RESET);
        }
    }

    public static void writeFile(String buffer, String word){
        try {
            FileWriter Writer = new FileWriter(buffer,true);
            Writer.write(word + "\n");
            Writer.close();
        }
        catch (IOException e) {
            System.out.println(Constants.RED + "An error has occurred" + Constants.RESET);
            e.printStackTrace();
        }
    }
}