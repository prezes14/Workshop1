package org.example;

import java.util.Scanner;

public class Main {
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

        System.out.println(ConsoleColors.BLUE+"Please select an option:");
        System.out.println(ConsoleColors.RESET+"add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");

    }
    static final String FILE_NAME = "tasks.csv";
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};
    static String[][] tasks;

}