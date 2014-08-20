package main.engine;

import java.util.LinkedList;
import java.util.List;

public class RollScore {

  private final int numPoints;
  private final List<DieValue> diceVals;

  public RollScore(List<DieValue> diceKept, int pointsGained) {
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
