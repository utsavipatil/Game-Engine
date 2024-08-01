package org.example.boards;
/*  Interface Segregation
*   interface CellBoard extends Board                                           interface Board
* ->Specific set of board classes will extend these responsibilities           ->All board classes will extend generic responsibilities'
*   ex. TicTacToe, Chess, Sudoku etc                                            ex. Circular, Hexagonal Boards etc
*  */
public interface CellBoard extends Board{
    String getSymbol(int i, int j);
}
