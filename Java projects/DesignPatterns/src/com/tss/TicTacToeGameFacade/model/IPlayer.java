package com.tss.TicTacToeGameFacade.model;

public interface IPlayer {
    String getName();
    char getSymbol();
    int[] makeMove();
}
