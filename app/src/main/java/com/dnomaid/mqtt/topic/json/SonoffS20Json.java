package com.dnomaid.mqtt.topic.json;

import com.dnomaid.mqtt.topic.ActionTopic;

public class SonoffS20Json implements ActionTopic {
    //../cmnd/Relay0x/PowerRetain message: "ON" --> enable MQTT power retain on status update

	private String POWER;

	public SonoffS20Json() {
		this.POWER = "NONE";
	}

	public String getPOWER() { return POWER; }
	public void setPOWER(String pOWER) { POWER = pOWER; }

	@Override
	public String getValueTopic(TypeTopic typeTopic) {
		String str = "--.--";
		switch (typeTopic) {
			case Power:
				str = String.valueOf(getPOWER());
				break;
			default:
				str = "??¿¿";
		}
		return str;
	}
}
