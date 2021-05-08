package com.dnomaid.mqtt.client;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.global.ConnectionConstants;
import com.dnomaid.mqtt.global.Notify;
import com.dnomaid.mqtt.global.Status;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.dnomaid.mqtt.global.Constants.STAT_PREFIX;

public class Connection {
  private static Connection instance = null;
  private MqttAndroidClient client;
  private MqttConnectOptions conOpt;
  private String uri;
  private String server;
  private int port;
  private String clientId;
  private int publishQos;
  private int subscribeQos;
  private boolean ssl;
  private boolean retained;
  private String messageLWT;
  private String publishTopic;
  private String subscribeTopic;
  private ArrayList<String> history;
  private ArrayList<PropertyChangeListener> listeners = new ArrayList<PropertyChangeListener>();
  private Context context;
  private long persistenceId;
  private static Persistence persistence;

  public Connection(Context context) {
    this.uri = "";
    this.server = "";
    this.port = 0;
    this.clientId = "";
    this.publishQos = 0;
    this.subscribeQos = 0;
    this.ssl = false;
    this.retained = false;
    this.messageLWT = "";
    this.publishTopic = "";
    this.subscribeTopic = "";
    this.context = context;
    this.history = new ArrayList<String>();
    this.persistenceId = 1;
    persistence = new Persistence(context);
    restoreConnection();
  }
  public synchronized static Connection getInstance(Context context) {
    if (instance == null) {
      instance = new Connection(context);
    }
    return instance;
  }
  //Methods
  public Spanned[] history() {
    int i = 0;
    Spanned[] array = new Spanned[history.size()];
    for (String s : history) {
      if (s != null) {
        array[i] = Html.fromHtml(s);
        i++;
      }
    }
    return array;
  }
  public long persistenceId() {return persistenceId; }
  public void persistConnection() {
      persistence.persistConnection();
  }
  public void restoreConnection() {
    try {
      persistence.restoreConnection(context);
    }
    catch (PersistenceException e) {
      Notify.toast(context,context.getString(R.string.failedPersistRestConn));
    }
  }
  public void removeConnection() {
    instance = null;
    persistence.deleteConnection(instance);
  }
  public MqttAndroidClient createClient() {
    server = ConnectionConstants.getInst().getServer();
    port = ConnectionConstants.getInst().getPort();
    ssl = ConnectionConstants.getInst().isSsl();
    if (ssl) {
      ConnectionConstants.getInst().setUri("ssl://"+server+":"+port);
    }
    else {
      ConnectionConstants.getInst().setUri("tcp://"+server+":"+port);
    }
    uri = ConnectionConstants.getInst().getUri();
    clientId = ConnectionConstants.getInst().getClientId();
    client = new MqttAndroidClient(this.context,uri, clientId);
    StringBuffer sb = new StringBuffer();
    sb.append("Client: ");
    sb.append(this.clientId);
    sb.append(" created");
    addHistory(sb.toString());
    return client;
  }
  public MqttConnectOptions createConnectionOptions() {
    messageLWT = ConnectionConstants.getInst().getMessageLWT();
    publishTopic = ConnectionConstants.getInst().getPublishTopic();
    publishQos = ConnectionConstants.getInst().getPublishQos();
    retained = ConnectionConstants.getInst().isRetained();
    conOpt = ConnectionConstants.getInst().getConOpt();
    String SslPassword = ConnectionConstants.getInst().getSslPassword();
    // last will message
    if ((!messageLWT.equals(Status.EMPTY)) || (!publishTopic.equals(Status.EMPTY))) {
      conOpt.setWill(publishTopic, messageLWT.getBytes(), publishQos, retained);
    }
    if (ssl){
      try {
        if(SslPassword != null && !SslPassword.equalsIgnoreCase(""))
        {
          FileInputStream key = new FileInputStream(SslPassword);
          conOpt.setSocketFactory(client.getSSLSocketFactory(key, ConnectionConstants.getInst().getSslPassword()));
        }
      } catch (MqttSecurityException e) {
        Log.e(this.getClass().getCanonicalName(), "MqttException Occured: ", e);
      } catch (FileNotFoundException e) {
        Log.e(this.getClass().getCanonicalName(), "MqttException Occured: SSL Key file not found", e);
      }
    }
    return conOpt;
  }
  public void createSubscribeTopic() {
    subscribeTopic = ConnectionConstants.getInst().getSubscribeTopic();
    subscribeQos = ConnectionConstants.getInst().getSubscribeQos();
    retained = ConnectionConstants.getInst().isRetained();
  }
  public void createPublishTopic() {
    publishTopic = ConnectionConstants.getInst().getPublishTopic();
    publishQos = ConnectionConstants.getInst().getPublishQos();
    retained = ConnectionConstants.getInst().isRetained();
  }
  public void addHistory(String textHistory) {
    Object[] args = new String[1];
    SimpleDateFormat sdf = new SimpleDateFormat(context.getString(R.string.dateFormatAction));
    args[0] = sdf.format(new Date());
    String timestamp = context.getString(R.string.timestampAction, args);
    Integer maxHistoryNumber = 100;
    Integer HistoryNumber = history.size();
    if(HistoryNumber >= maxHistoryNumber)history.remove(maxHistoryNumber-1);
    history.add(0,textHistory + timestamp);
    Status.getInst().setHistory(history);
  }
  //Getter
  public MqttAndroidClient getClient() { return client; }
  public MqttConnectOptions getConnectionOptions() { return conOpt; }
  public String getClientId() { return clientId; }
  public String getServer() { return server; }
  public int getPort() { return port; }
  public int isSSL() { return ssl ? 1 : 0; }
}
