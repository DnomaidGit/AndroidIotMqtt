package com.dnomaid.mqtt.ui.relay;

import android.content.Context;
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
    private Context context;

    public RelayViewHolder(@NonNull View itemView, RelayRecyclerViClickList listener, Context context) {
        super(itemView);
        this.context = context;
        mListener = listener;
        name = itemView.findViewById(R.id.textViNameRelay);
        RelayON = itemView.findViewById(R.id.btnRelayON);
        RelayON.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        RelayON.setOnClickListener(this);
        RelayOFF = itemView.findViewById(R.id.btnRelayOFF);
        RelayOFF.setTextColor(context.getResources().getColor(R.color.colorPrimary));
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
                    RelayON.setTextColor(context.getResources().getColor(R.color.colorTextButtonRelayON));
                    break;
                case "OFF":
                    RelayOFF.setTextColor(context.getResources().getColor(R.color.colorTextButtonRelayOFF));
                    break;
                default:
                    RelayON.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    RelayOFF.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            }
        }
    }

}
