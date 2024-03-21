//Responsibility = Any changes in the Board

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

