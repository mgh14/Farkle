package core;

import properties.PropertiesManager;

import java.util.HashMap;
import java.util.List;

public class ScoreCalculator {

  public static final int NUM_POINTS_FOR_ONE = 100;
  public static final int NUM_POINTS_FOR_FIVE = 50;
  public static final int FACTOR_POINTS_FOR_THREE_OF_SAME = 100;
  public static final int FACTOR_POINTS_FOR_FOUR_OR_MORE_OF_SAME = 1000;
  public static final int NUM_POINTS_FOR_STRAIGHT = 1500;
  public static final int NUM_POINTS_FOR_THREE_SETS_OF_TWO = NUM_POINTS_FOR_STRAIGHT;
  public static final int NUM_POINTS_FOR_ONE_SET_OF_FOUR_AND_ONE_SET_OF_TWO = NUM_POINTS_FOR_STRAIGHT;
  public static final int NUM_POINTS_FOR_TWO_SETS_OF_THREE = 2500;

  /*public enum ScoreType {
    SetOf
  }*/

  public ScoreCalculator() {}

    /*You cannot count any of your points until you reach at least 500 points in a single round. When you reach 500 points for the first time, you may choose to
      immediately end your turn to prevent losing the points.
    5′s = 50 points
    1′s = 100 points
    1,1,1 = 300 points
    2,2,2 = 200 points
    3,3,3 = 300 points
    4,4,4 = 400 points
    5,5,5 = 500 points
    6,6,6 = 600 points
    Four of a Kind = 1,000 points
    Five of a Kind = 2,000 points
    Six of a Kind = 3,000 points
    A Straight of 1-6 = 1,500 points
    Three Pairs = 1,500 points
    Four of a Kind + a Pair = 1,500
    Two sets of Three of a Kind = 2,500
  */

  public int calculateRollScore(Roll roll) {
    return calculateRollScore(roll.getDiceKept());
  }

  public int calculateRollScore(List<DieValue> diceKept) {
    final HashMap<Integer, Integer> counts = assignDieValueCounts(diceKept);

    int highestScore = 0;

    // n number of dice of same numeric value (e.g. two 5's or 4 6's)
    for (Integer key : counts.keySet()) {
      highestScore += scoreDiceOfSameValue(key, counts.get(key));
    }

    // straight (1 - 6)
    int straightScore = scoreStraightRun(counts);
    if (straightScore > highestScore) {
      highestScore = straightScore;
    }

    // three sets of two
    int threeSetsOfTwoScore = scoreThreeSetsOfTwo(counts);
    if (threeSetsOfTwoScore > highestScore) {
      highestScore = threeSetsOfTwoScore;
    }

    // a set of four and a set of two
    int setOfFourAndSetOfTwoScore = scoreASetOfFourAndASetOfTwo(counts);
    if (setOfFourAndSetOfTwoScore > highestScore) {
      highestScore = setOfFourAndSetOfTwoScore;
    }

    // two sets of three
    int twoSetsOfThreeScore = scoreTwoSetsOfThree(counts);
    if (twoSetsOfThreeScore > highestScore) {
      highestScore = twoSetsOfThreeScore;
    }

    return highestScore;
  }

  private HashMap<Integer, Integer> assignDieValueCounts(List<DieValue> diceVals) {
    HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();
    for (int i = PropertiesManager.getMinDieValue(); i <= PropertiesManager.getMaxDieValue(); i++) {
      counts.put(i, 0);
    }

    for (DieValue dieValue : diceVals) {
      int mine = dieValue.getDieValue();
      counts.put(mine, counts.get(mine) + 1);
    }

    return counts;
  }

  private int scoreDiceOfSameValue(int keyVal, int numDice) {
    // for ones
    if (keyVal == PropertiesManager.getMinDieValue() && numDice <= 3) {
      return NUM_POINTS_FOR_ONE * numDice;
    }

    // for fives
    else if (keyVal == PropertiesManager.getMaxDieValue() - 1 && numDice <= 2) {
      return NUM_POINTS_FOR_FIVE * numDice;
    }

    // for any dice number
    else if (numDice == 3) {
      return FACTOR_POINTS_FOR_THREE_OF_SAME * keyVal;
    }

    // for any dice number
    else if (numDice > 3) {
      return FACTOR_POINTS_FOR_FOUR_OR_MORE_OF_SAME * (numDice - (PropertiesManager.getMaxDieValue() / 2));
    }

    return 0;
  }

  private int scoreStraightRun(HashMap<Integer, Integer> counts) {
    int diceInEachSet = 1;
    return scoreNSetsOfNDice(counts, PropertiesManager.getNumDice(), diceInEachSet, NUM_POINTS_FOR_STRAIGHT);
  }

  private int scoreThreeSetsOfTwo(HashMap<Integer, Integer> counts) {
    int diceInEachSet = 2;
    return scoreNSetsOfNDice(counts, PropertiesManager.getNumDice() / diceInEachSet, diceInEachSet, NUM_POINTS_FOR_THREE_SETS_OF_TWO);
  }

  private int scoreASetOfFourAndASetOfTwo(HashMap<Integer, Integer> counts) {
    boolean keyForFourFound = false;
    boolean keyForTwoFound = false;
    for (Integer key : counts.keySet()) {
      int countForCurrentKey = counts.get(key);
      if (countForCurrentKey == 4) {
        keyForFourFound = true;
      }
      if (countForCurrentKey == 2) {
        keyForTwoFound = true;
      }
    }

    if (keyForFourFound && keyForTwoFound) {
      return NUM_POINTS_FOR_ONE_SET_OF_FOUR_AND_ONE_SET_OF_TWO;
    }

    return 0;
  }

  private int scoreTwoSetsOfThree(HashMap<Integer, Integer> counts) {
    int numDiceSets = 2;
    int diceInEachSet = 3;
    return scoreNSetsOfNDice(counts, numDiceSets, diceInEachSet, NUM_POINTS_FOR_TWO_SETS_OF_THREE);
  }

  private int scoreNSetsOfNDice(HashMap<Integer, Integer> counts, int numSets, int numDiceInEachSet, int pointsToGain) {
    int numSetsOfTwo = 0;
    for (Integer key : counts.keySet()) {
      if (counts.get(key) >= numDiceInEachSet) {
        numSetsOfTwo++;
      }
    }

    if (numSetsOfTwo >= numSets) {
      return pointsToGain;
    }

    return 0;
  }

}
