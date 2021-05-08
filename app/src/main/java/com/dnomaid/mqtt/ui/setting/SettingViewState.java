package com.dnomaid.mqtt.ui.setting;

import com.dnomaid.mqtt.device.DeviceConfig;

import java.util.ArrayList;

public class SettingViewState {
    private String server;
    private String port;
    private String clientId;
    private String cleanSession;
    private ArrayList<DeviceConfig> DeviceConfig;

    public SettingViewState() {

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

    public ArrayList<DeviceConfig> getDeviceConfig() {
        return DeviceConfig;
    }

    public void setDeviceConfig(ArrayList<DeviceConfig> deviceConfig) {
        DeviceConfig = deviceConfig;
    }
}
