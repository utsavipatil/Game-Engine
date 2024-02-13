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
        Scanner sc = new Scanner(System.in);
        //Make moves in a loop
//
        while (!gameEngine.isComplete(board).isOver()){
            System.out.println("Make your move!");
            int row = sc.nextInt(), col = sc.nextInt();
            Player computer = new Player("0"), opponent = new Player("X");
            Move oppMove = new Move(new Cell(row, col));
            gameEngine.move(board, opponent , oppMove);
            Move computerMove = gameEngine.suggestMove(computer, board);
            gameEngine.move(board, computer, computerMove);
            if(!gameEngine.isComplete(board).isOver()){
                gameEngine.move(board, computer, computerMove);
            }
        }

        System.out.println("GameResult: " + gameEngine.isComplete(board));
    }
}

