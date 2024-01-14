package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static final String FILE_NAME = "tasks.csv";
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};
    public static void Menu (String[] MENU2) {
        for (int i = 0; i < OPTIONS.length; i++) {
            System.out.println(ConsoleColors.RESET + OPTIONS[i]);
        }
    }

    static String[][] tasks;

    public static void main(String[] args) {

//        public static void printOptions(String[] tab) {
//
//            System.out.println(ConsoleColors.BLUE);
//
//            System.out.println("Please select an option: " + ConsoleColors.RESET);
//
//            for (String option : tab) {
//
//                System.out.println(option);
//
//            }
//
//        }
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        Menu((OPTIONS));
        tasks = loadData(FILE_NAME);
        tasks = Dane(FILE_NAME);
    }

    public static String[][] loadData(String wczytanie) {
        Path dir = Paths.get(FILE_NAME);
        if (!Files.exists(dir)) {
            System.out.println("File not exist.");
            System.exit(0);
        }
        String[][] tab = tasks;
        try {
            List<String> strings = Files.readAllLines(dir);
            tab = new String[strings.size()][strings.get(0).split(",").length];

            for (int i = 0; i < strings.size(); i++) {
                String[] split = strings.get(i).split(", ");
                for (int j = 0; j < split.length; j++) {
                    tab[i][j] = split[j];
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Program error");
        }
        return tab;
    }

    public static String[][] Dane(String File) {

        Path path1 = Paths.get("/home/prezes14/Documents/Workshop1/Workshop1/tasks.csv");


        try (Scanner scanner = new Scanner(System.in)) {
//            while (scanner.hasNextLine()) {
//                String input = scanner.nextLine();
//                switch (input) {
//                    case "exit":
//                        break;
//                    case "add":
//                        break;
//                    case "remove":
//                        break;
//                    case "list":
//                        break;
//                    default:
//                        System.out.println("Insert correct task");
//                }
//           }
            String input_name = scanner.nextLine();

            String tab[][] = tasks;
            if (input_name.equals("add")) {
                for (int i = 0; i < tab.length; i++) {
                    System.out.println("Please add task description");
                    String task_name = "\n" + scanner.nextLine() + ", ";
                    Files.writeString(path1, task_name, StandardOpenOption.APPEND);
                    for (int j = 0; j < tab[i].length; j++) {
                        System.out.println("Insert date:");
                        String date_name = scanner.nextLine() + ", ";
                        Files.writeString(path1, date_name, StandardOpenOption.APPEND);
                        System.out.println("Insert importance: (true/false)");
                        String Importance = scanner.nextLine();
                        Files.writeString(path1, Importance, StandardOpenOption.APPEND);
                        Menu((OPTIONS));
                    }
                }
            } else if (input_name.equals("list")) {

                for (int i =0; i < tasks.length; i++) {
                    System.out.println();
                    for (int j = 0; j < tasks[i].length; j++) {
                        System.out.print(Arrays.asList(tasks[i][j]));

                    }
                }

            } else if (input_name.equals("remove")) {

            } else if (input_name.equals("exit")) {
                System.out.println("Bye, bye babe");
        } else System.out.println("Insert correct task");
            String task2 = scanner.nextLine();
            Files.writeString(path1, task2, StandardOpenOption.APPEND);
            Menu((OPTIONS));
        } catch (IOException ex) {
            System.out.println("Cannot save the file");
        }
        return tasks;

    }
}
