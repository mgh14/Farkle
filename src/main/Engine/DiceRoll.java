package main.Engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DiceRoll {
  
	private List<Roll> values;
	private List<Roll> diceKept;
	private int MAX_NUM_DICE;
	
	private final int DEFAULT_MAX_NUM_DICE = 6;
	
	DiceRoll() {
		initializeDiceRoll(new ArrayList<Roll>());
	}
	
	DiceRoll(Roll[] diceRolls) {
		verifyOnlyNDice(diceRolls);
	}

    public void setNumDice(int numDice) {
        if(numDice < 1) {
            throw new IllegalArgumentException("Number of dice must be at least one");
        }

        MAX_NUM_DICE = numDice;
    }
	
	private void initializeDiceRole(int maxNumDice, List<Roll> diceRolls) {
		MAX_NUM_DICE = maxNumDice;
		values = diceRolls;
		diceKept = new LinkedList<Roll>();
	}
	
	private void initializeDiceRoll(List<Roll> diceRolls) {
		initializeDiceRole(DEFAULT_MAX_NUM_DICE, diceRolls);
	}
	
	private void verifyOnlyNDice(Roll[] rolls) {
		if(rolls.length > MAX_NUM_DICE) {
			throw new IllegalArgumentException("Only max of " + MAX_NUM_DICE + " allowed");
		}
	}
}
