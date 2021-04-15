package com.dnomaid.mqtt.ui.config;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dnomaid.mqtt.device.DeviceConfig;
import com.dnomaid.mqtt.device.Devices;
import com.dnomaid.mqtt.global.ConnectionConstants;

import java.util.ArrayList;

public class ConfigViewModel extends ViewModel {

    private ConfigViewState viewState;
    private ConnectionConstants status = ConnectionConstants.getInst();
    private ArrayList<DeviceConfig> status2 = Devices.getInst().getDevicesConfig();;
    private  MutableLiveData<ConfigViewState> viewMLD = new MutableLiveData<>();
    LiveData<ConfigViewState> viewLD = viewMLD;

    public ConfigViewModel(){
        viewState = new ConfigViewState();
    }

    public void updateState() {
        viewState.setServer(status.getServer());
        viewState.setPort(String.valueOf(status.getPort()));
        viewState.setClientId(status.getClientId());
        String aux = "false";
        if (status.isCleanSession()) aux = "true";
        viewState.setCleanSession(aux);
        viewState.setDevicesConfig(status2);
        viewMLD.setValue(viewState);
    }
}