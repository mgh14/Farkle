package main.engine;

import main.engine.properties.PropertiesManager;

public class Roll {
	
	private int value;
	
	public Roll() {
		setRollValue(PropertiesManager.DEFAULT_MAX_DIE_VALUE);
	}
	
	public Roll(int ROLL_VALUE) {
		setRollValue(ROLL_VALUE);
	}
	
	public void setRollValue(int newVal) {
		PropertiesManager.verifyDieValueIsValid(newVal);

        value = newVal;
	}
	
	public int getRollValue() {
		return value;
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
