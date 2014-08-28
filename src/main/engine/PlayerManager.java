package main.engine;

import main.engine.properties.PropertiesManager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PlayerManager {

  private List<Player> players;
  private int currentPlayerIndex;
  private HashMap<Player, Integer> playerScores;

  private final int STARTING_PLAYER_INDEX = -1;

  public PlayerManager() {
    players = new LinkedList<Player>();
    playerScores = new HashMap<Player, Integer>();

    currentPlayerIndex = STARTING_PLAYER_INDEX;
  }

  public void startGame() {
    currentPlayerIndex = 0;
  }

  public Player getCurrentPlayer() {
    if (players == null || players.isEmpty()) {
      throw new IllegalStateException("there must be at least one player");
    }

    return players.get(currentPlayerIndex);
  }

  private boolean scoreQualifiesForWin(int score) {
    return score >= PropertiesManager.getPointsReqForWin();
  }

  public boolean gameHasStarted() {
    return currentPlayerIndex > STARTING_PLAYER_INDEX;
  }

  public boolean someoneHasReachedWinningScore() {
    for (Player player : playerScores.keySet()) {
      if (scoreQualifiesForWin(playerScores.get(player))) {
        return true;
      }
    }

    return false;
  }

  public Player getNextPlayer() {
    if (!gameHasStarted()) {
      throw new IllegalStateException("Game hasnt started");
    }

    if (currentPlayerIndex == players.size() - 1) {
      if (someoneHasReachedWinningScore()) {
        return null;
      }
      else {
        currentPlayerIndex = STARTING_PLAYER_INDEX + 1;
      }
    }
    else {
      currentPlayerIndex++;
    }

    return getCurrentPlayer();
  }

  public void addPlayer(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }

    players.add(player);
    playerScores.put(player, 0);
  }

  public void addScore(int score) {
    if (score < PropertiesManager.getMinScore()) {
      throw new IllegalArgumentException("score cant be less than minimum score");
    }

    playerScores.put(getCurrentPlayer(), playerScores.get(getCurrentPlayer()) + score);
  }
}
