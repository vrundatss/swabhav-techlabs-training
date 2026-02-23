package com.tss.TicTacToeGameFacade.model;

public interface IGameLogic {
    boolean checkWin(IBoard board , char symbol);
    boolean isValidMove(IBoard board , int row , int col);
    boolean isDraw(IBoard board);
}
