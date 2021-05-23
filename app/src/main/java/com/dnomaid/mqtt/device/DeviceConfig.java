package com.dnomaid.mqtt.device;

import com.dnomaid.mqtt.global.Constants.TypeDevice;

public class DeviceConfig {
	private TypeDevice typeDevice; 
	private String numberDevice;
	private String alias;
	private long persistenceId = -1;
	
	public DeviceConfig(TypeDevice typeDevice, String numberDevice, String alias) {
		super();
		this.typeDevice = typeDevice;
		this.numberDevice = numberDevice;
		this.alias = alias;
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
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
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
