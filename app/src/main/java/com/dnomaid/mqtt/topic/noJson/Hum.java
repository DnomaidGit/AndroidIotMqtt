package com.dnomaid.mqtt.topic.noJson;

import com.dnomaid.mqtt.topic.ActionTopic;

public class Hum implements ActionTopic
{
	private String name = "Hum";
	private String Hum;

	public String getHum() {
		return Hum;
	}

	public void setHum(String temp) {
		Hum = temp;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public String getValueTopic(TypeTopic typeTopic) {
		String str = "--.--";
		switch (typeTopic) {
			case Humidity:
				str = getHum();
				break;
			default:
				str = "??¿¿";
		}
		return str;
	}

	
}
