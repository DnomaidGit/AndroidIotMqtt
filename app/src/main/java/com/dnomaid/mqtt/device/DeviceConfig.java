package com.dnomaid.mqtt.device;

import com.dnomaid.mqtt.global.Constants.TypeDevice;

public class DeviceConfig {
	private TypeDevice typeDevice; 
	private String numberDevice;
	private long persistenceId = -1;
	
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

	public void assignPersistenceId(long id) {
		persistenceId = id;
	}
	public long persistenceId() {
		return persistenceId;
	}
	
	@Override
	public String toString() {
		return typeDevice.name()+"_"+numberDevice;
	}
	
}
