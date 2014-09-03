package core;

import java.util.LinkedList;
import java.util.List;

public class Turn {

  final private List<Roll> rolls;
  final private Player player;

  public Turn(Player playerWhoTakesTurn) {
    if (playerWhoTakesTurn == null) {
      throw new IllegalArgumentException("player for turn cant be null");
    }

    rolls = new LinkedList<Roll>();
    player = playerWhoTakesTurn;
  }

  public void addRoll(Roll newRoll) {
    if (newRoll == null) {
      throw new IllegalArgumentException("core.Roll cant be null");
    }

    rolls.add(newRoll);
  }

  public final Player getTurnPlayer() {
    return player;
  }

  public int getNumRolls() {
    return rolls.size();
  }

  public int getTotalScoreForTurn() {
    int total = 0;
    for (Roll current : rolls) {
      total += current.getScoreForRoll();
    }

    return total;
  }

  public boolean canRollAgain(ScoreCalculator scoreCalc) {
    return canGetLastRoll() && getLastRoll().canRollAgain(scoreCalc);
  }

  public final List<Roll> getRolls() {
    return rolls;
  }

  public boolean canGetLastRoll() {
    return !rolls.isEmpty();
  }

  public Roll getLastRoll() {
    if (!canGetLastRoll()) {
      return null;
    }

    return rolls.get(rolls.size() - 1);
  }

  public void setDiceKept(List<DieValue> keptDice, int score, ScoreCalculator scoreCalc) {
    getLastRoll().setDiceKept(keptDice, score, scoreCalc);
  }

}
