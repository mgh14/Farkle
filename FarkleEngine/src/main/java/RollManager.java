import properties.PropertiesManager;

import java.util.LinkedList;
import java.util.List;

public class RollManager {

  private TurnManager turnManager;
  private DieValueGenerator generator;
  private ScoreCalculator scoreCalc;

  public RollManager(DieValueGenerator dieValueGenerator, ScoreCalculator scoreCalculator) {
    initializeRollManager(new TurnManager(), dieValueGenerator, scoreCalculator);
  }

  public RollManager() {
    new RollManager(new DieValueGenerator(), new ScoreCalculator());
  }

  public boolean turnInPlay() {
    return turnManager.turnInPlay();
  }

  public Roll beginTurn(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player cant be null");
    }

    setCurrentTurn(turnManager.getNewTurn(player));

    final Roll firstRoll = getRoll(PropertiesManager.getNumDice());
    turnManager.getCurrentTurn().addRoll(firstRoll);

    return firstRoll;
  }

  public boolean canRollAgain() {
    return turnInPlay() && getCurrentTurn().canRollAgain(scoreCalc);
  }

  public Roll getNextRoll() {
    if (!turnInPlay()) {
      throw new IllegalStateException("Cant get next roll until turn is started");
    }

    if (!canRollAgain()) {
      throw new IllegalStateException("no more rolls allowed");
    }

    Turn currentTurn = getCurrentTurn();
    int size = currentTurn.getLastRoll().getDiceVals().size();
    Roll nextRoll = getRoll(PropertiesManager.getNumDice() - size);

    currentTurn.addRoll(nextRoll);

    return nextRoll;
  }

  public void addRoll(Roll playerRoll) {
    if (playerRoll == null) {
      throw new IllegalArgumentException("Roll cant be null");
    }

    getCurrentTurn().addRoll(playerRoll);
  }

  public int keepDieValues(int... dieValIndices) {
    if (turnInPlay()) {
      throw new IllegalStateException("No turn is in play; cant keep dice");
    }

    List<DieValue> diceRolled = getCurrentTurn().getLastRoll().getDiceVals();
    List<DieValue> diceToKeep = new LinkedList<DieValue>();
    for (int dieVal : dieValIndices) {
      diceToKeep.add(diceRolled.get(dieVal));
    }

    Turn currentTurn = getCurrentTurn();
    currentTurn.setDiceKept(diceToKeep, scoreCalc.calculateRollScore(diceToKeep), scoreCalc);

    return currentTurn.getLastRoll().getScoreForRoll();
  }

  public Roll getLastRoll() {
    if(!turnInPlay()) {
      throw new IllegalStateException("no turn in play");
    }

    return getCurrentTurn().getLastRoll();
  }

  // used for testing
  public RollManager reset() {
    initializeRollManager(new TurnManager(), new DieValueGenerator(), new ScoreCalculator());

    return this;
  }

/*  public void addScore(int score) {
    if (score < PropertiesManager.getMinScore()) {
      throw new IllegalArgumentException("score cant be less than minimum score");
    }
    if(gameIsFinished()) {
      throw new IllegalStateException("Cant add score when game is finished");
    }

    playerScores.put(getCurrentPlayer(), playerScores.get(getCurrentPlayer()) + score);
  }

  public boolean someoneHasReachedWinningScore() {
    for (Player player : playerScores.keySet()) {
      if (scoreQualifiesForWin(playerScores.get(player))) {
        return true;
      }
    }

    return false;
  }

  private boolean scoreQualifiesForWin(int score) {
    return score >= PropertiesManager.getPointsReqForWin();
  }*/

  private void initializeRollManager(TurnManager newTurnManager, DieValueGenerator dieValueGenerator, ScoreCalculator scoreCalculator) {
    turnManager = newTurnManager;
    setCurrentTurn(null);

    generator = dieValueGenerator;
    scoreCalc = scoreCalculator;
  }

  private Roll getRoll(int numDice) {
    return new Roll(PropertiesManager.getNumDice(), generator.getDieValues(PropertiesManager.getMinDieValue(),
      PropertiesManager.getMaxDieValue(), numDice));
  }

  private Turn getCurrentTurn() {
    return turnManager.getCurrentTurn();
  }

  // used only for testing outside this class (should be an internal method)
  public void setCurrentTurn(Turn turn) {
    turnManager.setCurrentTurn(turn);
  }

  private class TurnManager {

    private List<Turn> turns;
    private Turn currentTurn;

    TurnManager() {
      turns = new LinkedList<Turn>();
      setCurrentTurn(null);
    }

    boolean turnInPlay() {
      return currentTurn != null;
    }

    Turn getCurrentTurn() {
      return currentTurn;
    }

    void setCurrentTurn(Turn turn) {
      currentTurn = turn;
    }

    Turn getNewTurn(Player player) {
      if(turnInPlay()) {
        throw new IllegalStateException("turn already in play; cant get new turn");
      }

      return new Turn(player);
    }

    void undoLastTurn() {
      if (turnInPlay()) {
        throw new IllegalStateException("Cant undo turn while turn is being taken");
      }

      turns.remove(turns.size() - 1);
    }
  }

}
