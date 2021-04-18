package com.dnomaid.mqtt.device;

import com.dnomaid.mqtt.global.Constants;

public interface ActionsDevice {
    void newDevice(Constants.TypeDevice typeDevice, String numberDevice);
    void deleteDevice(Integer position);
}
