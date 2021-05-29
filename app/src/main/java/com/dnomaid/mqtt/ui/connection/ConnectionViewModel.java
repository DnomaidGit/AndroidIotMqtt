package com.dnomaid.mqtt.ui.connection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dnomaid.mqtt.global.ConnectionConstants;
import com.dnomaid.mqtt.global.Status;

public class ConnectionViewModel extends ViewModel {

    private ConnectionViewState viewState;
    private Status status = Status.getInst();
    private  MutableLiveData<ConnectionViewState> viewMLD = new MutableLiveData<>();
    LiveData<ConnectionViewState> viewLD = viewMLD;

    public ConnectionViewModel(){
        viewState = new ConnectionViewState();
    }
    public void updateState() {
        if(status.getConnectionStatus().equals(Status.ConnectionStatus.NONE.name())){
            viewState.setConnectionStatus("");
        }else{
            viewState.setConnectionStatus(status.getConnectionStatus());
        }
        if(status.getTopicStatus().equals(Status.TopicStatus.NONE.name())){
            viewState.setSubscribeStatus("");
            viewState.setMessageArrived("");
        }else{
            viewState.setSubscribeStatus(status.getTopicStatus());
            viewState.setMessageArrived(status.getMessageArrived());
        }
        viewState.setServer(ConnectionConstants.getInst().getServer());
        viewState.setPort(String.valueOf(ConnectionConstants.getInst().getPort()));
        viewState.setClientId(ConnectionConstants.getInst().getClientId());
        viewMLD.setValue(viewState);
    }

}