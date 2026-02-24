package com.tss.TicTacToeGameFacade.facadePattern;

import com.tss.TicTacToeGameFacade.model.IBoard;
import com.tss.TicTacToeGameFacade.model.IGameLogic;
import com.tss.TicTacToeGameFacade.model.IPlayer;
import java.util.List;
import java.util.Scanner;

public class TicTacToeFacade {
    private final IBoard board;
    private final IGameLogic logic;
    private final List<IPlayer> players;
    private final int winCondition;

    private final int[] wins;
    private int draws = 0;

    public TicTacToeFacade(IBoard board, IGameLogic logic, List<IPlayer> players, int winCondition) {
        if (players == null || players.size() < 2) throw new IllegalArgumentException("Minimum 2 players required.");
        this.board = board;
        this.logic = logic;
        this.players = players;
        this.winCondition = Math.max(3, Math.min(winCondition, board.getSize()));
        this.wins = new int[players.size()];
    }

    public void playSingleRound(int round, int totalRounds) {
        System.out.println("\n==============================");
        System.out.println("          ROUND " + round + " of " + totalRounds);
        System.out.println("==============================");

        int current = 0;
        while (true) {
            board.display();
            IPlayer player = players.get(current);

            int[] move = player.makeMove();
            if (!logic.isValidMove(board, move[0], move[1])) {
                System.out.println("Invalid move...Try again.");
                continue;
            }

            board.markPlace(move[0], move[1], player.getSymbol());

            if (logic.checkWin(board, player.getSymbol(), winCondition)) {
                board.display();
                System.out.println("---> " + player.getName() + " wins this round!!!...");
                wins[current]++;
                break;
            }

            if (logic.isDraw(board)) {
                board.display();
                System.out.println("---> It's a draw!");
                draws++;
                break;
            }

            current = (current + 1) % players.size();
        }

        showScoreboard(false);
    }

    public void showScoreboard(boolean finalBoard) {
        System.out.println("========================================");
        System.out.println(finalBoard ? "        FINAL SCOREBOARD  " : "        CURRENT SCOREBOARD");
        System.out.println("========================================");
        for (int i = 0; i < players.size(); i++) {
            System.out.printf("%-15s : %d wins%n", players.get(i).getName(), wins[i]);
        }
        System.out.printf("%-15s : %d draws%n", "Draws", draws);
        System.out.println("========================================\n");
    }

    public void showFinalResult() {
        showScoreboard(true);

        int maxWins = -1;
        boolean tie = false;
        String winner = "";

        for (int i = 0; i < players.size(); i++) {
            if (wins[i] > maxWins) {
                maxWins = wins[i];
                winner = players.get(i).getName();
                tie = false;
            } else if (wins[i] == maxWins) {
                tie = true;
            }
        }

        if (maxWins <= 0 || tie) {
            System.out.println("---> The tournament ends in a tie...");
        } else {
            System.out.println("---> " + winner + " is the overall champion!!!...Congratulations...");
        }
    }

    public void resetBoard() {
        board.reset();
    }
}

//public class TicTacToeFacade {
//    private IBoard board;
//    private IGameLogic gameLogic;
//    private IPlayer player1;
//    private IPlayer player2;
//
//    Scanner scanner = new Scanner(System.in);
//
//    private int player1Wins = 0;
//    private int player2Wins = 0;
//    private int draws = 0;
//
//    public TicTacToeFacade(IBoard board, IGameLogic gameLogic, IPlayer player1, IPlayer player2) {
//        this.board = board;
//        this.gameLogic = gameLogic;
//        this.player1 = player1;
//        this.player2 = player2;
//    }
//
//    public void startGame(int round , int totalRounds){
//        System.out.println("\n==============================");
//        System.out.println("          ROUND " + round + " of " + totalRounds);
//        System.out.println("==============================");
//
//        IPlayer currentPlayer = player1;
//
//        while (true){
//            board.display();
//            int[] move = currentPlayer.makeMove();
//
//            if(gameLogic.isValidMove(board , move[0] , move[1])){
//                board.markPlace(move[0] , move[1] , currentPlayer.getSymbol());
//                if(gameLogic.checkWin(board , currentPlayer.getSymbol())){
//                    board.display();
//                    System.out.println(currentPlayer.getName() + " won this round!!!...\n");
//                    updateScore(currentPlayer);
//                    break;
//                }
//
//                if (gameLogic.isDraw(board)) {
//                    board.display();
//                    System.out.println("It's a draw!");
//                    draws++;
//                    break;
//                }
//                currentPlayer = (currentPlayer == player1) ? player2 : player1;
//            }else{
//                System.out.println("Invalid move...enter again...");
//            }
//        }
//    }
////    private boolean askToPlayAgain() {
////        System.out.print("Do you want to play again? (y/n): ");
////        return scanner.next().trim().equalsIgnoreCase("y");
////    }
//
//    private void updateScore(IPlayer winner) {
//        if (winner == player1)
//            player1Wins++;
//        else
//            player2Wins++;
//    }
//
//    public void showScoreboard(boolean finalDisplay) {
//        System.out.println("========================================");
//        System.out.println(finalDisplay ? "        FINAL SCOREBOARD  " : "         CURRENT SCOREBOARD");
//        System.out.println("========================================");
//        System.out.printf("%-15s : %d wins%n", player1.getName(), player1Wins);
//        System.out.printf("%-15s : %d wins%n", player2.getName(), player2Wins);
//        System.out.printf("%-15s : %d draws%n", "Draws", draws);
//        System.out.println("========================================\n");
//    }
//
//    public void resetBoard() {
//        board.reset();
//    }
//
//    public void showFinalResult() {
//        showScoreboard(true);
//        if (player1Wins > player2Wins)
//            System.out.println("===>" + player1.getName() + " is the overall winner!!!...Congratulations...");
//        else if (player2Wins > player1Wins)
//            System.out.println("===>" + player2.getName() + " is the overall winner!!!...Congratulations...");
//        else
//            System.out.println("===> The tournament ends in a draw!");
//    }
//}
