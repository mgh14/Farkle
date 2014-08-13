package test.engine;

import main.engine.Roll;
import main.engine.DiceScore;
import main.engine.ScoreCalculator;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class ScoresCalculatorTest {

    private ScoreCalculator scoreCalc = new ScoreCalculator();

    @Mock
    private Roll roll;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkNoPoints() {
        simulateDiceRoll(new int[]{2, 3, 4, 6, 2, 3});

        assertTrue(scoreCalc.calculateRollScore(roll).isEmpty());
        verifyDiceRollSimulation();
    }

    @Test
    public void checkOneOne() {
        testWithOnlyOneResult(new int[]{1, 3, 4, 6, 2, 3}, ScoreCalculator.NUM_POINTS_FOR_ONE);
    }

    @Test
    public void checkOneFive() {
        testWithOnlyOneResult(new int[]{2, 3, 4, 5, 6, 6}, ScoreCalculator.NUM_POINTS_FOR_FIVE);
    }

    @Test
    public void checkTwoOnes() {
        //testWithOnlyOneResult(new int[]{1, 1, 2, 3, 4, 6}, ScoresCalculator.NUM_POINTS_FOR_ONE * 2);
    }

    @Test
    public void checkOneOneAndOneFive() {

    }

    @Test
    public void checkTwoFives() {

    }

    @Test
    public void checkTwoOnesAndOneFive() {

    }

    @Test
    public void checkTwoOnesAndTwoFives() {

    }

    @Test
    public void checkThreeOnes() {

    }

    @Test
    public void checkThreeOnesAndAFive() {

    }

    @Test
    public void checkFourOnes() {

    }

    @Test
    public void checkFiveOnes() {

    }

    @Test
    public void checkSixOnes() {

    }

    @Test
    public void checkThreeTwos() {

    }

    @Test
    public void checkFourTwos() {

    }

    @Test
    public void checkFiveTwos() {

    }

    @Test
    public void checkSixTwos() {

    }

    @Test
    public void checkThreeThrees() {

    }

    @Test
    public void checkFourThrees() {

    }

    @Test
    public void checkFiveThrees() {

    }

    @Test
    public void checkSixThrees() {

    }

    @Test
    public void checkThreeFours() {

    }

    @Test
    public void checkFourFours() {

    }

    @Test
    public void checkFiveFours() {

    }

    @Test
    public void checkSixFours() {

    }

    @Test
    public void checkThreeFives() {

    }

    @Test
    public void checkFourFives() {

    }

    @Test
    public void checkFiveFives() {

    }

    @Test
    public void checkSixFives() {

    }

    @Test
    public void checkThreeSixes() {

    }

    @Test
    public void checkFourSixes() {

    }

    @Test
    public void checkFiveSixes() {

    }

    @Test
    public void checkSixSixes() {

    }

    @Test
    public void checkStraightInOrder() {

    }

    @Test
    public void checkStraightNotInOrder() {

    }

    @Test
    public void checkThreePairs() {

    }

    @Test
    public void checkFourOfAKindPlusAPair() {

    }

    @Test
    public void checkTwoSetsOfThree() {

    }

    private void simulateDiceRoll(int[] rollVals) {
        when(roll.getDiceVals()).thenReturn((Collection) Arrays.asList(rollVals));
    }

    private void verifyDiceRollSimulation() {
        verify(roll).getDiceVals();
    }

    private void testWithOnlyOneResult(int[] rollVals, int expectedPoints) {
        simulateDiceRoll(rollVals);

        Collection<DiceScore> results = scoreCalc.calculateRollScore(roll);
        assertEquals(1, results.size());
        DiceScore result = results.iterator().next();
        assertEquals(expectedPoints, result.getNumberOfPoints());

        verifyDiceRollSimulation();
    }

}