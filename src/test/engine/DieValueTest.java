package test.engine;

import java.util.LinkedList;

import main.engine.DieValue;
import main.engine.properties.PropertiesManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;

public class DieValueTest {

   private final DieValue TEST_ROLL = new DieValue();

  @BeforeMethod
  public void setUp() {
    PropertiesManager.loadDefaultConfig();
  }

  @Test
  public void defaultRollValueIsSix() {
    assertEquals(new DieValue().getDieValue(), PropertiesManager.DEFAULT_MAX_DIE_VALUE);
  }

    @Test
    public void testEqualsWithNull() {
        assertNotEquals(TEST_ROLL, null);
    }

    @Test
    public void testEqualsWithDifferentObjectType() {
        assertNotEquals(TEST_ROLL, new LinkedList<DieValue>());
    }

    @Test
    public void testEqualsWithDifferentRollVals() {
        assertNotEquals(TEST_ROLL, new DieValue(TEST_ROLL.getDieValue() - 1));
    }

    @Test
    public void testEqualsWithSameRolls() {
        assertEquals(TEST_ROLL, new DieValue(TEST_ROLL.getDieValue()));
    }

    @Test
    public void testEqualsWithSameObject() {
        assertEquals(TEST_ROLL, TEST_ROLL);
    }
}
