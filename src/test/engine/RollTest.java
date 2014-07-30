package test.engine;

import java.util.LinkedList;

import main.engine.Roll;
import main.engine.properties.PropertiesManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;

public class RollTest {

   private final Roll TEST_ROLL = new Roll();

  @BeforeMethod
  public void setUp() {
    PropertiesManager.loadDefaultConfig();
  }

  @Test
  public void defaultRollValueIsSix() {
    assertEquals(new Roll().getRollValue(), PropertiesManager.DEFAULT_MAX_DIE_VALUE);
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
    public void testEqualsWithDifferentRollVals() {
        assertNotEquals(TEST_ROLL, new Roll(TEST_ROLL.getRollValue() - 1));
    }

    @Test
    public void testEqualsWithSameRolls() {
        assertEquals(TEST_ROLL, new Roll(TEST_ROLL.getRollValue()));
    }

    @Test
    public void testEqualsWithSameObject() {
        assertEquals(TEST_ROLL, TEST_ROLL);
    }
}
