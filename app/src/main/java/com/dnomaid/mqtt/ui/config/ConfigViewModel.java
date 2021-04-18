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
    private ConnectionConstants status1 = ConnectionConstants.getInst();
    private ArrayList<DeviceConfig> status2 = Devices.getInst().getDevicesConfig();
    private  MutableLiveData<ConfigViewState> viewMLD = new MutableLiveData<>();
    LiveData<ConfigViewState> viewLD = viewMLD;

    public ConfigViewModel(){
        viewState = new ConfigViewState();
    }

    public void updateState(ArrayList<DeviceConfig> status2) {
        viewState.setServer(this.status1.getServer());
        viewState.setPort(String.valueOf(this.status1.getPort()));
        viewState.setClientId(this.status1.getClientId());
        String aux = "false";
        if (this.status1.isCleanSession()) aux = "true";
        viewState.setCleanSession(aux);
        this.status2 = status2;
        viewState.setDeviceConfig(this.status2);
        viewMLD.setValue(viewState);
    }
}