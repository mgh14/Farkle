package main.engine;

import java.util.LinkedList;
import java.util.List;

public class RollScore {

  private final int numPoints;
  private final List<DieValue> rollsKept;

  public RollScore(int NUM_POINTS, List<DieValue> DICE_VALS) {
    numPoints = NUM_POINTS;

    rollsKept = new LinkedList<DieValue>();
    rollsKept.addAll(DICE_VALS);
  }

  public int getNumberOfPoints() {
      return numPoints;
  }

  public List<DieValue> getRollsKept() {
      return rollsKept;
  }

}
