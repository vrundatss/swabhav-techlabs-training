package com.tss.MiniProject.FoodOrderingSystem.util;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ValidationUtil {

    private static final String NAME_REGEX = "^[a-zA-Z\\s]{2,29}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String PHONE_REGEX = "^[6-9]\\d{9}$";
    private static final String ID_REGEX = "^[A-Z0-9-]{3,15}$";
    private static final String STRONG_PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{6,20}$";
    private static final String USERNAME_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d._]{6,20}$";

    public static String getValidString(Scanner sc, String fieldName) {
        while (true) {
            String input = sc.nextLine().trim();
            if (Pattern.matches(NAME_REGEX, input)) {
                return input;
            }
            System.out.println("Invalid... " + fieldName + " must be alphabetic (2-30 characters).");
            System.out.print("Enter " + fieldName + " again: ");
        }
    }

    public static String getValidId(Scanner sc) {
        while (true) {
            String input = sc.nextLine().trim().toUpperCase();
            if (Pattern.matches(ID_REGEX, input)) {
                return input;
            }
            System.out.println("Invalid... ID must be 3-15 alphanumeric characters (e.g., AGNT-001).");
            System.out.print("Enter ID again: ");
        }
    }

    public static String getValidPassword(Scanner sc) {
        while (true) {
            String input = sc.nextLine().trim();
            if (Pattern.matches(STRONG_PASSWORD_REGEX, input)) {
                return input;
            }
            System.out.println("Weak Password!");
            System.out.println("Rules: 6–20 chars, include uppercase, lowercase, number, and one special character (@#$%^&+=!).");
            System.out.print("Try again: ");
        }
    }

//    public static String getValidPassword(Scanner sc) {
//        while (true) {
//            String input = sc.nextLine().trim();
//
//            if (Pattern.matches(PASSWORD_REGEX, input)) {
//                return input;
//            }
//
//            System.out.println("[INVALID] Password must be at least 6 characters long, " +
//                    "include one uppercase letter, one lowercase letter, and one number.");
//            System.out.print("Enter a stronger password: ");
//        }
//    }

    public static String getValidName(Scanner sc) {
        while (true) {
            String input = sc.nextLine().trim();
            if (Pattern.matches(NAME_REGEX, input)) {
                return input;
            }
            System.out.println("Invalid... Name must be 2-30 characters (alphabets only).");
            System.out.print("Try again: ");
        }
    }

    public static String getValidUsername(Scanner sc) {
        while (true) {
            String input = sc.nextLine().trim();
            if (Pattern.matches(USERNAME_REGEX, input)) {
                return input;
            }
            System.out.println("Rules: Must be 6–20 characters, contain both letters and digits, and may include '.' or '_'.");
            System.out.print("Try again: ");
        }
    }

    public static String getValidPhone(Scanner sc) {
        while (true) {
            String input = sc.nextLine().trim();
            if (Pattern.matches(PHONE_REGEX, input)) {
                return input;
            }
            System.out.println("Invalid... Must be a 10-digit number starting with 6-9.");
            System.out.print("Try again: ");
        }
    }

    public static String getValidEmail(Scanner sc) {
        while (true) {
            String input = sc.nextLine().trim();
            if (Pattern.matches(EMAIL_REGEX, input)) {
                return input.toLowerCase();
            }
            System.out.println("Invalid... Please enter a valid email (e.g., name@domain.com).");
            System.out.print("Try again: ");
        }
    }

    public static double getValidDouble(Scanner sc, String fieldName) {
        while (true) {
            try {
                double value = Double.parseDouble(sc.nextLine().trim());
                if (value > 0) return value;
                System.out.println("Invalid... " + fieldName + " must be greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid... Enter a valid decimal number.");
            }
            System.out.print("Try again: ");
        }
    }

    public static int getValidInt(Scanner sc, String fieldName, int min) {
        while (true) {
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                if (value >= min) return value;
                System.out.println("Invalid... " + fieldName + " cannot be less than " + min);
            } catch (NumberFormatException e) {
                System.out.println("Invalid... Enter a valid number.");
            }
            System.out.print("Try again: ");
        }
    }

    public static int getValidQty(Scanner sc, String fieldName, int min , int max) {
        while (true) {
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                if(value >= max)
                {
                    System.out.println("Quantity must be less than " + max);
                }
                else if (value <= min)  {
                    System.out.println("Invalid... " + fieldName + " cannot be less than " + min);
                }
                else {
                    return value;
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid... Enter a valid number.");
            }
            System.out.print("Try again: ");
        }
    }

    public static boolean getValidBoolean(Scanner sc) {
        while (true) {
            String input = sc.nextLine().trim().toLowerCase();

            if (input.equals("true") || input.equals("y") || input.equals("yes")) {
                return true;
            }
            if (input.equals("false") || input.equals("n") || input.equals("no")) {
                return false;
            }

            System.out.println("Invalid... Please enter 'true/false' or 'y/n'.");
            System.out.print("Try again: ");
        }
    }

    public static String readHiddenInput(Scanner sc, String prompt) {
        var console = System.console();
        if (console != null) {
            char[] input = console.readPassword(prompt);
            return new String(input);
        } else {
            // Fallback for IDE terminals that don't support Console.readPassword()
            System.out.print(prompt + " (Unmasked): ");
            return sc.next();
        }
    }

    public static boolean getValidUPI(Scanner sc) {
        System.out.print("Enter UPI ID (user@bank): ");
        String upi = sc.next();
        // Simple regex: must contain @ and a provider
        if (!upi.matches("^[a-zA-Z0-9.\\-_]{2,25}@[a-zA-Z]{2,15}$")) {
            System.out.println("\u001B[31mInvalid UPI Format! (Example: vrunda@okaxis)\u001B[0m");
            return false;
        }
        return true;
    }
}