package com.tss.TicTacToeGameFacade.Utils;

import java.util.Scanner;
import java.util.Set;

public class UtilityMethods {
    Scanner scanner = new Scanner(System.in);

    public static int getPositiveInt(Scanner scanner, int min) {
        int num = -1;
        while (num < min) {
            if (!scanner.hasNextInt()) {
                System.out.print("Invalid input...Enter a number: ");
                scanner.next();
                continue;
            }
            num = scanner.nextInt();
            scanner.nextLine();
            if (num < min) {
                System.out.print("Number must be at least " + min + "...Try again: ");
            }
        }
        return num;
    }

    public static String getPlayerName(Scanner scanner, String order) {
        System.out.print("Enter name of " + order + " player: ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("Name cannot be empty...Enter name again: ");
            name = scanner.nextLine().trim();
        }
        return name;
    }

    public static char getPlayerSymbol(Scanner scanner, String playerName) {
        while (true) {
            System.out.print("Enter symbol for " + playerName + " (X/O): ");
            String input = scanner.next().trim().toUpperCase();
            if (input.length() == 1 && (input.charAt(0) == 'X' || input.charAt(0) == 'O')) {
                scanner.nextLine();
                return input.charAt(0);
            } else {
                System.out.println("Invalid symbol...Please enter X or O.");
            }
        }
    }

    public static char getUniqueSymbol(Scanner sc, String playerName, Set<Character> usedSymbols) {
        while (true) {
            System.out.print("Enter single-character symbol for " + playerName + " (not used yet): ");
            String s = sc.nextLine().trim().toUpperCase();
            if (s.length() != 1) {
                System.out.println("Please enter exactly one character.");
                continue;
            }
            char c = s.charAt(0);
            if (usedSymbols.contains(c)) {
                System.out.println("Symbol already taken. Choose another.");
                continue;
            }
            usedSymbols.add(c);
            return c;
        }
    }

    public static int getNumberOfRounds(Scanner scanner) {
        int rounds = 0;
        while (rounds <= 0) {
            System.out.print("\nHow many rounds do you want to play? : ");
            if (scanner.hasNextInt()) {
                rounds = scanner.nextInt();
                if (rounds <= 0) {
                    System.out.println("Please enter a number greater than 0.");
                }
            } else {
                System.out.println("Invalid input...Please enter a valid number.");
                scanner.next();
            }
        }
        scanner.nextLine();
        return  rounds;
    }
}
