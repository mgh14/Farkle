package test.engine;

import main.engine.DieValue;
import main.engine.Roll;
import main.engine.ScoreCalculator;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class ScoreCalculatorTest {

  private ScoreCalculator scoreCalc = new ScoreCalculator();

  @Mock
  private Roll roll;

  @BeforeMethod
  public void setUp() {
      MockitoAnnotations.initMocks(this);
  }

  @Test
  public void checkNoPoints() {
    testDiceRoll(new int[]{2, 3, 4, 6, 2, 3}, 0);
  }

  @Test
  public void checkOneOne() {
    testDiceRoll(new int[]{1, 3, 4, 6, 2, 3}, ScoreCalculator.NUM_POINTS_FOR_ONE);
  }

  @Test
  public void checkOneFive() {
    testDiceRoll(new int[]{2, 3, 4, 5, 6, 6}, ScoreCalculator.NUM_POINTS_FOR_FIVE);
  }

  @Test
  public void checkTwoOnes() {
    testDiceRoll(new int[]{1, 1, 2, 3, 4, 6}, ScoreCalculator.NUM_POINTS_FOR_ONE * 2);
  }

  @Test
  public void checkOneOneAndOneFive() {
    testDiceRoll(new int[]{1, 5, 2, 2, 4, 6}, ScoreCalculator.NUM_POINTS_FOR_ONE  + ScoreCalculator.NUM_POINTS_FOR_FIVE);
  }

  @Test
  public void checkTwoFives() {
    testDiceRoll(new int[]{5, 2, 2, 3, 5, 6}, ScoreCalculator.NUM_POINTS_FOR_FIVE * 2);
  }

  @Test
  public void checkTwoOnesAndOneFive() {
    testDiceRoll(new int[]{1, 1, 2, 3, 5, 6}, ScoreCalculator.NUM_POINTS_FOR_ONE * 2 + ScoreCalculator.NUM_POINTS_FOR_FIVE);
  }

  @Test
  public void checkTwoOnesAndTwoFives() {
    testDiceRoll(new int[]{1, 1, 5, 5, 4, 6}, ScoreCalculator.NUM_POINTS_FOR_ONE * 2 + ScoreCalculator.NUM_POINTS_FOR_FIVE * 2);
  }

  @Test
  public void checkThreeOnes() {
    testDiceRoll(new int[]{1, 1, 1, 3, 4, 6}, ScoreCalculator.NUM_POINTS_FOR_ONE * 3);
  }

  @Test
  public void checkThreeOnesAndAFive() {
    testDiceRoll(new int[]{1, 1, 1, 3, 5, 6}, ScoreCalculator.NUM_POINTS_FOR_ONE * 3 + ScoreCalculator.NUM_POINTS_FOR_FIVE);
  }

  @Test
  public void checkFourOnes() {
    testDiceRoll(new int[]{1, 1, 1, 1, 4, 6}, ScoreCalculator.FACTOR_POINTS_FOR_FOUR_OR_MORE_OF_SAME);
  }

  @Test
  public void checkFiveOnes() {
    testDiceRoll(new int[]{1, 1, 1, 1, 1, 6}, ScoreCalculator.FACTOR_POINTS_FOR_FOUR_OR_MORE_OF_SAME * 2);
  }

  @Test
  public void checkSixOnes() {
    testDiceRoll(new int[]{1, 1, 1, 1, 1, 1}, ScoreCalculator.FACTOR_POINTS_FOR_FOUR_OR_MORE_OF_SAME * 3);
  }

  @Test
  public void checkThreeTwos() {
    testDiceRoll(new int[]{2, 2, 2, 3, 4, 6}, ScoreCalculator.FACTOR_POINTS_FOR_THREE_OF_SAME * 2);
  }

  @Test
  public void checkFourTwos() {
    testDiceRoll(new int[]{2, 2, 2, 2, 4, 6}, ScoreCalculator.FACTOR_POINTS_FOR_FOUR_OR_MORE_OF_SAME);
  }

  @Test
  public void checkFiveTwos() {
    testDiceRoll(new int[]{2, 2, 2, 2, 2, 6}, ScoreCalculator.FACTOR_POINTS_FOR_FOUR_OR_MORE_OF_SAME * 2);
  }

  @Test
  public void checkSixTwos() {
    testDiceRoll(new int[]{2, 2, 2, 2, 2, 2}, ScoreCalculator.FACTOR_POINTS_FOR_FOUR_OR_MORE_OF_SAME * 3);
  }

  @Test
  public void checkThreeThrees() {
    testDiceRoll(new int[]{3, 2, 2, 3, 3, 6}, ScoreCalculator.FACTOR_POINTS_FOR_THREE_OF_SAME * 3);
  }

  @Test
  public void checkFourThrees() {
    testDiceRoll(new int[]{3, 3, 3, 3, 4, 6}, ScoreCalculator.FACTOR_POINTS_FOR_FOUR_OR_MORE_OF_SAME);
  }

  @Test
  public void checkFiveThrees() {
    testDiceRoll(new int[]{3, 3, 3, 3, 3, 6}, ScoreCalculator.FACTOR_POINTS_FOR_FOUR_OR_MORE_OF_SAME * 2);
  }

  @Test
  public void checkSixThrees() {
    testDiceRoll(new int[]{3, 3, 3, 3, 3, 3}, ScoreCalculator.FACTOR_POINTS_FOR_FOUR_OR_MORE_OF_SAME * 3);
  }

  @Test
  public void checkThreeFours() {
    testDiceRoll(new int[]{3, 3, 4, 4, 4, 6}, ScoreCalculator.FACTOR_POINTS_FOR_THREE_OF_SAME * 4);
  }

  @Test
  public void checkFourFours() {
    testDiceRoll(new int[]{3, 4, 4, 4, 4, 6}, ScoreCalculator.FACTOR_POINTS_FOR_FOUR_OR_MORE_OF_SAME);
  }

  @Test
  public void checkFiveFours() {
    testDiceRoll(new int[]{4, 4, 4, 4, 4, 6}, ScoreCalculator.FACTOR_POINTS_FOR_FOUR_OR_MORE_OF_SAME * 2);
  }

  @Test
  public void checkSixFours() {
    testDiceRoll(new int[]{4, 4, 4, 4, 4, 4}, ScoreCalculator.FACTOR_POINTS_FOR_FOUR_OR_MORE_OF_SAME * 3);
  }

  @Test
  public void checkThreeFives() {
    testDiceRoll(new int[]{3, 3, 5, 5, 5, 6}, ScoreCalculator.FACTOR_POINTS_FOR_THREE_OF_SAME * 5);
  }

  @Test
  public void checkFourFives() {
    testDiceRoll(new int[]{4, 5, 5, 5, 5, 6}, ScoreCalculator.FACTOR_POINTS_FOR_FOUR_OR_MORE_OF_SAME);
  }

  @Test
  public void checkFiveFives() {
    testDiceRoll(new int[]{5, 5, 5, 5, 5, 6}, ScoreCalculator.FACTOR_POINTS_FOR_FOUR_OR_MORE_OF_SAME * 2);
  }

  @Test
  public void checkSixFives() {
    testDiceRoll(new int[]{5, 5, 5, 5, 5, 5}, ScoreCalculator.FACTOR_POINTS_FOR_FOUR_OR_MORE_OF_SAME * 3);
  }

  @Test
  public void checkThreeSixes() {
    testDiceRoll(new int[]{3, 3, 2, 6, 6, 6}, ScoreCalculator.FACTOR_POINTS_FOR_THREE_OF_SAME * 6);
  }

  @Test
  public void checkFourSixes() {
    testDiceRoll(new int[]{6, 2, 6, 4, 6, 6}, ScoreCalculator.FACTOR_POINTS_FOR_FOUR_OR_MORE_OF_SAME);
  }

  @Test
  public void checkFiveSixes() {
    testDiceRoll(new int[]{2, 6, 6, 6, 6, 6}, ScoreCalculator.FACTOR_POINTS_FOR_FOUR_OR_MORE_OF_SAME * 2);
  }

  @Test
  public void checkSixSixes() {
    testDiceRoll(new int[]{6, 6, 6, 6, 6, 6}, ScoreCalculator.FACTOR_POINTS_FOR_FOUR_OR_MORE_OF_SAME * 3);
  }

  @Test
  public void checkStraightInOrder() {
    testDiceRoll(new int[]{1, 2, 3, 4, 5, 6}, ScoreCalculator.NUM_POINTS_FOR_STRAIGHT);
  }

  @Test
  public void checkStraightNotInOrder() {
    testDiceRoll(new int[]{4, 6, 3, 1, 2, 5}, ScoreCalculator.NUM_POINTS_FOR_STRAIGHT);
  }

  @Test
  public void checkThreePairs() {
    testDiceRoll(new int[]{1, 2, 3, 1, 2, 3}, ScoreCalculator.NUM_POINTS_FOR_THREE_SETS_OF_TWO);
  }

  @Test
  public void checkFourOfAKindPlusAPairNotOfOnesOrFives() {
    testDiceRoll(new int[]{4, 4, 4, 4, 6, 6}, ScoreCalculator.NUM_POINTS_FOR_ONE_SET_OF_FOUR_AND_ONE_SET_OF_TWO);
  }

  @Test
  public void checkFourOfAKindPlusAPairOfOnes() {
    testDiceRoll(new int[]{4, 4, 4, 4, 1, 1}, ScoreCalculator.NUM_POINTS_FOR_ONE_SET_OF_FOUR_AND_ONE_SET_OF_TWO);
  }

  @Test
  public void checkTwoSetsOfThree() {
    testDiceRoll(new int[]{4, 4, 4, 6, 6, 6}, ScoreCalculator.NUM_POINTS_FOR_TWO_SETS_OF_THREE);
  }

  @Test
  public void checkTwoSetsOfThreeWithFives() {
    testDiceRoll(new int[]{4, 4, 4, 5, 5, 5}, ScoreCalculator.NUM_POINTS_FOR_TWO_SETS_OF_THREE);
  }

  private void testDiceRoll(int[] dieValsArray, int expectedPoints) {
    List<DieValue> dieVals = new LinkedList<DieValue>();
    for(int val : dieValsArray) {
      dieVals.add(new DieValue(val));
    }

    when(roll.getDiceVals()).thenReturn(dieVals);

    assertEquals(scoreCalc.calculateRollScore(roll), expectedPoints);
    verify(roll).getDiceVals();
  }


}