package org.example;

import org.example.gamestate.Board;
import org.example.gamestate.Cell;
import org.example.gamestate.Move;

public class TicTacToe extends Board {
    String cells[][] = new String[3][3];

    //If Board can exist without cells then we should not put cells in boards
    public String getCell(int row, int col){
        return cells[row][col];
    }
    public void setCell(Cell cell, String symbol ){
        cells[cell.getRow()][cell.getCol()] = symbol;
    }

    @Override
    public String toString(){
        String result = "";
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(cells[i][j] == null){
                    result += "- ";
                }else{
                    result += cells[i][j] + " ";
                }

            }
            result += "\n";
        }
        return result;
    }

    @Override
    public void move(Move move){
        setCell(move.getCell(), move.getPlayer().symbol());
    }
}
