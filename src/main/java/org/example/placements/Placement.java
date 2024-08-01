package org.example.placements;

import org.example.TicTacToe;
import org.example.api.RuleEngine;
import org.example.gamestate.Cell;
import org.example.gamestate.Player;

import java.util.Optional;
/* Design Pattern = Known solution of widely accepted design approach

Singleton Design = It is a class that can have only one object (an instance of the class) at a time
*   Benefits:-
* 1. Amount of memory required is lesser due to only one object being instantiated
* 2. Simplified access control with increased consistency
*
* NOTE - CONCURRENT UPDATES IN MULTI-THREADED APPLICATIONS BECOME DIFFICULT

Chain of responsibility requires "Singleton Design Pattern" to work
* * */
public interface Placement {
    Optional<Cell> place(Player player, TicTacToe board); //Optional is a container object which may or may not contain non-null value

    Placement next();
    RuleEngine ruleEngine = new RuleEngine();
}
