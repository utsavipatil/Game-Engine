package org.example.gamestate;

public interface Board {
    void move(Move move);
    Board copy();
}
