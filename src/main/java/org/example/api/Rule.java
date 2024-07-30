package org.example.api;

import org.example.gamestate.Board;
import org.example.gamestate.GameState;

import java.util.function.Function;

public class Rule<T extends Board> {
    Function<T, GameState> condition;

    public Rule(Function<T, GameState> condition) {
        this.condition = condition;
    }
}
