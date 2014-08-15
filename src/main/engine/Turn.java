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

    /*public Turn(Roll playerRoll, RollScore playerScore, Player playerWhoTookTurn) {
        roll = playerRoll;


        scores = playerScore;



        player = playerWhoTookTurn;
    }*/

    /*public Roll getRoll() {
        return roll;
    }

    public RollScore getDiceScore() {
        return scores;
    }

    public Player getPlayer() {
        return player;
    }*/
}
