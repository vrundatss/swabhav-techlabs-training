package com.tss.TicTacToe.TicTacToeGameFacade.exception;

public class CellAlreadyMarkedException extends RuntimeException{
    public CellAlreadyMarkedException(String message) {
        super(message);
    }
}
