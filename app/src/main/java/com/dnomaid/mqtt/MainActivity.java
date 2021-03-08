package com.dnomaid.mqtt;

import android.os.Bundle;
import android.os.Handler;
import android.text.Spanned;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.dnomaid.mqtt.client.Actions;
import com.dnomaid.mqtt.client.Connection;
import com.dnomaid.mqtt.client.Mqtt;
import com.dnomaid.mqtt.device.Devices;
import com.dnomaid.mqtt.global.ConnectionConstants;
import com.dnomaid.mqtt.fragment.ConfigFragment;
import com.dnomaid.mqtt.fragment.ConnectionFragment;
import com.dnomaid.mqtt.fragment.HistoryFragment;
import com.dnomaid.mqtt.fragment.RelayFragment;
import com.dnomaid.mqtt.fragment.TemperatureFragment;
import com.dnomaid.mqtt.global.Status;

public class MainActivity extends AppCompatActivity
        implements Actions, ConnectionFragment.OnFragmentCommunicationListener, TemperatureFragment.OnFragmentCommunicationListener,
        RelayFragment.OnFragmentCommunicationListener, HistoryFragment.OnFragmentCommunicationListener, ConfigFragment.OnFragmentCommunicationListener{
    ConnectionFragment connectionFragment;
    TemperatureFragment temperatureFragment;
    RelayFragment relayFragment;
    HistoryFragment historyFragment;
    ConfigFragment configFragment;
    private Connection connection = null;
    public static final long PERIODO = 500; // 60 segundos (6 * 1000 millisegundos)
    private Handler handler;
    private Runnable runnable;
    Spanned[] HISTORY;
    private Mqtt mqtt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectionFragment = new ConnectionFragment();
        temperatureFragment = new TemperatureFragment();
        relayFragment = new RelayFragment();
        historyFragment = new HistoryFragment();
        configFragment = new ConfigFragment();
        //Init
        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments,connectionFragment).commit();
        connection = Connection.getInstance(this);
        mqtt = new Mqtt(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, PERIODO);
                onNameChangeConnection(Status.getInst().getConnectionStatus());
                onNameChangeSubscribe(Status.getInst().getTopicStatus());
                onNameChangeMessageArrived(Status.getInst().getMessageArrived());
                onStatusChangeRelay(1,Devices.getInst().getDev010Topic001().getPower().getPOWER());
                onStatusChangeRelay(2,Devices.getInst().getDev020Topic001().getPower().getPOWER());
                onStatusChangeRelay(3,Devices.getInst().getDev030Topic001().getPower().getPOWER());
                onStatusChangeRelay(4,Devices.getInst().getDev040Topic001().getPower().getPOWER());
                onStatusChangeRelay(5,Devices.getInst().getDev050Topic001().getPower().getPOWER());
                onStatusChangeRelay(6,Devices.getInst().getDev310Topic002().getSet().getSet());
                onStatusChangeTemp(1,String.valueOf(Devices.getInst().getDev110Topic001().getSonoffSNZB02Json().getTemperature()));
                onStatusChangeHum(1,String.valueOf(Devices.getInst().getDev110Topic001().getSonoffSNZB02Json().getHumidity()));
                onStatusChangeTemp(2,String.valueOf(Devices.getInst().getDev120Topic001().getAqaraTempJson().getTemperature()));
                onStatusChangeHum(2,String.valueOf(Devices.getInst().getDev120Topic001().getAqaraTempJson().getHumidity()));
                onStatusChangeTemp(3,String.valueOf(Devices.getInst().getDev310Topic001().getXiaomiZNCZ04LMJson().getTemperature()));
                onStatusChangeHum(3," -.- ");
                onStatusChangeTemp(4,String.valueOf(Devices.getInst().getDev130Topic001().getTuyaZigBeeSensorJson().getTemperature()));
                onStatusChangeHum(4,String.valueOf(Devices.getInst().getDev130Topic001().getTuyaZigBeeSensorJson().getHumidity()));
                onNameChangeHistory();
                onNameChangeServer(ConnectionConstants.getInst().getServer());
                onNameChangePort(String.valueOf(ConnectionConstants.getInst().getPort()));
                onNameChangeClientId(ConnectionConstants.getInst().getClientId());
                onNameChangeCleanSession(String.valueOf(ConnectionConstants.getInst().isCleanSession()));
                if(Status.getInst().isConnected()&Status.getInst().isNoneTopicStatus())subscribe();
            }
        };
        handler.postDelayed(runnable, PERIODO);
        //Recover connections.
        connection = Connection.getInstance(this);
        //Register receivers again
        //if (connection != null) {
            //connection.getClient().registerResources(this);
            //connection.getClient().setCallback(new MqttCallbackHandler(this));
        //}
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        connection = Connection.getInstance(this);
        connection.getClient().unregisterResources();
        //connection.removeConnection();
    }
    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
    //onClickFragment
    public void onClickFragment(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.btnConnectionFragment:
                transaction.replace(R.id.contenedorFragments,connectionFragment);
                break;
            case R.id.btnTemperatureFragment:
                transaction.replace(R.id.contenedorFragments,temperatureFragment);
                break;
            case R.id.btnRelayFragment:
                transaction.replace(R.id.contenedorFragments,relayFragment);
                break;
            case R.id.btnHistoryFragment:
                transaction.replace(R.id.contenedorFragments,historyFragment);
                break;
            case R.id.btnConfigFragment:
                transaction.replace(R.id.contenedorFragments,configFragment);
                break;
        }
        transaction.commit();
    }
    //onNameChange
    @Override
    public void onNameChangeConnection(String name) {
        if (connectionFragment != null && connectionFragment.isVisible() ) {
            connectionFragment.onNameChangeConnection(name);
        }
    }
    @Override
    public void onNameChangeSubscribe(String name) {
        if (connectionFragment != null && connectionFragment.isVisible()) {
            connectionFragment.onNameChangeSubscribe(name);
        }
    }
    @Override
    public void onNameChangeMessageArrived(String name) {
        if (connectionFragment != null && connectionFragment.isVisible()) {
            connectionFragment.onNameChangeMessageArrived(name);
        }
    }
    @Override
    public void onStatusChangeRelay(int numberRelay, String status) {
        if (relayFragment != null && relayFragment.isVisible()) {
            relayFragment.onStatusChangeRelay(numberRelay, status);
        }
    }    
    @Override
    public void onStatusChangeTemp(int numberSensor, String status){
        if (temperatureFragment != null && temperatureFragment.isVisible()) {
            temperatureFragment.onStatusChangeTemp(numberSensor,status);
        }
    }
    @Override
    public void onStatusChangeHum(int numberSensor, String status) {
        if (temperatureFragment != null && temperatureFragment.isVisible()) {
            temperatureFragment.onStatusChangeHum(numberSensor,status);
        }
    }
    @Override
    public void onNameChangeHistory() {
        if (historyFragment != null && historyFragment.isVisible()) {
            connection = Connection.getInstance(this);
            if (connection != null) {
                HISTORY = Connection.getInstance(this).history();
                historyFragment.onNameChangeHistory(HISTORY);
            }
        }
    }
    @Override
    public void onNameChangeServer(String name) {
        if (configFragment != null && configFragment.isVisible()) {
            configFragment.onNameChangeServer(name);
        }
    }
    @Override
    public void onNameChangePort(String name) {
        if (configFragment != null && configFragment.isVisible()) {
            configFragment.onNameChangePort(name);
        }
    }
    @Override
    public void onNameChangeClientId(String name) {
        if (configFragment != null && configFragment.isVisible()) {
            configFragment.onNameChangeClientId(name);
        }
    }
    @Override
    public void onNameChangeCleanSession(String name) {
        if (configFragment != null && configFragment.isVisible()) {
            configFragment.onNameChangeCleanSession(name);
        }
    }
    //Interface Actions
    @Override
    public void connection() {
        mqtt.connection();
    }
    @Override
    public void disconnection() {
        mqtt.disconnection();
    }
    @Override
    public void subscribe() {
        mqtt.subscribe();
    }
    @Override
    public void unsubscribe() {
        mqtt.unsubscribe();
    }
    @Override
    public void publish(String topic, String message) {
        mqtt.publish(topic,message);
    }
}