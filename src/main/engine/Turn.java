package main.engine;

import java.util.LinkedList;
import java.util.List;

public class Turn {

    final private List<Roll> roll;
    final private List<RollScore> scores;
    final private Player player;

  public Turn(Player playerWhoTakesTurn) {
    roll = new LinkedList<Roll>();
    scores = new LinkedList<RollScore>();
    player = playerWhoTakesTurn;
  }

  public Player getPlayerWhoTookTurn() {
    return player;
  }

  public void addRoll(Roll playerRoll) {
    roll.add(playerRoll);
  }

  public void addRollScore(RollScore playerScore) {
    scores.add(playerScore);
  }

  public int getTotalScoreForTurn() {
    int total = 0;
    for(RollScore current : scores) {
      total += current.getNumberOfPoints();
    }

    return total;
  }

}
