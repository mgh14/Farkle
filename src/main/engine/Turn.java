package main.engine;

public class Turn {

    final private Roll roll;
    final private RollScore score;
    final private Player player;

    public Turn(Roll playerRoll, RollScore playerScore, Player playerWhoTookTurn) {
        roll = playerRoll;
        score = playerScore;
        player = playerWhoTookTurn;
    }

    public Roll getRoll() {
        return roll;
    }

    public RollScore getDiceScore() {
        return score;
    }

    public Player getPlayer() {
        return player;
    }
}
