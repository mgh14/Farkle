package main.engine;

import main.engine.properties.PropertiesManager;

import java.util.*;

public class Roll {
  
	private List<DieValue> diceVals;
	private int MAX_NUM_DICE;
	
	public Roll() {
		initializeDiceRoll(new ArrayList<DieValue>());
	}
	
	public Roll(int numDice, List<DieValue> diceVals) {
		initializeDiceRole(numDice, diceVals);
	}

  private void setNumDice(int numDice) {
    if(numDice < PropertiesManager.DEFAULT_MIN_NUM_DICE) {
        throw new IllegalArgumentException("Number of dice must be at least one");
    }

    MAX_NUM_DICE = numDice;
  }

  public List<DieValue> getDiceVals() {
      List<DieValue> valsCopy = new LinkedList<DieValue>();
      valsCopy.addAll(diceVals);

      return valsCopy;
  }

	private void initializeDiceRole(int maxNumDice, List<DieValue> diceValues) {
		setNumDice(maxNumDice);

    diceVals = (diceValues != null) ? diceValues : new ArrayList<DieValue>();
    verifyOnlyNDice(diceVals);
	}
	
	private void initializeDiceRoll(List<DieValue> diceRolls) {
		initializeDiceRole(PropertiesManager.DEFAULT_NUM_DICE, diceRolls);
	}
	
	private void verifyOnlyNDice(List<DieValue> rolls) {
		if(rolls.size() > MAX_NUM_DICE) {
			throw new IllegalArgumentException("Only max of " + MAX_NUM_DICE + " allowed");
		}
	}

}
