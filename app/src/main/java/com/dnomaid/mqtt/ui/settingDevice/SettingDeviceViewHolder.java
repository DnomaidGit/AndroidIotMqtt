package com.dnomaid.mqtt.ui.settingDevice;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dnomaid.mqtt.R;

public class SettingDeviceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private SettingDeviceRecyclerViClickList mListener;
    private TextView nameDevice;

    public SettingDeviceViewHolder(@NonNull View itemView, SettingDeviceRecyclerViClickList listener) {
        super(itemView);
        mListener = listener;
        nameDevice = itemView.findViewById(R.id.textViNameDevice);
        itemView.findViewById(R.id.btnDeleteDevice).setOnClickListener(this);
        itemView.findViewById(R.id.btnInfoDevice).setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        mListener.onClick(view, getAdapterPosition());
    }
    public void setNameDevice(String name) {
        this.nameDevice.setText(name);
    }
}
