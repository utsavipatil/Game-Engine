//Responsibility = Any changes in the Board
/*"Fork" means when a state where someone is going to win no matter what
* Problem 1 = Writing the logic for detection of forking should not be in the GameState class because GameState class is responsible for state of the game at any given point in of time
* Problem 2 = Adding more logic to getState() in Rule Engine makes the method fatter and more complicated
*  */

package org.example.api;

import org.example.*;
import org.example.gamestate.*;

public class GameEngine {

    public Board start(String type) throws IllegalAccessException { //Returns a board which that we can play
        if(type.equals("TicTacToe")){
            return new TicTacToe();
        }else{
            throw new IllegalAccessException();
        }
    }
    public void move(Board board, Move move){
        if(board instanceof TicTacToe){
            board.move(move);
        }else{
            throw new IllegalArgumentException();
        }
    }
}

