package Engine;

import org.testng.annotations.Test;
import org.testng.internal.annotations.ExpectedExceptionsAnnotation;

import java.util.LinkedList;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class RollTest {

    private final int MIN_VAL = 0;
    private final int MAX_VAL = 1000;

    private final int VALID_ROLL = 1;
    private final int INVALID_ROLL = -1;

    private final Roll TEST_ROLL = new Roll(MIN_VAL + 1, MAX_VAL, VALID_ROLL);

    @Test
    public void defaultMinValIsOne() {
        assertEquals(new Roll().getMinValue(), 1);
    }

    @Test
    public void defaultMaxValIsSix() {
        assertEquals(new Roll().getMaxValue(), 6);
    }

    @Test
    public void defaultRollValueIsOne() {
        assertEquals(new Roll().getRollValue(), 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void setNegativeMinValue() {
        new Roll(-1, MAX_VAL, VALID_ROLL);
    }

    @Test
    public void setZeroMinValue() {
        int minVal = 0;
        assertEquals(new Roll(minVal, MAX_VAL, VALID_ROLL).getMinValue(), minVal);
    }

    @Test
    public void setPositiveMinValue() {
        int minVal = 1;
        assertEquals(new Roll(minVal, MAX_VAL, VALID_ROLL).getMinValue(), minVal);
    }

    @Test
    public void setZeroMaxValue() {
        int maxVal = 0;
        assertEquals(new Roll(maxVal, maxVal, maxVal).getMaxValue(), maxVal);
    }

    @Test
    public void setPositiveMaxValue() {
        int maxVal = 3000;
        assertEquals(new Roll(MIN_VAL, maxVal, VALID_ROLL).getMaxValue(), maxVal);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void setGreaterMinThanMaxValue() {
        new Roll(MIN_VAL, MIN_VAL * 2, VALID_ROLL);
    }

    @Test
    public void setMinAndMaxValueToSamePositiveInt() {
        int sameVal = MAX_VAL;
        Roll roll = new Roll(sameVal, sameVal, sameVal);

        assertEquals(roll.getMinValue(), sameVal);
        assertEquals(roll.getMaxValue(), sameVal);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void setValueToNegativeInt() {
        new Roll(MIN_VAL, MAX_VAL, INVALID_ROLL);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void setValueToPositiveButOutOfRangeInt() {
        new Roll(MIN_VAL, MAX_VAL, MIN_VAL - 1);
    }

    @Test
    public void testEqualsWithNull() {
        assertNotEquals(TEST_ROLL, null);
    }

    @Test
    public void testEqualsWithDifferentObjectType() {
        assertNotEquals(TEST_ROLL, new LinkedList<Roll>());
    }

    @Test
    public void testEqualsWithDifferentMinVals() {
        assertNotEquals(TEST_ROLL, new Roll(TEST_ROLL.getMinValue() - 1,
                TEST_ROLL.getMaxValue(), TEST_ROLL.getRollValue()));
    }

    @Test
    public void testEqualswithDifferentMaxVals() {
        assertNotEquals(TEST_ROLL, new Roll(TEST_ROLL.getMinValue(),
                TEST_ROLL.getMaxValue() - 1, TEST_ROLL.getRollValue()));
    }

    @Test
    public void testEqualsWithDifferentRollVals() {
        assertNotEquals(TEST_ROLL, new Roll(TEST_ROLL.getMinValue(),
                TEST_ROLL.getMaxValue(), TEST_ROLL.getRollValue() + 1));
    }

    @Test
    public void testEqualsWithSameRolls() {
        assertEquals(TEST_ROLL, new Roll(TEST_ROLL.getMinValue(),
                TEST_ROLL.getMaxValue(), TEST_ROLL.getRollValue()));
    }

    @Test
    public void testEqualsWithSameObject() {
        assertEquals(TEST_ROLL, TEST_ROLL);
    }
}
