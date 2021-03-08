package com.dnomaid.mqtt.topic.noJson;

public class POWER {
	private String name = "POWER";
	private String POWER;

	public POWER() {
		this.POWER = "NONE";
	}

	public String getPOWER() { return POWER; }
	public void setPOWER(String pOWER) { POWER = pOWER; }
	@Override
	public String toString() {
		return name;
	}

}
