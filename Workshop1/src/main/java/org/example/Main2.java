package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.example.ConsoleColors;
import org.xml.sax.helpers.ParserAdapter;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.*;
import java.nio.channels.ScatteringByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Main2 {
    static final String FILE_NAME = "/home/bartosz/Dokumenty/kurs/Workshop1/Project1/tasks.csv";
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};
    static String[][] tasks;

    public static void main(String[] args) {
        System.out.println();
        System.out.println(ConsoleColors.BLUE + "Please select an option:");

        for (int i = 0; i < OPTIONS.length; i++) {
            System.out.println(ConsoleColors.RESET + OPTIONS[i]);
        }
        tasks = pobieranieDanych(FILE_NAME);
        tasks = wybórCzynności(FILE_NAME);

    }

    public static String[][] pobieranieDanych(String FileName) {

        Path dir = Paths.get(FILE_NAME);
        if (!Files.exists(dir)) {
            System.out.println("File not exist.");
            System.exit(0);
        }
        String tab[][] = null;
        try {
            List<String> strings = Files.readAllLines(dir);
            tab = new String[strings.size()][strings.get(0).split(",").length];

            for (int i = 0; i < strings.size(); i++) {
                String[] split = strings.get(i).split(",");
                for (int j = 0; j < split.length; j++) {
                    tab[i][j] = split[j];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("A może to?");
        } catch (IndexOutOfBoundsException EX) {
            System.out.println("");
        }

        return tab;
    }

    public static boolean czyNumerWiększyRównyZero(String input) {

        if (NumberUtils.isParsable(input)) {
            return Integer.parseInt(input) >= 0;

        }
        return false;
    }

    public static int wpiszNumer() {
        Path path1 = Paths.get("/home/prezes14/Dokumenty/Workshop1/Workshop1/tasks.csv");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz numer do usunięcia");

        String n = scanner.nextLine();
        while (!czyNumerWiększyRównyZero(n)) {
            System.out.println("Niepoprawna dana. Wpisz numer większy lub równy 0");
            n = scanner.nextLine();

        }
        return Integer.parseInt(n);

    }

    private static void usuwanieWiersza(String[][] tab, int index) {

        try {
            if (index < tab.length) {
                tasks = ArrayUtils.remove(tab, index);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Element not exist in tab");
        }

    }

    public static String[][] wybórCzynności(String FileName2) {

        Path path1 = Paths.get("/home/bartosz/Dokumenty/kurs/Workshop1/Project1/tasks.csv");
        Scanner scanner = new Scanner(System.in);


        String dodajZmienna = scanner.nextLine();

        try {
            String tab[][] = tasks;
            if (dodajZmienna.equals("add")) {

                for (int i = 0; i < tab.length; i++) {
                    System.out.println("Please add task description");
                    String dodanieZdania = "\n" + scanner.nextLine() + ", ";
                    Files.writeString(path1, dodanieZdania, StandardOpenOption.APPEND);
                    for (int j = 0; j < tab[i].length; j++) ;
                    System.out.println("Podaj datę");
                    String dodanieDaty = scanner.nextLine() + ", ";
                    Files.writeString(path1, dodanieDaty, StandardOpenOption.APPEND);
                    System.out.println("Podaj ważność true/false");
                    String isItImportant = scanner.nextLine();
                    Files.writeString(path1, isItImportant, StandardOpenOption.APPEND);

                    main(OPTIONS);

                }
            } else if (dodajZmienna.equals("exit")) {

                zapiszDoPliku(FILE_NAME, tasks);

                System.out.println(ConsoleColors.RED + "Bye, bye.");

                System.exit(0);


            } else if (dodajZmienna.equals("remove")) {
                usuwanieWiersza(tasks, wpiszNumer());
                System.out.println("Wartość została usunięta");
                zapiszDoPliku(FILE_NAME, tasks);
                main(OPTIONS);

            } else if (dodajZmienna.equals("list")) {

                for (int i = 0; i < tasks.length; i++) {
                    System.out.println();
                    for (int j = 0; j < tasks[i].length; j++) {
                        System.out.print(Arrays.asList(tasks[i][j]));
                    }
                }
                main(OPTIONS);
            } else {
                System.out.println("Please select correct option");
                main(OPTIONS);

            }
        } catch (
                IOException exeption) {
            System.out.println("wpisuj jak nalezy");

        }


        return tasks;
    }

    public static void zapiszDoPliku(String fileName, String[][] tab) {
        Path path1 = Paths.get("/home/bartosz/Dokumenty/kurs/Workshop1/Project1/tasks.csv");
        String[] lines = new String[tasks.length];
        for (int i = 0; i < tab.length; i++) {

            lines[i] = String.join(",", tab[i]);
        }

        try {
            Files.write(path1, Arrays.asList(lines));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}