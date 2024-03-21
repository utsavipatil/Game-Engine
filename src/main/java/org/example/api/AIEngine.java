//Responsibility = Smartness or Artificially Intelligent Decision

package org.example.api;

import org.example.TicTacToe;
import org.example.gamestate.*;

public class AIEngine {

    public Move suggestMove(Player computer , Board board){
        if(board instanceof TicTacToe){
            TicTacToe board1 = (TicTacToe) board;
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    if(board1.getCell(i,j) == null){
                        return new Move(new Cell(i,j), computer);
                    }
                }
            }
        }
        throw new IllegalArgumentException();
    }
}
