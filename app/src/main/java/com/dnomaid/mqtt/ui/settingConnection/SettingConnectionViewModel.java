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
            if ((!this.viewValueUser.getServer().equals(Status.EMPTY))&&(this.viewValueUser.getServer()!=null))
            {ConnectionConstants.getInst().setServer(this.viewValueUser.getServer());}

            ConnectionConstants.getInst().setPort(this.viewValueUser.getPort());

            if ((!this.viewValueUser.getClientId().equals(Status.EMPTY))&&(this.viewValueUser.getClientId()!=null))
            {ConnectionConstants.getInst().setClientId(this.viewValueUser.getClientId());}

            ConnectionConstants.getInst().setCleanSession(this.viewValueUser.isCleanSession());

            ConnectionConstants.getInst().setTimeOut(this.viewValueUser.getTimeOut());

            ConnectionConstants.getInst().setKeepAlive(this.viewValueUser.getKeepAlive());

            if ((!this.viewValueUser.getUsername().equals(Status.EMPTY))&&(this.viewValueUser.getUsername()!=null))
            {ConnectionConstants.getInst().setUsername(this.viewValueUser.getUsername());}

            if ((!this.viewValueUser.getPassword().equals(Status.EMPTY))&&(this.viewValueUser.getPassword()!=null))
            {ConnectionConstants.getInst().setPassword(this.viewValueUser.getPassword());}

            aux = true;
            updateState();
        }
        return aux;
    }

}