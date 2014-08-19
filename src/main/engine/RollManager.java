package main.engine;

import main.engine.properties.PropertiesManager;

import java.util.LinkedList;
import java.util.List;

public class RollManager {

    private List<Roll> rolls;
    private List<RollScore> scores;

    private Roll currentRoll;
    private RollScore currentRollScore;

    private DieValueGenerator generator;
    private ScoreCalculator scoreCalc;

    public RollManager() {
        rolls = new LinkedList<Roll>();
        scores = new LinkedList<RollScore>();
        setCurrentRoll(null);

        generator = new DieValueGenerator();
        scoreCalc = new ScoreCalculator();
    }

    private Roll getRoll(int numDice) {
        return new Roll(PropertiesManager.getNumDice(), generator.getDieValues(PropertiesManager.getMinDieValue(),
                PropertiesManager.getMaxDieValue(), numDice));
    }

    public Roll beginRoll() {
        setCurrentRoll(getRoll(PropertiesManager.getNumDice()));
        return currentRoll;
    }

    public int keepDieValues(int ... dieVals) {

    }

    public int endRoll() {
        setCurrentRoll(null);
    }

    public Roll getNextRoll() {
        if(!canGetNextRoll()) {
            throw new IllegalStateException("no more rolls allowed");
        }

        int size = currentRoll.getDiceVals().size();

        return getRoll();
    }

    public boolean inRollState() {
        return currentRoll != null;
    }

    public boolean canGetNextRoll() {
      return scoreCalc.calculateRollScore(currentRoll) > 0;
    }

    public void addRoll(Roll playerRoll) {
        rolls.add(playerRoll);
    }

    public void addRollScore(RollScore playerScore) {
        scores.add(playerScore);
    }

    public void removeLastRoll() {
        scores.remove(scores.size() - 1);
        rolls.remove(rolls.size() - 1);
    }

    private void setCurrentRoll(Roll roll) {
        currentRoll = null;
    }

}
