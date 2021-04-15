package com.dnomaid.mqtt.ui.relay;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dnomaid.mqtt.R;

public class RelayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private RecyclerViewClickListener mListener;
    private TextView name;
    private TextView power;

    public RelayViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
        super(itemView);
        mListener = listener;
        name = itemView.findViewById(R.id.textViNameRelay);
        power = itemView.findViewById(R.id.textViRelayONOFF);
        itemView.findViewById(R.id.btnRelayOFF).setOnClickListener(this);
        itemView.findViewById(R.id.btnRelayON).setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        mListener.onClick(view, getAdapterPosition());
    }
    public void setDataPower(String power) {
        this.power.setText(power);
    }
    public void setNameRelay(String name) {
        this.name.setText(name);
    }
}
