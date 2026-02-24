package com.tss.TicTacToeGameFacade.model;

public interface IBoard {
    void display();
    boolean isDraw();
    boolean  markPlace(int row , int column , char symbol);
//    char[][] getGrid();
    void reset();
    Cell[][] getCells();
    int getSize();
}
