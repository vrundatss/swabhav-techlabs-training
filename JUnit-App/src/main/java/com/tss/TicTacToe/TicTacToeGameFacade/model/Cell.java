package com.tss.TicTacToe.TicTacToeGameFacade.model;

public class Cell {
    private int row;
    private int column;
    private char symbol;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.symbol = '-';
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public boolean isEmpty() {
        return this.symbol == '-';
    }

    public void reset() {
        this.symbol = '-';
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
