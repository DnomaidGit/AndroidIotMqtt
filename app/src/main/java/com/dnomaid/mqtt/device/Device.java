package com.dnomaid.mqtt.device;

import java.util.ArrayList;

import com.dnomaid.mqtt.global.Constants;
import com.dnomaid.mqtt.topic.Topic;

public class Device implements Constants {
	private ArrayList<Topic> Topics;
	private TypeGateway Gateway;
	private TypeDevice Device;
	private GroupList GroupList;
	private String NameDevice;
	private String NumberDevice;
	
	public Device(TypeGateway gateway, TypeDevice device, String numberDevice, GroupList groupList) {
		super();
		Topics = new ArrayList<Topic>();
		Gateway = gateway;
		Device = device;
		GroupList = groupList;
		NumberDevice = numberDevice;
		NameDevice = setNameDevice(device, numberDevice);
	}

	public ArrayList<? extends Topic> getTopics() {
		try {
			if(Topics==null)throw new Exception("Null ArrayList<Topic>");
		} catch (Exception e) {
			System.out.println("Error "+ e);
		}
		return Topics;
		}
	
	public void addTopic(Topic topic) {
		try {
			topic.setName(topic.getIdFunc()+"/"+Gateway+"/"+NameDevice+"/"+topic.getName());
			Topics.add(topic);
		} catch (Exception e) {
			System.out.println("Error "+ e);
		}
	}
		
	public TypeGateway getGateway() {
		return Gateway;
	}

	public TypeDevice getDevice() {
		return Device;
	}

	public GroupList getGroupList() {
		return GroupList;
	}
	
	public String getNameDevice() {
		return NameDevice;
	}

	
	public String getNumberDevice() {
		return NumberDevice;
	}

	private String setNameDevice(TypeDevice device, String NumberDevice) {
		return device.name() + "_" + NumberDevice;
	}

	@Override
	public String toString() {
		return NameDevice;
	}
	
}
