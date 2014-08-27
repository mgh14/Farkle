package main.engine;

import main.engine.properties.PropertiesManager;

import java.util.LinkedList;
import java.util.List;

public class RollManager {

  private List<Turn> turns;
  private Turn currentTurn;

  private DieValueGenerator generator;
  private ScoreCalculator scoreCalc;

  public RollManager(DieValueGenerator dieValueGenerator, ScoreCalculator scoreCalculator) {
    initializeRollManager(dieValueGenerator, scoreCalculator);
  }

  public RollManager() {
    new RollManager(new DieValueGenerator(), new ScoreCalculator());
  }

  public boolean turnInPlay() {
    return currentTurn != null;
  }

  public Roll beginTurn(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player cant be null");
    }

    setCurrentTurn(new Turn(player));

    final Roll firstRoll = getRoll(PropertiesManager.getNumDice());
    currentTurn.addRoll(firstRoll);

    return firstRoll;
  }

  public boolean canRollAgain() {
    return turnInPlay() && currentTurn.canRollAgain(scoreCalc);
  }

  public Roll getNextRoll() {
    if (!turnInPlay()) {
      throw new IllegalStateException("Cant get next roll until turn is started");
    }

    if (!canRollAgain()) {
      throw new IllegalStateException("no more rolls allowed");
    }

    int size = currentTurn.getLastRoll().getDiceVals().size();
    Roll nextRoll = getRoll(PropertiesManager.getNumDice() - size);

    currentTurn.addRoll(nextRoll);

    return nextRoll;
  }

  public void addRoll(Roll playerRoll) {
    if (playerRoll == null) {
      throw new IllegalArgumentException("Roll cant be null");
    }

    currentTurn.addRoll(playerRoll);
  }

  public int keepDieValues(int... dieValIndices) {
    if (currentTurn == null) {
      throw new IllegalStateException("No turn has been initialized; cant keep dice");
    }

    List<DieValue> diceRolled = currentTurn.getLastRoll().getDiceVals();
    List<DieValue> diceToKeep = new LinkedList<DieValue>();
    for (int dieVal : dieValIndices) {
      diceToKeep.add(diceRolled.get(dieVal));
    }

    currentTurn.setDiceKept(diceToKeep, scoreCalc.calculateRollScore(diceToKeep), scoreCalc);

    return currentTurn.getLastRoll().getScoreForRoll();
  }

  public void undoLastTurn() {
    if (turnInPlay()) {
      throw new IllegalStateException("Cant undo turn while turn is being taken");
    }

    turns.remove(turns.size() - 1);
  }

  public Roll getLastRoll() {
    if(!turnInPlay()) {
      throw new IllegalStateException("no turn in play");
    }

    return currentTurn.getLastRoll();
  }

  // used for testing
  public RollManager reset() {
    initializeRollManager(new DieValueGenerator(), new ScoreCalculator());

    return this;
  }

  private void initializeRollManager(DieValueGenerator dieValueGenerator, ScoreCalculator scoreCalculator) {
    turns = new LinkedList<Turn>();
    setCurrentTurn(null);

    generator = dieValueGenerator;
    scoreCalc = scoreCalculator;
  }

  private Roll getRoll(int numDice) {
    return new Roll(PropertiesManager.getNumDice(), generator.getDieValues(PropertiesManager.getMinDieValue(),
      PropertiesManager.getMaxDieValue(), numDice));
  }

  // used only for testing outside this class (should be an internal method)
  public void setCurrentTurn(Turn turn) {
    currentTurn = turn;
  }

}
