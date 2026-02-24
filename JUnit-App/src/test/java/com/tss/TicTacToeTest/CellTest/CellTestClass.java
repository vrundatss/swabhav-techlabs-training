package com.tss.TicTacToeTest.CellTest;

import com.tss.TicTacToe.TicTacToeGameFacade.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CellTestClass {
    private Cell cell;

    @BeforeEach
    void setUp() {
        cell = new Cell(1, 1);
    }

    @Test
    void testByDefaultSymbol()
    {
        assertEquals('-' , cell.getSymbol() , "New cell should have symbol '-' as by default symbol");
    }

    @Test
    void testIsEmptyWhenDefault() {
        assertTrue(cell.isEmpty(), "Cell with '-' should be considered as empty");
    }

    @Test
    void testIsNotEmptyWhenOccupied() {
        cell.setSymbol('X');
        assertFalse(cell.isEmpty(), "Cell with a symbol should not be empty");
    }

    @Test
    void testForReset() {
        cell.setSymbol('X');
        cell.reset();

        assertEquals('-' , cell.getSymbol() , "Reset should set all Cells as '-' ");
        assertTrue(cell.isEmpty() , "After reset...all Cell should be empty...");
    }

    @Test
    void testToStringMethod(){
        cell.setSymbol('X');
        assertEquals("X" , cell.toString() , "toString() should return symbol as a String. ");
    }

}
