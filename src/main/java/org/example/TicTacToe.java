package org.example;

import org.example.gamestate.Board;
import org.example.gamestate.Cell;

public class TicTacToe extends Board {
    String cells[][] = new String[3][3];

    //If Board can exist without cells then we should not put cells in boards
    public String getCell(int row, int col){
        return cells[row][col];
    }
    public void setCell(Cell cell, String symbol ){
        cells[cell.getRow()][cell.getCol()] = symbol;
    }
}
