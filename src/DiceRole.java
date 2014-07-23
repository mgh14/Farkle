import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class DiceRole {
  
	private List<Integer> values;
	private List<Integer> diceKept;
	private int MAX_NUM_DICE;
	
	private final int DEFAULT_MAX_NUM_DICE = 6;
	
	DiceRole() {
		initializeDiceRole(new int[] {});
	}
	
	DiceRole(int[] diceRolls) {
		verifyOnlyNDice(diceRolls);
	}
	
	private void initializeDiceRole(int maxNumDice, int[] diceRolls) {
		MAX_NUM_DICE = maxNumDice;
		
		values = new ArrayList<Integer>();
		for(int current : diceRolls) {
			values.add(current);
		}
		
		diceKept = new LinkedList<Integer>();
	}
	
	private void initializeDiceRole(int[] diceRolls) {
		initializeDiceRole(DEFAULT_MAX_NUM_DICE, diceRolls);
	}
	
	private void verifyOnlyNDice(int[] rolls) {
		if(rolls.length > MAX_NUM_DICE) {
			throw new IllegalArgumentException("Only max of " + MAX_NUM_DICE + " allowed");
		}
	}
}
