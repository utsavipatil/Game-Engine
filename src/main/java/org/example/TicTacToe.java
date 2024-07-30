package org.example;

import org.example.api.Rule;
import org.example.api.RuleSet;
import org.example.gamestate.Board;
import org.example.gamestate.Cell;
import org.example.gamestate.GameState;
import org.example.gamestate.Move;

import java.util.function.BiFunction;
import java.util.function.Function;

/*
* Prototype Pattern is used when the type of objects to create is determined by a prototypical instance, which is cloned to produce new objects.
* */
public class TicTacToe implements Board {
    String cells[][] = new String[3][3];

    public static RuleSet<TicTacToe> getRules(){
        RuleSet rules = new RuleSet();
        rules.add(new Rule<TicTacToe>(board-> outerTraversals((i, j) -> board.getSymbol(i,j))));
        rules.add(new Rule<TicTacToe>(board-> outerTraversals((i,j) -> board.getSymbol(j,i))));
        rules.add(new Rule<TicTacToe>(board-> traverse(i -> board.getSymbol(i,i))));
        rules.add(new Rule<TicTacToe>(board-> traverse(i-> board.getSymbol(i,2-i))));
        rules.add(new Rule<TicTacToe>(board-> {
            int countOfFilledCells = 0;
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    if(board.getSymbol(i, j) != null){
                        countOfFilledCells++;
                    }
                }
            }
            if(countOfFilledCells == 9){
                return new GameState(true , "-");
            }
            return new GameState(false, "-");
        }));
        return rules;
    }

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

    private static GameState outerTraversals(BiFunction<Integer, Integer, String> next){
        //Iterator in Java is an Object used to traverse and access elements sequentially in a collection

        GameState result = new GameState(false, "-");
        for(int i=0; i<3; i++){ //All Rows
            final int ii = i;
            GameState traversal1 = traverse(j -> next.apply(ii,j));
            if(traversal1.isOver()){
                result = traversal1;
                break;
            }
        }
        return result;
    }

    private static GameState traverse(Function<Integer, String> traversal){
        GameState result = new GameState(false, "-");
        boolean possibleStreak = true;
        for(int j=0; j<3; j++){
            if(traversal.apply(j) == null || !traversal.apply(0).equals(traversal.apply(j))){
                possibleStreak = false;
                break;
            }
        }
        if(possibleStreak){
            result = new GameState(true, traversal.apply(0));
        }
        return result;
    }

}
