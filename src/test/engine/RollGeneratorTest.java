package test.engine;

import main.engine.RollGenerator;
import main.engine.properties.PropertiesManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RollGeneratorTest {

    private final RollGenerator generator = new RollGenerator();

    private final int MIN_VAL = 0;
    private final int MAX_VAL = 1000;
    private final int VALID_ROLL = 1;
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

  /*@Test
  public void testGetRollWithValidBounds() {
    Roll result = generator.getRoll(0, 6);
    assertTrue(result.getRollValue() >= 0);
    assertTrue(result.getRollValue() <= 6);
  }*/
    /*


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
