package com.tss.TicTacToeGameFacade.model;

public class BoardNxN implements IBoard{
    private final int size;
    private Cell[][] cells;

    public BoardNxN(int size) {
        if (size < 3) throw new IllegalArgumentException("Board size must be at least 3x3.");
        this.size = size;
        reset();
    }

    @Override
    public void display() {
        System.out.println("\n   Current Board State (" + size + "x" + size + ")");
        System.out.print("     ");
        for (int j = 0; j < size; j++) System.out.printf("%2d  ", j);
        System.out.println("\n   " + "-".repeat(Math.max(6, size * 4)));

        for (int i = 0; i < size; i++) {
            System.out.printf("%2d | ", i);
            for (int j = 0; j < size; j++) System.out.print(cells[i][j] + " | ");
            System.out.println();
            System.out.println("   " + "-".repeat(Math.max(6, size * 4)));
        }
        System.out.println();
    }

    @Override
    public boolean markPlace(int row, int col, char symbol) {
        if (isValidCell(row, col) && cells[row][col].isEmpty()) {
            cells[row][col].setSymbol(symbol);
            return true;
        }
        return false;
    }

    @Override
    public boolean isDraw() {
        for (Cell[] row : cells)
            for (Cell cell : row)
                if (cell.isEmpty()) return false;
        return true;
    }

    @Override
    public Cell[][] getCells() {
        return cells;
    }

    @Override
    public void reset() {
        cells = new Cell[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                cells[i][j] = new Cell(i, j);
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
