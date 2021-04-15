package com.dnomaid.mqtt.ui.temperature;

import com.dnomaid.mqtt.device.Device;

import java.util.ArrayList;

public class TemperatureViewState {
    private ArrayList<Device> Device;

    public ArrayList<Device> getDevice() {
        return Device;
    }
    public void setDevice(ArrayList<Device> device) {
        Device = device;
    }
}
