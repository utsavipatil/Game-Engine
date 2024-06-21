package org.example.api;

import org.example.TicTacToe;
import org.example.gamestate.GameState;

import java.util.function.Function;

class Rule<T extends TicTacToe> {
    Function<T, GameState> condition;

    public Rule(Function<T, GameState> condition) {
        this.condition = condition;
    }
}
