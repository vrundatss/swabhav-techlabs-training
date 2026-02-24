package com.tss.TicTacToeTest.GameLogicTest;

import com.tss.TicTacToe.TicTacToeGameFacade.model.Board;
import com.tss.TicTacToe.TicTacToeGameFacade.model.GameLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {
    private GameLogic gameLogic;
    private Board board;

    @BeforeEach
    void setUp(){
        gameLogic = new GameLogic();
        board = new Board();
    }

    @Test
    void testCheckRowWin(){
        board.markPlace(0 ,0 , 'X');
        board.markPlace(0 ,1 , 'X');
        board.markPlace(0 ,2 , 'X');

        assertTrue(gameLogic.checkWin(board , 'X') ,"Should return true when player wins in a row" );
    }

    @Test
    void testCheckColumnWin(){
        board.markPlace(0 ,0 , 'X');
        board.markPlace(1 ,0 , 'X');
        board.markPlace(2 ,0 , 'X');

        assertTrue(gameLogic.checkWin(board , 'X') ,"Should return true when player wins in a column" );
    }

    @Test
    void testCheckWinForLeftToRightDiagonal(){
        board.markPlace(0 ,0 , 'X');
        board.markPlace(1 ,1 , 'X');
        board.markPlace(2 ,2 , 'X');

        assertTrue(gameLogic.checkWin(board , 'X') ,"Should return true when player wins in a left to right diagonal" );
    }
    @Test
    void testCheckWinForRightToLeftDiagonal(){
        board.markPlace(0 ,2 , 'X');
        board.markPlace(1 ,1 , 'X');
        board.markPlace(2 ,0 , 'X');

        assertTrue(gameLogic.checkWin(board , 'X') ,"Should return true when player wins in a right to left diagonal" );
    }

    @Test
    void testCheckWinWhenPlayerIsNotWin(){
        board.markPlace(0 ,0 , 'X');
        board.markPlace(1 ,1 , 'O');
        board.markPlace(2 ,2 , 'X');

        assertFalse(gameLogic.checkWin(board , 'X') ,"Should return false when player is not win" );
    }

    @Test
    void testIsValidMoveOnEmptyCell() {
        assertTrue(gameLogic.isValidMove(board, 1, 1),
                "Empty cell should be a valid move");
    }

    @Test
    void testIsValidMoveOnOccupiedCell() {
        board.markPlace(1, 1, 'O');

        assertFalse(gameLogic.isValidMove(board, 1, 1),
                "Occupied cell should not be a valid move");
    }

    @Test
    void testIsValidMoveForOutOfBounds() {
        assertFalse(gameLogic.isValidMove(board, -1, 0),
                "Negative index should be invalid");
        assertFalse(gameLogic.isValidMove(board, 3, 0),
                "Row Index greater than 3 should be invalid");
        assertFalse(gameLogic.isValidMove(board, 0, 3),
                "Column Index grater than 3 should be invalid");
    }

    @Test
    void testIsDrawMethodWhenBoardIsFullWithNoWinner(){
        char[][] moves =
                {       {'O' , 'X', 'O' },
                        {'X' , 'O', 'X' },
                        {'X' , 'O', 'X' }};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.markPlace(i, j, moves[i][j]);
            }
        }
        assertTrue(board.isBoardFull() , "isDraw() should return true when board is fully filled with No winning player");
    }

    @Test
    void testIsDrawWhenBoardIsNotFull() {
        board.markPlace(0, 0, 'X');
        board.markPlace(0, 1, 'O');

        assertFalse(gameLogic.isDraw(board),
                "Board with empty cells should not be a draw");
    }

    @Test
    void testIsDrawWithWinner(){

        char[][] moves =
                {       {'X' , 'X', 'O' },
                        {'X' , 'O', 'X' },
                        {'X' , 'O', 'X' }};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.markPlace(i, j, moves[i][j]);
            }
        }

        assertFalse(gameLogic.isDraw(board), "isDraw() should return false with winner");
    }


}
