package Engine;

public class Roll {
	
	private int value;
	private int minValue;
	private int maxValue;

    private final int DEFAULT_MIN_VALUE = 1;
	private final int DEFAULT_MAX_VALUE = 6;

    public final int ERROR_VALUE = -1;
	
	public Roll() {
		assignClassVars(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, ERROR_VALUE);
	}
	
	public Roll(int MIN_VALUE, int MAX_VALUE, int ROLL_VALUE) {
		assignClassVars(MIN_VALUE, MAX_VALUE, ROLL_VALUE);
	}
	
	private void assignClassVars(int minVal, int maxVal, int rollVal) {
		if(minVal > maxVal) {
			throw new IllegalArgumentException("minimum value is less than maximum value");
		}
		if(minVal < 0) {
			throw new IllegalArgumentException("minimum value must be positive");
		}
		
		minValue = minVal;
		maxValue = maxVal;
		value = rollVal;
	}
	
	public void setRollValue(int newVal) {
		value = newVal;
	}
	
	public int getRollValue() {
		return value;
	}
	
	public boolean valueIsValid(int value) {
		return minValue <= value && value <= maxValue;
	}
	
	public boolean valueIsValid(Roll roll) {
		return valueIsValid(roll.getRollValue());
	}
}
