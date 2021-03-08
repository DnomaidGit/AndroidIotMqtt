package com.dnomaid.mqtt.client;

import android.content.Context;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.global.Status;
import com.dnomaid.mqtt.global.Notify;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

public class ActionListener implements IMqttActionListener {
  public enum Action {CONNECT, DISCONNECT, SUBSCRIBE, UNSUBSCRIBE, PUBLISH}

  private Action action;
  private String[] addArgs;
  private Connection connection;
  private Context context;

  public ActionListener(Context context, Action action, String... addArgs) {
    this.context = context;
    this.action = action;
    this.addArgs = addArgs;
  }
  private void addAction(String actionTaken, Boolean setNotify){
    connection = Connection.getInstance(context);
    connection.addHistory(actionTaken);
    if(setNotify)Notify.toast(context, actionTaken);
  }
  @Override
  public void onSuccess(IMqttToken asyncActionToken) {
    addAction("##Action::>"+action.toString(),false);
    switch (action) {
      case CONNECT : connect();break;
      case DISCONNECT : disconnect();break;
      case SUBSCRIBE : subscribe();break;
      case UNSUBSCRIBE : unsubscribe();break;
      case PUBLISH : publish();break;
    }
  }
  private void publish() {
    addAction(context.getString(R.string.pubAction, (Object[]) addArgs),false);
    Status.getInst().changeTopicStatus(Status.TopicStatus.PUBLISHED);
  }
  private void subscribe() {
    addAction(context.getString(R.string.subAction, (Object[]) addArgs),false);
    Status.getInst().changeTopicStatus(Status.TopicStatus.SUBSCRIBED);
  }
  private void unsubscribe() {
    addAction(context.getString(R.string.unSubAction, (Object[]) addArgs),false);
    Status.getInst().changeTopicStatus(Status.TopicStatus.UNSUBSCRIBED);
  }
  private void disconnect() {
    addAction(context.getString(R.string.clientDiscAction),true);
    Status.getInst().changeConnectionStatus(Status.ConnectionStatus.DISCONNECTED);
    Status.getInst().changeTopicStatus(Status.TopicStatus.NONE);
  }
  private void connect() {
    addAction(context.getString(R.string.clientConnAction),true);
    Status.getInst().changeConnectionStatus(Status.ConnectionStatus.CONNECTED);
  }
  @Override
  public void onFailure(IMqttToken token, Throwable exception) {
    addAction("##Action::>"+action.toString(),false);
    addAction("!!Action Exception::>"+" Msg: "+exception.getMessage(),false);
    switch (action) {
      case CONNECT : connect(exception);break;
      case DISCONNECT : disconnect(exception);break;
      case SUBSCRIBE : subscribe(exception);break;
      case UNSUBSCRIBE : unsubscribe(exception);break;
      case PUBLISH : publish(exception);break;
    }
  }
  private void publish(Throwable exception) {
    addAction(context.getString(R.string.pubActionFailed, (Object[]) addArgs),false);
    Status.getInst().changeTopicStatus(Status.TopicStatus.ERROR);
  }
  private void subscribe(Throwable exception) {
    addAction(context.getString(R.string.subActionFailed, (Object[]) addArgs),false);
    Status.getInst().changeTopicStatus(Status.TopicStatus.ERROR);
  }
  private void unsubscribe(Throwable exception) {
    addAction(context.getString(R.string.unSubActionFailed, (Object[]) addArgs),false);
    Status.getInst().changeTopicStatus(Status.TopicStatus.ERROR);
  }
  private void disconnect(Throwable exception) {
    addAction(context.getString(R.string.clientDiscActionFailed),false);
    Status.getInst().changeConnectionStatus(Status.ConnectionStatus.DISCONNECTED);
    Status.getInst().changeTopicStatus(Status.TopicStatus.NONE);
  }
  private void connect(Throwable exception) {
    addAction(context.getString(R.string.clientConnActionFailed),false);
    Status.getInst().changeConnectionStatus(Status.ConnectionStatus.ERROR);
  }
}