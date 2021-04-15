package com.dnomaid.mqtt.ui.temperature;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dnomaid.mqtt.device.Device;
import com.dnomaid.mqtt.device.Devices;

import java.util.ArrayList;

public class TemperatureViewModel extends ViewModel {

    private TemperatureViewState viewState;
    private ArrayList<Device> status = Devices.getInst().getSensorsClimate();
    private  MutableLiveData<TemperatureViewState> viewMLD = new MutableLiveData<>();
    LiveData<TemperatureViewState> viewLD = viewMLD;

    public TemperatureViewModel(){
        viewState = new TemperatureViewState();
    }
    public void updateState() {
        viewState.setDevice(status);
        viewMLD.setValue(viewState);
    }
}