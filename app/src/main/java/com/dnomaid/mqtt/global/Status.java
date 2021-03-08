package com.dnomaid.mqtt.global;

public class Status {
    private String messageArrived;
    private ConnectionStatus status;
    private TopicStatus topicStatus;
    public static final String SPACE = " ";
    public static final String EMPTY = new String();
    public enum ConnectionStatus {CONNECTING, CONNECTED, DISCONNECTING, DISCONNECTED, ERROR, NONE}
    public enum TopicStatus {SUBSCRIBED, UNSUBSCRIBED, PUBLISHED, ERROR, NONE}

    private static Status instance = null;
    private Status(){
        changeConnectionStatus(ConnectionStatus.NONE);
        changeTopicStatus(TopicStatus.NONE);

    }
    public  static synchronized Status getInst() {
        if (instance==null) {
            instance=new Status();
        }
        return instance;
    }

    public void changeConnectionStatus(ConnectionStatus connectionStatus) { status = connectionStatus; }
    public String getConnectionStatus() { return status.toString(); }
    public void changeTopicStatus(TopicStatus topicStatus) { this.topicStatus = topicStatus; }
    public String getTopicStatus() { return topicStatus.toString(); }
    public boolean isNoneTopicStatus() {
        return topicStatus == topicStatus.NONE;
    }
    public boolean isConnected() {
        return status == ConnectionStatus.CONNECTED;
    }
    public boolean isSubscribed() { return (topicStatus==TopicStatus.SUBSCRIBED||topicStatus==TopicStatus.PUBLISHED);}
    public boolean isConnectedOrConnecting() {
        return (status == ConnectionStatus.CONNECTED) || (status == ConnectionStatus.CONNECTING);
    }
    public boolean noError() { return status != ConnectionStatus.ERROR; }

    public String getMessageArrived() { return messageArrived; }
    public void setMessageArrived(String messageArrived) { this.messageArrived = messageArrived; }



}