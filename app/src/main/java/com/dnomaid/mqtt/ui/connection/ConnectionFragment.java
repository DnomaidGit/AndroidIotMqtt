package com.dnomaid.mqtt.ui.connection;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.client.ActionsMqtt;
import com.dnomaid.mqtt.global.ConnectionConstants;

public class ConnectionFragment extends Fragment {

    private View view;
    private ConnectionViewModel connectionViewModel;
    private ConnectionViewValueUser viewValueUser;
    private TextView serverInfo, portInfo, clientIdInfo, cleanSessionInfo;
    private TextView textViConnect,textViSubscribe,textViMessageArrived;
    private Button btnConnect,btnDisconnect,btnSubscribe,btnUnsubscribe;
    private Activity activity;
    private ActionsMqtt actions;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        connectionViewModel =
                ViewModelProviders.of(this).get(ConnectionViewModel.class);
        view = inflater.inflate(R.layout.fragment_connection, container, false);
        setupViewValueUser();
        setupViewOnclick(view);
        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            activity = (Activity) context;
            actions = (ActionsMqtt) activity;
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
        setupViewModel();
    }

    private void setupView(View view) {
        serverInfo = view.findViewById(R.id.textViServer);
        portInfo = view.findViewById(R.id.textViPort);
        clientIdInfo = view.findViewById(R.id.textViClientId);
        cleanSessionInfo = view.findViewById(R.id.textViCleanSession);
        textViConnect = view.findViewById(R.id.textViConnect);
        textViSubscribe = view.findViewById(R.id.textViSubscribe);
        textViMessageArrived = view.findViewById(R.id.textViMessageArrived);
    }
    private void setupViewModel() {
        ConnectionVMFactory connectionVMFactory = new ConnectionVMFactory(viewValueUser);
        connectionViewModel = new ViewModelProvider(requireActivity(),connectionVMFactory).get(ConnectionViewModel.class);
        connectionViewModel.viewLD.observe(getViewLifecycleOwner(), item -> {
            textViConnect.setText(item.getConnectionStatus());
            textViSubscribe.setText(item.getSubscribeStatus());
            textViMessageArrived.setText(item.getMessageArrived());
            serverInfo.setText(item.getServer());
            portInfo.setText(item.getPort());
            clientIdInfo.setText(item.getClientId());
            cleanSessionInfo.setText(item.getCleanSession());
        });
    }
    private void setupViewValueUser() {
        viewValueUser = new ConnectionViewValueUser();
        viewValueUser.setServer(ConnectionConstants.getInst().getServer());
        viewValueUser.setPort(ConnectionConstants.getInst().getPort());
        viewValueUser.setClientId(ConnectionConstants.getInst().getClientId());
        viewValueUser.setCleanSession(ConnectionConstants.getInst().isCleanSession());
    }
    private void setupViewOnclick(View view) {
        btnConnect = view.findViewById(R.id.btnConnect);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
    }
}

