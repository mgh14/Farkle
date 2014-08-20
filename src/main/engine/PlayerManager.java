package main.engine;

import main.engine.properties.PropertiesManager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PlayerManager {

    List<Player> players;
    Player currentPlayer;
    HashMap<Player, Integer> playerScores;

    public PlayerManager() {
        players = new LinkedList<Player>();
        playerScores = new HashMap<Player, Integer>();
    }

    public Player getNextPlayer() {
        if(players == null || players.isEmpty()) {
            throw new IllegalStateException("there must be at least one player");
        }

        return currentPlayer;
    }

    public void addPlayer(Player player) {
        if(player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }

        if(currentPlayer == null && players.isEmpty()) {
            currentPlayer = player;
        }

        players.add(player);
        playerScores.put(player, 0);
    }

    public void addScore(int score) {
        if(score < 0) {
            throw new IllegalArgumentException("score cant be less than zero");
        }

        playerScores.put(currentPlayer, playerScores.get(currentPlayer) + score);

        if(playerScores.get(currentPlayer) >= PropertiesManager.getPointsReqForWin()) {
            // put logic here......
        }
    }
}
