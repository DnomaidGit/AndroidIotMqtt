package com.dnomaid.mqtt.ui.settingConnection;

public class SettingConnectionViewValueUser {
    private String server;
    private int port;
    private String clientId;
    private boolean cleanSession;
    private int timeOut;
    private int keepAlive;
    private String username;
    private String password;

    public SettingConnectionViewValueUser() {
        this.server = "";
        this.port = 0;
        this.clientId = "";
        this.cleanSession = true;
        this.timeOut = 0;
        this.keepAlive = 0;
        this.username = "";
        this.password = "";
    }

    public String getServer() {
        return server;
    }
    public void setServer(String server) {
        this.server = server;
    }

    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }

    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public boolean isCleanSession() {
        return cleanSession;
    }
    public void setCleanSession(boolean cleanSession) {
        this.cleanSession = cleanSession;
    }

    public int getTimeOut() {
        return timeOut;
    }
    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public int getKeepAlive() {
        return keepAlive;
    }
    public void setKeepAlive(int keepAlive) {
        this.keepAlive = keepAlive;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
