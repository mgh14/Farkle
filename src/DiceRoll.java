import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class DiceRoll {
  
	private List<Roll> values;
	private List<Roll> diceKept;
	private int MAX_NUM_DICE;
	
	private final int DEFAULT_MAX_NUM_DICE = 6;
	
	DiceRoll() {
		initializeDiceRole(new Roll[] {});
	}
	
	DiceRoll(Roll[] diceRolls) {
		verifyOnlyNDice(diceRolls);
	}
	
	private void initializeDiceRole(int maxNumDice, Roll[] diceRolls) {
		MAX_NUM_DICE = maxNumDice;
		
		values = new ArrayList<Roll>();
		for(Roll current : diceRolls) {
			values.add(current);
		}
		
		diceKept = new LinkedList<Roll>();
	}
	
	private void initializeDiceRole(Roll[] diceRolls) {
		initializeDiceRole(DEFAULT_MAX_NUM_DICE, diceRolls);
	}
	
	private void verifyOnlyNDice(Roll[] rolls) {
		if(rolls.length > MAX_NUM_DICE) {
			throw new IllegalArgumentException("Only max of " + MAX_NUM_DICE + " allowed");
		}
	}
}
