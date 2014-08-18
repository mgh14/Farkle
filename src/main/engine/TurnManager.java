package main.engine;

import main.engine.properties.PropertiesManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TurnManager {
  private List<Turn> turns = new LinkedList<Turn>();
  private List<Player> players = new ArrayList<Player>();
  private int currentPlayerIndex;

  private Turn currentTurn;

  private boolean isPlayerTakingTurn = false;

  public TurnManager(Player ... gamePlayers) {
    for(Player player : players) {
      players.add(player);
    }

    setCurrentTurn(null);
    setCurrentPlayerIndex(0);
  }

  public void startTurn() {
    if(isPlayerTakingTurn()) {
      throw new IllegalStateException("Cant start turn; a player is currently taking a turn");
    }

    currentTurn = new Turn(players.get(currentPlayerIndex));
    setIsPlayerTakingTurn(true);
  }

  public void addRollToTurn(Roll roll) {
    if(!isPlayerTakingTurn()) {
      throw new IllegalStateException("Cant add roll; no player is currently taking their turn");
    }

    currentTurn.addRoll(roll);
  }

  public void addRollScoreToTurn(RollScore rollScore) {
    if(!isPlayerTakingTurn()) {
      throw new IllegalStateException("Cant add roll; no player is currently taking their turn");
    }

    currentTurn.addRollScore(rollScore);
  }

  public void endTurn() {
    if(!isPlayerTakingTurn()) {
      throw new IllegalStateException("Cant end turn; no player is currently taking their turn");
    }

    turns.add(currentTurn);

    setCurrentTurn(null);

    isPlayerTakingTurn = false;
  }

  public boolean isPlayerTakingTurn() {
    return isPlayerTakingTurn;
  }

  public int getCurrentPlayerIndex() {
    return currentPlayerIndex;
  }

  public void undoLastCompletedTurn() {
    turns.remove(turns.remove(turns.size() - 1));

    setIsPlayerTakingTurn(false);

    setLastPlayerTakingTurn();
  }

  private void setCurrentTurn(Turn turn) {
    currentTurn = turn;
  }

  private void setNextPlayerTakingTurn() {
    if(isPlayerTakingTurn()) {
      throw new RuntimeException("Current player hasnt finished the turn");
    }

    currentPlayerIndex = (currentPlayerIndex + 1) % PropertiesManager.getNumPlayers();
  }

  private void setLastPlayerTakingTurn() {
    if(isPlayerTakingTurn()) {
      throw new RuntimeException("Current player hasnt finished the turn");
    }

    currentPlayerIndex--;
    if(currentPlayerIndex < 0) {
      currentPlayerIndex += PropertiesManager.getNumPlayers();
    }
  }

  private void setIsPlayerTakingTurn(boolean state) {
    isPlayerTakingTurn = state;
  }

  private void setCurrentPlayerIndex(int playerIndex) {
    if(playerIndex < 0 || playerIndex > PropertiesManager.getNumPlayers() - 1) {
      throw new IllegalArgumentException("playerIndex " + playerIndex + " isnt a valid index");
    }

    currentPlayerIndex = playerIndex;
  }

}
