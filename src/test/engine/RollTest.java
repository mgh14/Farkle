package test.engine;

import main.engine.DieValue;
import main.engine.Roll;
import main.engine.properties.PropertiesManager;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class RollTest {

  private final int DEFAULT_NUM_DICE = PropertiesManager.DEFAULT_NUM_DICE;

  @Test
  public void verifyDefaultDiceNumberInNoParamRollConstructor() {
    Roll roll = new Roll();
    assertEquals(roll.getDiceVals().size(), 0);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void verifyZeroNumDiceCausesException() {
    new Roll(0, new LinkedList<DieValue>());
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void verifyInvalidNumDiceCausesException() {
    new Roll(-1, new LinkedList<DieValue>());
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void verifyTooManyDieValuesCausesException() {
    List<DieValue> tooManyVals = new LinkedList<DieValue>();
    for(int i=0; i<=DEFAULT_NUM_DICE; i++) {
      tooManyVals.add(new DieValue());
    }

    new Roll(DEFAULT_NUM_DICE, tooManyVals);
  }

  @Test
  public void verifyValidDieValuesSize() {
    List<DieValue> legitimateVals = new LinkedList<DieValue>();
    for(int i=0; i<DEFAULT_NUM_DICE; i++) {
      legitimateVals.add(new DieValue());
    }

    List<DieValue> legitimateRoll = new Roll(DEFAULT_NUM_DICE, legitimateVals).getDiceVals();
    assertEquals(legitimateRoll.size(), legitimateVals.size());
    for(int i=0; i<DEFAULT_NUM_DICE; i++) {
      assertEquals(legitimateRoll.get(i), legitimateVals.get(i));
    }
  }

  /*	Roll() {
		initializeDiceRoll(new ArrayList<DieValue>());
	}

	Roll(int numDice, DieValue[] diceRolls) {
		initializeDiceRole(numDice, Arrays.asList(diceRolls));
	}*/
}
