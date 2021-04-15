package com.dnomaid.mqtt.ui.config;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.device.DeviceConfig;

import java.util.ArrayList;

public class ConfigDataAdapter extends RecyclerView.Adapter<ConfigDataAdapter.ViewHolderData> {
    ArrayList<DeviceConfig> DevicesConfig;

    public ConfigDataAdapter(ArrayList<DeviceConfig> devicesConfig) {
        DevicesConfig = devicesConfig;
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_list,null,false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {
        holder.data.setText(DevicesConfig.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return DevicesConfig.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {
        TextView data;
        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.recyclerViDeviceData);
        }
    }
    public void notifyDataSetChanged(ArrayList<DeviceConfig> devicesConfig) {
        DevicesConfig = devicesConfig;
    }
}
