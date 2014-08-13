package main.engine;

public class Turn {

    final private Roll roll;
    final private DiceScore score;
    final private Player player;

    public Turn(Roll playerRoll, DiceScore playerScore, Player playerWhoTookTurn) {
        roll = playerRoll;
        score = playerScore;
        player = playerWhoTookTurn;
    }

    public Roll getRoll() {
        return roll;
    }

    public DiceScore getDiceScore() {
        return score;
    }

    public Player getPlayer() {
        return player;
    }
}
