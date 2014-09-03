package core;

import java.util.List;
import java.util.Random;

import properties.PropertiesManager;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

public class DieValueGeneratorTest {

    @Mock
    private Random random;

    @InjectMocks
    private DieValueGenerator generator = new DieValueGenerator(random);

    private final int MIN_VAL = 1;
    private final int MAX_VAL = PropertiesManager.getMaxDieValue();
    private final int MIN_DICE_REQ = DieValueGenerator.MIN_NUM_DICE_REQUIRED;
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
    generator.getDieValue(MAX_VAL, MIN_VAL);
  }

  @Test
  public void testGetRollWithValidMinAndMax() {
    when(random.nextInt(MAX_VAL)).thenReturn(MIN_VAL - 1);

    assertEquals(MIN_VAL, generator.getDieValue(MIN_VAL, MAX_VAL).getDieValue());
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
    generator.verifyNumDiceIsValid(MIN_DICE_REQ);
    // no exception indicates success
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testTurnRollWithInvalidNumDice() {
    generator.getDieValues(MIN_VAL, MAX_VAL, MIN_DICE_REQ - 1);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testTurnRollWithInvalidMinAndMax() {
    generator.getDieValues(MAX_VAL, MIN_VAL, MIN_DICE_REQ);
  }

  @Test
  public void testTurnRollWithOneDieRoll() {
    int result = MAX_VAL - 1;
    when(random.nextInt(anyInt())).thenReturn(result);

    List<DieValue> dieVals = generator.getDieValues(MIN_VAL, MAX_VAL, MIN_DICE_REQ);
    assertEquals(MIN_DICE_REQ, dieVals.size());
    assertEquals(result + 1, dieVals.get(0).getDieValue());
    verify(random).nextInt(anyInt());
  }

  @Test
  public void testTurnRollWithMultipleDiceRolls() {
    int result = MAX_VAL - 1;
    when(random.nextInt(anyInt())).thenReturn(result);

    List<DieValue> dieVals = generator.getDieValues(MIN_VAL, MAX_VAL, MIN_DICE_REQ + 1);
    assertEquals(MIN_DICE_REQ + 1, dieVals.size());
    for(int i=0; i<MIN_DICE_REQ + 1; i++) {
      assertEquals(result + 1, dieVals.get(i).getDieValue());
    }

    verify(random, times(MIN_DICE_REQ + 1)).nextInt(anyInt());
  }

}
