package com.auraXP.aura;

public class LeaderboardEntry {

    private String playerName;
    private int playerScore;

    public LeaderboardEntry(String playerName, int playerScore) {
        this.playerName = playerName;
        this.playerScore = playerScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }
}
