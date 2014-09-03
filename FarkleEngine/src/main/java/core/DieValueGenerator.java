package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DieValueGenerator {

  public static int MIN_NUM_DICE_REQUIRED = 1;

    private Random generator;

  public DieValueGenerator() {
    generator = new Random();
  }

    public DieValueGenerator(Random random) {
        generator = random;
    }

  public DieValue getDieValue(int minVal, int maxVal) {
    verifyMinAndMaxValid(minVal, maxVal);
    return new DieValue(generator.nextInt((maxVal - minVal) + 1) + minVal);
  }

  public List<DieValue> getDieValues(int minVal, int maxVal, int numDice) {
    verifyNumDiceIsValid(numDice);

    List<DieValue> rolls = new ArrayList<DieValue>();
    for(int i=0; i<numDice; i++) {
      rolls.add(getDieValue(minVal, maxVal)); // min, max are verified here
    }

    return rolls;
  }

  public void verifyNumDiceIsValid(int numDice) {
      if(numDice < MIN_NUM_DICE_REQUIRED) {
          throw new IllegalArgumentException("minimum number of dice is one");
      }
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
