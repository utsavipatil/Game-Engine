package org.example.placements;

import org.example.TicTacToe;
import org.example.gamestate.Cell;
import org.example.gamestate.Move;
import org.example.gamestate.Player;
import org.example.utils.Utils;

import java.util.Optional;

//1. If you have a winning move, then play it
public class OffensivePlacement implements Placement{

    private OffensivePlacement(){}
    private static OffensivePlacement offensivePlacement;
    public static synchronized Placement get(){
        offensivePlacement = (OffensivePlacement) Utils.getIfNull(offensivePlacement , () -> new OffensivePlacement());
        return offensivePlacement;
    }

    @Override
    public Optional<Cell> place(Player player, TicTacToe board) {
        return Optional.ofNullable(offense(player, board));
    }

    @Override
    public Placement next() {
        return DefensivePlacement.get();
    }

    private static Cell offense(Player player, TicTacToe board){
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
}
