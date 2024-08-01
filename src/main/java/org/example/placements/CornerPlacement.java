package org.example.placements;

import org.example.TicTacToe;
import org.example.gamestate.Cell;
import org.example.gamestate.Player;
import org.example.utils.Utils;

import java.util.Optional;

//6. If corner is available, take it
public class CornerPlacement implements Placement{
    private CornerPlacement(){}

    private static CornerPlacement cornerPlacement;

    public static synchronized Placement get(){
        cornerPlacement = (CornerPlacement) Utils.getIfNull(cornerPlacement, () -> new CornerPlacement());
        return cornerPlacement;
    }
    @Override
    public Optional<Cell> place(Player player, TicTacToe board) {
        Cell corner = null;
        int[][] corners = new int[][]{{0,0}, {0,2}, {2,0}, {2,2}};
        for(int i =0; i<corners.length; i++){
            if(board.getSymbol(corners[i][0], corners[i][1]) == null){
                corner = new Cell(corners[i][0], corners[i][1]);
            }
        }
        return Optional.ofNullable(corner);
    }

    @Override
    public Placement next() {
        return null;
    }
}
