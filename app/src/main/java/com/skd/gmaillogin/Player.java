package com.skd.gmaillogin;

public class Player {
    String pId;
    String playerName;
    String game;

    public Player() {

    }

    public Player(String pId, String playerName, String game) {

        this.pId = pId;
        this.playerName = playerName;
        this.game = game;
    }

    public String getpId() {
        return pId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getGame() {
        return game;
    }
}
