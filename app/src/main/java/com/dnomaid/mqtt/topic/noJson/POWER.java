package com.dnomaid.mqtt.topic.noJson;

import com.dnomaid.mqtt.topic.ActionTopic;

public class POWER implements ActionTopic {
	private String name = "POWER";
	private String POWER;

	public String getPOWER() {
		return POWER;
	}

	public void setPOWER(String pOWER) {
		POWER = pOWER;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public String getValueTopic(TypeTopic typeTopic) {
		String str = "--.--";
		switch (typeTopic) {
			case Power:
				str = getPOWER();
				break;
			default:
				str = "??¿¿";
		}
		return str;
	}

}
