package com.dnomaid.mqtt.ui.settingConnection;

public class SettingConnectionViewState {
    private String Server;
    private String Port;
    private String ClientId;
    private String CleanSession;

    public String getServer() {
        return Server;
    }
    public void setServer(String server) {
        Server = server;
    }

    public String getPort() {
        return Port;
    }
    public void setPort(String port) {
        Port = port;
    }

    public String getClientId() {
        return ClientId;
    }
    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public String getCleanSession() {
        return CleanSession;
    }
    public void setCleanSession(String cleanSession) {
        CleanSession = cleanSession;
    }
}
