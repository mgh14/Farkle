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
    for (int i = 0; i <= DEFAULT_NUM_DICE; i++) {
      tooManyVals.add(new DieValue());
    }

    new Roll(DEFAULT_NUM_DICE, tooManyVals);
  }

  @Test
  public void verifyValidDieValuesSize() {
    List<DieValue> legitimateVals = new LinkedList<DieValue>();
    for (int i = 0; i < DEFAULT_NUM_DICE; i++) {
      legitimateVals.add(new DieValue());
    }

    List<DieValue> legitimateRoll = new Roll(DEFAULT_NUM_DICE, legitimateVals).getDiceVals();
    assertEquals(legitimateRoll.size(), legitimateVals.size());
    for (int i = 0; i < DEFAULT_NUM_DICE; i++) {
      assertEquals(legitimateRoll.get(i), legitimateVals.get(i));
    }
  }

  @Test
  public void testSetDiceKeptWithNullDiceKept() {

  }

  @Test
  public void testSetDiceKeptWithDiceKeptBiggerThanNumDice() {

  }

  @Test
  public void testSetDiceKeptWithNegativePointsGained() {

  }

  @Test
  public void testSetDiceKeptWithNullScoreCalc() {

  }

  @Test
  public void testSetDiceKeptWithUnmatchingDiceKeptScoreAndPointsGained() {

  }

  @Test
  public void testCanRollAgainWithNullScoreCalc() {

  }

  @Test
  public void testCanRollAgainWithNullDiceKept() {

  }

  @Test
  public void testCanRollAgainWithEmptyDiceKept() {

  }

  @Test
  public void testCanRollAgainWithDiceKeptThatDontScore() {

  }

  @Test
  public void testCanRollAgainWithDiceKeptThatDoScore() {

  }

  @Test
  public void testRollGainsNoPointsWithNullScoreCalc() {

  }

  @Test
  public void testRollGainsWithDiceValsThatGainPoints() {

  }

  @Test
  public void testRollGainsWithDiceValsThatDontGainPoints() {

  }


  @Test
  public void testKeptDiceGainNoPointsWithNullScoreCalc() {

  }

  @Test
  public void testKeptDiceGainNoPointsWithDiceKeptThatGainPoints() {

  }

  @Test
  public void testKeptDiceGainNoPointsWithDiceKeptThatDontGainPoints() {

  }

}