package com.tss.TicTacToeGameFacade.model;

public class GameLogicKInARow implements IGameLogic{
    @Override
    public boolean checkWin(IBoard board, char symbol, int winCondition) {
        Cell[][] g = board.getCells();
        int size = board.getSize();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (g[i][j].getSymbol() == symbol) {
                    if (checkDirection(g, i, j, 0, 1, symbol, winCondition) || // horizontal
                            checkDirection(g, i, j, 1, 0, symbol, winCondition) || // vertical
                            checkDirection(g, i, j, 1, 1, symbol, winCondition) || // diag
                            checkDirection(g, i, j, 1, -1, symbol, winCondition))  // anti-diag
                        return true;
                }
            }
        }
        return false;
    }

    private boolean checkDirection(Cell[][] g, int row, int col, int dr, int dc, char s, int winCond) {
        int size = g.length;
        for (int k = 0; k < winCond; k++) {
            int r = row + k * dr;
            int c = col + k * dc;
            if (r < 0 || r >= size || c < 0 || c >= size) return false;
            if (g[r][c].getSymbol() != s) return false;
        }
        return true;
    }

    @Override
    public boolean isValidMove(IBoard board, int row, int col) {
        int size = board.getSize();
        if (row < 0 || row >= size || col < 0 || col >= size) return false;
        return board.getCells()[row][col].isEmpty();
    }

    @Override
    public boolean isDraw(IBoard board) {
        return board.isDraw();
    }
}
