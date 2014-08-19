package main.engine;

import java.util.LinkedList;
import java.util.List;

public class Turn {

    final private List<Roll> rolls;
    final private List<RollScore> scores;
    final private Player player;

  public Turn(List<Roll> diceRolls, List<RollScore> playerScores, Player playerWhoTakesTurn) {
    rolls = diceRolls;
    scores = playerScores;
    player = playerWhoTakesTurn;
  }

  public Player getPlayerWhoTookTurn() {
    return player;
  }

  public int getNumRolls() {
    return rolls.size();
  }

  public int getTotalScoreForTurn() {
    int total = 0;
    for(RollScore current : scores) {
      total += current.getNumberOfPoints();
    }

    return total;
  }

}
