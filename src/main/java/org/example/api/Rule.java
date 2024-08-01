package org.example.api;

import org.example.boards.CellBoard;
import org.example.gamestate.GameState;

import java.util.function.Function;

public class Rule{
    Function<CellBoard, GameState> condition;

    public Rule(Function<CellBoard, GameState> condition) {
        this.condition = condition;
    }
}
