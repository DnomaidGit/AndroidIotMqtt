package com.dnomaid.mqtt.device;

import java.util.ArrayList;

import com.dnomaid.mqtt.global.Constants;
import com.dnomaid.mqtt.topic.TopicJson;
import com.dnomaid.mqtt.topic.TopicNoJson;
import com.dnomaid.mqtt.topic.json.*;
import com.dnomaid.mqtt.topic.noJson.Hum;
import com.dnomaid.mqtt.topic.noJson.POWER;
import com.dnomaid.mqtt.topic.noJson.Set;
import com.dnomaid.mqtt.topic.noJson.Temp;

public class Devices implements Constants {	
    private ArrayList<Device> Devices;
    private ArrayList<Device> Relays;
    private Device Device010,Device020,Device030,Device040,Device050;
    private Device Device110;
	private Device Device120;
	private Device Device130;
	private Device Device210;
	private Device Device310;

    private TopicNoJson dev010Topic001,dev010Topic002;
    private TopicNoJson dev020Topic001,dev020Topic002;
    private TopicNoJson dev030Topic001,dev030Topic002;
    private TopicNoJson dev040Topic001,dev040Topic002;
    private TopicNoJson dev050Topic001,dev050Topic002;
    private TopicJson dev110Topic001;
    private TopicJson dev120Topic001;
	private TopicJson dev130Topic001;
    private TopicNoJson dev210Topic001,dev210Topic002;
    private TopicJson dev310Topic001;
    private TopicNoJson dev310Topic002;
    

    private static Devices myGlobal = null;

    public  static synchronized Devices getInst() {
        if (myGlobal==null) {
            myGlobal=new Devices();
        }
        return myGlobal;
    }    
    Devices(){
		Device010 = new Device(GATEWAY01,DEVICE010);
		dev010Topic001 = new TopicNoJson(STAT_PREFIX, RELAY011, new POWER());
		dev010Topic002 = new TopicNoJson(CMND_PREFIX, RELAY011, new POWER());
		Device010.addTopic(dev010Topic001);
		Device010.addTopic(dev010Topic002);		
		Device020 = new Device(GATEWAY01,DEVICE020);
		dev020Topic001 = new TopicNoJson(STAT_PREFIX, RELAY021, new POWER());
		dev020Topic002 = new TopicNoJson(CMND_PREFIX, RELAY021, new POWER());		
		Device020.addTopic(dev020Topic001);
		Device020.addTopic(dev020Topic002);		
		Device030 = new Device(GATEWAY01,DEVICE030);
		dev030Topic001 = new TopicNoJson(STAT_PREFIX, RELAY031, new POWER());
		dev030Topic002 = new TopicNoJson(CMND_PREFIX, RELAY031, new POWER());		
		Device030.addTopic(dev030Topic001);
		Device030.addTopic(dev030Topic002);		
		Device040 = new Device(GATEWAY01,DEVICE040);
		dev040Topic001 = new TopicNoJson(STAT_PREFIX, RELAY041, new POWER());
		dev040Topic002 = new TopicNoJson(CMND_PREFIX, RELAY041, new POWER());		
		Device040.addTopic(dev040Topic001);
		Device040.addTopic(dev040Topic002);		
		Device050 = new Device(GATEWAY01,DEVICE050);
		dev050Topic001 = new TopicNoJson(STAT_PREFIX, RELAY051, new POWER());
		dev050Topic002 = new TopicNoJson(CMND_PREFIX, RELAY051, new POWER());		
		Device050.addTopic(dev050Topic001);
		Device050.addTopic(dev050Topic002);		

		Device110 = new Device(GATEWAY02,DEVICE110);
		dev110Topic001 = new TopicJson(STAT_PREFIX,SENSOR111,new SonoffSNZB02Json());
		Device110.addTopic(dev110Topic001);
		Device120 = new Device(GATEWAY02,DEVICE120);
		dev120Topic001 = new TopicJson(STAT_PREFIX,SENSOR121,new AqaraTempJson());		
		Device120.addTopic(dev120Topic001);
		Device130 = new Device(GATEWAY02,DEVICE130);
		dev130Topic001 = new TopicJson(STAT_PREFIX,SENSOR131,new TuyaZigBeeSensorJson());
		Device130.addTopic(dev130Topic001);

		Device210 = new Device(GATEWAY01,DEVICE210);
		dev210Topic001 = new TopicNoJson(STAT_PREFIX,SENSOR211,new Temp());
		dev210Topic002 = new TopicNoJson(STAT_PREFIX,SENSOR212,new Hum());
		Device210.addTopic(dev210Topic001);
		Device210.addTopic(dev210Topic002);

		Device310 = new Device(GATEWAY02,DEVICE310);
		dev310Topic001 = new TopicJson(MIX_PREFIX, SENSOR311, new XiaomiZNCZ04LM());
		dev310Topic002 = new TopicNoJson(MIX_PREFIX, RELAY311, new Set());
		Device310.addTopic(dev310Topic001);
		Device310.addTopic(dev310Topic002);		
		
		Devices  = new ArrayList<>();
		Devices.add(Device010);
		Devices.add(Device020);
		Devices.add(Device030);
		Devices.add(Device040);
		Devices.add(Device050);
		Devices.add(Device110);
		Devices.add(Device120);
		Devices.add(Device130);
		Devices.add(Device210);
		Devices.add(Device310);
		Relays  = new ArrayList<>();
		Relays.add(Device010);
		Relays.add(Device020);
		Relays.add(Device030);
		Relays.add(Device040);
		Relays.add(Device050);
		Relays.add(Device310);

    }
	public ArrayList<Device> getDevices() {return Devices;}
	public ArrayList<Device> getRelays() {return Relays;}

	public Device getDevice010() {return Device010;}
	public Device getDevice020() {return Device020;}
	public Device getDevice030() {return Device030;}
	public Device getDevice040() {return Device040;}
	public Device getDevice050() {return Device050;}
	public Device getDevice110() {return Device110;}
	public Device getDevice120() {return Device120;}
	public Device getDevice130() {return Device130;}
	public Device getDevice210() {return Device210;}
	public Device getDevice310() {return Device310;}
	
    public TopicNoJson getDev010Topic001() {return dev010Topic001;}
	public TopicNoJson getDev010Topic002() {return dev010Topic002;}
	public TopicNoJson getDev020Topic001() {return dev020Topic001;}
	public TopicNoJson getDev020Topic002() {return dev020Topic002;}
	public TopicNoJson getDev030Topic001() {return dev030Topic001;}
	public TopicNoJson getDev030Topic002() {return dev030Topic002;}
	public TopicNoJson getDev040Topic001() {return dev040Topic001;}
	public TopicNoJson getDev040Topic002() {return dev040Topic002;}
	public TopicNoJson getDev050Topic001() {return dev050Topic001;}
	public TopicNoJson getDev050Topic002() {return dev050Topic002;}
	public TopicJson getDev110Topic001() {return dev110Topic001;}
	public TopicJson getDev120Topic001() {return dev120Topic001;}
	public TopicJson getDev130Topic001() {return dev130Topic001;}
	public TopicNoJson getDev210Topic001() {return dev210Topic001;}
	public TopicNoJson getDev210Topic002() {return dev210Topic002;}
	public TopicJson getDev310Topic001() {return dev310Topic001;}
	public TopicNoJson getDev310Topic002() {return dev310Topic002;}
        
}
