package com.dnomaid.mqtt.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.client.Actions;
import com.dnomaid.mqtt.global.Constants;

import java.util.ArrayList;

public class ConfigFragment extends Fragment {
    private TextView textViServer,textViPort,textViClientId,textViCleanSession;
    private Spinner spinTypeDevice;
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
        spinTypeDevice = view.findViewById(R.id.spinTypeDevice);
        ArrayList<Constants.TypeDevice> spinTypeDeviceList = new ArrayList<>();
        for(Constants.TypeDevice value: Constants.TypeDevice.values() )
        {
            spinTypeDeviceList.add(value);
        }
        ArrayAdapter<Constants.TypeDevice> adapter = new ArrayAdapter<>(this.getContext(),android.R.layout.simple_spinner_item,spinTypeDeviceList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTypeDevice.setAdapter(adapter);
        spinTypeDevice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(),"Selected : "
                        +parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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