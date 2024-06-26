//Responsibility = Making sure that all rules are followed in the game
//Rubber Duck Debugging theory is commonly used by programmers. The idea is that when a programmer needs to debug their code, they should explain the program line-by-line to
//a rubber duck. Often, the act of explaining the problem step by step will cause the solution to present itself.
/*  GameState = Current State of the game
    GameInfo = Any other relevant information about the game
    traverse(...) method checks whether a particular rule is met and Rule Engine checks a set of such rules
* */

package org.example.api;

import org.example.TicTacToe;
import org.example.gamestate.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/* Open-Closed Principle = Open for extension (add more rules) and closed for modification (can't directly change internal code)
* We are extracting logic from the core functioning of the class. So, core function becomes tighter & tighter It becomes extremely closed
* for modifications but its earlier to pass right parameters to this functions and get them to do what you want */
public class RuleEngine {
    public RuleEngine(){
        String key = TicTacToe.class.getName();
        ruleMap.put(key, new ArrayList<>()); //This makes Rule Engine much more extensible, Rules can also put in configuration files / db
        ruleMap.get(key).add(new Rule<>(board-> outerTraversals((i,j) -> board.getSymbol(i,j))));
        ruleMap.get(key).add(new Rule<>(board-> outerTraversals((i,j) -> board.getSymbol(j,i))));
        ruleMap.get(key).add(new Rule<>(board-> traverse(i -> board.getSymbol(i,i))));
        ruleMap.get(key).add(new Rule<>(board-> traverse(i-> board.getSymbol(i,2-i))));
        ruleMap.get(key).add(new Rule<>(board-> {
            int countOfFilledCells = 0;
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    if(board.getCell(i, j) != null){
                        countOfFilledCells++;
                    }
                }
            }
            if(countOfFilledCells == 9){
                return new GameState(true , "-");
            }
            return new GameState(false, "-");
        }));
    }

    Map<String, List<Rule<TicTacToe>>> ruleMap = new HashMap<>(); //String = Board class name(ex. "TicTacToe"), List<Rule> are all the rules applicable to that particular class

    public GameInfo gameInfo(Board board){
        if(board instanceof TicTacToe){
            GameState gameState = getState(board);
            final String[] players = new String[]{"X", "0"};
            for(int index = 0; index < 2; index++){
                for(int i=0; i< 3; i++){
                    for(int j=0; j< 3; j++){
                        Board b = board.copy();
                        Player player = new Player(players[index]);
                        b.move(new Move(new Cell(i,j), player));
                        boolean canStillWin = false;
                        for(int k=0; k < 3; k++){
                            for(int l=0; l < 3; l++){
                                Board b1 = b.copy();
                                b1.move(new Move( new Cell(k , l ), player.flip()));
                                if(getState(b1).getWinner().equals(player.flip().symbol())){
                                    canStillWin = true;
                                    break;
                                }
                            }
                            if(canStillWin){
                                break;
                            }
                        }
                        if(canStillWin){
                            return new GameInfoBuilder()
                                    .isOver(gameState.isOver())
                                    .winner(gameState.getWinner())
                                    .hasFork(true)
                                    .player(player.flip())
                                    .build();
                        }
                    }
                }
            }
            return new GameInfoBuilder().isOver(gameState.isOver()).winner(gameState.getWinner()).build();
        }else{
            throw new IllegalArgumentException();
        }
    }

    public GameState getState(Board board){
        if(board instanceof TicTacToe){
            TicTacToe b = (TicTacToe) board;

            for(Rule<TicTacToe> r: ruleMap.get(TicTacToe.class.getName())){
                GameState gameState = r.condition.apply(b);
                if(gameState.isOver()){
                    return gameState;
                }
            }
            return new GameState(false, "-");
        }else{
            throw new IllegalArgumentException();
        }
    }

    private GameState outerTraversals(BiFunction<Integer,Integer,String> next){
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

