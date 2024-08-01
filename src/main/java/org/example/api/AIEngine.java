/* Responsibility = Smartness or Artificially Intelligent Decision */
//Unit tests are as poor as earlier but GameEngine has become little more robust and TicTacToe functions are have become more accurate

package org.example.api;

import org.example.TicTacToe;
import org.example.boards.Board;
import org.example.gamestate.Cell;
import org.example.gamestate.Move;
import org.example.gamestate.Player;
import org.example.placements.OffensivePlacement;
import org.example.placements.Placement;

import java.util.Optional;

public class AIEngine {

    public Move suggestMove(Player player , Board board){
        if(board instanceof TicTacToe){
            TicTacToe board1 = (TicTacToe) board;
            Cell suggestion;
            int threshould = 3;
            if(countMoves(board1) < threshould){
                suggestion = getBasicMove(board1);
            }else if(countMoves(board1) < threshould + 1){
                suggestion = getCellToPlay(player , board1);
            }else{
                suggestion = getOptimalMove(player,board1);
            }
            if(suggestion != null)
                return new Move(suggestion, player);
        }
        throw new IllegalArgumentException();
    }

/* Singleton class use in Sequential move = Implementing "Chain of Responsibility" Principle */
    private Cell getOptimalMove(Player player , TicTacToe board){

        Placement placement = OffensivePlacement.get();
        while (placement.next() != null){
            Optional<Cell> place = placement.place(player, board);
            if(place.isPresent()){
                return place.get();
            }
            placement = placement.next();
        }
        return null;
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

    public static Cell getBasicMove(TicTacToe board){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board.getSymbol(i, j) == null){
                    return new Cell(i,j);
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
    private Cell getCellToPlay(Player player , TicTacToe board){

        //Victorious moves
        Cell best = offense(player, board);
        if(best != null)
            return null;

        //Defensive moves
        best = defense(player, board);
        if(best != null)
            return best;
        return getBasicMove(board);
    }

    private static Cell offense(Player player, TicTacToe board){
        RuleEngine ruleEngine = new RuleEngine();
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board.getSymbol(i, j) == null){
                    Move move = new Move(new Cell(i,j), player);
                    TicTacToe boardCopy = board.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()){
                        return move.getCell();
                    }
                }
            }
        }
        return null;
    }

    private static Cell defense(Player player, TicTacToe board){
        /* Defensive moves :-
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
        RuleEngine ruleEngine = new RuleEngine();
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board.getSymbol(i,j) == null){
                    Move move = new Move(new Cell(i, j), player.flip() );
                    TicTacToe boardCopy = board.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()){
                        return new Cell(i,j);
                    }
                }
            }
        }
        return getBasicMove(board);
    }
}
