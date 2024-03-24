package org.example.gamestate;

public class Player {
    private String playerSymbol;
    public Player(String playerSymbol){
        this.playerSymbol = playerSymbol;
    }

    public String getPlayerSymbol() {
        return playerSymbol;
    }

    public void setPlayerSymbol(String playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public String symbol(){
        return playerSymbol;
    }

    public Player flip(){
        return new Player(playerSymbol.equals("X") ? "0" : "X");
    }
}
