package com.tss.TicTacToe.TicTacToeGameFacade.test;

import com.tss.TicTacToe.TicTacToeGameFacade.Utils.UtilityMethods;
import com.tss.TicTacToe.TicTacToeGameFacade.facadePattern.TicTacToeFacade;
import com.tss.TicTacToe.TicTacToeGameFacade.model.*;

import java.util.Scanner;

public class TicTacToeGameFacadeMain {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);

        System.out.println("=====  Welcome to Tic Tac Toe Tournament  =====\n");

        String player1Name = UtilityMethods.getPlayerName(scanner, "first");
        char player1Symbol = UtilityMethods.getPlayerSymbol(scanner, player1Name);

        String player2Name = UtilityMethods.getPlayerName(scanner, "second");
        char player2Symbol = UtilityMethods.getUniqueSymbol(scanner, player2Name, player1Symbol);

        int totalRounds = UtilityMethods.getNumberOfRounds(scanner);

        IBoard board = new Board();
        IGameLogic logic = new GameLogic();
        IPlayer player1 = new Player(player1Name, player1Symbol , scanner);
        IPlayer player2 = new Player(player2Name, player2Symbol , scanner);

        TicTacToeFacade game = new TicTacToeFacade(board, logic, player1, player2);

        for (int round = 1; round <= totalRounds; round++) {
            game.startGame(round, totalRounds);
            if (round < totalRounds) {
                System.out.println("Playing next round...");
                game.resetBoard();
            }
        }

        game.showFinalResult();
        System.out.println("\nThanks for playing...");
    }
}
