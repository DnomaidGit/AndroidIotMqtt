package com.dnomaid.mqtt.ui.temperature;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dnomaid.mqtt.R;

public class TemperatureViewHolder extends RecyclerView.ViewHolder {

    private TextView name, temp, hum;

    public TemperatureViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.textViNameTemp);
        temp = itemView.findViewById(R.id.textViTemp);
        hum = itemView.findViewById(R.id.textViHum);
    }
    public void setDataTemp(String temp) {
        this.temp.setText(temp);
    }
    public void setNameTemperature(String name) {
        this.name.setText(name);
    }
    public void setDataHum(String hum) {
        this.hum.setText(hum);
    }
}
