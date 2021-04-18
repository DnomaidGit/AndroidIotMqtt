package com.dnomaid.mqtt.client;

public interface ActionsMqtt {
    void connection();
    void disconnection();
    void subscribe();
    void unsubscribe();
    void publish(String topic, String message);
}
