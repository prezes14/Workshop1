package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final String FILE_NAME = "tasks.csv";
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};
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
        System.out.println(ConsoleColors.RESET + "add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
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
        }        return tab;
    }

    public static String[][] Dane(String File) {

        Path path1 = Paths.get("/home/prezes14/Documents/Workshop1/Workshop1/tasks.csv");
        System.out.println(Files.exists(path1));
        try (Scanner scanner = new Scanner(System.in)) {
            String task_name = scanner.nextLine();
            if (task_name.equals("add"))
                System.out.println("Please add task description");

            String tab[][] = tasks;

            for (int i = 0; i < tab.length; i++) {
                String task_name2 = scanner.nextLine();
                Files.writeString(path1, task_name2, StandardOpenOption.APPEND);
                for (int j = 0; j < tab[i].length; j++) {
                    System.out.println("Insert date:");
                    String date_name = scanner.nextLine();
                    Files.writeString(path1, date_name, StandardOpenOption.APPEND);
                    System.out.println("Insert importance: (true/false)");
                    String Importance = scanner.nextLine();
                    Files.writeString(path1, Importance, StandardOpenOption.APPEND);
                }
            }
            String task2 = scanner.nextLine();
            Files.writeString(path1, task2, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            System.out.println("Brak mozliwosci zapisu do pliku");
        }
        return tasks;
//        if
//        System.out.println("Add task due date");
    }}
