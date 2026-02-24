package com.tss.TicTacToe.TicTacToeGameFacade.model;

import java.util.Scanner;

public class Player implements IPlayer{

    private String name;
    private char symbol;

    private final Scanner scanner;
//    Scanner scanner = new Scanner(System.in);

    public Player(String name, char symbol , Scanner scanner) {
        this.name = name;
        this.symbol = symbol;
        this.scanner = scanner;
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
        System.out.print(name + " enter row and column: ");
        return new int[]{scanner.nextInt(), scanner.nextInt()};
    }
}
