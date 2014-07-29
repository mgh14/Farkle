package main.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class RollGenerator {

    private Random generator = new Random();

  public RollGenerator(Random random) {
    setRandom(random);
  }

  public RollGenerator() {
    setRandom(null);
  }

  public void setRandom(Random random) {
    generator = (generator != null) ? random : new Random();
  }

    public Roll getRoll(int minVal, int maxVal) {
      verifyMinAndMaxValid(minVal, maxVal);
      return new Roll(generator.nextInt((maxVal - minVal) + 1) + minVal);
    }

    public Collection<Roll> getTurnRoll(int minVal, int maxVal, int numDice) {
        List<Roll> rolls = new ArrayList<Roll>();
        for(int i=0; i<numDice; i++) {
            rolls.add(getRoll(minVal, maxVal));
        }

        return rolls;
    }

  public void verifyMinAndMaxValid(int min, int max) {
    if(min > max) {
      throw new IllegalArgumentException("minimum value is less than maximum value");
    }
    if(min < 0) {
      throw new IllegalArgumentException("minimum value must be positive");
    }
  }

  public void verifyDieValueIsValid(int min, int max, int value) {
    if(!(min <= value && value <= max)) {
      throw new IllegalArgumentException("Value is not within min/max value range");
    }
  }
}
