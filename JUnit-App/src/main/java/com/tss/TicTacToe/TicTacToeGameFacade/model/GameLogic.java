package com.tss.TicTacToe.TicTacToeGameFacade.model;

public class GameLogic implements IGameLogic{

    @Override
    public boolean checkWin(IBoard board, char symbol) {
        Cell[][] grid = board.getGrid();

        for (int i = 0; i < 3; i++) {

            if (grid[i][0].getSymbol() == symbol &&
                    grid[i][1].getSymbol() == symbol &&
                    grid[i][2].getSymbol() == symbol) {
                return true;
            }

            if (grid[0][i].getSymbol() == symbol &&
                    grid[1][i].getSymbol() == symbol &&
                    grid[2][i].getSymbol() == symbol) {
                return true;
            }
        }

        if (grid[0][0].getSymbol() == symbol &&
                grid[1][1].getSymbol() == symbol &&
                grid[2][2].getSymbol() == symbol) {
            return true;
        }

        if (grid[0][2].getSymbol() == symbol &&
                grid[1][1].getSymbol() == symbol &&
                grid[2][0].getSymbol() == symbol) {
            return true;
        }
        return false;

//        boolean isWin = (grid[0][0]==symbol && grid[0][1]==symbol && grid[0][2] == symbol ) ||
//                        (grid[1][0]==symbol && grid[1][1]==symbol && grid[1][2] == symbol)  ||
//                        (grid[2][0]==symbol && grid[2][1]==symbol && grid[2][2] == symbol ) ||
//                        (grid[0][0]==symbol && grid[1][0]==symbol && grid[2][0] == symbol)  ||
//                        (grid[0][1]==symbol && grid[1][1]==symbol && grid[2][1] == symbol ) ||
//                        (grid[0][2]==symbol && grid[1][2]==symbol && grid[2][2] == symbol)  ||
//                        (grid[0][0]==symbol && grid[1][1]==symbol && grid[2][2] == symbol)  ||
//                        (grid[0][2]==symbol && grid[1][1]==symbol && grid[2][0] == symbol) ;
//
//        return isWin;
    }

    @Override
    public boolean isValidMove(IBoard board, int row, int col) {
        Cell[][] grid = board.getGrid();

        return (row >= 0 && row < 3 && col >= 0 && col < 3 && grid[row][col].isEmpty());
    }

    @Override
    public boolean isDraw(IBoard board) {
        if (!board.isBoardFull()) {
            return false;
        }

        if (checkWin(board, 'X') || checkWin(board, 'O')) {
            return false;
        }

        return board.isBoardFull();
    }
}
