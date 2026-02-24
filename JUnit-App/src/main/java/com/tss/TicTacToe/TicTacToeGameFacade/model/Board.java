package com.tss.TicTacToe.TicTacToeGameFacade.model;

import com.tss.TicTacToe.TicTacToeGameFacade.exception.CellAlreadyMarkedException;

public class Board implements IBoard{

    private Cell[][] grid = new Cell[3][3];

    public Board() {
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = new Cell(i, j);
            }
        }
    }

    @Override
    public void display() {
        System.out.println("\n   Current Board State");
        System.out.println("   --------------------");
        System.out.println("     0   1   2 ");
        System.out.println("   -------------");

        for (int i = 0; i < 3; i++) {
            System.out.print(" " + i + " | ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] + " | ");
            }
            System.out.println();
            System.out.println("   -------------");
        }
        System.out.println();
    }

    @Override
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean markPlace(int row, int column, char symbol) {
        if(grid[row][column].isEmpty())
        {
            grid[row][column].setSymbol(symbol);
            return true;
        }
        throw new CellAlreadyMarkedException(
                String.format("Cell at (%d,%d) is already marked with '%c'",
                        row, column, grid[row][column].getSymbol()));
    }

    @Override
    public Cell[][] getGrid() {
        return grid;
    }

    @Override
    public void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j].reset();
            }
        }
    }
}
