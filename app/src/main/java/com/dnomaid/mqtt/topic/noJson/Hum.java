package com.dnomaid.mqtt.topic.noJson;

public class Hum {
	private String name = "Hum";
	private String Hum;

	public Hum() {
		Hum = "0.0";
	}

	public String getHum() { return Hum; }
	public void setHum(String temp) {
		Hum = temp;
	}
	@Override
	public String toString() {
		return name;
	}

}
