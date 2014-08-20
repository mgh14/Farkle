package test.engine;

import main.engine.Player;
import main.engine.Roll;
import main.engine.ScoreCalculator;
import main.engine.Turn;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TurnTest {

  @Mock
  private Player testPlayer;

  @Mock
  private Roll testRollOne;

  @Mock
  private Roll testRollTwo;

  @Mock
  private ScoreCalculator testScoreCalc;

  @BeforeMethod
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test (expectedExceptions = IllegalArgumentException.class)
  public void testCreateTurnWithNullPlayer() {
    new Turn(null);
  }

  @Test
  public void testCreateTurnWithLegitimatePlayer() {
    Turn turn = getTestTurn();
    assertEquals(turn.getTurnPlayer(), testPlayer);
  }

  @Test (expectedExceptions = IllegalArgumentException.class)
  public void addNullRollToTurn() {
    getTestTurn().addRoll(null);
  }

  @Test
  public void testGetNumRollsWithNoRollsAdded() {
    assertEquals(getTestTurn().getNumRolls(), 0);
  }

  @Test
  public void testGetNumRollsWithNRollsAdded() {
    Turn turn = getTestTurn();
    int numRolls = 3;
    for(int i=0; i<numRolls; i++) {
      turn.addRoll(testRollOne);
    }

    assertEquals(turn.getNumRolls(), numRolls);
  }

  @Test
  public void testTotalScoreForEmptyTurn() {
    assertEquals(getTestTurn().getTotalScoreForTurn(), 0);
  }

  @Test
  public void testTotalScoreForOneRoll() {
    int score = ScoreCalculator.NUM_POINTS_FOR_STRAIGHT;
    when(testRollOne.getScoreForRoll()).thenReturn(score);
    Turn turn = getTestTurn();
    turn.addRoll(testRollOne);

    assertEquals(turn.getTotalScoreForTurn(), score);
    verify(testRollOne).getScoreForRoll();
  }

  @Test
  public void testTotalScoreForNOfSameRolls() {
    int score = ScoreCalculator.NUM_POINTS_FOR_FIVE;
    when(testRollOne.getScoreForRoll()).thenReturn(score);
    Turn turn = getTestTurn();

    int numRolls = 3;
    for(int i=0; i<numRolls; i++) {
      turn.addRoll(testRollOne);
    }

    assertEquals(turn.getTotalScoreForTurn(), score * numRolls);
    verify(testRollOne, atLeast(3)).getScoreForRoll();
  }

  @Test
  public void testTotalScoreForDifferentRolls() {
    Roll testRollTwo = mock(Roll.class);
    when(testRollOne.getScoreForRoll()).thenReturn(ScoreCalculator.NUM_POINTS_FOR_ONE);
    when(testRollTwo.getScoreForRoll()).thenReturn(ScoreCalculator.NUM_POINTS_FOR_ONE_SET_OF_FOUR_AND_ONE_SET_OF_TWO);
    Turn turn = getTestTurn();

    turn.addRoll(testRollOne);
    turn.addRoll(testRollTwo);

    assertEquals(turn.getTotalScoreForTurn(),
      ScoreCalculator.NUM_POINTS_FOR_ONE + ScoreCalculator.NUM_POINTS_FOR_ONE_SET_OF_FOUR_AND_ONE_SET_OF_TWO);
    verify(testRollOne).getScoreForRoll();
    verify(testRollTwo).getScoreForRoll();
  }

  @Test
  public void testCanRollAgainWithEmptyRolls() {
    assertFalse(getTestTurn().canRollAgain(testScoreCalc));
  }

  @Test
  public void testCanRollAgainWithRollCantRollAgain() {
    when(testRollOne.canRollAgain(any(ScoreCalculator.class))).thenReturn(false);
    Turn turn = getTestTurn();
    turn.addRoll(testRollOne);

    assertFalse(turn.canRollAgain(testScoreCalc));
    verify(testRollOne).canRollAgain(any(ScoreCalculator.class));
  }

  @Test
  public void testCanRollAgainWithRollCanRollAgain() {
    when(testRollOne.canRollAgain(any(ScoreCalculator.class))).thenReturn(true);
    Turn turn = getTestTurn();
    turn.addRoll(testRollOne);

    assertTrue(turn.canRollAgain(testScoreCalc));
    verify(testRollOne).canRollAgain(any(ScoreCalculator.class));
  }

  @Test
  public void testCanRollAgainWithNRollsAndLastRollCantRollAgain() {
    when(testRollOne.canRollAgain(any(ScoreCalculator.class))).thenReturn(true);
    when(testRollTwo.canRollAgain(any(ScoreCalculator.class))).thenReturn(false);
    Turn turn = getTestTurn();
    turn.addRoll(testRollOne);
    turn.addRoll(testRollTwo);

    assertFalse(turn.canRollAgain(testScoreCalc));
    verify(testRollTwo).canRollAgain(any(ScoreCalculator.class));
  }

  private Turn getTestTurn() {
    return new Turn(testPlayer);
  }
}
