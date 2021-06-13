package com.dnomaid.mqtt.device;

import com.dnomaid.mqtt.global.Constants;

public interface ActionsDevice {
    String  newDevice(Constants.TypeDevice typeDevice, String numberDevice, String alias);
    void deleteDevice(Integer position);
}
