package com.dnomaid.mqtt.ui.relay;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dnomaid.mqtt.R;

public class RelayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private RelayRecyclerViClickList mListener;
    private TextView name;
    private Button RelayON,RelayOFF;

    public RelayViewHolder(@NonNull View itemView, RelayRecyclerViClickList listener) {
        super(itemView);
        mListener = listener;
        name = itemView.findViewById(R.id.textViNameRelay);
        RelayON = itemView.findViewById(R.id.btnRelayON);
        RelayON.setTextColor(Color.WHITE);
        RelayON.setOnClickListener(this);
        RelayOFF = itemView.findViewById(R.id.btnRelayOFF);
        RelayOFF.setTextColor(Color.WHITE);
        RelayOFF.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        mListener.onClick(view, getAdapterPosition());
    }
    public void setNameRelay(String name) {
        this.name.setText(name);
    }
    public void setTextColorButton (String power){
        if(power != null) {
            switch (power) {
                case "ON":
                    RelayON.setTextColor(Color.parseColor("#13da33"));
                    break;
                case "OFF":
                    RelayOFF.setTextColor(Color.RED);
                    break;
                default:
                    RelayON.setTextColor(Color.WHITE);
                    RelayOFF.setTextColor(Color.WHITE);
            }
        }
    }

}
