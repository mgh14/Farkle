package main.engine;

import java.util.LinkedList;
import java.util.List;

public class PlayerManager {

  private List<Player> players;
  private int currentPlayerIndex;
  private boolean someoneHasReachedWinningScore;
  private boolean finalTurnCompleteForAllPlayers;

  private final int STARTING_PLAYER_INDEX = -1;

  public PlayerManager() {
    players = new LinkedList<Player>();
    currentPlayerIndex = STARTING_PLAYER_INDEX;

    setSomeoneHasReachedWinningScore(false);
    setFinalTurnCompleteForAllPlayers(false);
  }

  public void startGame() {
    setCurrentPlayerIndexToFirstPlayer();
  }

  public Player getCurrentPlayer() {
    if (players == null || players.isEmpty() || !gameHasStarted()) {
      throw new IllegalStateException("there must be at least one player");
    }

    return players.get(currentPlayerIndex);
  }

  public boolean gameHasStarted() {
    return currentPlayerIndex > STARTING_PLAYER_INDEX || gameIsFinished();
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

  public int getNumPlayers() {
    if(players == null) {
      throw new IllegalStateException("players havent been initialized yet");
    }

    return players.size();
  }

  public Player getNextPlayer() {
    if (!gameHasStarted()) {
      throw new IllegalStateException("Game hasnt started");
    }

    int lastIndex = players.size() - 1;
    if (currentPlayerIndex == lastIndex && someoneHasReachedWinningScore()) {
      setFinalTurnCompleteForAllPlayers(true);
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

  public void finishLastTurnAndEndGame() {
    // this method will attempt to get the next player and thus
    // find itself inside setFinalTurnCompleteForAllPlayers(),
    // where it will set that flag to true and the game will
    // now evaluate to finished
    getNextPlayer();
  }

  public void setSomeoneHasReachedWinningScore(boolean value) {
    someoneHasReachedWinningScore = value;
  }

  public boolean someoneHasReachedWinningScore() {
    return someoneHasReachedWinningScore;
  }

  public boolean gameIsFinished() {
    return someoneHasReachedWinningScore() && getFinalTurnCompleteForAllPlayers();
  }

  private int getFirstPlayerIndex() {
    return STARTING_PLAYER_INDEX + 1;
  }

  private void setCurrentPlayerIndexToFirstPlayer() {
    currentPlayerIndex = getFirstPlayerIndex();
  }

  private boolean getFinalTurnCompleteForAllPlayers() {
    return finalTurnCompleteForAllPlayers;
  }

  private void setFinalTurnCompleteForAllPlayers(boolean value) {
    finalTurnCompleteForAllPlayers = value;
  }

}
