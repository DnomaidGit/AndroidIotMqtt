package com.dnomaid.mqtt.ui.settingConnection;

public class SettingConnectionViewState {
    private String Server;
    private String Port;
    private String ClientId;
    private String CleanSession;
    private String timeOut;
    private String keepAlive;
    private String username;
    private String password;

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

    public String getTimeOut() {
        return timeOut;
    }
    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getKeepAlive() {
        return keepAlive;
    }
    public void setKeepAlive(String keepAlive) {
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
