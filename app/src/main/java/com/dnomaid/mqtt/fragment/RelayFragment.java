package com.dnomaid.mqtt.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.client.Actions;
import com.dnomaid.mqtt.global.ConnectionConstants;

public class RelayFragment extends Fragment {
    View view;
    Activity activity;
    Actions actions;
    public RelayFragment() { }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_relay, container, false);
        String topic;
        String buttonIndex = "btnRelay0";
        int numberRelay;
        String message;
        for(int i=1; i<=6; i++) {
            numberRelay = i;
            topic = ConnectionConstants.getInst().getPTOPIC01RELAY(numberRelay);
            message = "OFF";
            onClickHandler( buttonIndex, numberRelay, topic, message);
            message = "ON";
            onClickHandler(buttonIndex, numberRelay, topic, message);
        }
        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            activity = (Activity) context;
            actions = (Actions) activity;
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public interface OnFragmentCommunicationListener {
        void onStatusChangeRelay(int numberRelay, String status);
    }
    public void onStatusChangeRelay(int numberRelay, String status) {
        setTextView("textViRelay0", numberRelay, status);
        }

    public Button onClickHandler(String buttonIndex, int numberRelay, String topic, String message){
        String buttonID = buttonIndex + numberRelay + message;
        int resID = getResources().getIdentifier(buttonID, "id", this.getContext().getPackageName());
        Button relay = view.findViewById(resID);
        relay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actions.publish(topic,message);
            }
        });
        return relay;
    }
    public void setTextView(String buttonIndex, int numberRelay,String name){
        String buttonID = buttonIndex + numberRelay + "ONOFF";
        int resID = getResources().getIdentifier(buttonID, "id", this.getContext().getPackageName());
        TextView textView =  view.findViewById(resID);
        textView.setText(name);
    }
}