package com.dnomaid.mqtt.ui.settingConnection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dnomaid.mqtt.global.ConnectionConstants;
import com.dnomaid.mqtt.global.Status;

public class SettingConnectionViewModel extends ViewModel {

    private SettingConnectionViewState viewState;
    private SettingConnectionViewValueUser viewValueUser;
    private  MutableLiveData<SettingConnectionViewState> viewMLD = new MutableLiveData<>();
    LiveData<SettingConnectionViewState> viewLD = viewMLD;

    public SettingConnectionViewModel(){
        viewState = new SettingConnectionViewState();
        viewValueUser = new SettingConnectionViewValueUser();
        this.viewValueUser.setServer(ConnectionConstants.getInst().getServer());
        this.viewValueUser.setPort(ConnectionConstants.getInst().getPort());
        this.viewValueUser.setClientId(ConnectionConstants.getInst().getClientId());
        this.viewValueUser.setCleanSession(ConnectionConstants.getInst().isCleanSession());
        this.viewValueUser.setTimeOut(ConnectionConstants.getInst().getTimeOut());
        this.viewValueUser.setKeepAlive(ConnectionConstants.getInst().getKeepAlive());
        this.viewValueUser.setUsername(ConnectionConstants.getInst().getUsername());
        this.viewValueUser.setPassword(ConnectionConstants.getInst().getPassword());
        updateState();
    }
    public SettingConnectionViewModel(SettingConnectionViewValueUser viewValueUser){
        viewState = new SettingConnectionViewState();
        this.viewValueUser = viewValueUser;
        updateState();
    }
    public void uploadValueUser(SettingConnectionViewValueUser viewValueUser) {
        this.viewValueUser = viewValueUser;
    }
    public void updateState() {
        viewState.setServer(ConnectionConstants.getInst().getServer());
        viewState.setPort(String.valueOf(ConnectionConstants.getInst().getPort()));
        viewState.setClientId(ConnectionConstants.getInst().getClientId());
        viewState.setCleanSession(ConnectionConstants.getInst().isCleanSession() ? "True" : "False");
        viewState.setTimeOut(String.valueOf(ConnectionConstants.getInst().getTimeOut()));
        viewState.setKeepAlive(String.valueOf(ConnectionConstants.getInst().getKeepAlive()));
        viewState.setUsername(ConnectionConstants.getInst().getUsername());
        viewState.setPassword(ConnectionConstants.getInst().getPassword());
        viewMLD.setValue(viewState);
    }
    public boolean  saveSettingConnection(){
        boolean aux = false;
        if (this.viewValueUser!=null) {
            if ((!this.viewValueUser.getServer().equals(Status.EMPTY)))
            ConnectionConstants.getInst().setServer(this.viewValueUser.getServer());

            if (this.viewValueUser.getPort()>0)
            ConnectionConstants.getInst().setPort(this.viewValueUser.getPort());

            if ((!this.viewValueUser.getClientId().equals(Status.EMPTY)))
            ConnectionConstants.getInst().setClientId(this.viewValueUser.getClientId());

            ConnectionConstants.getInst().setCleanSession(this.viewValueUser.isCleanSession());

            if (this.viewValueUser.getTimeOut()>0)
            ConnectionConstants.getInst().setTimeOut(this.viewValueUser.getTimeOut());

            if (this.viewValueUser.getKeepAlive()>0)
            ConnectionConstants.getInst().setKeepAlive(this.viewValueUser.getKeepAlive());

            if ((!this.viewValueUser.getUsername().equals(Status.EMPTY)))
            ConnectionConstants.getInst().setUsername(this.viewValueUser.getUsername());

            if ((!this.viewValueUser.getPassword().equals(Status.EMPTY)))
            ConnectionConstants.getInst().setPassword(this.viewValueUser.getPassword());

            aux = true;
            updateState();
        }
        return aux;
    }

}