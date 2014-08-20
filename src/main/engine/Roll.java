package main.engine;

import main.engine.properties.PropertiesManager;

import java.util.*;

public class Roll {
  
	private List<DieValue> diceVals;
  private RollScore rollScore;
	private int MAX_NUM_DICE;
	
	public Roll() {
		initializeDiceRoll(new ArrayList<DieValue>());
	}
	
	public Roll(int numDice, List<DieValue> diceVals) {
		initializeDiceRole(numDice, diceVals);
	}

  public Integer getScoreForRoll() {
    return (rollScore != null) ? (Integer) rollScore.getNumberOfPoints() : null;
  }

  public List<DieValue> getDiceVals() {
    List<DieValue> valsCopy = new LinkedList<DieValue>();
    valsCopy.addAll(diceVals);

    return valsCopy;
  }

  public void setDiceKept(List<DieValue> diceKept, int pointsGained, ScoreCalculator scoreCalc) {
    rollScore = new RollScore(diceKept, pointsGained, scoreCalc);
  }

  public List<DieValue> getDiceKept() {
    return (rollScore != null) ? rollScore.getDiceKept() : null;
  }

  public boolean canRollAgain(ScoreCalculator scoreCalc) {
    if(scoreCalc == null) {
        throw new IllegalArgumentException("Score calculator cant be null");
    }

    // if dice kept hasn't been initialized, the player's turn hasn't
    // started and they can roll again.
    List<DieValue> diceKept = getDiceKept();
    if(diceKept == null) {
        return true;
    }

    List<DieValue> leftoverDice = new LinkedList<DieValue>();
    for(DieValue current : getDiceVals()) {
      if(!diceKept.contains(current)) {
          leftoverDice.add(current);
      }
    }

    return scoreCalc.calculateRollScore(leftoverDice) > 0;
  }


  private void setNumDice(int numDice) {
    if(numDice < PropertiesManager.DEFAULT_MIN_NUM_DICE) {
      throw new IllegalArgumentException("Number of dice must be at least one");
    }

    MAX_NUM_DICE = numDice;
  }

	private void initializeDiceRole(int maxNumDice, List<DieValue> diceVals) {
    rollScore = null;
		setNumDice(maxNumDice);

    this.diceVals = (diceVals != null) ? diceVals : new LinkedList<DieValue>();
    verifyOnlyNDice(this.diceVals);
	}
	
	private void initializeDiceRoll(List<DieValue> diceVals) {
		initializeDiceRole(PropertiesManager.DEFAULT_NUM_DICE, diceVals);
	}
	
	private void verifyOnlyNDice(List<DieValue> diceVals) {
		if(diceVals.size() > MAX_NUM_DICE) {
			throw new IllegalArgumentException("Only max of " + MAX_NUM_DICE + " allowed");
		}
	}

}
