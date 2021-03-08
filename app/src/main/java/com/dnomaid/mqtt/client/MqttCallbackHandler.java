package com.dnomaid.mqtt.client;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.device.Devices;
import com.dnomaid.mqtt.global.Status;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttCallbackHandler implements MqttCallback {
  private Context context;
  private boolean knownTopic = false;

  public MqttCallbackHandler(Context context) {
    this.context = context;
  }
  @Override
  public void connectionLost(Throwable cause) {
    if (cause != null) {
      Connection c = Connection.getInstance(context);
      Status.getInst().changeConnectionStatus(Status.ConnectionStatus.DISCONNECTED);
      //format string to use a notification text
      Object[] args = new Object[2];
      args[0] = c.getClientId();
      args[1] = c.getServer();
      String message = context.getString(R.string.connectionLost, args);
      c.addHistory(message + " -> " + cause.toString());
    }
  }
  @RequiresApi(api = Build.VERSION_CODES.N)
  @Override
  public void messageArrived(String topic, MqttMessage message) throws Exception {
    Connection c = Connection.getInstance(context);
    String messagePayload = new String(message.getPayload());
    String[] args = new String[2];
    args[0] = messagePayload;
    args[1] = topic+";qos:"+message.getQos()+";retained:"+message.isRetained();
    @SuppressLint("StringFormatMatches") String messageString = context.getString(R.string.messageRecieved, (Object[]) args);
    c.addHistory(messageString);
    Object[] notifyArgs = new String[3];
    notifyArgs[0] = c.getClientId();
    notifyArgs[1] = messagePayload;
    notifyArgs[2] = topic;
    String messageArrived =context.getString(R.string.notification, notifyArgs);
    Status.getInst().setMessageArrived(messageArrived);
    knownTopic = false;
    Devices.getInst().getDevices().stream().forEach(a->{
      a.getTopics().stream().forEach(b->{
        if(topic.equals(b.getName())){
          if(b.updateValueTopic(messagePayload)){
            knownTopic = true;
            System.out.println("Update::>"+b.getName());
          }
        }
      });
    });
    if(!knownTopic)System.out.println("Unknown topic::>"+topic);
  }
  @Override
  public void deliveryComplete(IMqttDeliveryToken token) { }
}
