package main.engine;

import main.engine.properties.PropertiesManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ScoreCalculator {

    public static final int NUM_POINTS_FOR_ONE = 100;
    public static final int NUM_POINTS_FOR_FIVE = 50;

    public ScoreCalculator() {}

    public int calculateRollScore(Roll roll) {
        final HashMap<Integer, Integer> counts = assignDieValueCounts(roll);

        int score = 0;

        // n number of dice of same numeric value (e.g. two 5's or 4 6's)
        for(int i= PropertiesManager.getMinDieValue(); i<=PropertiesManager.getMaxDieValue(); i++)
            score += scoreNDiceOfSameValue(counts, i);

        // straight (1 - 6)
            score += scoreStraightRun(counts);


        return score;
    }

    private HashMap<Integer, Integer> assignDieValueCounts(Roll roll) {
        HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for(DieValue dieValue : roll.getDiceVals()) {
            int mine = dieValue.getDieValue();
            if(counts.containsKey(mine)) {
                counts.put(mine, counts.get(mine) + 1);
            }
            else {
                counts.put(mine, 1);
            }
        }

        return counts;
    }

    private int scoreNDiceOfSameValue(HashMap<Integer, Integer> counts, int dieValue) {
        return 0;
    }

    private int scoreStraightRun(HashMap<Integer, Integer> counts) {
        for(int i=PropertiesManager.getMinDieValue(); i<= PropertiesManager.getMaxDieValue(); i++) {
            if (!counts.containsKey(i) || counts.get(i) != 1) {
                return 0;
            }
        }

        return 0;
    }

    //private int score
}
