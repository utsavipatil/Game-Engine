/* Responsibility = Smartness or Artificially Intelligent Decision */
//Unit tests are as poor as earlier but GameEngine has become little more robust and TicTacToe functions are have become more accurate

package org.example.api;

import org.example.TicTacToe;
import org.example.gamestate.Board;
import org.example.gamestate.Cell;
import org.example.gamestate.Move;
import org.example.gamestate.Player;

public class AIEngine {

    public Move suggestMove(Player computer , Board board){
        if(board instanceof TicTacToe){
            TicTacToe board1 = (TicTacToe) board;
            Move suggestion;
            int threshould = 3;
            if(countMoves(board1) < threshould){
                suggestion = getBasicMove(computer , board1);
            }else{
                suggestion = getSmartMove(computer , board1);
            }
            if(suggestion != null)
                return suggestion;
        }
        throw new IllegalArgumentException();
    }

    private int countMoves(TicTacToe board){
        int count = 0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board.getCell(i,j) != null){
                    count++;
                }
            }
        }
        return count;
    }

    public static Move getBasicMove(Player computer, TicTacToe board){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board.getSymbol(i, j) == null){
                    return new Move(new Cell(i,j), computer);
                }
            }
        }
        return null;
    }

    /*
     * Get Smart Move
     * 1. Can AI win with this move ?
     *       - Make the winning move
     * 2. Will Human win with their next Move?
     *       - Block Human from winning
     * */

    private Move getSmartMove(Player player, TicTacToe board){
        RuleEngine ruleEngine = new RuleEngine();

        //Victorious moves
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board.getSymbol(i, j) == null){
                    Move move = new Move(new Cell(i,j), player);
                    TicTacToe boardCopy = board.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()){
                        return move;
                    }
                }
            }
        }

        //Defensive moves
        /*
        * Option 1 = Undo the moves made while looking for the best move
        * "+" No duplication of Objects
        * "+" History of the Game is cheaply accessible
        * "-" Board cannot be concurrently accessed
        * "-" Undo operations can be very complex to implement (for ex. = in chess if undo something may be checked no longer valid
        *       so king gets more news to play)
        *
        * Option 2 = Create and work-around using a copy of the original board
        * "+" Game History and undo() are simple to implement though not very efficient
        * "+" Concurrent Access to the board is possible
        * */
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board.getSymbol(i,j) == null){
                    Move move = new Move(new Cell(i, j), player.flip() );
                    TicTacToe boardCopy = board.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()){
                        return new Move(new Cell(i,j), player);
                    }
                }
            }
        }
        return getBasicMove(player,board);
    }
}
