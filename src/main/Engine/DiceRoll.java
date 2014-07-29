package main.Engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class DiceRoll {
  
	private Collection<Roll> values;
	private Collection<Roll> diceKept;
    private Collection<ScoreOption> scores;
	private int MAX_NUM_DICE;
	
	private final int DEFAULT_MAX_NUM_DICE = 6;
	
	DiceRoll() {
		initializeDiceRoll(new ArrayList<Roll>());
	}
	
	DiceRoll(Roll[] diceRolls) {
		verifyOnlyNDice(diceRolls);
	}

    private void setNumDice(int numDice) {
        if(numDice < 1) {
            throw new IllegalArgumentException("Number of dice must be at least one");
        }

        MAX_NUM_DICE = numDice;
    }

    public Collection<Roll> getRolls() {
        List<Roll> valsCopy = new LinkedList<Roll>();
        valsCopy.addAll(values);

        return valsCopy;
    }

	private void initializeDiceRole(int maxNumDice, Collection<Roll> diceRolls) {
		setNumDice(maxNumDice);

        values = (diceRolls != null) ? diceRolls : new ArrayList<Roll>();
		diceKept = new LinkedList<Roll>();
	}
	
	private void initializeDiceRoll(Collection<Roll> diceRolls) {
		initializeDiceRole(DEFAULT_MAX_NUM_DICE, diceRolls);
	}
	
	private void verifyOnlyNDice(Roll[] rolls) {
		if(rolls.length > MAX_NUM_DICE) {
			throw new IllegalArgumentException("Only max of " + MAX_NUM_DICE + " allowed");
		}
	}

    public Collection<ScoreOption> calculatePossibleScoresFromRoll() {
        return calculatePossibleScoresFromRoll(values);
    }

    public Collection<ScoreOption> calculatePossibleScoresFromRoll(Collection<Roll> rollVals) {
        return null;
    }

}
