package com.dnomaid.mqtt.topic.noJson;

import com.dnomaid.mqtt.topic.ActionTopic;

public class Set implements ActionTopic{
	private String name = "Set";
	private String Set;

	public String getSet() {
		return Set;
	}

	public void setSet(String set) {
		Set = set;
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
				str = getSet();
				break;
			default:
				str = "??¿¿";
		}
		return str;
	}

}
