package com.tss.TicTacToe.TicTacToeGameFacade.model;

public interface IPlayer {
    String getName();
    char getSymbol();
    int[] makeMove();
}
