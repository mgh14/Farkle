import main.engine.Player;
import main.engine.PlayerManager;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class PlayerManagerTest {

  @Test(expectedExceptions = IllegalStateException.class)
  public void testGetCurrentPlayerWhenPlayersIsEmpty() {
   new PlayerManager().getCurrentPlayer();
  }

  @Test(expectedExceptions = IllegalStateException.class)
  public void testGetCurrentPlayerBeforeGameHasStarted() {
    PlayerManager manager = getNewPlayerManager();
    manager.getCurrentPlayer();
  }

  @Test
  public void testGetCurrentPlayerWhenPlayersIsntEmpty() {
    PlayerManager manager = getNewPlayerManager();
    manager.startGame();

    assertNotNull(manager.getCurrentPlayer());
  }

  @Test
  public void testGetCurrentPlayerFromGameStart() {
    PlayerManager manager = new PlayerManager();
    Player mockPlayerOne = mock(Player.class);
    manager.addPlayer(mockPlayerOne);
    manager.startGame();

    assertEquals(manager.getCurrentPlayer(), mockPlayerOne);
  }

  @Test
  public void testGameHasStartedBeforeItHasStarted() {
    PlayerManager manager = getNewPlayerManager();
    assertFalse(manager.gameHasStarted());
  }

  @Test
  public void testGameHasStartedAfterItHasStarted() {
    PlayerManager manager = getNewPlayerManager();
    manager.startGame();

    assertTrue(manager.gameHasStarted());
  }

  @Test
  public void testGameHasStartedAfterItHasFinished() {
    PlayerManager manager = getNewPlayerManager();
    manager.startGame();
    manager.setSomeoneHasReachedWinningScore(true);
    manager.finishLastTurnAndEndGame();

    assertTrue(manager.gameIsFinished());
    assertTrue(manager.gameHasStarted());
  }

  @Test(expectedExceptions = IllegalStateException.class)
  public void testAddPlayerAfterGameHasStarted() {
    PlayerManager manager = getNewPlayerManager();
    manager.startGame();

    manager.addPlayer(mock(Player.class));
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testAddNullPlayer() {
    getNewPlayerManager().addPlayer(null);
  }

  @Test
  public void testAddLegitimatePlayer() {
    PlayerManager manager = getNewPlayerManager();
    manager.addPlayer(mock(Player.class));

    assertEquals(manager.getNumPlayers(), 2);
  }

  @Test
  public void testGetNumPlayers() {
    PlayerManager manager = getNewPlayerManager();
    Player mockPlayer = mock(Player.class);

    manager.addPlayer(mockPlayer);
    manager.addPlayer(mockPlayer);
    manager.addPlayer(mockPlayer);

    assertEquals(manager.getNumPlayers(), 4);
  }

  @Test(expectedExceptions = IllegalStateException.class)
  public void testGetNextPlayerBeforeGameHasStarted() {
    getNewPlayerManager().getNextPlayer();
  }

  @Test
  public void testGetNextPlayerWhenWinningScoreIsReachedAndLastPlayerHasTakenTurn() {
    PlayerManager manager = getNewPlayerManager();
    manager.startGame();
    manager.setSomeoneHasReachedWinningScore(true);

    assertNull(manager.getNextPlayer());
  }

  @Test
  public void testGetNextPlayerFromPlayerOneToPlayerTwo() {
    PlayerManager manager = new PlayerManager();
    Player mockPlayerOne = mock(Player.class);
    Player mockPlayerTwo = mock(Player.class);
    manager.addPlayer(mockPlayerOne);
    manager.addPlayer(mockPlayerTwo);
    manager.startGame();

    assertEquals(manager.getNextPlayer(), mockPlayerTwo);
  }

  @Test
  public void testGetNextPlayerWhenIndexOfPlayerIsLastIndex() {
    PlayerManager manager = new PlayerManager();
    Player mockPlayerOne = mock(Player.class);
    manager.addPlayer(mockPlayerOne);
    manager.addPlayer(mock(Player.class));

    manager.startGame();
    manager.getNextPlayer();
    assertEquals(manager.getNextPlayer(), mockPlayerOne);
  }

  @Test
  public void testGetNextPlayerWhenOnlyOnePlayer() {
    PlayerManager manager = new PlayerManager();
    Player mockPlayerOne = mock(Player.class);
    manager.addPlayer(mockPlayerOne);
    manager.startGame();

    manager.getNextPlayer();
    assertEquals(manager.getNextPlayer(), mockPlayerOne);
  }

  @Test
  public void testGameIsFinishedBeforeStarting() {
    assertFalse(getNewPlayerManager().gameIsFinished());
  }

  @Test
  public void testGameIsFinishedJustAfterStarting() {
    PlayerManager manager = getNewPlayerManager();
    manager.startGame();

    assertFalse(manager.gameIsFinished());
  }

  @Test
  public void testGameIsFinishedWithSomeoneReachingWinningScoreButPlayersHaveTurnLeft() {
    PlayerManager manager = getNewPlayerManager();
    manager.addPlayer(mock(Player.class));
    manager.addPlayer(mock(Player.class));
    manager.startGame();

    manager.setSomeoneHasReachedWinningScore(true);
    assertFalse(manager.gameIsFinished());

    manager.getNextPlayer();
    assertFalse(manager.gameIsFinished());

    manager.getNextPlayer();
    assertFalse(manager.gameIsFinished());

    manager.finishLastTurnAndEndGame();
    assertTrue(manager.gameIsFinished());
  }

/*  public boolean gameIsFinished() {
    return someoneHasReachedWinningScore() && isCurrentPlayerIndexOnFirstPlayer();
  }*/

  private PlayerManager getNewPlayerManager() {
    PlayerManager manager = new PlayerManager();
    manager.addPlayer(mock(Player.class));

    return manager;
  }

}
