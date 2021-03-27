package com.dnomaid.mqtt.device;

import java.util.ArrayList;

import com.dnomaid.mqtt.topic.Topic;

public class Device {
	private ArrayList<Topic> Topics;
	private String Gateway;
	private String Device;
	private String GroupList;
		
	public Device(String gateway, String device, String groupList) {
		super();
		Topics = new ArrayList<Topic>();
		Gateway = gateway;
		Device = device;
		GroupList = groupList;
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
			topic.setName(topic.getIdFunc()+"/"+Gateway+"/"+Device+"/"+topic.getName());
			Topics.add(topic);
		} catch (Exception e) {
			System.out.println("Error "+ e);
		}
	}
	
	public String getGateway() {
		return Gateway;
	}

	public void setGateway(String gateway) {
		Gateway = gateway;
	}

	public String getDevice() {
		return Device;
	}

	public void setDevice(String device) {
		Device = device;
	}
	
	public String getGroupList() {
		return GroupList;
	}

	public void setGroupList(String groupList) {
		GroupList = groupList;
	}
	
	@Override
	public String toString() {
		return Device;
	}
	
}
