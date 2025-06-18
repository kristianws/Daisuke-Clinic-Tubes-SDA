package utility;

import java.io.IOException; 
import java.util.Scanner;   

public class ConsoleUtil {
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println(Constants.RED+"Cannot clean the console"+Constants.RESET);
        }
    }

    public static void waitForEnter(Scanner sc) {
        System.out.print("\nPress Enter to continue...");
        sc.nextLine();
    }
}

