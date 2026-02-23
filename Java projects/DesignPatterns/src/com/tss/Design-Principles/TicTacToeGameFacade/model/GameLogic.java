package com.tss.TicTacToeGameFacade.model;

public class GameLogic implements IGameLogic{

    @Override
    public boolean checkWin(IBoard board, char symbol) {
        char[][] grid = board.getGrid();

        for (int i = 0; i < 3; i++) {
            if ((grid[i][0] == symbol && grid[i][1] == symbol && grid[i][2] == symbol) ||
                    (grid[0][i] == symbol && grid[1][i] == symbol && grid[2][i] == symbol)) {
                return true;
            }
        }

        if ((grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol) ||
                (grid[0][2] == symbol && grid[1][1] == symbol && grid[2][0] == symbol)) {
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
        char[][] grid = board.getGrid();

        return (row >= 0 && row < 3 && col >= 0 && col < 3 && grid[row][col] == '-');
    }

    @Override
    public boolean isDraw(IBoard board) {
        return board.isDraw();
    }
}
