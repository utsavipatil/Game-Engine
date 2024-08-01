package org.example.placements;

import org.example.TicTacToe;
import org.example.gamestate.Cell;
import org.example.gamestate.GameInfo;
import org.example.gamestate.Player;
import org.example.utils.Utils;

import java.util.Optional;

//3. If opponent have a fork , then play it (Fork is a situation where a player has 2 winning moves)
//4. If you have a fork, then block it
public class ForkPlacement implements Placement{
    private ForkPlacement(){}
    private static ForkPlacement forkPlacement;
    public static synchronized Placement get(){
        forkPlacement = (ForkPlacement) Utils.getIfNull(forkPlacement, ()-> new ForkPlacement());
        return forkPlacement;
    }

    @Override
    public Optional<Cell> place(Player player, TicTacToe board) {
        Cell best = null;
        GameInfo gameInfo = ruleEngine.getInfo(board);
        if(gameInfo.hasAFork()){
            best = gameInfo.getForkCell();
        }
        return Optional.ofNullable(best);
    }

    @Override
    public Placement next() {
        return CenterPlacement.get();
    }
}
