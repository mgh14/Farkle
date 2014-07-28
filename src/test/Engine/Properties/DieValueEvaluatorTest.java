package test.Engine.Properties;

import main.Engine.Properties.DieValueEvaluator;
import main.Engine.Roll;
import org.testng.annotations.Test;

public class DieValueEvaluatorTest {

  private DieValueEvaluator evaluator;

  private final int MIN_VAL = 0;
  private final int MAX_VAL = 1000;
  private final int VALID_ROLL = 1;
  private final int INVALID_ROLL = -1;
  private final Roll TEST_ROLL = new Roll(VALID_ROLL);

/*  @Test(expectedExceptions = IllegalArgumentException.class)
  public void checkWhenMinIsGreaterThanMax() {
    evaluator.verifyMinAndMaxValid(2, 1);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void checkMinIsNegative() {
    evaluator.verifyMinAndMaxValid(-1, 2);
  }*/

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void verifyNegativeMinValueIsInvalid() {
    evaluator.verifyMinAndMaxValid(INVALID_ROLL, MAX_VAL);
  }

  @Test
  public void verifyZeroMinValueIsValid() {
    int minVal = 0;
    evaluator.verifyMinAndMaxValid(minVal, MAX_VAL);
  }

  @Test
  public void verifyPositiveMinValueIsValid() {
    int minVal = 1;
    evaluator.verifyMinAndMaxValid(minVal, MAX_VAL);
  }

  @Test
  public void verifyZeroMinAndMaxValuesAreValid() {
    int maxVal = 0;
    evaluator.verifyMinAndMaxValid(maxVal, maxVal);
  }

  @Test
  public void verifyPositiveMaxValueIsValid() {
    int maxVal = 3000;
    evaluator.verifyMinAndMaxValid(MIN_VAL, maxVal);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void verifyGreaterMinThanMaxValueIsInvalid() {
    evaluator.verifyMinAndMaxValid(MIN_VAL, MIN_VAL * 2);
  }

  @Test
  public void verifyMinAndMaxValueToSamePositiveIntAreValid() {
    int sameVal = MAX_VAL;
    evaluator.verifyMinAndMaxValid(sameVal, sameVal);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void verifyNegativeIntRollIsInvalid() {
    evaluator.verifyDieValueIsValid(MIN_VAL, MAX_VAL, INVALID_ROLL);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void verifyPositiveButOutOfRangeIntRollIsInvalid() {
    evaluator.verifyDieValueIsValid(MIN_VAL, MAX_VAL, MIN_VAL - 1);
  }

  @Test
  public void verifyMinValRollIsValid() {
    evaluator.verifyDieValueIsValid(MIN_VAL, MAX_VAL, MIN_VAL);
  }

  @Test
  public void verifyMaxValidRollIsValid() {
    evaluator.verifyDieValueIsValid(MIN_VAL, MAX_VAL, MAX_VAL);
  }

  /*    public void verifyMinAndMaxValid(int min, int max) {
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
    }*/

}
