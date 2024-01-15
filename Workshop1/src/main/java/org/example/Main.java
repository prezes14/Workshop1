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
                    if (index < tab.length) {
                        tasks = ArrayUtils.remove(tab, index);
                    for (int i = 0; i < tab.length; i++) {
                        System.out.println("What task number you want to delete:");
                        String DeleteTask = scanner.nextLine();
                        for (int j = 0; j < tab[i].length; j++) ;
                    }
                    }
                } catch (ArrayIndexOutOfBoundsException exe) {
                    System.out.println("Element doesn't exist");
                }
            } else if (input_name.equals("exit")) {
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
