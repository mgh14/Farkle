package main.Engine.Properties;

public class DieValueEvaluator {

    public void verifyMinAndMaxValid(int min, int max) {
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
    }
}