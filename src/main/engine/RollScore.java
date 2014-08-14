package main.engine;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class RollScore {

    private final int numPoints;
    private final Set<DieValue> rollsKept;

    public RollScore(int NUM_POINTS, List<DieValue> DICE_VALS) {
        numPoints = NUM_POINTS;

        rollsKept = new TreeSet<DieValue>();
        rollsKept.addAll(DICE_VALS);
    }

    public int getNumberOfPoints() {
        return numPoints;
    }

    public Set<DieValue> getRollsKept() {
        return rollsKept;
    }
}
