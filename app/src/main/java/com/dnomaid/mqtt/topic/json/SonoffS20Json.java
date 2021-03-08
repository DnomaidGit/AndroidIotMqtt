package com.dnomaid.mqtt.topic.json;

public class SonoffS20Json {
    //../cmnd/Relay0x/PowerRetain message: "ON" --> enable MQTT power retain on status update

	private String POWER;

	public SonoffS20Json() {
		this.POWER = "NONE";
	}

	public String getPOWER() { return POWER; }
	public void setPOWER(String pOWER) { POWER = pOWER; }
}
