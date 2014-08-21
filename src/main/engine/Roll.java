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

  public List<DieValue> getDiceKept() {
    return (rollScore != null) ? rollScore.getDiceKept() : null;
  }

  public void setDiceKept(List<DieValue> diceKept, int pointsGained, ScoreCalculator scoreCalc) {
    if(diceKept == null || diceKept.size() > diceVals.size()) {
      throw new IllegalArgumentException("diceKept must be same size as dice vals");
    }
    for(DieValue dieKept : diceKept) {
      if(!diceVals.contains(dieKept)) {
        throw new IllegalArgumentException("Die " + dieKept.getDieValue() + " is not in the original roll");
      }
    }

    if(pointsGained < 0) {
      throw new IllegalArgumentException("Points gained cant be less than zero");
    }

    if(scoreCalc == null) {
      throw new IllegalArgumentException("Score calculator cant be null");
    }

    rollScore = new RollScore(diceKept, pointsGained, scoreCalc);
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

    // todo: Check if this portion is necessary
    /*List<DieValue> leftoverDice = new LinkedList<DieValue>();
    for(DieValue current : getDiceVals()) {
      if(!diceKept.contains(current)) {
          leftoverDice.add(current);
      }
    }*/

    return !diceKept.isEmpty() && !rollGainsNoPoints(scoreCalc) && !keptDiceGainNoPoints(scoreCalc);
  }

  public boolean rollGainsNoPoints(ScoreCalculator scoreCalc) {
    if(scoreCalc == null) {
      throw new IllegalArgumentException("ScoreCalculator cant be null");
    }

    return scoreCalc.calculateRollScore(getDiceVals()) == 0;
  }

  public boolean keptDiceGainNoPoints(ScoreCalculator scoreCalc) {
    if(scoreCalc == null) {
      throw new IllegalArgumentException("ScoreCalculator cant be null");
    }

    return scoreCalc.calculateRollScore(getDiceKept()) == 0;
  }

  private void setMaxNumDice(int maxNumDice) {
    if(maxNumDice < PropertiesManager.DEFAULT_MIN_NUM_DICE) {
      throw new IllegalArgumentException("Number of dice must be at least one");
    }

    MAX_NUM_DICE = maxNumDice;
  }

	private void initializeDiceRole(int maxNumDice, List<DieValue> diceVals) {
    rollScore = null;
		setMaxNumDice(maxNumDice);

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
