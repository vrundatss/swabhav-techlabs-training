//package com.tss.TicTacToeGameFacade.model;
//
//public class Board implements IBoard{
//
//    private char[][] grid = new char[3][3];
//
//    public Board(){
//        reset();
//    }
//
//    @Override
//    public void display() {
//        System.out.println("\n   Current Board State");
//        System.out.println("   --------------------");
//        System.out.println("     0   1   2 ");
//        System.out.println("   -------------");
//
//        for (int i = 0; i < 3; i++) {
//            System.out.print(" " + i + " | ");
//            for (int j = 0; j < 3; j++) {
//                System.out.print(grid[i][j] + " | ");
//            }
//            System.out.println();
//            System.out.println("   -------------");
//        }
//        System.out.println();
//    }
//
//    @Override
//    public boolean isDraw() {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (grid[i][j] == '-') {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public boolean  markPlace(int row, int column, char symbol) {
//        if(grid[row][column] == '-')
//        {
//            grid[row][column]= symbol;
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public char[][] getGrid() {
//        return grid;
//    }
//
//    @Override
//    public void reset() {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                grid[i][j] = '-';
//            }
//        }
//    }
//}
