package com.dnomaid.mqtt.ui.relay;

import com.dnomaid.mqtt.device.Device;

import java.util.ArrayList;

public class RelayViewState {
    private ArrayList<Device> Device;

    public ArrayList<Device> getDevice() {
        return Device;
    }
    public void setDevice(ArrayList<Device> device) {
        Device = device;
    }
}
