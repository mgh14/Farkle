package test.engine;

import main.engine.Roll;
import main.engine.RollGenerator;
import main.engine.properties.PropertiesManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class RollGeneratorTest {

    private final RollGenerator generator = new RollGenerator();

    private final int MIN_VAL = 0;
    private final int MAX_VAL = 1000;
    private final int INVALID_ROLL = MIN_VAL - 1;

  @BeforeMethod
  public void setUp() {
    PropertiesManager.loadDefaultConfig();
  }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void verifyNegativeMinValueIsInvalid() {
      generator.verifyMinAndMaxValid(INVALID_ROLL, MAX_VAL);
    }

    @Test
    public void verifyZeroMinValueIsValid() {
      int minVal = 0;
      generator.verifyMinAndMaxValid(minVal, MAX_VAL);
    }

    @Test
    public void verifyPositiveMinValueIsValid() {
      int minVal = 1;
      generator.verifyMinAndMaxValid(minVal, MAX_VAL);
    }

    @Test
    public void verifyZeroMinAndMaxValuesAreValid() {
      int maxVal = 0;
      generator.verifyMinAndMaxValid(maxVal, maxVal);
    }

    @Test
    public void verifyPositiveMaxValueIsValid() {
      int maxVal = 3000;
      generator.verifyMinAndMaxValid(MIN_VAL, maxVal);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void verifyGreaterMinThanMaxValueIsInvalid() {
      generator.verifyMinAndMaxValid(MIN_VAL + 1, MIN_VAL);
    }

    @Test
    public void verifyMinAndMaxValueToSamePositiveIntAreValid() {
      int sameVal = MAX_VAL;
      generator.verifyMinAndMaxValid(sameVal, sameVal);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void verifyNegativeIntRollIsInvalid() {
      generator.verifyDieValueIsValid(MIN_VAL, MAX_VAL, INVALID_ROLL);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void verifyPositiveButOutOfRangeIntRollIsInvalid() {
      generator.verifyDieValueIsValid(MIN_VAL, MAX_VAL, MIN_VAL - 1);
    }

    @Test
    public void verifyMinValRollIsValid() {
      generator.verifyDieValueIsValid(MIN_VAL, MAX_VAL, MIN_VAL);
    }

    @Test
    public void verifyMaxValidRollIsValid() {
      generator.verifyDieValueIsValid(MIN_VAL, MAX_VAL, MAX_VAL);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetRollWithInvalidBounds() {
      generator.getRoll(1,0);
    }

  @Test
  public void testGetRollWithValidBounds() {
    int minVal = PropertiesManager.DEFAULT_MIN_DIE_VALUE;
    int maxVal = PropertiesManager.DEFAULT_MAX_DIE_VALUE;

    Roll result = generator.getRoll(minVal, maxVal);
    assertTrue(result.getRollValue() >= minVal);
    assertTrue(result.getRollValue() <= maxVal);
  }
    /*

    public void verifyNumDiceIsValid(int numDice) {
        if(numDice < 1) {
            throw new IllegalArgumentException("minimum number of dice is one");
        }
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
      }*/

}
