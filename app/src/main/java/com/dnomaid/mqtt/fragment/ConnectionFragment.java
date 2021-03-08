package com.dnomaid.mqtt.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.client.Actions;
import com.dnomaid.mqtt.global.ConnectionConstants;
import com.dnomaid.mqtt.global.Notify;
import com.dnomaid.mqtt.global.Status;

public class ConnectionFragment extends Fragment  {
    private TextView server,port,clientId;
    private CheckBox cleanSessionCheckBox;
    private TextView textViConnect,textViSubscribe,textViMessageArrived;
    View view;
    Activity activity;
    Actions actions;
    Button btnConnect,btnDisconnect,btnSubscribe,btnUnsubscribe;
    public ConnectionFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_connection, container, false);
        btnConnect = view.findViewById(R.id.btnConnect);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeConnectionParameter();
                actions.connection();
            }
        });
        btnDisconnect = view.findViewById(R.id.btnDisconnect);
        btnDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actions.disconnection();
            }
        });
        btnSubscribe = view.findViewById(R.id.btnSubscribe);
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actions.subscribe();
            }
        });
        btnUnsubscribe = view.findViewById(R.id.btnUnsubscribe);
        btnUnsubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actions.unsubscribe();
            }
        });
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
        server = view.findViewById(R.id.server);
        port = view.findViewById(R.id.port);
        cleanSessionCheckBox = view.findViewById(R.id.cleanSessionCheckBox);
        clientId = view.findViewById(R.id.clientId);
        textViConnect = view.findViewById(R.id.textViConnect);
        textViSubscribe = view.findViewById(R.id.textViSubscribe);
        textViMessageArrived = view.findViewById(R.id.textViMessageArrived);
    }
    private void changeConnectionParameter() {
        String auxServer=server.getText().toString();
        String auxPort=port.getText().toString();
        int auxiPort=0;
        String auxClientId=clientId.getText().toString();
        if (!auxServer.equals(Status.EMPTY))ConnectionConstants.getInst().setServer(auxServer);
        if (auxPort!=null & !auxPort.equals(Status.EMPTY) )auxiPort=Integer.parseInt(auxPort);
        if (!auxPort.equals(Status.EMPTY))ConnectionConstants.getInst().setPort(auxiPort);
        if (!auxClientId.equals(Status.EMPTY))ConnectionConstants.getInst().setClientId(auxClientId);
        ConnectionConstants.getInst().setCleanSession(cleanSessionCheckBox.isChecked());
    }
    public interface OnFragmentCommunicationListener {
        void onNameChangeConnection(String name);
        void onNameChangeSubscribe(String name);
        void onNameChangeMessageArrived(String name);
    }
    public void onNameChangeConnection(String name) {textViConnect.setText(name);}
    public void onNameChangeSubscribe(String name) {textViSubscribe.setText(name);}
    public void onNameChangeMessageArrived(String name) {textViMessageArrived.setText(name);}
}