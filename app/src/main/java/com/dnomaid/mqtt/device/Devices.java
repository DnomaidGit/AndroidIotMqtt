package com.dnomaid.mqtt.device;

import java.util.ArrayList;

import com.dnomaid.mqtt.global.Constants;
import com.dnomaid.mqtt.topic.TopicJson;
import com.dnomaid.mqtt.topic.TopicNoJson;
import com.dnomaid.mqtt.topic.json.*;
import com.dnomaid.mqtt.topic.noJson.*;

public class Devices implements Constants {	
    private ArrayList<Device> Devices;
    private ArrayList<Device> Relays;
    private ArrayList<Device> SensorsClimate;

    private static Devices myGlobal = null;

    public  static synchronized Devices getInst() {
        if (myGlobal==null) {
            myGlobal=new Devices();
        }
        return myGlobal;
    }    
    Devices(){		
		Devices  = new ArrayList<>();
		Relays  = new ArrayList<>();
		SensorsClimate  = new ArrayList<>();
		selectDevice(TypeDevice.SonoffS20, "1");
		selectDevice(TypeDevice.SonoffS20, "2");
		selectDevice(TypeDevice.SonoffS20, "3");
		selectDevice(TypeDevice.SonoffS20, "4");
		selectDevice(TypeDevice.SonoffS20, "5");
		selectDevice(TypeDevice.SonoffSNZB02, "1");
		selectDevice(TypeDevice.AqaraTemp, "1");
		selectDevice(TypeDevice.TuyaZigBeeSensor, "1");
		selectDevice(TypeDevice.XiaomiZNCZ04LM, "1");
		
    }
    public void addDevice(Device device, GroupList groupList){
    	Devices.add(device);
    	 switch (groupList) {
		case Relay:
			Relays.add(device);			
			break;
		case SensorClimate:
			SensorsClimate.add(device);						
			break;
		case RelaySensorClimate:
			Relays.add(device);			
			SensorsClimate.add(device);						
			break;
		default:
			break;
		}   	
    }
    
	public ArrayList<Device> getDevices() {return Devices;}
	public ArrayList<Device> getRelays() {return Relays;}
	public ArrayList<Device> getSensors() {return SensorsClimate;}
	
	public void selectDevice (TypeDevice typeDevice, String numberDevice){
		String nametopic01 = "";
		String nametopic02 = "";
		String nameDevice = typeDevice+"_"+numberDevice;
		GroupList groupList;
		TypeGateway typeGateway;
		TopicNoJson topicNoJson01;
		TopicNoJson topicNoJson02;
		TopicJson topicJson01;
		Device device;
		
		switch (typeDevice) {
		case SonoffS20:
			typeGateway = TypeGateway.Router_1;
			groupList = GroupList.Relay;
			nametopic01 = groupList+"_1"+"/POWER";
			nametopic02 = nametopic01;			
			topicNoJson01 = new TopicNoJson(STAT_PREFIX, nametopic01, new POWER());
			topicNoJson02 = new TopicNoJson(CMND_PREFIX, nametopic02, new POWER());
			device = createDevice(typeGateway.name(),nameDevice, groupList.name(), topicNoJson01, topicNoJson02);		
			addDevice(device, groupList);
			break;
		case SonoffSNZB02:
			typeGateway = TypeGateway.CC2531_1;
			groupList = GroupList.SensorClimate;
			nametopic01 = groupList+"_1";
			topicJson01 = new TopicJson(STAT_PREFIX, nametopic01, new SonoffSNZB02Json());
			device = createDevice(typeGateway.name(),nameDevice, groupList.name(), topicJson01);	
			addDevice(device, groupList);
			break;
		case AqaraTemp:
			typeGateway = TypeGateway.CC2531_1;
			groupList = GroupList.SensorClimate;
			nametopic01 = groupList+"_1";
			topicJson01 = new TopicJson(STAT_PREFIX, nametopic01, new AqaraTempJson());
			device = createDevice(typeGateway.name(),nameDevice, groupList.name(), topicJson01);	
			addDevice(device, groupList);
			break;
		case TuyaZigBeeSensor:
			typeGateway = TypeGateway.CC2531_1;
			groupList = GroupList.SensorClimate;
			nametopic01 = groupList+"_1";
			topicJson01 = new TopicJson(STAT_PREFIX, nametopic01, new TuyaZigBeeSensorJson());
			device = createDevice(typeGateway.name(),nameDevice, groupList.name(), topicJson01);	
			addDevice(device, groupList);
			break;
		case XiaomiZNCZ04LM:
			typeGateway = TypeGateway.CC2531_1;
			groupList = GroupList.RelaySensorClimate;
			nametopic01 = groupList+"_1";
			nametopic02 = nametopic01+"/set";
			topicJson01 = new TopicJson(MIX_PREFIX, nametopic01, new XiaomiZNCZ04LM());
			topicNoJson02 = new TopicNoJson(MIX_PREFIX, nametopic02, new Set());
			device = createDevice(typeGateway.name(),nameDevice, groupList.name(), topicJson01, topicNoJson02);		
			addDevice(device, groupList);
			break;
		default:
			break;
		}
		
	}
	
	public Device createDevice(String gateway, String typeDevice, String groupList, TopicNoJson topic01, TopicNoJson topic02){
		Device device = new Device(gateway,typeDevice,groupList);
		device.addTopic(topic01);
		device.addTopic(topic02);
		return device;
	}
	
	public Device createDevice(String gateway, String typeDevice, String groupList, TopicJson topic01){
		Device device = new Device(gateway,typeDevice, groupList);
		device.addTopic(topic01);		
		return device;
	}

	public Device createDevice(String gateway, String typeDevice, String groupList, TopicJson topic01, TopicNoJson topic02){
		Device device = new Device(gateway,typeDevice,groupList);
		device.addTopic(topic01);
		device.addTopic(topic02);
		return device;
	}

	public String getPublishTopicRelay(Integer numberRelay) {
		String PublishTopicRelay = "PublishTopic01Relay??";
		if(numberRelay>0&getRelays().size()>=numberRelay) {
			PublishTopicRelay = getRelays().get(numberRelay-1).getTopics().get(1).getName();
		}
		return PublishTopicRelay;
	}
}
