package com.dnomaid.mqtt.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.client.Actions;

public class ConfigFragment extends Fragment {
    private TextView textViServer,textViPort,textViClientId,textViCleanSession;
    View view;
    Activity activity;
    Actions actions;
    public ConfigFragment() { }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_config, container, false);
        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            activity = (Activity) context;
            actions = (Actions) activity;
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViServer = view.findViewById(R.id.textViServer);
        textViPort = view.findViewById(R.id.textViPort);
        textViClientId = view.findViewById(R.id.textViClientId);
        textViCleanSession = view.findViewById(R.id.textViCleanSession);
    }
    public interface OnFragmentCommunicationListener {
        void onNameChangeServer(String name);
        void onNameChangePort(String name);
        void onNameChangeClientId(String name);
        void onNameChangeCleanSession(String name);
    }
    public void onNameChangeServer(String name) {textViServer.setText(name);}
    public void onNameChangePort(String name) {textViPort.setText(name);}
    public void onNameChangeClientId(String name) {textViClientId.setText(name);}
    public void onNameChangeCleanSession(String name) {textViCleanSession.setText(name);}
}