package com.dnomaid.mqtt.device;

import java.util.ArrayList;

import com.dnomaid.mqtt.topic.Topic;

public class Device {
	private ArrayList<Topic> Topics;
	private String Gateway;
	private String Device;
		
	public Device(String gateway, String device) {
		super();
		Topics = new ArrayList<Topic>();
		Gateway = gateway;
		Device = device;
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
	
	@Override
	public String toString() {
		return Device;
	}
	
}
