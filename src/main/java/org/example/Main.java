package org.example;

import org.example.api.AIEngine;
import org.example.api.GameEngine;
import org.example.api.RuleEngine;
import org.example.gamestate.Board;
import org.example.gamestate.Cell;
import org.example.gamestate.Move;
import org.example.gamestate.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        GameEngine gameEngine = new GameEngine();
        AIEngine aiEngine = new AIEngine();
        RuleEngine ruleEngine = new RuleEngine();
        Board board = gameEngine.start("TicTacToe");
        System.out.println("Game started");
        Scanner scanner = new Scanner(System.in);
        int row, col;
        //Make moves in a loop
//
        while (!ruleEngine.getState(board).isOver()){
            Player computer = new Player("0"), human = new Player("X");
            System.out.println("Make your move!");
            System.out.println(board);
            row = scanner.nextInt();
            col = scanner.nextInt();
            Move oppMove = new Move(new Cell(row , col), human);
            gameEngine.move(board,oppMove);
            if(!ruleEngine.getState(board).isOver()){
                Move secondPlayerMove = aiEngine.suggestMove(computer,board);
                gameEngine.move(board,secondPlayerMove);
            }
        }

        System.out.println("GameResult: Congratulations winner is  " + ruleEngine.getState(board).getWinner() + " !!!!");
        System.out.println(board);
    }
}

