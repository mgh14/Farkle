package main.engine;

import main.engine.properties.PropertiesManager;

import java.util.HashMap;

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
    for(int i=PropertiesManager.getMinDieValue(); i<=PropertiesManager.getMaxDieValue(); i++) {
      counts.put(i, 0);
    }

    for(DieValue dieValue : roll.getDiceVals()) {
        int mine = dieValue.getDieValue();
        counts.put(mine, counts.get(mine) + 1);
    }

      return counts;
  }

  private int scoreNDiceOfSameValue(HashMap<Integer, Integer> counts, int dieValue) {
      return 0;
  }

  private int scoreThreeSetsOfTwo(HashMap<Integer, Integer> counts) {
    return scoreNSetsOfNDice(counts, 3, 2, 5000);
  }

  private int scoreTwoSetsOfThree(HashMap<Integer, Integer> counts) {
    return scoreNSetsOfNDice(counts, 2, 3, 10000);
  }

  private int scoreStraightRun(HashMap<Integer, Integer> counts) {
    return scoreNSetsOfNDice(counts, 6, 1, 20000);
  }

  private int scoreNSetsOfNDice(HashMap<Integer, Integer> counts, int numSets, int numDiceInEachSet, int pointsToGain) {
    int numSetsOfTwo = 0;
    for(Integer key : counts.keySet()) {
      if(counts.get(key) >= numDiceInEachSet) {
        numSetsOfTwo++;
      }
    }

    if(numSetsOfTwo >= numSets) {
      return pointsToGain;
    }

    return 0;
  }

}
