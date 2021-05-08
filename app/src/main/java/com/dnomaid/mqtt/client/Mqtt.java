package com.dnomaid.mqtt.client;

import android.content.Context;
import android.util.Log;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.global.ConnectionConstants;
import com.dnomaid.mqtt.global.Constants;
import com.dnomaid.mqtt.global.Notify;
import com.dnomaid.mqtt.global.Status;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

import static com.dnomaid.mqtt.global.Constants.CMND_PREFIX;
import static com.dnomaid.mqtt.global.Constants.MIX_PREFIX;
import static com.dnomaid.mqtt.global.Constants.STAT_PREFIX;
import static com.dnomaid.mqtt.global.Constants.SubscribeTopicList;

public class Mqtt implements ActionsMqtt {
    private Connection connection;
    private ActionListener actionListener;
    private MqttAndroidClient client;
    private MqttConnectOptions conOpt;
    private Context context;

    public Mqtt(Context context){this.context = context;}

    @Override
    public void connection() {
        connection = Connection.getInstance(this.context);
        if (connection != null) {
            if (Status.getInst().isConnectedOrConnecting()) {
                if (Status.getInst().isConnected()) {
                    Notify.toast(this.context,context.getString(R.string.alreadyConnecting));
                    return;
                }
                Notify.toast(this.context,context.getString(R.string.connecting___));
            }
        }
        //conOpt = Connection.getInstance(this.context).createConnectionOptions();
        //client = Connection.getInstance(this.context).createClient();
        // connect client
        String[] actionArgs = new String[1];
        actionArgs[0] = ConnectionConstants.getInst().getClientId();
        Status.getInst().changeConnectionStatus(Status.ConnectionStatus.CONNECTING);
        final ActionListener callback = new ActionListener(this.context, ActionListener.Action.CONNECT, actionArgs);
        boolean doConnect = true;
        try {
            client = Connection.getInstance(this.context).createClient();
            conOpt = Connection.getInstance(this.context).createConnectionOptions();
            client.setCallback(new MqttCallbackHandler(this.context));
            client.setTraceCallback(new MqttTraceCallback());
            connection.persistConnection();
        }
        catch (Exception e) {
            doConnect = false;
            actionListener.onFailure(null, e);
        }
        if (doConnect) {
            try {
                client.connect(conOpt, null, callback);
                Notify.toast(this.context,context.getString(R.string.client_connecting));
            }
            catch (MqttException e) {
                Log.e(this.getClass().getCanonicalName(), "MqttException Occured", e);
                Notify.toast(this.context,context.getString(R.string.client_error_connecting));
            }
        }
    }
    @Override
    public void disconnection() {
        connection = Connection.getInstance(this.context);
        if (connection == null) {
            Notify.toast(this.context,context.getString(R.string.notConnecting));
            return;
        }
        if (Status.getInst().isConnected()) {
            if (!Status.getInst().isConnected()) {
                return;
            }else {
                Notify.toast(this.context,context.getString(R.string.disconnecting___));
            }
            try {
                connection.getClient().disconnect(null, new ActionListener(this.context, ActionListener.Action.DISCONNECT));
                Status.getInst().changeConnectionStatus(Status.ConnectionStatus.DISCONNECTING);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
        else {
            Notify.toast(this.context,context.getString(R.string.alreadyDisconnected));
            Connection.getInstance(this.context).removeConnection();
            Status.getInst().changeConnectionStatus(Status.ConnectionStatus.NONE);
        }
    }
    @Override
    public void subscribe() {
        connection = Connection.getInstance(this.context);
        if (connection == null) {
            Notify.toast(this.context,context.getString(R.string.notConnecting));
            return;
        }
        for (int i = 0;i<SubscribeTopicList.length;i++){
            ConnectionConstants.getInst().setSubscribeTopic(SubscribeTopicList[i]);
            connection.createSubscribeTopic();
            if (Status.getInst().isConnectedOrConnecting()) {
                if (!Status.getInst().isConnected()) {
                    return;
                }else {
                    try {
                        String[] topics = new String[1];
                        topics[0] = ConnectionConstants.getInst().getSubscribeTopic();
                        connection.getClient().subscribe(ConnectionConstants.getInst().getSubscribeTopic(), ConnectionConstants.getInst().getSubscribeQos(), null,
                                new ActionListener(this.context, ActionListener.Action.SUBSCRIBE, topics));
                    }
                    catch (MqttSecurityException e) {
                        Log.e(this.getClass().getCanonicalName(), "Failed to subscribe to" + ConnectionConstants.getInst().getSubscribeTopic(), e);
                    }
                    catch (MqttException e) {
                        Log.e(this.getClass().getCanonicalName(), "Failed to subscribe to" + ConnectionConstants.getInst().getSubscribeTopic(), e);
                    }
                }
            }
        }
    }
    @Override
    public void unsubscribe() {
        connection = Connection.getInstance(this.context);
        if (connection == null) {
            Notify.toast(this.context,context.getString(R.string.notConnecting));
            return;
        }
        if (Status.getInst().isConnectedOrConnecting()) {
            if (!Status.getInst().isConnected()) {
                return;
            } else {
                try {
                    String[] topics = new String[1];
                    topics[0] = ConnectionConstants.getInst().getSubscribeTopic();
                    connection.getClient().unsubscribe(ConnectionConstants.getInst().getSubscribeTopic(), ConnectionConstants.getInst().getUsername(),
                            new ActionListener(this.context, ActionListener.Action.UNSUBSCRIBE, topics));
                } catch (MqttSecurityException e) {
                    Log.e(this.getClass().getCanonicalName(), "Failed to unsubscribe to" + ConnectionConstants.getInst().getSubscribeTopic(), e);
                } catch (MqttException e) {
                    Log.e(this.getClass().getCanonicalName(), "Failed to unsubscribe to" + ConnectionConstants.getInst().getSubscribeTopic(), e);
                }
            }
        }
    }
    @Override
    public void publish(String topic, String message) {
        connection = Connection.getInstance(this.context);
        if (connection == null) {
            Notify.toast(this.context,context.getString(R.string.notConnecting));
            return;
        }
        connection.createPublishTopic();
        if (Status.getInst().isConnectedOrConnecting()) {
            if (!Status.getInst().isConnected()||!Status.getInst().isSubscribed()) {
                return;
            }else {
                int qos = ConnectionConstants.getInst().getPublishQos();
                boolean retained = ConnectionConstants.getInst().isRetained();
                String[] args = new String[2];
                args[0] = message;
                args[1] = topic + ";qos:" + qos + ";retained:" + retained;
                try {
                    connection.getClient()
                            .publish(topic, message.getBytes(), qos, retained,
                                    this.context, new ActionListener(this.context, ActionListener.Action.PUBLISH, args));
                } catch (MqttException e) {
                    Log.e(this.getClass().getCanonicalName(), "Failed to publish a messaged from the client with the handle "
                            + ConnectionConstants.getInst().getUri()+ConnectionConstants.getInst().getClientId(), e);
                }
            }
        }

    }

}


