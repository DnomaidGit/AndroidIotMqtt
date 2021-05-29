package com.dnomaid.mqtt.ui.connection;

public class ConnectionViewState {
    private String ConnectionStatus;
    private String SubscribeStatus;
    private String MessageArrived;
    private String Server;
    private String Port;
    private String ClientId;

    public String getConnectionStatus() {
        return ConnectionStatus;
    }
    public void setConnectionStatus(String connectionStatus) {
        ConnectionStatus = connectionStatus;
    }

    public String getSubscribeStatus() {
        return SubscribeStatus;
    }
    public void setSubscribeStatus(String subscribeStatus) {
        SubscribeStatus = subscribeStatus;
    }

    public String getMessageArrived() {
        return MessageArrived;
    }
    public void setMessageArrived(String messageArrived) {
        MessageArrived = messageArrived;
    }

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

}
