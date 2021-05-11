package com.dnomaid.mqtt.ui.settingConnection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dnomaid.mqtt.global.ConnectionConstants;
import com.dnomaid.mqtt.global.Status;

public class SettingConnectionViewModel extends ViewModel {

    private SettingConnectionViewState viewState;
    private SettingConnectionViewValueUser viewValueUser;
    private Status status = Status.getInst();
    private  MutableLiveData<SettingConnectionViewState> viewMLD = new MutableLiveData<>();
    LiveData<SettingConnectionViewState> viewLD = viewMLD;

    public SettingConnectionViewModel(){
        viewState = new SettingConnectionViewState();
        viewValueUser = new SettingConnectionViewValueUser();
    }
    public SettingConnectionViewModel(SettingConnectionViewValueUser viewValueUser){
        viewState = new SettingConnectionViewState();
        this.viewValueUser = viewValueUser;
    }
    public boolean uploadValueUser(SettingConnectionViewValueUser viewValueUser) {
        boolean aux = false;
        this.viewValueUser = viewValueUser;
        if (this.viewValueUser!=null) {
            if ((!this.viewValueUser.getServer().equals(Status.EMPTY))&&(this.viewValueUser.getServer()!=null))
            {ConnectionConstants.getInst().setServer(this.viewValueUser.getServer());}

            ConnectionConstants.getInst().setPort(this.viewValueUser.getPort());

            if ((!this.viewValueUser.getClientId().equals(Status.EMPTY))&&(this.viewValueUser.getClientId()!=null))
            {ConnectionConstants.getInst().setClientId(this.viewValueUser.getClientId());}

            ConnectionConstants.getInst().setCleanSession(this.viewValueUser.isCleanSession());
            aux = true;
        }
        return aux;
    }
    public void updateState() {
        viewState.setServer(ConnectionConstants.getInst().getServer());
        viewState.setPort(String.valueOf(ConnectionConstants.getInst().getPort()));
        viewState.setClientId(ConnectionConstants.getInst().getClientId());
        viewState.setCleanSession(ConnectionConstants.getInst().isCleanSession() ? "True" : "False");
        viewMLD.setValue(viewState);
    }

}