package main.engine;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class DiceScore {

    private final int numPoints;
    private final Set<DieValue> rollsKept;

    public DiceScore(int NUM_POINTS, Collection<DieValue> DICE_VALS) {
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
