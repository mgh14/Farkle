package main.Engine;

import main.Engine.Properties.PropertiesManager;

public class Roll {
	
	private int value;

    public static final int DEFAULT_ROLL_VALUE = PropertiesManager.DEFAULT_MAX_DIE_VALUE;
	
	public Roll() {
		assignClassVars(DEFAULT_ROLL_VALUE);
	}
	
	public Roll(int ROLL_VALUE) {
		assignClassVars(ROLL_VALUE);
	}
	
	public void setRollValue(int newVal) {
		PropertiesManager.verifyDieValueIsValid(newVal);

        value = newVal;
	}
	
	public int getRollValue() {
		return value;
	}

    private void assignClassVars(int rollVal) {
        setRollValue(rollVal);
    }

    @Override
    public boolean equals(Object other) {
        if(other == null || !(other instanceof Roll)) {
            return false;
        }

        Roll otherRoll = (Roll) other;

        return getRollValue() == otherRoll.getRollValue();
    }
}
