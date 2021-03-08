package com.dnomaid.mqtt.topic.noJson;

public class Temp {
	private String name = "Temp";
	private String Temp;

	public Temp() {
		Temp = "0.0";
	}

	public String getTemp() { return Temp; }
	public void setTemp(String temp) { Temp = temp; }
	@Override
	public String toString() {
		return name;
	}

}
