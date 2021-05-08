package com.dnomaid.mqtt.ui.setting;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dnomaid.mqtt.R;

public class SettingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private SettingRecyclerViClickList mListener;
    private TextView device;

    public SettingViewHolder(@NonNull View itemView, SettingRecyclerViClickList listener) {
        super(itemView);
        mListener = listener;
        device = itemView.findViewById(R.id.textViNameDevice);
        itemView.findViewById(R.id.btnDeleteDevice).setOnClickListener(this);
        itemView.findViewById(R.id.btnInfoDevice).setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        mListener.onClick(view, getAdapterPosition());
    }
    public void setNameDevice(String name) {
        this.device.setText(name);
    }
}
