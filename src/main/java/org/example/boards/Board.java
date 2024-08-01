package org.example.boards;

/* Liskov Substitution Principle = You should not have subclasses, which do extend all the behaviours of the parent class
Objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program.
* */

import org.example.gamestate.Move;

public interface Board {
    void move(Move move);
    Board copy();
//    String getSymbol(int i, int j); Violate Liskov Substitution Principle (Every board not need getSymbol method its specific to TicTacToe)
}
