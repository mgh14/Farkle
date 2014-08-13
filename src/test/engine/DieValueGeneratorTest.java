package test.engine;

import java.util.Random;

import main.engine.RollGenerator;
import main.engine.properties.PropertiesManager;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

public class DieValueGeneratorTest {

    @Mock
    private Random random;

    @InjectMocks
    private RollGenerator generator = new RollGenerator(random);

    private final int MIN_VAL = 1;
    private final int MAX_VAL = PropertiesManager.getMaxDieValue();
    private final int INVALID_ROLL = MIN_VAL - 1;

    @BeforeMethod
    public void setUp() {
      MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void verifyNegativeMinValueIsInvalid() {
      generator.verifyMinAndMaxValid(-1, MAX_VAL);
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
  public void testGetRollWithInvalidMinAndMaxVals() {
    generator.getRoll(MAX_VAL, MIN_VAL);
  }

  @Test
  public void testGetRollWithValidMinAndMax() {
    when(random.nextInt(MAX_VAL)).thenReturn(MIN_VAL - 1);

    assertEquals(MIN_VAL, generator.getRoll(MIN_VAL, MAX_VAL).getDieValue());
    verify(random).nextInt(MAX_VAL);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void verifyNegativeNumDiceIsInvalid() {
    generator.verifyNumDiceIsValid(-1);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void verifyZeroDiceIsInvalid() {
    generator.verifyNumDiceIsValid(0);
  }

  @Test
  public void verifyOneOrMoreDiceIsValid() {
    generator.verifyNumDiceIsValid(RollGenerator.MIN_NUM_DICE_REQUIRED);
    // no exception indicates success
  }

  @Test
  public void testTurnRollWithInvalidNumDice() {

  }

  @Test
  public void testTurnRollWithInvalidMinAndMax() {

  }

  @Test
  public void testTurnRollWithOneDieRoll() {

  }

  @Test
  public void testTurnRollWithMultipleDiceRolls() {

  }

    /*

      public Collection<Roll> getTurnRoll(int minVal, int maxVal, int numDice) {
          List<Roll> rolls = new ArrayList<Roll>();
          for(int i=0; i<numDice; i++) {
              rolls.add(getRoll(minVal, maxVal));
          }

          return rolls;
      }*/

}
