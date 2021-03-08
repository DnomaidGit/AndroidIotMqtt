package com.dnomaid.mqtt.topic;

import com.dnomaid.mqtt.topic.noJson.Hum;
import com.dnomaid.mqtt.topic.noJson.POWER;
import com.dnomaid.mqtt.topic.noJson.Set;
import com.dnomaid.mqtt.topic.noJson.Temp;

public class TopicNoJson extends Topic {

	public TopicNoJson(String idFunc, String name, Object type) {
		super(idFunc, name, type);
		setTypeJson(false);
	}

	public Hum getHum() {
		Hum hum=new Hum();
		if(isTypeNoJsonException(Hum.class.getName()))hum = (Hum)getType();
		return hum;
	}

	public POWER getPower() {		
		POWER power=new POWER();
		if(isTypeNoJsonException(POWER.class.getName()))power = (POWER)getType();			
		return power;
	}

	public Set getSet() {
		Set set=new Set();
		if(isTypeNoJsonException(Set.class.getName()))set = (Set)getType();
		return set;
	}

	public Temp getTemp() {
		Temp temp=new Temp();
		if(isTypeNoJsonException(Temp.class.getName()))temp = (Temp)getType();
		return temp;
	}

	private boolean isTypeNoJsonException(String nameNoJsonClass){
		boolean test = false;
			try {
				if(isTypeJson()){
					throw new Exception("Is type Json: " + getName());
				}else{
					if(!nameNoJsonClass.equals(getType().getClass().getName())) 
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
