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
    turns = new LinkedList<Turn>();
    setCurrentTurn(null);

    generator = dieValueGenerator;
    scoreCalc = scoreCalculator;
  }

  public RollManager() {
    new RollManager(new DieValueGenerator(), new ScoreCalculator());
  }

  private void setCurrentTurn(Turn turn) {
    currentTurn = turn;
  }

  private Roll getRoll(int numDice) {
    return new Roll(PropertiesManager.getNumDice(), generator.getDieValues(PropertiesManager.getMinDieValue(),
      PropertiesManager.getMaxDieValue(), numDice));
  }

  public int keepDieValues(int... dieValIndices) {
    //currentTurn.getRolls().get(1)
  }

  public Roll getNextRoll() {
    if (!canRollAgain()) {
      throw new IllegalStateException("no more rolls allowed");
    }

    //int size = currentRoll.getDiceVals().size();

    //return getRoll();
    //currentTurn.
    return null;
  }

  public boolean canRollAgain() {
    return currentTurn == null || currentTurn.canRollAgain(scoreCalc);
  }

/*    public void addRoll(Roll playerRoll) {
        rolls.add(playerRoll);
    }

*/

  public void undoLastTurn() {
    if (currentTurn != null) {
      throw new IllegalStateException("Cant undo turn while turn is being taken");
    }

    turns.remove(turns.size() - 1);
  }

}
