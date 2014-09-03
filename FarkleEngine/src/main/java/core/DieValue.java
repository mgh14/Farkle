package core;

import properties.PropertiesManager;

public class DieValue {
	
	private int value;
	
	public DieValue() {
		setDieValue(PropertiesManager.DEFAULT_MAX_DIE_VALUE);
	}
	
	public DieValue(int ROLL_VALUE) {
		setDieValue(ROLL_VALUE);
	}
	
	public void setDieValue(int newVal) {
		PropertiesManager.verifyDieValueIsValid(newVal);
        value = newVal;
	}
	
	public int getDieValue() {
		return value;
	}

  @Override
  public boolean equals(Object other) {
    return !(other == null || !(other instanceof DieValue)) &&
            getDieValue() == ((DieValue) other).getDieValue();
  }
}
