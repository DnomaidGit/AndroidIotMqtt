package com.dnomaid.mqtt.topic;

import java.lang.reflect.Field;

import com.google.gson.Gson;

public abstract class Topic implements ActionTopic {
	private String IdFunc;
	private String Name;
	private Object Type;
	private boolean TypeJson;	
	private Gson gson;

	protected Topic(String idFunc, String name, Object type) {
		super();
		IdFunc = idFunc;
		Name = name;
		Type = type;		
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}

	public Object getType() {		
		return Type;
	}

	public void setType(Object type) {
		Type = type;
	}

	public boolean isTypeJson() {
		return TypeJson;
	}
	
	public void setTypeJson(boolean typeJson) {
		TypeJson = typeJson;
	}

	public String getIdFunc() {
		return IdFunc;
	}
	
	//----------
	public boolean updateValueTopic(String messagePayload){
		boolean update = false;
		if(isTypeJson()){    		
    		gson = new Gson(); 
    		try {
        		setType(gson.fromJson(messagePayload, getType().getClass()));
        		update = true;
			} catch (Exception e) {
				System.out.println("Update error Json: "+ e);
			}
		}else{
    		try {    			
    			Class<? extends Object> clazz = this.getType().getClass();    			
    			Field fieldNAME = clazz.getDeclaredField(this.getType().toString());
    			fieldNAME.setAccessible(true);
    			fieldNAME.set(this.getType(), messagePayload);
    			fieldNAME.setAccessible(false);
    			update = true;    			
    			} catch (Exception e) {
    				System.out.println("Update error no Json: "+ e);    		
    			}    							
		}				
		return update;
	}	
		
	@Override
	public String toString() {
		return Name;
	}
	@Override
	public String getValueTopic(TypeTopic typeTopic) {
			System.out.println("Error getValueTopic");
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
}
