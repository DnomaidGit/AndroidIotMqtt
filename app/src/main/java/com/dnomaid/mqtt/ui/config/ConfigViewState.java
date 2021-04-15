package com.dnomaid.mqtt.ui.config;

import com.dnomaid.mqtt.device.DeviceConfig;

import java.util.ArrayList;

public class ConfigViewState {
    private String server;
    private String port;
    private String clientId;
    private String cleanSession;
    private ArrayList<DeviceConfig> DevicesConfig;

    public ConfigViewState() {

    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCleanSession() {
        return cleanSession;
    }

    public void setCleanSession(String cleanSession) {
        this.cleanSession = cleanSession;
    }

    public ArrayList<DeviceConfig> getDevicesConfig() {
        return DevicesConfig;
    }

    public void setDevicesConfig(ArrayList<DeviceConfig> devicesConfig) {
        DevicesConfig = devicesConfig;
    }

}
