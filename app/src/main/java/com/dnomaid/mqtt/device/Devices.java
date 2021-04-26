package com.dnomaid.mqtt.device;

import android.content.Context;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.global.Constants;
import com.dnomaid.mqtt.global.Notify;
import com.dnomaid.mqtt.topic.TopicJson;
import com.dnomaid.mqtt.topic.TopicNoJson;
import com.dnomaid.mqtt.topic.json.*;
import com.dnomaid.mqtt.topic.noJson.*;

public class Devices implements Constants {	
    private ArrayList<DeviceConfig> DevicesConfig;
	private ArrayList<Device> Devices;
	private Context context;
	private static Persistence persistence;

	private static class DevicesHolder {
		public static Devices instance = new Devices();
	}
	private Devices() {
		DevicesConfig  = new ArrayList<>();
		Devices  = new ArrayList<>();
	}
	public static Devices getInst() {
		return DevicesHolder.instance;
	}

    public void newDevice(TypeDevice typeDevice, String numberDevice) {
		DeviceConfig deviceConfig = newDevicePersist(typeDevice, numberDevice);
    	DevicesConfig.add(deviceConfig);
    	selectDevice(typeDevice, numberDevice);
	}
    public void deleteDevice(DeviceConfig deviceConfig){
		for (int i = 0; i < getDevices().size(); ++i) {
			if (deviceConfig.toString().equals(getDevices().get(i).toString())){
				getDevices().remove(i);
			}
		}
		for (int i = 0; i < getDevicesConfig().size(); ++i) {
			if (deviceConfig.toString().equals(getDevicesConfig().get(i).toString())){
				getDevicesConfig().remove(i);
			}
		}
		removeDevicePersist(deviceConfig);
	}
	public void restoreDevice() {
		restoreDevicePersist();
		for (DeviceConfig devicesConfig:getDevicesConfig()
		) {
			selectDevice(devicesConfig.getTypeDevice(), devicesConfig.getNumberDevice());
		}
	}
    public ArrayList<DeviceConfig> getDevicesConfig() {return DevicesConfig;}
	public void setDevicesConfig(ArrayList<DeviceConfig> devicesConfig) {
		DevicesConfig = devicesConfig;
	}
	public ArrayList<Device> getDevices() {return Devices;}
	public ArrayList<Device> getRelays() {
		ArrayList<Device> filterList = (ArrayList<Device>) getDevices().stream()
				  .filter(c -> c.getGroupList().equals(GroupList.Relay) 
						  || c.getGroupList().equals(GroupList.RelaySensorClimate))
				  .collect(Collectors.toList()); 		
		return filterList;		
		}
	public ArrayList<Device> getSensorsClimate() {
		ArrayList<Device> filterList = (ArrayList<Device>) getDevices().stream()
				  .filter(c -> c.getGroupList().equals(GroupList.SensorClimate) 
						  || c.getGroupList().equals(GroupList.RelaySensorClimate))
				  .collect(Collectors.toList()); 		
		return filterList;
		}
	public String getPublishTopicRelay(Integer numberRelay) {
		String PublishTopicRelay = "PublishTopic01Relay??";
		if(numberRelay>0&getRelays().size()>=numberRelay) {
			PublishTopicRelay = getRelays().get(numberRelay-1).getTopics().get(1).getName();
		}
		return PublishTopicRelay;
	}    
	
	private void selectDevice (TypeDevice typeDevice, String numberDevice){
		String nametopic01 = "";
		String nametopic02 = "";
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
			device = createDevice(typeGateway, typeDevice, numberDevice, groupList, topicNoJson01, topicNoJson02);		
	    	Devices.add(device);
			break;
		case SonoffSNZB02:
			typeGateway = TypeGateway.CC2531_1;
			groupList = GroupList.SensorClimate;
			nametopic01 = groupList+"_1";
			topicJson01 = new TopicJson(STAT_PREFIX, nametopic01, new SonoffSNZB02Json());
			device = createDevice(typeGateway, typeDevice, numberDevice, groupList, topicJson01);	
	    	Devices.add(device);
			break;
		case AqaraTemp:
			typeGateway = TypeGateway.CC2531_1;
			groupList = GroupList.SensorClimate;
			nametopic01 = groupList+"_1";
			topicJson01 = new TopicJson(STAT_PREFIX, nametopic01, new AqaraTempJson());
			device = createDevice(typeGateway, typeDevice, numberDevice, groupList, topicJson01);	
	    	Devices.add(device);
			break;
		case TuyaZigBeeSensor:
			typeGateway = TypeGateway.CC2531_1;
			groupList = GroupList.SensorClimate;
			nametopic01 = groupList+"_1";
			topicJson01 = new TopicJson(STAT_PREFIX, nametopic01, new TuyaZigBeeSensorJson());
			device = createDevice(typeGateway, typeDevice, numberDevice, groupList, topicJson01);	
	    	Devices.add(device);
			break;
		case XiaomiZNCZ04LM:
			typeGateway = TypeGateway.CC2531_1;
			groupList = GroupList.RelaySensorClimate;
			nametopic01 = groupList+"_1";
			nametopic02 = nametopic01+"/set";
			topicJson01 = new TopicJson(MIX_PREFIX, nametopic01, new XiaomiZNCZ04LM());
			topicNoJson02 = new TopicNoJson(MIX_PREFIX, nametopic02, new Set());
			device = createDevice(typeGateway, typeDevice, numberDevice, groupList, topicJson01, topicNoJson02);		
	    	Devices.add(device);
			break;
		default:
			break;
		}
		
	}

	private Device createDevice(TypeGateway gateway, TypeDevice typeDevice, String numberDevice, GroupList groupList, TopicNoJson topic01, TopicNoJson topic02){
		Device device = new Device(gateway,typeDevice,numberDevice,groupList);
		device.addTopic(topic01);
		device.addTopic(topic02);
		return device;
	}	
	private Device createDevice(TypeGateway gateway, TypeDevice typeDevice, String numberDevice, GroupList groupList, TopicJson topic01){
		Device device = new Device(gateway,typeDevice,numberDevice,groupList);
		device.addTopic(topic01);		
		return device;
	}
	private Device createDevice(TypeGateway gateway, TypeDevice typeDevice, String numberDevice, GroupList groupList, TopicJson topic01, TopicNoJson topic02){
		Device device = new Device(gateway,typeDevice,numberDevice,groupList);
		device.addTopic(topic01);
		device.addTopic(topic02);
		return device;
	}

	public void persistence(Context context){
		this.context = context;
		persistence = new Persistence(this.context);
		restoreDevice();
	}
	private DeviceConfig newDevicePersist(TypeDevice typeDevice, String numberDevice) {
		DeviceConfig deviceConfig = null;
		try {
			deviceConfig = persistence.newDevice(typeDevice,numberDevice);
		}
		catch (PersistenceException e)
		{
			Notify.toast(context,context.getString(R.string.failedPersistDev));
		}
		return deviceConfig;
	}
	private void restoreDevicePersist() {
		try {
			setDevicesConfig(persistence.restoreDevice(context));
		}
		catch (PersistenceException e) {
			Notify.toast(context,context.getString(R.string.failedPersistRestDev));
		}
	}
	private void removeDevicePersist(DeviceConfig deviceConfig) {
		persistence.deleteDevice(deviceConfig);
	}
}
