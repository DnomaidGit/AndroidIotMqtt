package com.dnomaid.mqtt.device;

import com.dnomaid.mqtt.global.Constants.TypeDevice;

public class DeviceConfig {
	private TypeDevice typeDevice; 
	private String numberDevice;
	
	public DeviceConfig(TypeDevice typeDevice, String numberDevice) {
		super();
		this.typeDevice = typeDevice;
		this.numberDevice = numberDevice;
	}
	public TypeDevice getTypeDevice() {
		return typeDevice;
	}
	public void setTypeDevice(TypeDevice typeDevice) {
		this.typeDevice = typeDevice;
	}
	public String getNumberDevice() {
		return numberDevice;
	}
	public void setNumberDevice(String numberDevice) {
		this.numberDevice = numberDevice;
	}
	
	@Override
	public String toString() {
		return typeDevice.name()+"_"+numberDevice;
	}
	
}
