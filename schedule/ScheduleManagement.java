package schedule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import utility.ConsoleUtil;
import utility.Constants;

public class ScheduleManagement {
    public void menu(Scanner input) {
        int choice;

        do {
            ConsoleUtil.clearScreen();
            System.out.println("================================================");
            System.out.println("-------------- Practice Schedule  --------------");
            System.out.println("================================================");
            System.out.println("| 1 | Search Practice Schedule By Name Doctor  |");
            System.out.println("| 2 | Search Practice Schedule By Speciality   |");
            System.out.println("| 3 | Display All Practice Schedule            |");
            System.out.println("| 0 | Exit From Appointment Management         |");
            System.out.println("------------------------------------------------");
            System.out.print("Choose Appointment Management Menu : ");
            choice = input.nextInt();
            input.nextLine();

            ConsoleUtil.clearScreen();
            switch (choice) {
                case 0 -> {return;}
                case 1 -> {
                    searchScheduleByName(input);
                    ConsoleUtil.waitForEnter(input);
                    break;
                }
                case 2 -> {
                    searchScheduleBySpeciality(input);
                    ConsoleUtil.waitForEnter(input);
                    break;
                }
                case 3 -> {
                    displayAllSchedule(input);
                    ConsoleUtil.waitForEnter(input);
                    break;
                }
                default -> System.out.println(Constants.RED+"Invalid option. Try again"+Constants.RESET);
            }
        } while (choice != 0);
    }

    public void searchScheduleByName(Scanner input) {
        System.out.print("Name Doctor : ");
        String name = input.nextLine();

        ConsoleUtil.clearScreen();
        try (BufferedReader scheduleReader = new BufferedReader(new FileReader("data/schedule.txt"))) {
            String scheduleLine;
            boolean found = false;

            while ((scheduleLine = scheduleReader.readLine()) != null) {
                String[] scheduleParts = scheduleLine.split("\\|");
                if (scheduleParts.length >= 7) {
                    String scheduleId = scheduleParts[1];

                    if (scheduleId.equalsIgnoreCase(name)) {
                        System.out.println("\n================ Practice Schedule ================");
                        System.out.println("Doctor ID    : " + scheduleParts[0]);
                        System.out.println("Name         : " + scheduleParts[1]);
                        System.out.println("Speciality   : " + scheduleParts[2]);
                        System.out.println("Day          : " + scheduleParts[3]);
                        System.out.println("Shift        : " + scheduleParts[4]);
                        System.out.println("Start Time   : " + scheduleParts[5]);
                        System.out.println("End Time     : " + scheduleParts[6]);
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println(Constants.RED+"There is no schedule " + name+Constants.RESET);
            }

        } catch (IOException e) {
            System.out.println(Constants.RED+"Failed to open schedule.txt : " + e.getMessage()+Constants.RESET);
        }
    }

    public void searchScheduleBySpeciality(Scanner input) {
        System.out.print("Enter speciality : ");
        String speciality = input.nextLine();

        ConsoleUtil.clearScreen();
        try (BufferedReader scheduleReader = new BufferedReader(new FileReader("data/schedule.txt"))) {
            String scheduleLine;
            boolean found = false;

            while ((scheduleLine = scheduleReader.readLine()) != null) {
                String[] scheduleParts = scheduleLine.split("\\|");
                if (scheduleParts.length >= 7) {
                    String scheduleSpeciality = scheduleParts[2];

                    if (scheduleSpeciality.equalsIgnoreCase(speciality)) {
                        System.out.println("\n================ Practice Schedule ================");
                        System.out.println("Doctor ID    : " + scheduleParts[0]);
                        System.out.println("Name         : " + scheduleParts[1]);
                        System.out.println("Speciality   : " + scheduleParts[2]);
                        System.out.println("Day          : " + scheduleParts[3]);
                        System.out.println("Shift        : " + scheduleParts[4]);
                        System.out.println("Start Time   : " + scheduleParts[5]);
                        System.out.println("End Time     : " + scheduleParts[6]);
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println(Constants.RED+"There is no schedule " + speciality+Constants.RESET);
            }

        } catch (IOException e) {
            System.out.println(Constants.RED+"Failed to open schedule.txt : " + e.getMessage()+Constants.RESET);
        }
    }

    public void displayAllSchedule(Scanner input) {
        System.out.print("Enter Day : ");
        String day = input.nextLine();

        ConsoleUtil.clearScreen();
        System.out.println("_____________________________________________________________________________________________________________");
        System.out.println("|                                           PRACTICAL SCHEDULE INFORMATION                                   |");
        System.out.println("|============================================================================================================|");
        System.out.printf("| %-10s | %-20s | %-15s | %-10s | %-12s | %-11s | %-9s  |\n",
                "Doctor ID", "Name","Specialist", "Day","Shift", "Start Time", "End Time");
        System.out.println("=============================================================================================================|");

        try (BufferedReader reader = new BufferedReader(new FileReader("data/schedule.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 7) {
                    String scheduleDay = parts[3];

                    if (scheduleDay.equalsIgnoreCase(day)) {
                        System.out.printf("| %-10s | %-20s | %-15s | %-10s | %-12s | %-12s | %-9s |\n",
                        parts[0], parts[1], parts[2], parts[3], parts[4],
                        parts[5], parts[6]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(Constants.RED + "Failed to read schedule.txt: " + e.getMessage() + Constants.RESET);
        }
        System.out.println("==============================================================================================================");
    }
}
