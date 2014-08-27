package main.engine;

import main.engine.properties.PropertiesManager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PlayerManager {

    List<Player> players;
    int currentPlayerIndex;
    HashMap<Player, Integer> playerScores;

    public PlayerManager() {
        players = new LinkedList<Player>();
        playerScores = new HashMap<Player, Integer>();

      currentPlayerIndex = -1;
    }

    public void startGame() {
      currentPlayerIndex = 0;
    }

    public Player getCurrentPlayer() {
        if(players == null || players.isEmpty()) {
            throw new IllegalStateException("there must be at least one player");
        }

        return players.get(currentPlayerIndex);
    }

  public boolean gameHasStarted() {
    return currentPlayerIndex >= 0;
  }

  public Player getNextPlayer() {
    if(!gameHasStarted()) {
      throw new IllegalStateException("Game hasnt started");
    }

    if(currentPlayerIndex == players.size() - 1) {
      currentPlayerIndex = 0;
    }
    else {
      currentPlayerIndex++;
    }

    return getCurrentPlayer();
  }

    public void addPlayer(Player player) {
        if(player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }

        players.add(player);
        playerScores.put(player, 0);
    }

    public void addScore(int score) {
        if(score < 0) {
            throw new IllegalArgumentException("score cant be less than zero");
        }


        playerScores.put(getCurrentPlayer(), playerScores.get(getCurrentPlayer()) + score);

        if(playerScores.get(getCurrentPlayer()) >= PropertiesManager.getPointsReqForWin()) {
            // put logic here......
        }
    }
}
