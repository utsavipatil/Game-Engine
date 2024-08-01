package org.example.placements;

import org.example.TicTacToe;
import org.example.gamestate.Cell;
import org.example.gamestate.Player;
import org.example.utils.Utils;

import java.util.Optional;

//5. If center is available, take it
public class CenterPlacement implements Placement {
    private CenterPlacement(){}

    private static CenterPlacement centerPlacement;

    public static synchronized Placement get(){
        centerPlacement = (CenterPlacement) Utils.getIfNull(centerPlacement, () -> new CenterPlacement());
        return centerPlacement;
    }

    @Override
    public Optional<Cell> place(Player player, TicTacToe board) {
        Cell center = null;
        if(board.getSymbol(1,1) == null){
            center = new Cell(1,1);
        }
        return Optional.ofNullable(center);
    }

    @Override
    public Placement next() {
        return CornerPlacement.get();
    }
}
