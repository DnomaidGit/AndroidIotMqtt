package com.dnomaid.mqtt.global;

public interface Constants {
    String ID= "Dnomaid";
    String STAT_PREFIX =ID+"/stat";
    String CMND_PREFIX =ID+"/cmnd";
    String MIX_PREFIX=ID+"/mix";

    String [] SubscribeTopicList = {STAT_PREFIX+"/#",MIX_PREFIX+"/#",CMND_PREFIX+"/#"};
    enum TypeGateway {Router_1, CC2531_1}
    enum TypeDevice {SonoffS20, SonoffSNZB02, AqaraTemp, XiaomiZNCZ04LM, TuyaZigBeeSensor, None}
    enum GroupList {Relay, SensorClimate, RelaySensorClimate}

}
