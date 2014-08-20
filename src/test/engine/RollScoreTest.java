package test.engine;

import java.util.LinkedList;
import java.util.List;

import main.engine.DieValue;
import main.engine.RollScore;
import main.engine.ScoreCalculator;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class RollScoreTest {

  @Mock
  private ScoreCalculator scoreCalc;

  private final int NUM_POINTS_FOR_TWO_ONES = ScoreCalculator.NUM_POINTS_FOR_ONE * 2;

  @BeforeMethod
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test (expectedExceptions = IllegalArgumentException.class)
  public void testCreateRollScoreWithNullDiceKept() {
    new RollScore(null, 0, scoreCalc);
  }

  @Test (expectedExceptions = IllegalArgumentException.class)
  public void testCreateRollScoreWithNullScoreCalc() {
    new RollScore(getTestDiceKeptList(new int[]{}), 0, null);
  }

  @Test (expectedExceptions = IllegalArgumentException.class)
  public void testCreateRollScoreWithNonmatchingScores() {
    // this List would earn a score NUM_POINTS_FOR_TWO_ONES,
    // but we will pass in zero to the RollScore constructor
    List<DieValue> diceKept = getTestDiceKeptList(new int[]{1, 1, 2});
    when(scoreCalc.calculateRollScore(anyList())).thenReturn(NUM_POINTS_FOR_TWO_ONES);

    new RollScore(diceKept, 0, scoreCalc);
  }

  @Test
  public void testCreateLegitimateRollScore() {
    int pointsExpected = NUM_POINTS_FOR_TWO_ONES;
    List<DieValue> diceKept = getTestDiceKeptList(new int[]{1, 1, 2});
    when(scoreCalc.calculateRollScore(anyList())).thenReturn(pointsExpected);

    RollScore rollScore = new RollScore(diceKept, pointsExpected, scoreCalc);
    assertEquals(rollScore.getDiceKept(), diceKept);
    assertEquals(rollScore.getNumberOfPoints(), pointsExpected);
    verify(scoreCalc).calculateRollScore(anyList());
  }

  private List<DieValue> getTestDiceKeptList(int[] intDiceVals) {
    List<DieValue> diceVals = new LinkedList<DieValue>();
    for(Integer current : intDiceVals) {
      diceVals.add(new DieValue(current));
    }

    return diceVals;
  }
}
