package com.tss.TicTacToeGameFacade.model;

import java.util.Scanner;

public class Player implements IPlayer{

    private String name;
    private char symbol;

    Scanner scanner = new Scanner(System.in);

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public int[] makeMove() {
        while (true) {
            System.out.print(name + " (" + symbol + ") enter row and column (e.g. 0 1): ");
            String line = scanner.nextLine().trim();
            String[] parts = line.split("\\s+");
            if (parts.length >= 2) {
                try {
                    int r = Integer.parseInt(parts[0]);
                    int c = Integer.parseInt(parts[1]);
                    return new int[]{r, c};
                } catch (NumberFormatException e) {
                    System.out.println("Invalid numbers...Please enter two integers separated by space.");
                }
            } else {
                System.out.println("Please enter row and column separated by a space.");
            }
        }
    }

//    @Override
//    public int[] makeMove() {
//        System.out.print(name + " enter row and column: ");
//        return new int[]{scanner.nextInt(), scanner.nextInt()};
//    }
}
