package org.example.placements;
/* Annotation in Java is a tag representing metadata about classes, interfaces, variables, methods or fields
    Synchronized = enables any thread presently accessing the method to have a lock on it so that no other thread can run method simultaneously
* */

import org.example.TicTacToe;
import org.example.api.RuleEngine;
import org.example.gamestate.Cell;
import org.example.gamestate.Move;
import org.example.gamestate.Player;
import org.example.utils.Utils;

import java.util.Optional;

import static org.example.api.AIEngine.getBasicMove;

//2. If opponent have a winning move, then block it
public class DefensivePlacement implements Placement{
    private DefensivePlacement(){}
    private static DefensivePlacement defensivePlacement;
    public static synchronized Placement get(){
       defensivePlacement = (DefensivePlacement) Utils.getIfNull(defensivePlacement, ()-> new DefensivePlacement());
       return defensivePlacement;
    }

    @Override
    public Optional<Cell> place(Player player, TicTacToe board) {
        return Optional.ofNullable(defense(player, board));
    }

    @Override
    public Placement next() {
        return ForkPlacement.get();
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
