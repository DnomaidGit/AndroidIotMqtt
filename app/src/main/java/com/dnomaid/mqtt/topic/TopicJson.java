package com.dnomaid.mqtt.topic;

import com.dnomaid.mqtt.topic.json.AqaraTempJson;
import com.dnomaid.mqtt.topic.json.SonoffS20Json;
import com.dnomaid.mqtt.topic.json.SonoffSNZB02Json;
import com.dnomaid.mqtt.topic.json.TuyaZigBeeSensorJson;
import com.dnomaid.mqtt.topic.json.XiaomiZNCZ04LM;

public class TopicJson extends Topic {
	public TopicJson(String idFunc, String name, Object type) {
		super(idFunc, name, type);
		setTypeJson(true);
	}
	
	private boolean isTypeJsonException(String nameJsonClass){
		boolean test = false;
			try {
				if(!isTypeJson()){
					throw new Exception("Not is type Json: " + getName());
				}else{
					if(!nameJsonClass.equals(getType().getClass().getName())) 
						throw new Exception("Is diferent type: " + getName());
				}	
				test = true;
			} catch (Exception e) {
				test = false;
				System.out.println("Error "+ e);
			}	
		return test;
	}
	
	public String getCast(){
		String str="--";
		Object obj = getType(); 
		if (obj instanceof AqaraTempJson) str="AqaraTempJson"; 
		if (obj instanceof SonoffS20Json) str="SonoffS20Json"; 
		if (obj instanceof SonoffSNZB02Json) str="SonoffSNZB02Json"; 
		if (obj instanceof TuyaZigBeeSensorJson) str="TuyaZigBeeSensorJson"; 
		if (obj instanceof XiaomiZNCZ04LM) str="XiaomiZNCZ04LM"; 
		return str;
	}
	
	public String getValueTopic(TypeTopic typeTopic) {	
		String value = "--.--";
		String str = getCast();
		switch (str) {
		case "AqaraTempJson":
			AqaraTempJson aqaraTemp=new AqaraTempJson();
			if(isTypeJsonException(AqaraTempJson.class.getName()))aqaraTemp = (AqaraTempJson)getType();			
			value = aqaraTemp.getValueTopic(typeTopic);
			break;
		case "SonoffS20Json":
			SonoffS20Json sonoffS20Json=new SonoffS20Json();
			if(isTypeJsonException(SonoffS20Json.class.getName()))sonoffS20Json = (SonoffS20Json)getType();			
			value = sonoffS20Json.getValueTopic(typeTopic);
			break;
		case "SonoffSNZB02Json":
			SonoffSNZB02Json sonoffSNZB02Json=new SonoffSNZB02Json();
			if(isTypeJsonException(SonoffSNZB02Json.class.getName()))sonoffSNZB02Json = (SonoffSNZB02Json)getType();	
			value = sonoffSNZB02Json.getValueTopic(typeTopic);
			break;
		case "TuyaZigBeeSensorJson":
			TuyaZigBeeSensorJson tuyaZigBeeSensorJson = new TuyaZigBeeSensorJson();
			if(isTypeJsonException(TuyaZigBeeSensorJson.class.getName()))tuyaZigBeeSensorJson = (TuyaZigBeeSensorJson)getType();
			value = tuyaZigBeeSensorJson.getValueTopic(typeTopic);
			break;									
		case "XiaomiZNCZ04LM":
			XiaomiZNCZ04LM xiaomiZNCZ04LM = new XiaomiZNCZ04LM();
			if(isTypeJsonException(XiaomiZNCZ04LM.class.getName()))xiaomiZNCZ04LM = (XiaomiZNCZ04LM)getType();			
			value = xiaomiZNCZ04LM.getValueTopic(typeTopic);
			break;
		default:
			value = "??¿¿";
			break;
		}
		return value;		
	}	

}
