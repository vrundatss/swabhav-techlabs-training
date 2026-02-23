package com.tss.TicTacToeGameFacade.model;

public class Cell {
    private final int row;
    private final int col;
    private char symbol;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.symbol = '-'; // default empty
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public boolean isEmpty() {
        return symbol == '-';
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void clear() {
        this.symbol = '-';
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
