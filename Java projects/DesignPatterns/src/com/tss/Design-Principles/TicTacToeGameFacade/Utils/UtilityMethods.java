package com.tss.TicTacToeGameFacade.Utils;

import java.util.Scanner;

public class UtilityMethods {
    Scanner scanner = new Scanner(System.in);

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

    public static char getUniqueSymbol(Scanner scanner, String playerName, char otherSymbol) {
        while (true) {
            System.out.print("Enter symbol for " + playerName + " (X/O): ");
            String input = scanner.next().trim().toUpperCase();
            if (input.length() == 1 && (input.charAt(0) == 'X' || input.charAt(0) == 'O')) {
                char symbol = input.charAt(0);
                if (symbol != otherSymbol) {
                    scanner.nextLine();
                    return symbol;
                } else {
                    System.out.println("Both players cannot have the same symbol...\nChoose symbol again.");
                }
            } else {
                System.out.println("Invalid symbol...Please enter X or O.");
            }
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
