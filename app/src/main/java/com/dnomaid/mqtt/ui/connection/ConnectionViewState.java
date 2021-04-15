package com.dnomaid.mqtt.ui.connection;

public class ConnectionViewState {
    private String ConnectionStatus;
    private String SubscribeStatus;
    private String MessageArrived;

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

}
