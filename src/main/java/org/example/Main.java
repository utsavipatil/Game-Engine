package org.example;

import org.example.api.GameEngine;
import org.example.gamestate.Board;
import org.example.gamestate.Cell;
import org.example.gamestate.Move;
import org.example.gamestate.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        GameEngine gameEngine = new GameEngine();
        Board board = gameEngine.start("TicTacToe");
        System.out.println("Game started");
        Scanner scanner = new Scanner(System.in);
        int row, col;
        //Make moves in a loop
//
        while (!gameEngine.isComplete(board).isOver()){
            Player computer = new Player("0"), human = new Player("X");
            System.out.println("Make your move!");
            System.out.println(board);
            row = scanner.nextInt();
            col = scanner.nextInt();
            Move oppMove = new Move(new Cell(row , col));
            gameEngine.move(board,human,oppMove);
            if(!gameEngine.isComplete(board).isOver()){
                Move computerMove = gameEngine.suggestMove(computer,board);
                gameEngine.move(board,computer,computerMove);
            }
        }

        System.out.println("GameResult: Congratulations winner is  " + gameEngine.isComplete(board).getWinner() + " !!!!");
        System.out.println(board);
    }
}

