package com.dnomaid.mqtt.topic.json;

public class XiaomiZNCZ04LM {
	                          //{"consumption":0.21,"current":0,"energy":0.21,"linkquality":78,"power":0,"state":"OFF","temperature":17,"voltage":230}
	//{"consumer_connected":true,"consumption":0.83,"current":0,"energy":0.83,"linkquality":23,"power":0,"state":"OFF","temperature":17,"voltage":230}
	private Boolean consumer_connected;
	private Double consumption;
	private Double current;
	private Double energy;
	private Double linkquality;
	private Double power;
	private String state;
	private Double temperature;
	private Double voltage;

	public XiaomiZNCZ04LM() {
		this.consumption = 0.0;
		this.current = 0.0;
		this.energy = 0.0;
		this.power = 0.0;
		this.state = "NONE";
		this.temperature = 0.0;
		this.voltage = 0.0;
	}

	public Boolean getConsumer_connected() {
		return consumer_connected;
	}
	public void setConsumer_connected(Boolean consumer_connected) {
		this.consumer_connected = consumer_connected;
	}
	public Double getConsumption() {
		return consumption;
	}
	public void setConsumption(Double consumption) {
		this.consumption = consumption;
	}
	public Double getCurrent() {
		return current;
	}
	public void setCurrent(Double current) {
		this.current = current;
	}
	public Double getEnergy() {
		return energy;
	}
	public void setEnergy(Double energy) {
		this.energy = energy;
	}	
	public Double getLinkquality() {
		return linkquality;
	}
	public void setLinkquality(Double linkquality) {
		this.linkquality = linkquality;
	}
	public Double getPower() {
		return power;
	}
	public void setPower(Double power) {
		this.power = power;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
