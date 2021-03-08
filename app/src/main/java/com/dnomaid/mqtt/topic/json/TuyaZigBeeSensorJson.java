package com.dnomaid.mqtt.topic.json;

public class TuyaZigBeeSensorJson {
	//{"battery":100,"humidity":00.00,"linkquality":00,"temperature":00.00,"voltage":3200}
	private Double battery;
	private Double humidity;
	private Double linkquality;
	private Double temperature;
	private Double voltage;

	public TuyaZigBeeSensorJson() {
		this.battery = 0.0;
		this.humidity = 0.0;
		this.temperature = 0.0;
		this.voltage = 0.0;
	}

	public Double getBattery() {
		return battery;
	}
	public void setBattery(Double battery) {
		this.battery = battery;
	}
	public Double getHumidity() {
		return humidity;
	}
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
	public Double getLinkquality() {
		return linkquality;
	}
	public void setLinkquality(Double linkquality) {
		this.linkquality = linkquality;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Double getVoltage() {
		return voltage;
	}
	public void setVoltage(Double voltage) {
		this.voltage = voltage;
	}
	
}
