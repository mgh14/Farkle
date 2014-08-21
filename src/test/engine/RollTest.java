package test.engine;

import main.engine.DieValue;
import main.engine.Roll;
import main.engine.ScoreCalculator;
import main.engine.properties.PropertiesManager;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class RollTest {

  @Mock
  private ScoreCalculator scoreCalc;

  private final int[] TEST_ROLL_ARRAY = {1, 2, 3, 4, 5, 4};
  private final List<DieValue> TEST_ROLL_LIST = getDiceVals(TEST_ROLL_ARRAY);
  private final int TEST_ROLL_LIST_SCORE = ScoreCalculator.NUM_POINTS_FOR_ONE +
    ScoreCalculator.NUM_POINTS_FOR_FIVE;
  private final int DEFAULT_NUM_DICE = PropertiesManager.DEFAULT_NUM_DICE;

  @BeforeMethod
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

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
    int[] diceVals = new int[DEFAULT_NUM_DICE];
    for(int i=0; i<DEFAULT_NUM_DICE; i++) {
      diceVals[i] = i + 1;
    }
    List<DieValue> legitimateVals = getDiceVals(diceVals);

    List<DieValue> legitimateRoll = new Roll(DEFAULT_NUM_DICE, legitimateVals).getDiceVals();
    assertEquals(legitimateRoll.size(), legitimateVals.size());
    for (int i = 0; i < DEFAULT_NUM_DICE; i++) {
      assertEquals(legitimateRoll.get(i), legitimateVals.get(i));
    }
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testSetDiceKeptWithNullDiceKept() {
    Roll roll = new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST);
    roll.setDiceKept(null, 0, scoreCalc);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testSetDiceKeptWithDiceKeptBiggerThanNumDice() {
    Roll roll = new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST);

    int[] diceKeptVals = new int[DEFAULT_NUM_DICE + 1];
    for(int i=0; i<DEFAULT_NUM_DICE + 1; i++) {
      diceKeptVals[i] = PropertiesManager.getMaxDieValue() - 1;
    }

    roll.setDiceKept(getDiceVals(diceKeptVals), 0, scoreCalc);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testSetDiceKeptWithNegativePointsGained() {
    Roll roll = new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST);
    roll.setDiceKept(TEST_ROLL_LIST, -1, scoreCalc);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testSetDiceKeptWithNullScoreCalc() {
    Roll roll = new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST);
    roll.setDiceKept(TEST_ROLL_LIST, 0, null);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testSetDiceKeptWithUnmatchingDiceKeptScoreAndPointsGained() {
    when(scoreCalc.calculateRollScore(anyList())).thenReturn(TEST_ROLL_LIST_SCORE);

    Roll roll = new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST);
    roll.setDiceKept(TEST_ROLL_LIST, TEST_ROLL_LIST_SCORE - 1, scoreCalc);
    verify(scoreCalc).calculateRollScore(anyList());
  }

  @Test
  public void testSetDiceKeptWithSomeDiceKept() {
    when(scoreCalc.calculateRollScore(anyList())).thenReturn(TEST_ROLL_LIST_SCORE);

    Roll roll = new Roll(DEFAULT_NUM_DICE, getDiceVals(TEST_ROLL_ARRAY));
    roll.setDiceKept(getDiceVals(TEST_ROLL_ARRAY[0], TEST_ROLL_ARRAY[4]),
      TEST_ROLL_LIST_SCORE, scoreCalc);
    verify(scoreCalc).calculateRollScore(anyList());
  }

  @Test
  public void testSetDiceKeptWithAllDiceKept() {
    when(scoreCalc.calculateRollScore(anyList())).thenReturn(TEST_ROLL_LIST_SCORE);

    Roll roll = new Roll(DEFAULT_NUM_DICE, getDiceVals(TEST_ROLL_ARRAY));
    roll.setDiceKept(getDiceVals(TEST_ROLL_ARRAY), TEST_ROLL_LIST_SCORE, scoreCalc);
    verify(scoreCalc).calculateRollScore(anyList());
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

  private List<DieValue> getDiceVals(int ... diceVals) {
    List<DieValue> diceValsList = new LinkedList<DieValue>();
    if(diceVals == null) {
      return diceValsList;
    }

    for (Integer dieVal : diceVals) {
      diceValsList.add(new DieValue(dieVal));
    }

    return diceValsList;
  }

}