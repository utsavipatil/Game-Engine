//Responsibility = Making sure that all rules are followed in the game
//Rubber Duck Debugging theory is commonly used by programmers. The idea is that when a programmer needs to debug their code, they should explain the program line-by-line to
//a rubber duck. Often, the act of explaining the problem step by step will cause the solution to present itself.


package org.example.api;

import org.example.TicTacToe;
import org.example.gamestate.Board;
import org.example.gamestate.GameState;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {
    private boolean isStreak;
    private String firstCharacter;
    private GameState rowWin;

    public GameState getState(Board board){
        if(board instanceof TicTacToe){
            TicTacToe board1 = (TicTacToe) board;
            Function<Integer, String > getRow = i -> board1.getSymbol(i,0); //Function that take Integer argument and return String
            Function<Integer, String > getCol = i -> board1.getSymbol(0,i); //Lambada expression is a short block of code which takes in parameters and return value

            BiFunction<Integer,Integer,String > next = (i, j) -> board1.getSymbol(i,j);
            BiFunction<Integer,Integer, String > getNextCol = (i,j) -> board1.getSymbol(j,i);

            //Row Complete
            Function<Integer,String> startsWith = getRow;
            GameState rowWin = outerTraversal((i,j) -> board1.getSymbol(i,j));
            if(rowWin.isOver()) return rowWin;

            //Column Complete
            GameState colWin = outerTraversal((i,j) -> board1.getSymbol(j,i));
            if(colWin.isOver()) return colWin;

            //Diagonal Complete
            GameState diagWin = findDiagStreak(i1 -> board1.getSymbol(i1, i1));
            if(diagWin.isOver()) return diagWin;

            //Reverse Diagonal Complete
            GameState revDiagWin = findDiagStreak(i1 -> board1.getSymbol(i1, 2-i1));
            if(revDiagWin.isOver()) return revDiagWin;


            Function<Integer, String> diag = i -> board1.getSymbol(i,i);
            Function<Integer, String> revDia = i -> board1.getSymbol(i, 2 - i);

            firstCharacter = diag.apply(0);
            diagWin = findDiagStreak(diag);
            if(diagWin != null) return  diagWin;

            boolean isStreak;

            isStreak = firstCharacter != null;
            for(int i=1; i<3; i++){ //i = j Diagonal
                if(firstCharacter != null && !firstCharacter.equals(diag.apply(i))){
                    isStreak = false;
                    break;
                }
            }
            if(isStreak){
                return new GameState(true, firstCharacter);
            }


            revDiagWin = findDiagStreak(revDia);
            if(revDiagWin != null) return revDiagWin;

            int countOfFilledCells = 0;
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    if(board1.getCell(i, j) != null){
                        countOfFilledCells++;
                    }
                }
            }

            if(countOfFilledCells == 9){
                return new GameState(true , "-");
            }else{
                return new GameState(false, "-");
            }
        }else{
            return new GameState(false, "-");
        }
    }

    private GameState outerTraversal(BiFunction<Integer,Integer,String> next){
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

    private GameState findDiagStreak(Function<Integer, String> traversal){
        return traverse(traversal);
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
