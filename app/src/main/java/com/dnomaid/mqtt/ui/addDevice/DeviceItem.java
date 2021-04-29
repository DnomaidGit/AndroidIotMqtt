package com.dnomaid.mqtt.ui.addDevice;

public class DeviceItem {
    private String name;
    private int image;

    public DeviceItem(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
