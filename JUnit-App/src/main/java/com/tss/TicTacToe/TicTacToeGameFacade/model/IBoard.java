package com.tss.TicTacToe.TicTacToeGameFacade.model;

public interface IBoard {
    void display();
    boolean isBoardFull();
    boolean  markPlace(int row , int column , char symbol);
    Cell[][] getGrid();
    void reset();
}
