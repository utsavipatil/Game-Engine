import org.example.api.AIEngine;
import org.example.api.GameEngine;
import org.example.api.RuleEngine;
import org.example.gamestate.Board;
import org.example.gamestate.Cell;
import org.example.gamestate.Move;
import org.example.gamestate.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

public class GamePlayTest {
    GameEngine gameEngine;
    AIEngine aiEngine;
    RuleEngine ruleEngine;

    @Before
    public void setup(){
        gameEngine = new GameEngine();
        aiEngine = new AIEngine();
        ruleEngine = new RuleEngine();
    }

    @Test
    public void checkForRowWin() throws IllegalAccessException {

        Board board = gameEngine.start("TicTacToe");
        System.out.println("Game started");
        int[][] moves = new int[][]{{1,0}, {1,1}, {1,2}}; //Pre-defined moves so that user interaction not needed
        playGame(board, moves);

//        Computer checks whether human won or not
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X"); //Human won or not
    }

    @Test
    public void checkForColWin() throws IllegalAccessException {

        Board board = gameEngine.start("TicTacToe");
        System.out.println("Game started");
        int[][] moves = new int[][]{{0,0}, {1,0}, {2,0}}; //Pre-defined moves so that user interaction not needed
        playGame(board, moves);

//        Computer checks whether human won or not
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X"); //Human won or not
    }

    @Test
    public void checkForDiagWin() throws IllegalAccessException {

        Board board = gameEngine.start("TicTacToe");
        System.out.println("Game started");
        int[][] moves = new int[][]{{0,0}, {1,1}, {2,2}}; //Pre-defined moves so that user interaction not needed
        playGame(board, moves);

//        Computer checks whether human won or not
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X"); //Human won or not
    }

    @Test
    public void checkForRevDiagWin() throws IllegalAccessException {

        Board board = gameEngine.start("TicTacToe");
        System.out.println("Game started");
        int[][] moves = new int[][]{{0,2}, {1,1}, {2,0}}; //Pre-defined moves so that user interaction not needed
        playGame(board, moves);

//        Computer checks whether human won or not
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X"); //Human won or not
    }

    @Test
    public void checkForComputerWin() throws IllegalAccessException {

        Board board = gameEngine.start("TicTacToe");
        System.out.println("Game started");
        int[][] moves = new int[][]{{1,0}, {1,1}, {2,0}}; //Pre-defined moves so that user interaction not needed
        playGame(board, moves);

//        Computer checks whether human won or not
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "0"); //Computer won or not
    }

    private void playGame(Board board , int[][] moves){
        int row, col;
        int next = 0;

        //Make moves in a loop
        while (!ruleEngine.getState(board).isOver()){
            Player computer = new Player("0"), human = new Player("X");
            System.out.println("Make your move!");
            System.out.println(board);
            row = moves[next][0];
            col = moves[next][1];
            next++;

            Move oppMove = new Move(new Cell(row , col), human);
            gameEngine.move(board,oppMove);
            if(!ruleEngine.getState(board).isOver()){
                Move computerMove = aiEngine.suggestMove(computer,board);
                gameEngine.move(board,computerMove);
            }
        }
    }
}
