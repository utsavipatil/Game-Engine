package org.example.gamestate;

public class Move {
    private static Cell cell;

    public static Cell getCell(){
        return cell;
    }

    public static void setCell(Cell cell) {
        Move.cell = cell;
    }

    public Move(Cell cell) {
        this.cell = cell;
    }
}




