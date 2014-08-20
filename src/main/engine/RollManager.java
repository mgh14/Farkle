package main.engine;

import main.engine.properties.PropertiesManager;

import java.util.LinkedList;
import java.util.List;

public class RollManager {

    private List<Turn> rolls;
    private Turn currentTurn;

    private DieValueGenerator generator;
    private ScoreCalculator scoreCalc;

    public RollManager() {
        rolls = new LinkedList<Turn>();
        setCurrentTurn(null);

        generator = new DieValueGenerator();
        scoreCalc = new ScoreCalculator();
    }

    private void setCurrentTurn(Turn turn) {
        currentTurn = turn;
    }

    private Roll getRoll(int numDice) {
        return new Roll(PropertiesManager.getNumDice(), generator.getDieValues(PropertiesManager.getMinDieValue(),
                PropertiesManager.getMaxDieValue(), numDice));
    }

/*    public int keepDieValues(int ... dieVals) {

    }

    public Roll getNextRoll() {
        if(!canRollAgain()) {
            throw new IllegalStateException("no more rolls allowed");
        }

        int size = currentRoll.getDiceVals().size();

        return getRoll();
    }*/

    public boolean canRollAgain() {
        return currentTurn == null || currentTurn.canRollAgain(scoreCalc);
    }

/*    public void addRoll(Roll playerRoll) {
        rolls.add(playerRoll);
    }

*/

 /*   public void undoLastTurn() {
        turns.remove(turns.size() - 1);
    }*/

}
