package core;

import properties.PropertiesManager;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RollTest {

  @Mock
  private ScoreCalculator scoreCalc;

  private final int[] TEST_ROLL_ARRAY = {1, 2, 3, 4, 5, 4};
  private final int[] TEST_ROLL_ARRAY_NO_POINTS = {2, 3, 4, 6, 4, 3};
  private final List<DieValue> TEST_ROLL_LIST = getDiceVals(TEST_ROLL_ARRAY);
  private final int TEST_ROLL_LIST_SCORE = ScoreCalculator.NUM_POINTS_FOR_ONE +
    ScoreCalculator.NUM_POINTS_FOR_FIVE;
  private final int DEFAULT_NUM_DICE = PropertiesManager.DEFAULT_NUM_DICE;
  private final int MIN_SCORE = PropertiesManager.DEFAULT_MIN_SCORE;

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
    roll.setDiceKept(null, MIN_SCORE, scoreCalc);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testSetDiceKeptWithDiceKeptBiggerThanNumDice() {
    Roll roll = new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST);

    int[] diceKeptVals = new int[DEFAULT_NUM_DICE + 1];
    for(int i=0; i<DEFAULT_NUM_DICE + 1; i++) {
      diceKeptVals[i] = PropertiesManager.getMaxDieValue() - 1;
    }

    roll.setDiceKept(getDiceVals(diceKeptVals), MIN_SCORE, scoreCalc);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testSetDiceKeptWithLessThanMinPointsGained() {
    Roll roll = new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST);
    roll.setDiceKept(TEST_ROLL_LIST, MIN_SCORE - 1, scoreCalc);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testSetDiceKeptWithNullScoreCalc() {
    Roll roll = new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST);
    roll.setDiceKept(TEST_ROLL_LIST, MIN_SCORE, null);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testSetDiceKeptWithUnmatchingDiceKeptScoreAndPointsGained() {
    simulateScoreCalcMock(TEST_ROLL_LIST_SCORE);
    Roll roll = new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST);

    roll.setDiceKept(TEST_ROLL_LIST, TEST_ROLL_LIST_SCORE - 1, scoreCalc);
  }

  @Test
  public void testSetDiceKeptWithNoDiceKept() {
    simulateScoreCalcMock(MIN_SCORE);
    Roll roll = new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST);

    // should execute without throwing an exception
    roll.setDiceKept(new LinkedList<DieValue>(), MIN_SCORE, scoreCalc);
    verifyScoreCalcMock();
  }

  @Test
  public void testSetDiceKeptWithSomeDiceKept() {
    simulateScoreCalcMock(TEST_ROLL_LIST_SCORE);
    Roll roll = new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST);

    // should execute without throwing an exception
    roll.setDiceKept(getDiceVals(TEST_ROLL_ARRAY[0], TEST_ROLL_ARRAY[4]),
      TEST_ROLL_LIST_SCORE, scoreCalc);
    verifyScoreCalcMock();
  }

  @Test()
  public void testSetDiceKeptWithSomeDiceGivingPointsAndSomeDiceNoPoints() {
    simulateScoreCalcMock(TEST_ROLL_LIST_SCORE);
    Roll roll = new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST);

    // should execute without throwing an exception
    roll.setDiceKept(getDiceVals(TEST_ROLL_ARRAY[0], TEST_ROLL_ARRAY[1],
        TEST_ROLL_ARRAY[4]),TEST_ROLL_LIST_SCORE, scoreCalc);
    verifyScoreCalcMock();
  }

  @Test
  public void testSetDiceKeptWithAllDiceKept() {
    simulateScoreCalcMock(TEST_ROLL_LIST_SCORE);
    Roll roll = new Roll(DEFAULT_NUM_DICE, getDiceVals(TEST_ROLL_ARRAY));

    // should execute without throwing an exception
    roll.setDiceKept(getDiceVals(TEST_ROLL_ARRAY), TEST_ROLL_LIST_SCORE, scoreCalc);
    verifyScoreCalcMock();
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testCanRollAgainWithNullScoreCalc() {
    Roll roll = new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST);
    roll.canRollAgain(null);
  }

  @Test
  public void testCanRollAgainWithNullDiceKept() {
    Roll roll = new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST);
    assertTrue(roll.canRollAgain(scoreCalc));
  }

  @Test
  public void testCanRollAgainWithEmptyDiceKept() {
    simulateScoreCalcMock(MIN_SCORE);
    Roll roll = new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST);
    roll.setDiceKept(new LinkedList<DieValue>(), MIN_SCORE, scoreCalc);

    assertFalse(roll.canRollAgain(scoreCalc));
    verifyScoreCalcMock();
  }

  @Test
  public void testCanRollAgainWithDiceKeptThatDontGainPoints() {
    simulateScoreCalcMock(MIN_SCORE);
    Roll roll = new Roll(DEFAULT_NUM_DICE, getDiceVals(TEST_ROLL_ARRAY_NO_POINTS));
    roll.setDiceKept(new LinkedList<DieValue>(), MIN_SCORE, scoreCalc);

    assertFalse(roll.canRollAgain(scoreCalc));
    verifyScoreCalcMock();
  }

  @Test
  public void testCanRollAgainWithDiceKeptThatDoGainPoints() {
    simulateScoreCalcMock(TEST_ROLL_LIST_SCORE);
    Roll roll = new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST);
    roll.setDiceKept(getDiceVals(TEST_ROLL_ARRAY[0], TEST_ROLL_ARRAY[4]),
      TEST_ROLL_LIST_SCORE, scoreCalc);

    assertTrue(roll.canRollAgain(scoreCalc));
    verifyScoreCalcMock();
  }

  @Test
  public void testCanRollAgainWithDiceKeptThatDoAndDontGainPoints() {
    simulateScoreCalcMock(TEST_ROLL_LIST_SCORE);
    Roll roll = new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST);
    roll.setDiceKept(getDiceVals(TEST_ROLL_ARRAY[0], TEST_ROLL_ARRAY[1],
      TEST_ROLL_ARRAY[4]), TEST_ROLL_LIST_SCORE, scoreCalc);

    assertTrue(roll.canRollAgain(scoreCalc));
    verifyScoreCalcMock();
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testRollGainsNoPointsWithNullScoreCalc() {
    new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST).rollGainsNoPoints(null);
  }

  @Test
  public void testRollGainsNoPointsWithDiceValsThatGainPoints() {
    simulateScoreCalcMock(TEST_ROLL_LIST_SCORE);
    assertFalse(new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST).rollGainsNoPoints(scoreCalc));
    verifyScoreCalcMock();
  }

  @Test
  public void testRollGainsNoPointsWithDiceValsThatDontGainPoints() {
    simulateScoreCalcMock(MIN_SCORE);
    assertTrue(new Roll(DEFAULT_NUM_DICE,
      getDiceVals(TEST_ROLL_ARRAY_NO_POINTS)).rollGainsNoPoints(scoreCalc));
    verifyScoreCalcMock();
  }

  @Test
  public void testRollGainsNoPointsWithDiceValsThatDoAndDontGainPoints() {
    simulateScoreCalcMock(TEST_ROLL_LIST_SCORE);
    assertFalse(new Roll(DEFAULT_NUM_DICE,
      TEST_ROLL_LIST).rollGainsNoPoints(scoreCalc));
    verifyScoreCalcMock();
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testKeptDiceGainNoPointsWithNullScoreCalc() {
    new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST).keptDiceGainNoPoints(null);
  }

  @Test
  public void testKeptDiceGainNoPointsWithDiceKeptThatGainPoints() {
    simulateScoreCalcMock(TEST_ROLL_LIST_SCORE);
    Roll roll = new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST);
    roll.setDiceKept(TEST_ROLL_LIST, TEST_ROLL_LIST_SCORE, scoreCalc);

    assertFalse(roll.keptDiceGainNoPoints(scoreCalc));
    verifyScoreCalcMock();
  }

  @Test
  public void testKeptDiceGainNoPointsWithDiceKeptThatDontGainPoints() {
    simulateScoreCalcMock(MIN_SCORE);
    Roll roll = new Roll(DEFAULT_NUM_DICE, getDiceVals(TEST_ROLL_ARRAY_NO_POINTS));
    roll.setDiceKept(new LinkedList<DieValue>(), MIN_SCORE, scoreCalc);

    assertTrue(roll.keptDiceGainNoPoints(scoreCalc));
    verifyScoreCalcMock();
  }

  @Test
  public void testKeptDiceGainNoPointsWithDiceKeptThatDoAndDontGainPoints() {
    simulateScoreCalcMock(TEST_ROLL_LIST_SCORE);
    Roll roll = new Roll(DEFAULT_NUM_DICE, TEST_ROLL_LIST);
    roll.setDiceKept(getDiceVals(TEST_ROLL_ARRAY[0], TEST_ROLL_ARRAY[1],
      TEST_ROLL_ARRAY[4]), TEST_ROLL_LIST_SCORE, scoreCalc);

    assertFalse(roll.keptDiceGainNoPoints(scoreCalc));
    verifyScoreCalcMock();
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

  private void simulateScoreCalcMock(int expectedPoints) {
    when(scoreCalc.calculateRollScore(anyListOf(DieValue.class))).thenReturn(expectedPoints);
  }

  private void verifyScoreCalcMock() {
    verify(scoreCalc, atLeastOnce()).calculateRollScore(anyListOf(DieValue.class));
  }

}