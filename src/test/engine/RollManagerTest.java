package test.engine;

import java.util.LinkedList;
import java.util.List;

import main.engine.DieValue;
import main.engine.DieValueGenerator;
import main.engine.Player;
import main.engine.Roll;
import main.engine.RollManager;
import main.engine.ScoreCalculator;
import main.engine.Turn;
import main.engine.properties.PropertiesManager;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RollManagerTest {

  private final int NUM_DICE_START = PropertiesManager.getNumDice();

  @InjectMocks
  private RollManager manager;

  @Mock
  private DieValueGenerator generator;

  @Mock
  private ScoreCalculator scoreCalc;

  @Mock
  private Player mockPlayer;

  @BeforeMethod
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testTurnInPlayBeforeTurnStarts() {
    assertFalse(manager.turnInPlay());
  }

  @Test
  public void testTurnInPlayAfterTurnStarts() {
    manager.beginTurn(mockPlayer);
    assertTrue(manager.turnInPlay());

    manager.reset();
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testBeginTurnWithNullPlayer() {
    manager.beginTurn(null);
  }

  @Test
  public void testBeginLegitimateTurn() {
    when(generator.getDieValues(anyInt(), anyInt(), anyInt())).thenReturn(getTestDieValueList(null, NUM_DICE_START));

    final Roll roll = manager.beginTurn(mockPlayer);
    assertTrue(manager.turnInPlay());
    assertEquals(roll.getDiceVals().size(), NUM_DICE_START);
    verify(generator).getDieValues(anyInt(), anyInt(), anyInt());

    manager.reset();
  }

  @Test
  public void testCanRollAgainWithNoTurnInPlay() {
    assertFalse(manager.canRollAgain());
  }

  @Test
  public void testCanRollAgainWithNoMoreRollsAvailable() {
    final Turn mockTurn = mock(Turn.class);
    when(mockTurn.canRollAgain(any(ScoreCalculator.class))).thenReturn(false);
    manager.setCurrentTurn(mockTurn);   // this will put turn in play

    assertFalse(manager.canRollAgain());
    verify(mockTurn).canRollAgain(any(ScoreCalculator.class));

    manager.reset();
  }

  @Test
  public void testCanRollAgainWithMoreRollsAvailable() {
    final Turn mockTurn = mock(Turn.class);
    when(mockTurn.canRollAgain(any(ScoreCalculator.class))).thenReturn(true);
    manager.setCurrentTurn(mockTurn);   // this will put turn in play as well

    assertTrue(manager.canRollAgain());
    verify(mockTurn).canRollAgain(any(ScoreCalculator.class));

    manager.reset();
  }

  @Test(expectedExceptions = IllegalStateException.class)
  public void testGetNextRollWhenTurnIsNotInPlay() {
    manager.getNextRoll();
  }

  @Test(expectedExceptions = IllegalStateException.class)
  public void testGetNextRollWhenCantRollAgain() {
    manager.beginTurn(mockPlayer);

    Turn mockTurn = mock(Turn.class);
    when(mockTurn.canRollAgain(any(ScoreCalculator.class))).thenReturn(false);

    manager.getNextRoll();
  }

  @Test
  public void testGetLegitimateNextRoll() {
    Turn mockTurn = mock(Turn.class);
    when(mockTurn.canRollAgain(any(ScoreCalculator.class))).thenReturn(true);
    manager.setCurrentTurn(mockTurn);

    int[] diceValIndicesToKeep = {0, 1, 2};
    //NUM_DICE_START - diceValIndicesToKeep.length
    Roll mockRoll = mock(Roll.class);
    when(mockTurn.getLastRoll()).thenReturn(mockRoll);
    when(mockRoll.getDiceKept()).thenReturn(getTestDieValueList(mock(DieValue.class), 3));

    System.out.println(manager.getNextRoll().equals(mockRoll));
    System.out.println(manager.getNextRoll().getDiceKept());
    assertEquals(manager.getNextRoll().getDiceVals().size(), 3);
    verify(mockTurn).canRollAgain(any(ScoreCalculator.class));
    verify(mockTurn).getLastRoll();
    verify(mockTurn).addRoll(mockRoll);
    verify(mockRoll).getDiceKept();

  }

/*  public Roll getNextRoll() {
    if (!turnInPlay()) {
      throw new IllegalStateException("Cant get next roll until turn is started");
    }

    if (!canRollAgain()) {
      throw new IllegalStateException("no more rolls allowed");
    }

    int size = currentTurn.getLastRoll().getDiceVals().size();
    Roll nextRoll = getRoll(PropertiesManager.getNumDice() - size);

    currentTurn.addRoll(nextRoll);

    return nextRoll;
  }*/


  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testAddRollWithNullRoll() {
    manager.addRoll(null);
  }

  @Test
  public void testAddRollWithLegitimateRoll() {
    final Turn mockTurn = mock(Turn.class);
    manager.setCurrentTurn(mockTurn);

    final Roll mockRoll = mock(Roll.class);
    when(mockTurn.getLastRoll()).thenReturn(mockRoll);

    manager.addRoll(mockRoll);
    assertEquals(manager.getLastRoll(), mockRoll);
    verify(mockTurn).getLastRoll();

    manager.reset();
  }




  private List<DieValue> getTestDieValueList(DieValue dieValue, int numToAdd) {
    List<DieValue> dieValues = new LinkedList<DieValue>();
    for(int i=0; i<numToAdd; i++) {
      dieValues.add(dieValue);
    }

    return dieValues;
  }

}
