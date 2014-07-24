package Engine;

public class Roll {
	
	private int value;
	private int minValue;
	private int maxValue;

    private final int DEFAULT_MIN_VALUE = 1;
	private final int DEFAULT_MAX_VALUE = 6;

    public static final int DEFAULT_ROLL_VALUE = 1;
	
	public Roll() {
		assignClassVars(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, DEFAULT_ROLL_VALUE);
	}
	
	public Roll(int MIN_VALUE, int MAX_VALUE, int ROLL_VALUE) {
		assignClassVars(MIN_VALUE, MAX_VALUE, ROLL_VALUE);
	}
	
	public void setRollValue(int newVal) {
		if(!valueIsValid(newVal)) {
            throw new IllegalArgumentException("Value is less than minimum value");
        }

        value = newVal;
	}
	
	public int getRollValue() {
		return value;
	}

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }
	
	public boolean valueIsValid(int value) {
		return minValue <= value && value <= maxValue;
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

        setRollValue(rollVal);
    }

    @Override
    public boolean equals(Object other) {
        if(other == null || !(other instanceof Roll)) {
            return false;
        }

        Roll otherRoll = (Roll) other;

        return getMinValue() == otherRoll.getMinValue() &&
                getMaxValue() == otherRoll.getMaxValue() &&
                getRollValue() == otherRoll.getRollValue();
    }
}
