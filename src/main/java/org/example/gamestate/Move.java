package org.example.gamestate;

public class Move {
    private Player player;
    private static Cell cell;

    public Cell getCell() {
        return cell;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static void setCell(Cell cell) {
        Move.cell = cell;
    }

    public Move(Cell cell, Player player) {
        this.cell = cell;
        this.player = player;
    }
}




