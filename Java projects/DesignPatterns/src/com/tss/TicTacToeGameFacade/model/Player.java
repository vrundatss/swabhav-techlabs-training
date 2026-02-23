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
        System.out.print(name + " enter row and column: ");
        return new int[]{scanner.nextInt(), scanner.nextInt()};
    }
}
