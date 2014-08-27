package main.engine;

import java.util.LinkedList;
import java.util.List;

public class Turn {

    final private List<Roll> rolls;
    final private Player player;

  public Turn(Player playerWhoTakesTurn) {
      if(playerWhoTakesTurn == null) {
          throw new IllegalArgumentException("player for turn cant be null");
      }

    rolls = new LinkedList<Roll>();
    player = playerWhoTakesTurn;
  }

  public void addRoll(Roll newRoll) {
    if(newRoll == null) {
      throw new IllegalArgumentException("Roll cant be null");
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
    for(Roll current : rolls) {
      total += current.getScoreForRoll();
    }

    return total;
  }

  public boolean canRollAgain(ScoreCalculator scoreCalc) {
      return !rolls.isEmpty() && getLastRoll().canRollAgain(scoreCalc);
  }

  public final List<Roll> getRolls() {
    return rolls;
  }

  public final Roll getLastRoll() {
    if(rolls.isEmpty()) {
      return null;
    }

    return rolls.get(rolls.size() - 1);
  }

}
