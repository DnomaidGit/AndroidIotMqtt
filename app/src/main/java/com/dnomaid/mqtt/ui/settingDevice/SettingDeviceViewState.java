package com.dnomaid.mqtt.ui.settingDevice;

import com.dnomaid.mqtt.device.DeviceConfig;

import java.util.ArrayList;

public class SettingDeviceViewState {
    private ArrayList<DeviceConfig> DeviceConfig;

    public SettingDeviceViewState() {

    }

    public ArrayList<DeviceConfig> getDeviceConfig() {
        return DeviceConfig;
    }

    public void setDeviceConfig(ArrayList<DeviceConfig> deviceConfig) {
        DeviceConfig = deviceConfig;
    }
}
