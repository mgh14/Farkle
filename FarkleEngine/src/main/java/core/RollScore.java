package core;

import java.util.LinkedList;
import java.util.List;

public class RollScore {

  private final int numPoints;
  private final List<DieValue> diceVals;

  public RollScore(List<DieValue> diceKept, int pointsGained, ScoreCalculator scoreCalc) {
    if(diceKept == null) {
      throw new IllegalArgumentException("Dice kept cant be null");
    }
    if(scoreCalc == null) {
      throw new IllegalArgumentException("core.RollScore requires a core.ScoreCalculator");
    }

    final int scoreCalcScore = scoreCalc.calculateRollScore(diceKept);
    if(scoreCalcScore != pointsGained) {
      throw new IllegalArgumentException("Scores are not equal: (scoreCalc): " +
        scoreCalcScore + " should equal " + pointsGained);
    }

    numPoints = pointsGained;

    diceVals = new LinkedList<DieValue>();
    diceVals.addAll(diceKept);
  }

  public List<DieValue> getDiceKept() {
      return diceVals;
  }

    public int getNumberOfPoints() {
        return numPoints;
    }

}
