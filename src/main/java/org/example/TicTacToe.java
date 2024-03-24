package org.example;

import org.example.gamestate.Board;
import org.example.gamestate.Cell;
import org.example.gamestate.Move;

/*
* Prototype Pattern is used4 when the type of objects to create is determined by a prototypical instance, which is cloned to produce new objects.
* */
public class TicTacToe implements Board {
    String cells[][] = new String[3][3];

    //If Board can exist without cells then we should not put cells in boards
    public String getCell(int row, int col){
        return cells[row][col];
    }
    public void setCell(Cell cell, String symbol ){
        if(cells[cell.getRow()][cell.getCol()] == null){
            cells[cell.getRow()][cell.getCol()] = symbol;
        }else{
            System.out.println(this);
            throw new IllegalArgumentException(cell.getRow() + " " + cell.getCol());
        }
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

    @Override //Board will check whether any move is legal before it actually make it
    public void move(Move move){
        setCell(move.getCell(), move.getPlayer().symbol());
    }

    @Override
    public TicTacToe copy(){
        TicTacToe ticTacToe = new TicTacToe();
        for(int i=0; i<3; i++){
            System.arraycopy(cells[i], 0, ticTacToe.cells[i], 0 , 3); //Deep Copy of Object
        }
        return ticTacToe;
    }
    public String getSymbol(int row, int col) {
        return cells[row][col];
    }

}
