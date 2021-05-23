package com.dnomaid.mqtt.ui.temperature;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.device.Device;
import com.dnomaid.mqtt.device.Devices;
import com.dnomaid.mqtt.topic.ActionTopic;

import java.util.ArrayList;

public class TemperatureDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Device> Device;

    public TemperatureDataAdapter() {
        Device = new ArrayList<>();
        updateData(Devices.getInst().getSensorsClimate());
    }
    public void updateData(ArrayList<Device> device) {
        Device.clear();
        Device.addAll(device);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.temperature_list, null, false);
        return new TemperatureViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TemperatureViewHolder) {
            TemperatureViewHolder temperatureHolder = (TemperatureViewHolder) holder;
            temperatureHolder.setDataTemp(Device.get(position).getTopics().get(0).getValueTopic(ActionTopic.TypeTopic.Temperature));
            temperatureHolder.setDataHum(Device.get(position).getTopics().get(0).getValueTopic(ActionTopic.TypeTopic.Humidity));
            temperatureHolder.setNameTemperature(Device.get(position).getAlias());
        }
    }
    @Override
    public int getItemCount() {
        return Device.size();
    }
}
