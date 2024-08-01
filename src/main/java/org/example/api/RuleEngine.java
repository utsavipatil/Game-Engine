//Responsibility = Making sure that all rules are followed in the game
//Rubber Duck Debugging theory is commonly used by programmers. The idea is that when a programmer needs to debug their code, they should explain the program line-by-line to
//a rubber duck. Often, the act of explaining the problem step by step will cause the solution to present itself.
/*  GameState = Current State of the game
    GameInfo = Any other relevant information about the game
    traverse(...) method checks whether a particular rule is met and Rule Engine checks a set of such rules
* */

package org.example.api;

import org.example.TicTacToe;
import org.example.boards.Board;
import org.example.gamestate.*;

import java.util.HashMap;
import java.util.Map;
/* How to make sure the game always ends in a Timeframe
- Each player has to make a move in 10 seconds
- Each player gets 3000 seconds to play the entire game
* */


/* Open-Closed Principle = Open for extension (add more rules) and closed for modification (can't directly change internal code)
* We are extracting logic from the core functioning of the class. So, core function becomes tighter & tighter It becomes extremely closed
* for modifications, but it's earlier to pass right parameters to these functions and get them to do what you want */
public class RuleEngine {

    //String = Board class name(ex. "TicTacToe"), List<Rule> are all the rules applicable to that particular class
    Map<String, RuleSet<TicTacToe>> ruleMap = new HashMap<>();

    public RuleEngine(){
        //This makes Rule Engine much more extensible, Rules can also put in configuration files / db
        ruleMap.put(TicTacToe.class.getName(), TicTacToe.getRules());
    }

    public GameInfo getInfo(Board board){
        if(board instanceof TicTacToe){
            GameState gameState = getState(board);
            Cell forkCell = null;

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
                                forkCell = new Cell(k,l);
                                b1.move(new Move(forkCell, player.flip()));
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
                                    .forkCell(forkCell)
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

            for(Rule r: ruleMap.get(TicTacToe.class.getName())){
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


}

