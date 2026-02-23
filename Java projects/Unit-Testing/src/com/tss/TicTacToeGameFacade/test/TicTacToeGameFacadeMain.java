package com.tss.TicTacToeGameFacade.test;

import com.tss.TicTacToeGameFacade.Utils.UtilityMethods;
import com.tss.TicTacToeGameFacade.facadePattern.TicTacToeFacade;
import com.tss.TicTacToeGameFacade.model.*;

import java.util.Scanner;
import java.util.*;

public class TicTacToeGameFacadeMain {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);

        System.out.println("===== Multi-Player Tic Tac Toe (K-in-a-row) =====\n");

        System.out.print("Enter board size (N×N, minimum 3): ");
        int size = UtilityMethods.getPositiveInt(scanner, 3);

        // Winning condition K
        System.out.print("Enter how many symbols in a row to win (K, 3 ≤ K ≤ " + size + "): ");
        int k = UtilityMethods.getPositiveInt(scanner, 3);
        if (k > size) {
            System.out.println("K cannot exceed board size; setting K = " + size);
            k = size;
        }

        System.out.print("Enter number of players (minimum 2 and less than N): ");
        int numPlayers = UtilityMethods.getPositiveInt(scanner, 2);

        while (numPlayers >= size) {
            System.out.print("Number of players must be less than board size (" + size + "). Enter again: ");
            numPlayers = UtilityMethods.getPositiveInt(scanner, 2);
        }

//        scanner.nextLine();

        List<IPlayer> players = new ArrayList<>();
        Set<Character> usedSymbols = new HashSet<>();

        for (int i = 1; i <= numPlayers; i++) {
            String name = UtilityMethods.getPlayerName(scanner, String.valueOf(i));
            char symbol = UtilityMethods.getUniqueSymbol(scanner, name, usedSymbols);
            players.add(new Player(name, symbol));
        }

        System.out.print("Enter number of rounds (≥1): ");
        int rounds = UtilityMethods.getPositiveInt(scanner, 1);

        IBoard board = new BoardNxN(size);
        IGameLogic logic = new GameLogicKInARow();
        TicTacToeFacade game = new TicTacToeFacade(board, logic, players, k);

        for (int round = 1; round <= rounds; round++) {
            game.playSingleRound(round, rounds);
            if (round < rounds) {
                game.resetBoard();
                System.out.println("\nPlaying next round...");
            }
        }

        game.showFinalResult();

        System.out.println("\nThanks for playing...");
    }
}
