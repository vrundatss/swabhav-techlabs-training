package com.tss.TicTacToeTest.BoardTest;

import com.tss.TicTacToe.TicTacToeGameFacade.exception.CellAlreadyMarkedException;
import com.tss.TicTacToe.TicTacToeGameFacade.model.Board;
import com.tss.TicTacToe.TicTacToeGameFacade.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTestClass {
    private Board board;

    @BeforeEach
    void setUp(){
        board = new Board();
    }

    @Test
    void testInitializeBoardGridNotNull() {
        assertNotNull(board.getGrid(), "Grid should be initialized...not null");
    }

    @Test
    void testInitializeBoardAllCellsShouldEmptyInitially(){
        Cell[][] grid = board.getGrid();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals('-' , grid[i][j].getSymbol() , "All cells should be empty initially");
            }
        }
    }

    @Test
    void testBoardFullWhenBoardIsEmpty(){
        assertFalse(board.isBoardFull() , "isBoardFull() should return false when board is empty");
    }

    @Test
    void testIsBoardFullMethodWhenBoardIsPartiallyFilled(){
        board.markPlace(0 , 0 , 'X');
        board.markPlace(1 , 1 , '0');
        assertFalse(board.isBoardFull() , "isBoardFull() should return false when board is partially filled");
    }

    @Test
    void testIsBoardFullMethodWhenBoardIsFullyFilled(){
        char[][] moves =
                {       {'O' , 'X', 'O' },
                        {'X' , 'O', 'X' },
                        {'O' , 'O', 'X' }};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.markPlace(i, j, moves[i][j]);
            }
        }
        assertTrue(board.isBoardFull() , "isBoardFull() should return true when board is fully filled");
    }

    @Test
    void testIsBoardFullMethodAfterReset(){

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.markPlace(i, j, 'X');
            }
        }
        assertTrue(board.isBoardFull(), "Board should be full before reset");

        board.reset();

        assertFalse(board.isBoardFull(), "Board should not be full after reset");
    }

    @Test
    void testMarkPlaceMethodForOneCell(){
        boolean result = board.markPlace(1 , 1 , 'X');
        assertTrue(result , "empty Cell should be marked, so it should return true.");
    }

    @Test
    void testMarkPlaceMethodForAlreadyMarkedCell(){
        board.markPlace(1 , 1 , 'X');

        CellAlreadyMarkedException exception = assertThrows(CellAlreadyMarkedException.class ,
                () -> board.markPlace(1 ,1 ,'X'),
                "Should throw an exception when marking already marked cell");

        assertTrue(exception.getMessage().contains("(1,1)") , "Should return Correct coordinates");
        assertTrue(exception.getMessage().contains("X") , "Should return Correct Symbol");
    }

    @Test
    void testMarkPlaceOnOutOfBounds(){
        assertThrows(ArrayIndexOutOfBoundsException.class ,
                () -> board.markPlace(3 , 3 , 'X') ,
                "should throw ArrayIndexOutOfBoundsException when it will occur");
    }


    @Test
    void testGetGridIsNotNull() {
        assertNotNull(board.getGrid(), "Grid should not be null after initialization");
    }

    @Test
    void testGetGridSizeIs3x3() {
        Cell[][] grid = board.getGrid();

        assertEquals(3, grid.length, "Grid should have 3 rows");
        assertEquals(3, grid[0].length, "Grid should have 3 columns");
    }

    @Test
    void testGetGridAfterMarked(){
        board.markPlace(1 ,1,'X');
        Cell[][] grid = board.getGrid();

        assertEquals('X' , grid[1][1].getSymbol() , "Grid should be marked after marking any place");
    }

    @Test
    void testResetOnEmptyBoarShouldCellsEmpty() {
        board.reset();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals('-', board.getGrid()[i][j].getSymbol(),
                        "all cells should be empty");
            }
        }
    }
}
