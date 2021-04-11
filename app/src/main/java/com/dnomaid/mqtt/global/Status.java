package com.dnomaid.mqtt.global;

import android.text.Html;
import android.text.Spanned;

import java.util.ArrayList;

public class Status {
    private String messageArrived;
    private ConnectionStatus status;
    private TopicStatus topicStatus;
    private Spanned[] history;
    public static final String SPACE = " ";
    public static final String EMPTY = new String();
    public enum ConnectionStatus {CONNECTING, CONNECTED, DISCONNECTING, DISCONNECTED, ERROR, NONE}
    public enum TopicStatus {SUBSCRIBED, UNSUBSCRIBED, PUBLISHED, ERROR, NONE}

    private static Status instance = null;
    private Status(){
        changeConnectionStatus(ConnectionStatus.NONE);
        changeTopicStatus(TopicStatus.NONE);
        history = new Spanned[new ArrayList<String>().size()];
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
    public Spanned[] getHistory() {
        return history;
    }
    public void setHistory(ArrayList<String> historys) {
        int i = 0;
        history = new Spanned[historys.size()];
        for (String s : historys) {
            if (s != null) {
                history[i] = Html.fromHtml(s);
                i++;
            }
        }
    }

}