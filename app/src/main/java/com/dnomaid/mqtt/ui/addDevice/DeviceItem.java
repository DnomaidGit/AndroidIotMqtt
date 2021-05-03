package com.dnomaid.mqtt.ui.addDevice;

import com.dnomaid.mqtt.global.Constants.TypeDevice;

public class DeviceItem {
    private TypeDevice type;
    private int image;

    public DeviceItem(TypeDevice type, int image) {
        this.type = type;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public TypeDevice getType() {
        return type;
    }
}
