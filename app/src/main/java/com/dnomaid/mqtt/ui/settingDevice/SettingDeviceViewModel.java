package com.dnomaid.mqtt.ui.settingDevice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dnomaid.mqtt.device.DeviceConfig;
import com.dnomaid.mqtt.device.Devices;
import com.dnomaid.mqtt.global.ConnectionConstants;

import java.util.ArrayList;

public class SettingDeviceViewModel extends ViewModel {

    private SettingDeviceViewState viewState;
    private ArrayList<DeviceConfig> status2 = Devices.getInst().getDevicesConfig();
    private  MutableLiveData<SettingDeviceViewState> viewMLD = new MutableLiveData<>();
    LiveData<SettingDeviceViewState> viewLD = viewMLD;

    public SettingDeviceViewModel(){
        viewState = new SettingDeviceViewState();
    }

    public void updateState(ArrayList<DeviceConfig> status2) {
        String aux = "false";
        this.status2 = status2;
        viewState.setDeviceConfig(this.status2);
        viewMLD.setValue(viewState);
    }
}