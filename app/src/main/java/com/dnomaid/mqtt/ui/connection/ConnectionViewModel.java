package com.dnomaid.mqtt.ui.connection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dnomaid.mqtt.global.ConnectionConstants;
import com.dnomaid.mqtt.global.Status;

public class ConnectionViewModel extends ViewModel {

    private ConnectionViewState viewState;
    private ConnectionViewValueUser viewValueUser;
    private Status status = Status.getInst();
    private  MutableLiveData<ConnectionViewState> viewMLD = new MutableLiveData<>();
    LiveData<ConnectionViewState> viewLD = viewMLD;

    public ConnectionViewModel(){
        viewState = new ConnectionViewState();
        viewValueUser = new ConnectionViewValueUser();
    }
    public ConnectionViewModel(ConnectionViewValueUser viewValueUser){
        viewState = new ConnectionViewState();
        this.viewValueUser = viewValueUser;
    }
    public boolean uploadValueUser(ConnectionViewValueUser viewValueUser) {
        boolean aux = false;
        this.viewValueUser = viewValueUser;
        if (this.viewValueUser!=null) {
            if ((!this.viewValueUser.getServer().equals(Status.EMPTY))&&(this.viewValueUser.getServer()!=null))
            {ConnectionConstants.getInst().setServer(this.viewValueUser.getServer());}

            ConnectionConstants.getInst().setPort(this.viewValueUser.getPort());

            if ((!this.viewValueUser.getClientId().equals(Status.EMPTY))&&(this.viewValueUser.getClientId()!=null))
            {ConnectionConstants.getInst().setClientId(this.viewValueUser.getClientId());}

            aux = true;
        }
        return aux;
    }
    public void updateState() {
        viewState.setConnectionStatus(status.getConnectionStatus());
        viewState.setSubscribeStatus(status.getTopicStatus());
        viewState.setMessageArrived(status.getMessageArrived());
        viewState.setServer(ConnectionConstants.getInst().getServer());
        viewState.setPort(String.valueOf(ConnectionConstants.getInst().getPort()));
        viewState.setClientId(ConnectionConstants.getInst().getClientId());
        viewState.setCleanSession(ConnectionConstants.getInst().isCleanSession() ? "True" : "False");
        viewMLD.setValue(viewState);
    }

}