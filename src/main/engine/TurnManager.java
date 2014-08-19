package main.engine;

import main.engine.properties.PropertiesManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TurnManager {

  private List<Turn> turns = new LinkedList<Turn>();
  private List<Player> players = new ArrayList<Player>();
  private int currentPlayerIndex;

  private boolean isPlayerTakingTurn = false;

  public TurnManager(Player ... gamePlayers) {
    if(gamePlayers == null || gamePlayers.length == 0) {
      throw new IllegalArgumentException("must be at least one player");
    }

    for(Player player : players) {
      players.add(player);
    }

    setCurrentPlayerIndex(0);
  }

  public void startTurn() {
    if(isPlayerTakingTurn()) {
      throw new IllegalStateException("Cant start turn; a player is currently taking a turn");
    }

    setIsPlayerTakingTurn(true);
  }

  public void endTurn(Turn finishedTurn) {
    if(!isPlayerTakingTurn()) {
      throw new IllegalStateException("Cant end turn; no player is currently taking their turn");
    }
    if(finishedTurn == null) {
        throw new IllegalArgumentException("finished turn cant be null");
    }

    turns.add(finishedTurn);

    setNextPlayerTakingTurn();
    setIsPlayerTakingTurn(false);
  }

  public boolean isPlayerTakingTurn() {
    return isPlayerTakingTurn;
  }

  public Player getCurrentPlayer() {
    return players.get(currentPlayerIndex);
  }

  public void undoLastCompletedTurn() {
    turns.remove(turns.remove(turns.size() - 1));

    setIsPlayerTakingTurn(false);

    setLastPlayerTakingTurn();
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
