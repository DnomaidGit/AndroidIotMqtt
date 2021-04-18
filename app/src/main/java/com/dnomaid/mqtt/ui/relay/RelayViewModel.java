package com.dnomaid.mqtt.ui.relay;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dnomaid.mqtt.device.Device;
import com.dnomaid.mqtt.device.Devices;

import java.util.ArrayList;

public class RelayViewModel extends ViewModel {

    private RelayViewState viewState;
    private ArrayList<Device> status = Devices.getInst().getRelays();
    private  MutableLiveData<RelayViewState> viewMLD = new MutableLiveData<>();
    LiveData<RelayViewState> viewLD = viewMLD;

    public RelayViewModel(){
        viewState = new RelayViewState();
    }
    public void updateState(ArrayList<Device> status) {
        this.status = status;
        viewState.setDevice(this.status);
        viewMLD.setValue(viewState);
    }
}