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


/*
* Now Test can write in two ways
* 1. Modify the unit tests to align with new logic
*   - As logic keep on changing Test will keep on fail
* 2. Enforce the logic from the AI engine by clearly defining moves
*   + Hardcoding the moves made by AIEngine is the best way to move ahead with our unit tests
*   + Test have become more reliable now because they no longer dependent on Artificial Intelligence of Game AI
* */
public class GamePlayTest {
    GameEngine gameEngine;
    RuleEngine ruleEngine;

    @Before
    public void setup(){
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
    }

    @Test
    public void checkForRowWin() throws IllegalAccessException {

        Board board = gameEngine.start("TicTacToe");
        System.out.println("Game started");
        int[][] firstPlayerMoves = new int[][]{{1,0}, {1,1}, {1,2}}; //Pre-defined moves so that user interaction not needed
        int[][] secondPlayerMoves = new int[][]{{0,0}, {0,1}, {0,2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);

//        Computer checks whether human won or not
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X"); //Human won or not
    }

    @Test
    public void checkForColWin() throws IllegalAccessException {

        Board board = gameEngine.start("TicTacToe");
        System.out.println("Game started");
        int[][] firstPlayerMoves = new int[][]{{0,0}, {1,0}, {2,0}}; //Pre-defined moves so that user interaction not needed
        int[][] secondPlayerMoves = new int[][]{{0,1}, {0,2}, {1,1}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);

//        Computer checks whether human won or not
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X"); //Human won or not
    }

    @Test
    public void checkForDiagWin() throws IllegalAccessException {

        Board board = gameEngine.start("TicTacToe");
        System.out.println("Game started");
        int[][] firstPlayerMoves = new int[][]{{0,0}, {1,1}, {2,2}}; //Pre-defined moves so that user interaction not needed
        int[][] secondPlayerMoves = new int[][]{{0,1}, {0,2}, {1,0}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);

//        Computer checks whether human won or not
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X"); //Human won or not
    }

    @Test
    public void checkForRevDiagWin() throws IllegalAccessException {

        Board board = gameEngine.start("TicTacToe");
        System.out.println("Game started");
        int[][] firstPlayerMoves = new int[][]{{0,2}, {1,1}, {2,0}}; //Pre-defined moves so that user interaction not needed
        int[][] secondPlayerMoves = new int[][]{{0,0}, {0,1}, {1,0}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);

//        Computer checks whether human won or not
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X"); //Human won or not
    }

    @Test
    public void checkForSecondPlayerWin() throws IllegalAccessException {

        Board board = gameEngine.start("TicTacToe");
        System.out.println("Game started");
        int[][] firstPlayerMoves = new int[][]{{1,0}, {1,1}, {2,0}}; //Pre-defined moves so that user interaction not needed
        int[][] secondPlayerMoves = new int[][]{{0,0}, {0,1}, {0,2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);

//        Computer checks whether human won or not
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "0"); //Computer won or not
    }

    private void playGame(Board board , int[][] firstPlayerMoves, int[][] secondPlayerMoves){
        int next = 0;

        //Make moves in a loop
        while (!ruleEngine.getState(board).isOver()){
            Player first = new Player("X"), second = new Player("0");
            int firstRow = firstPlayerMoves[next][0];
            int firstCol = firstPlayerMoves[next][1];

            Move firstPlayerMove = new Move(new Cell(firstRow , firstCol), first);
            gameEngine.move(board,firstPlayerMove);
            if(!ruleEngine.getState(board).isOver()){
                int secondRow = secondPlayerMoves[next][0];
                int secondCol = secondPlayerMoves[next][1];
                Move secondPlayerMove = new Move(new Cell(secondRow, secondCol), second);
                gameEngine.move(board,secondPlayerMove);
            }
            next++;
        }
    }
}
