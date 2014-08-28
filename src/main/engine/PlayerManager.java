package main.engine;

import java.util.LinkedList;
import java.util.List;

public class PlayerManager {

  private List<Player> players;
  private int currentPlayerIndex;
  private boolean someoneHasReachedWinningScore;

  private final int STARTING_PLAYER_INDEX = -1;

  public PlayerManager() {
    players = new LinkedList<Player>();
    currentPlayerIndex = STARTING_PLAYER_INDEX;

    setSomeoneHasReachedWinningScore(false);
  }

  public void startGame() {
    setCurrentPlayerIndexToFirstPlayer();
  }

  public Player getCurrentPlayer() {
    if (players == null || players.isEmpty()) {
      throw new IllegalStateException("there must be at least one player");
    }

    return players.get(currentPlayerIndex);
  }

  public boolean gameHasStarted() {
    return currentPlayerIndex > STARTING_PLAYER_INDEX;
  }

  public void addPlayer(Player player) {
    if(gameHasStarted()) {
      throw new IllegalStateException("game has already started");
    }
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }

    players.add(player);
  }

  public Player getNextPlayer() {
    if (!gameHasStarted()) {
      throw new IllegalStateException("Game hasnt started");
    }

    int lastIndex = players.size() - 1;
    if (currentPlayerIndex == lastIndex && someoneHasReachedWinningScore()) {
        return null;
    }
    else if(currentPlayerIndex == lastIndex) {
        setCurrentPlayerIndexToFirstPlayer();
    }
    else {
      currentPlayerIndex++;
    }

    return getCurrentPlayer();
  }

  public void setSomeoneHasReachedWinningScore(boolean value) {
    someoneHasReachedWinningScore = value;
  }

  public boolean someoneHasReachedWinningScore() {
    return someoneHasReachedWinningScore;
  }

  public boolean gameIsFinished() {
    return gameHasStarted() && someoneHasReachedWinningScore() && isCurrentPlayerIndexOnFirstPlayer();
  }

  private int getFirstPlayerIndex() {
    return STARTING_PLAYER_INDEX + 1;
  }

  private void setCurrentPlayerIndexToFirstPlayer() {
    currentPlayerIndex = getFirstPlayerIndex();
  }

  private boolean isCurrentPlayerIndexOnFirstPlayer() {
    return currentPlayerIndex == getFirstPlayerIndex();
  }

}
