package com.tss.TicTacToeTest.PlayerTest;

import com.tss.TicTacToe.TicTacToeGameFacade.model.Player;
import org.junit.jupiter.api.BeforeEach;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerMockitoTest {
    private Player player;

    private Scanner scanner;

    @BeforeEach
    void setUp(){
        scanner = Mockito.mock(Scanner.class);
        player = new Player("Vrunda" , 'X' , scanner);
    }

    @Test
    void testGetName(){
        assertEquals("Vrunda" , player.getName());
    }

    @Test
    void testGetSymbol(){
        assertEquals('X' , player.getSymbol());
    }

    @Test
    void testMakeMoveWithCorrectCoordinates(){
        Mockito.when(scanner.nextInt()).thenReturn(1, 2);

        int[] move = player.makeMove();

        assertArrayEquals(new int[]{1 ,2} , move , "should return correct coordinates");
    }
}
