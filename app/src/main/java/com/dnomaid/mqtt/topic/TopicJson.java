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
	public TuyaZigBeeSensorJson getTuyaZigBeeSensorJson(){
		TuyaZigBeeSensorJson tuyaZigBeeSensorJson = new TuyaZigBeeSensorJson();
		if(isTypeJsonException(TuyaZigBeeSensorJson.class.getName()))tuyaZigBeeSensorJson = (TuyaZigBeeSensorJson)getType();
		return tuyaZigBeeSensorJson;
	}

	public XiaomiZNCZ04LM getXiaomiZNCZ04LMJson(){
		XiaomiZNCZ04LM xiaomiZNCZ04LM = new XiaomiZNCZ04LM();
		if(isTypeJsonException(XiaomiZNCZ04LM.class.getName()))xiaomiZNCZ04LM = (XiaomiZNCZ04LM)getType();			
		return xiaomiZNCZ04LM;
	}
		
	public SonoffS20Json getSonoffS20Json() {		
		SonoffS20Json sonoffS20Json=new SonoffS20Json();
		if(isTypeJsonException(SonoffS20Json.class.getName()))sonoffS20Json = (SonoffS20Json)getType();			
		return sonoffS20Json;
	}

	public SonoffSNZB02Json getSonoffSNZB02Json() {		
		SonoffSNZB02Json sonoffSNZB02Json=new SonoffSNZB02Json();
		if(isTypeJsonException(SonoffSNZB02Json.class.getName()))sonoffSNZB02Json = (SonoffSNZB02Json)getType();	
		return sonoffSNZB02Json;
	}
	
	public AqaraTempJson getAqaraTempJson() {		
		AqaraTempJson aqaraTemp=new AqaraTempJson();
		if(isTypeJsonException(AqaraTempJson.class.getName()))aqaraTemp = (AqaraTempJson)getType();			
		return aqaraTemp;
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
}
