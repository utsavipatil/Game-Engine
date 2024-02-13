package org.example.api;

import org.example.*;
import org.example.gamestate.*;

public class GameEngine {

    public Board start(String type) throws IllegalAccessException { //Returns a board which that we can play
        if(type.equals("TicTacToe")){
            return new TicTacToe();
        }else{
            throw new IllegalAccessException();
        }
    }
    public void move(Board board, Player player, Move move){
        if(board instanceof TicTacToe){
            TicTacToe board1 = (TicTacToe) board;
            board1.setCell( move.getCell(), player.symbol());
        }else{
            throw new IllegalArgumentException();
        }
    }
    public GameResult isComplete(Board board){
        if(board instanceof TicTacToe){
            TicTacToe board1 = (TicTacToe) board;
            String firstCharacter = "";

            boolean rowComplete = true;
            for(int i=0; i<3; i++){ //All Rows
                firstCharacter = board1.getCell(i,0);
                rowComplete = firstCharacter != null;
                    for(int j=1; j<3; j++){
                        if(firstCharacter != null && !firstCharacter.equals(board1.getCell(i,j))){ //If first character is not null
                            rowComplete = false;
                            break;
                        }
                    }
                if(rowComplete){
                    break;
                }
            }

            if(rowComplete){
                return new GameResult(true, firstCharacter);
            }

            boolean colComplete = firstCharacter != null;
            for(int i=0; i<3; i++){ //All Columns
                firstCharacter = board1.getCell(0,i);
                    for(int j=1; j<3; j++){
                        if(firstCharacter != null && !firstCharacter.equals(board1.getCell(j,i))){
                            colComplete = false;
                            break;
                        }
                    }
                if(colComplete){
                    break;
                }
            }

            if(colComplete){
                return new GameResult(true, firstCharacter);
            }

            boolean diaComplete = firstCharacter != null;
            firstCharacter = board1.getCell(0,0);
            for(int i=0; i<3; i++){ //i = j Diagonal
                    if(firstCharacter != null && !firstCharacter.equals(board1.getCell(i,i))){
                        diaComplete = false;
                        break;
                    }
            }

            if(diaComplete){
                return new GameResult(true, firstCharacter);
            }

            boolean revDiaComplete = firstCharacter != null;
            firstCharacter = board1.getCell(0,2);
            for(int i=0; i<3; i++){ //i+j = constance Diagonal (Reverse Diagonal)
                    if(firstCharacter != null && !firstCharacter.equals(board1.getCell(i, 2 - i))){ // 0 + i + 2 - i = 2 (constant)
                        revDiaComplete = false;
                        break;
                    }

            }

            if(revDiaComplete){
                return new GameResult(true, firstCharacter);
            }

            int countOfFilledCells = 0;
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    if(board1.getCell(i, j) != null){
                        countOfFilledCells++;
                    }
                }
            }

            if(countOfFilledCells == 9){
                return new GameResult(true , "");
            }else{
                return new GameResult(false, "");
            }
        }else{
            return new GameResult(false, "");
        }
    }
    public Move suggestMove(Player computer , Board board){
        if(board instanceof TicTacToe){
            TicTacToe board1 = (TicTacToe) board;
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    if(board1.getCell(j,i) == null){
                        return new Move(new Cell(i,j));
                    }
                }
            }
        }
        throw new IllegalArgumentException();
    }
}

