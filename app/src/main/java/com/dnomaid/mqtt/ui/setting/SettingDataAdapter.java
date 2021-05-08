package com.dnomaid.mqtt.ui.setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.device.DeviceConfig;
import com.dnomaid.mqtt.device.Devices;

import java.util.ArrayList;

public class SettingDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<DeviceConfig> DeviceConfig;
    private SettingRecyclerViClickList mListener;

    public SettingDataAdapter(SettingRecyclerViClickList mListener) {
        this.mListener = mListener;
        DeviceConfig = new ArrayList<>();
        updateData(Devices.getInst().getDevicesConfig());
    }
    public void updateData(ArrayList<DeviceConfig> deviceConfig) {
        DeviceConfig.clear();
        DeviceConfig.addAll(deviceConfig);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.device_list, null, false);
        return new SettingViewHolder(v, mListener);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SettingViewHolder) {
            SettingViewHolder configHolder = (SettingViewHolder) holder;
            configHolder.setNameDevice(DeviceConfig.get(position).toString());
        }
    }
    @Override
    public int getItemCount() {
        return DeviceConfig.size();
    }
}
