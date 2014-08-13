package main.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Roll {
  
	private Collection<DieValue> diceVals;
	private int MAX_NUM_DICE;
	
	private final int DEFAULT_MAX_NUM_DICE = 6;
	
	Roll() {
		initializeDiceRoll(new ArrayList<DieValue>());
	}
	
	Roll(DieValue[] diceRolls) {
		verifyOnlyNDice(diceRolls);
	}

    private void setNumDice(int numDice) {
        if(numDice < 1) {
            throw new IllegalArgumentException("Number of dice must be at least one");
        }

        MAX_NUM_DICE = numDice;
    }

    public Collection<DieValue> getDiceVals() {
        List<DieValue> valsCopy = new LinkedList<DieValue>();
        valsCopy.addAll(diceVals);

        return valsCopy;
    }

	private void initializeDiceRole(int maxNumDice, Collection<DieValue> diceValues) {
		setNumDice(maxNumDice);

        diceVals = (diceValues != null) ? diceValues : new ArrayList<DieValue>();
		diceKept = new LinkedList<DieValue>();
	}
	
	private void initializeDiceRoll(Collection<DieValue> diceRolls) {
		initializeDiceRole(DEFAULT_MAX_NUM_DICE, diceRolls);
	}
	
	private void verifyOnlyNDice(DieValue[] rolls) {
		if(rolls.length > MAX_NUM_DICE) {
			throw new IllegalArgumentException("Only max of " + MAX_NUM_DICE + " allowed");
		}
	}

    public Collection<DiceScore> calculatePossibleScoresFromRoll() {
        return calculatePossibleScoresFromRoll(diceVals);
    }

    public Collection<DiceScore> calculatePossibleScoresFromRoll(Collection<DieValue> rollVals) {
        return null;
    }

}
