package com.dnomaid.mqtt.ui.relay;

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

public class RelayDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Device> Device;
    private RelayRecyclerViClickList mListener;

    public RelayDataAdapter(RelayRecyclerViClickList mListener) {
        this.mListener = mListener;
        Device = new ArrayList<>();
        updateData(Devices.getInst().getRelays());
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
        View v = LayoutInflater.from(context).inflate(R.layout.relay_list, null, false);
        return new RelayViewHolder(v, mListener,context);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RelayViewHolder) {
            RelayViewHolder relayHolder = (RelayViewHolder) holder;
            relayHolder.setNameRelay(Device.get(position).getAlias());
            relayHolder.setTextColorButton(Device.get(position).getTopics().get(0).getValueTopic(ActionTopic.TypeTopic.Power));
        }
    }
    @Override
    public int getItemCount() {
        return Device.size();
    }
}
