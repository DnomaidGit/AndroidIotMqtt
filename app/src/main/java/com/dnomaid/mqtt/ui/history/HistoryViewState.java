package com.dnomaid.mqtt.ui.history;

import android.text.Spanned;

public class HistoryViewState {
    private Spanned[] history;
    public Spanned[] getHistory() {
        return history;
    }

    public void setHistory(Spanned[] history) {
        this.history = history;
    }
}
