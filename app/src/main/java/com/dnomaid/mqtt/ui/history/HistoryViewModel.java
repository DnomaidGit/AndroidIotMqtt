package com.dnomaid.mqtt.ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dnomaid.mqtt.global.Status;

public class HistoryViewModel extends ViewModel {

    private HistoryViewState viewState;
    private Status status = Status.getInst();
    private  MutableLiveData<HistoryViewState> viewMLD = new MutableLiveData<>();
    LiveData<HistoryViewState> viewLD = viewMLD;

    public HistoryViewModel(){
        viewState = new HistoryViewState();
    }

    public void updateState() {
        viewState.setHistory(status.getHistory());
        viewMLD.setValue(viewState);
    }
}