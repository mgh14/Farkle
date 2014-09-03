package core;

import java.util.LinkedList;
import java.util.List;

import properties.PropertiesManager;
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
import static org.testng.Assert.assertNull;
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
    assertFalse(new RollManager(generator, scoreCalc).turnInPlay());
  }

  @Test
  public void testTurnInPlayAfterTurnStarts() {
    RollManager manager = new RollManager(generator, scoreCalc);
    manager.beginTurn(mockPlayer);
    assertTrue(manager.turnInPlay());
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
    new RollManager(generator, scoreCalc).getNextRoll();
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
    RollManager manager = new RollManager(generator, scoreCalc);
    Turn mockTurn = mock(Turn.class);
    when(mockTurn.canRollAgain(any(ScoreCalculator.class))).thenReturn(true);
    manager.setCurrentTurn(mockTurn);

    int[] diceValIndicesToKeep = {0, 1, 2};
    int numDiceToGetInNextRoll = NUM_DICE_START - diceValIndicesToKeep.length;
    Roll mockRoll = mock(Roll.class);
    when(mockTurn.getLastRoll()).thenReturn(mockRoll);
    List<DieValue> testList = getTestDieValueList(mock(DieValue.class), numDiceToGetInNextRoll);
    when(generator.getDieValues(anyInt(), anyInt(), anyInt())).thenReturn(testList);
    when(mockRoll.getDiceKept()).thenReturn(testList);

    Roll roll = manager.getNextRoll();
    assertEquals(roll.getDiceVals().size(), numDiceToGetInNextRoll);
    verify(mockTurn).canRollAgain(any(ScoreCalculator.class));
    verify(mockTurn).getLastRoll();
    //verify(mockTurn).addRoll(any(Roll.class));
    verify(mockRoll).getDiceKept();

  }

/*  public core.Roll getNextRoll() {
    if (!turnInPlay()) {
      throw new IllegalStateException("Cant get next roll until turn is started");
    }

    if (!canRollAgain()) {
      throw new IllegalStateException("no more rolls allowed");
    }

    int size = currentTurn.getLastRoll().getDiceVals().size();
    core.Roll nextRoll = getRoll(PropertiesManager.getNumDice() - size);

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


  @Test
  public void testUndoLastTurnWhileTurnIsInPlay() {
    //manager.
    //manager.setCurrentTurn(mock(core.Turn.class));
  }

  @Test
  public void test() {

  }

/*  public void undoLastTurn() {
    if (turnInPlay()) {
      throw new IllegalStateException("Cant undo turn while turn is being taken");
    }

    turns.remove(turns.size() - 1);
  }*/

  @Test(expectedExceptions = IllegalStateException.class)
  public void testGetLastRollWithNoTurnInPlay() {
    manager.getLastRoll();
  }

  @Test
  public void testGetLastRollWithTurnInPlayAndLastRollNull() {
    Turn mockTurn = mock(Turn.class);
    when(mockTurn.getLastRoll()).thenReturn(null);
    manager.setCurrentTurn(mockTurn);

    assertNull(manager.getLastRoll());
    verify(mockTurn).getLastRoll();
  }

  @Test
  public void testGetLastRollWithTurnInPlayAndLastRollNotNull() {
    Turn mockTurn = mock(Turn.class);
    Roll mockRoll = mock(Roll.class);

    when(mockTurn.getLastRoll()).thenReturn(mockRoll);
    manager.setCurrentTurn(mockTurn);

    assertEquals(manager.getLastRoll(), mockRoll);
    verify(mockTurn).getLastRoll();
  }

  private List<DieValue> getTestDieValueList(DieValue dieValue, int numToAdd) {
    List<DieValue> dieValues = new LinkedList<DieValue>();
    for(int i=0; i<numToAdd; i++) {
      dieValues.add(dieValue);
    }

    return dieValues;
  }

}
