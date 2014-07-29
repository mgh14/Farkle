package main.Engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class RollGenerator {

    private int minValue;
    private int maxValue;
    private int numDice;

    private Random generator;

    public RollGenerator(int minVal, int maxVal, int numOfDice) {
        minValue = minVal;
        maxValue = maxVal;
        numDice = numOfDice;

        generator = new Random();
    }

    public Roll getRoll() {
        return new Roll(generator.nextInt((maxValue - minValue) + 1) + minValue);
    }

    public Collection<Roll> getTurnRoll() {
        return getTurnRoll(numDice);
    }

    public Collection<Roll> getTurnRoll(int numDice) {
        List<Roll> rolls = new ArrayList<Roll>();
        for(int i=0; i<numDice; i++) {
            rolls.add(getRoll());
        }

        return rolls;
    }
}
