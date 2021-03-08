package com.dnomaid.mqtt.global;

import com.dnomaid.mqtt.device.Devices;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class ConnectionConstants implements ConnectionDefaults {
  private static ConnectionConstants connectionConstants = null;
  private String Uri;
  private String Server;
  private int Port;
  private String ClientId;
  private boolean CleanSession;
  private int PublishQos;
  private int SubscribeQos;
  private int TimeOut;
  private int KeepAlive;
  private boolean Ssl;
  private String SslPassword;
  private boolean Retained;
  private String Username;
  private String Password;
  private String MessageLWT;
  private String PublishTopic;
  private String SubscribeTopic;
  private MqttConnectOptions conOpt;

  private ConnectionConstants(){
    InitDefault();
    InitConOpt();
  }
  public static synchronized ConnectionConstants getInst() {
    if (connectionConstants==null) {
      connectionConstants=new ConnectionConstants();
    }
    return connectionConstants;
  }
  //Getters and Setters
  public String getUri() {return Uri;}
  public void setUri(String uri) {Uri = uri;}

  public String getServer() {return Server;}
  public void setServer(String server) {Server = server;}

  public String getClientId() {return ClientId;}
  public void setClientId(String clientId) {ClientId = clientId;}

  public boolean isCleanSession() {return CleanSession;}
  public void setCleanSession(boolean cleanSession) { CleanSession = cleanSession;}

  public int getPublishQos() {return PublishQos;}
  public void setPublishQos(int publishQos) {PublishQos = publishQos;}

  public int getSubscribeQos() {return SubscribeQos;}
  public void setSubscribeQos(int subscribeQos) {SubscribeQos = subscribeQos;}

  public int getTimeOut() {return TimeOut;}
  public void setTimeOut(int timeOut) {TimeOut = timeOut;}

  public int getKeepAlive() {return KeepAlive;}
  public void setKeepAlive(int keepAlive) {KeepAlive = keepAlive;}

  public boolean isSsl() {return Ssl;}
  public void setSsl(boolean ssl) {Ssl = ssl;}

  public String getSslPassword() {return SslPassword;}
  public void setSslPassword(String sslPassword) {SslPassword = sslPassword;}

  public boolean isRetained() {return Retained;}
  public void setRetained(boolean retained) {Retained = retained;}

  public int getPort() {return Port;}
  public void setPort(int port) {Port = port;}

  public String getUsername() {return Username;}
  public void setUsername(String username) {Username = username;}

  public String getPassword() {return Password;}
  public void setPassword(String password) {Password = password;}

  public String getSubscribeTopic() {return SubscribeTopic;}
  public void setSubscribeTopic(String subscribeTopic) {SubscribeTopic = subscribeTopic;}

  public String getMessageLWT() {return MessageLWT;}
  public void setMessageLWT(String messageLWT) {this.MessageLWT = messageLWT;}

  public String getPublishTopic() {return PublishTopic;}
  public void setPublishTopic(String publishTopic) {this.PublishTopic = publishTopic;}

  public MqttConnectOptions getConOpt() {return conOpt;}

  //Methods
  private void InitConOpt(){
    conOpt =  new MqttConnectOptions();
    conOpt.setCleanSession(CleanSession);
    conOpt.setConnectionTimeout(TimeOut);
    conOpt.setKeepAliveInterval(KeepAlive);
    conOpt.setUserName(Username);
    conOpt.setPassword(Password.toCharArray());
  }
  private void InitDefault(){
    Uri = URI;
    Server = SERVER;
    Port = PORT;
    ClientId = CLIENT_ID;
    CleanSession = CLEAN_SESSION;
    PublishQos = PUBLISH_QOS;
    SubscribeQos = SUBSCRIBE_QOS;
    TimeOut = TIME_OUT;
    KeepAlive = KEEP_ALIVE;
    Ssl = SSL;
    SslPassword = SSL_PASSWORD;
    Retained = RETAINED;
    Username = USERNAME;
    Password = PASSWORD;
    MessageLWT = MESSAGE_LWT;
    PublishTopic = PUBLISH_TOPIC;
    SubscribeTopic = SUBSCRIBE_TOPIC;
  }
  public String getPTOPIC01RELAY(Integer numberRelay) {
    String PTOPIC01RELAY = "PublishTopic01Relay??";
    if(numberRelay>0&Devices.getInst().getRelays().size()>=numberRelay) {
      PTOPIC01RELAY = Devices.getInst().getRelays().get(numberRelay-1).getTopics().get(1).getName();
    }
    return PTOPIC01RELAY;
  }
}
