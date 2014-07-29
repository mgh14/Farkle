package test.engine;

import main.engine.Roll;

public class RollTest {

    private final int MIN_VAL = 0;
    private final int MAX_VAL = 1000;

    private final int VALID_ROLL = 1;
    private final int INVALID_ROLL = -1;

    private final Roll TEST_ROLL = new Roll(VALID_ROLL);

    /*@Test
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
    }*/

    /*@Test
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
    }*/
}
