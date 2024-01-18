package org.example;

import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void saveTabToFile(String FILE_NAME, String[][] tab) {

        Path dir = Paths.get(FILE_NAME);
        String[] lines = new String[tasks.length];
        for (int i = 0; i < tab.length; i++) {
            lines[i] = String.join(",", tab[i]);
        }
        try {
            Files.write(dir, Arrays.asList(lines));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    static final String FILE_NAME = "tasks.csv";
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};
    static String[][] tasks;
    public static void main(String[] args) {

        System.out.println();
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        for (int i = 0; i < OPTIONS.length; i++) {
            System.out.println(ConsoleColors.RESET + OPTIONS[i]);}
        tasks = loadData(FILE_NAME);
        tasks = Dane(FILE_NAME);

    }
//    public static String[][] Remove(String remove1) {
//
//        Path path1 = Paths.get("/home/prezes14/Documents/Workshop1/Workshop1/tasks.csv");
//        String[][] tab = tasks;
//
//        try (Scanner scanner = new Scanner(System.in)) {
//            if (scanner.equals("remove")) {
//                int index = 0;
//                for (int i = 0; i < tab.length; i++) {
//                    System.out.println("What task number you want to delete:");
//                    index = scanner.nextInt();
//                    for (int j = 0; j < tab[i].length; j++) {
//                        if (index < tab[i].length) {
//                            tasks = ArrayUtils.remove(tab, index);
//                            return tasks;
//                        }
//                    }
//                }
//            }} catch (ArrayIndexOutOfBoundsException exept) {
//            System.out.println("Element doesn't exist");
//        }
//    }

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

            String input_name = scanner.nextLine();
            String tab[][] = tasks;
            if (input_name.equals("add")) {
                for (int i = 0; i < tab.length; i++) {
                    System.out.println("Please add task description");
                    String task_name = "\n" + scanner.nextLine() + ", ";
                    Files.writeString(path1, task_name, StandardOpenOption.APPEND);
                    for (int j = 0; j < tab[i].length; j++);
                    System.out.println("Insert date:");
                    String date_name = scanner.nextLine() + ", ";
                    Files.writeString(path1, date_name, StandardOpenOption.APPEND);
                    System.out.println("Insert importance: (true/false)");
                    String Importance = scanner.nextLine();
                    Files.writeString(path1, Importance, StandardOpenOption.APPEND);
                    main(OPTIONS);
                }
        } else if (input_name.equals("list")) {
                for (int i =0; i < tasks.length; i++) {
                    System.out.println();
                    for (int j = 0; j < tasks[i].length; j++) {
                        System.out.print(Arrays.asList(tasks[i][j]));
                    }
                }
                main(OPTIONS);
            } else if (input_name.equals("remove")) {
                int index = 0;
                try {
                    System.out.println("What task number you want to delete:");
                    index = scanner.nextInt();
                    if (index <= 0) {
                        System.out.println("File doesn't have task "+index);
                    }
                    for (int i = 0; i < tab.length; i++) {
                        if (index < tab.length) {
                            tasks = ArrayUtils.remove(tab, index);
                            saveTabToFile(FILE_NAME, tasks);
                        }
                    }   main(OPTIONS);
                } catch (ArrayIndexOutOfBoundsException exe) {
                    System.out.println("Element doesn't exist");
                }
            } else if (input_name.equals("exit")) {
                saveTabToFile(FILE_NAME, tasks);
                System.out.println("Bye, bye");
                System.exit(0);
        } else { System.out.println("Insert correct task");
            String task2 = scanner.nextLine();
            Files.writeString(path1, task2, StandardOpenOption.APPEND);
            main((OPTIONS));
        }} catch (IOException ex) {
            System.out.println("Cannot save the file");
        }
        return tasks;
    }
}
