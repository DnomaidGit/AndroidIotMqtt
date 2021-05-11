package com.dnomaid.mqtt.ui.settingConnection;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.client.ActionsMqtt;
import com.dnomaid.mqtt.global.ConnectionConstants;

public class SettingConnectionFragment extends Fragment {

    private View view;
    private SettingConnectionViewModel connectionViewModel;
    private SettingConnectionViewValueUser viewValueUser;
    private EditText server,port,clientId;
    private CheckBox cleanSessionCheckBox;
    private TextView serverUser,portUser,clientIdUser,cleanSessionUser;
    private Activity activity;
    private ActionsMqtt actions;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        connectionViewModel =
                ViewModelProviders.of(this).get(SettingConnectionViewModel.class);
        view = inflater.inflate(R.layout.fragment_setting_connection, container, false);
        setupViewValueUser();
        setupEditTextChange();
        setupCheckedChanged();
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
        serverUser = view.findViewById(R.id.serverUser);
        portUser = view.findViewById(R.id.portUser);
        clientIdUser = view.findViewById(R.id.clientIdUser);
        cleanSessionUser = view.findViewById(R.id.cleanSessionUser);
    }
    private void setupViewModel() {
        SettingConnectionVMFactory connectionVMFactory = new SettingConnectionVMFactory(viewValueUser);
        connectionViewModel = new ViewModelProvider(requireActivity(),connectionVMFactory).get(SettingConnectionViewModel.class);
        connectionViewModel.viewLD.observe(getViewLifecycleOwner(), item -> {
            serverUser.setText(item.getServer());
            portUser.setText(item.getPort());
            clientIdUser.setText(item.getClientId());
            cleanSessionUser.setText(item.getCleanSession());
        });
    }
    private void setupViewValueUser() {
        viewValueUser = new SettingConnectionViewValueUser();
        viewValueUser.setServer(ConnectionConstants.getInst().getServer());
        viewValueUser.setPort(ConnectionConstants.getInst().getPort());
        viewValueUser.setClientId(ConnectionConstants.getInst().getClientId());
        viewValueUser.setCleanSession(ConnectionConstants.getInst().isCleanSession());
    }
    private void setupEditTextChange(){
        server = view.findViewById(R.id.server);
        server.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if(server.getText().toString().equals("")){
                        viewValueUser.setServer("0.0.0.0");
                    }
                    else{
                        viewValueUser.setServer(server.getText().toString());
                    }
                    connectionViewModel.uploadValueUser(viewValueUser);
                }catch (Exception e){
                    System.err.println("error text Server: "+ e);
                    e.printStackTrace();
                }
            }
        });
        port = view.findViewById(R.id.port);
        port.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if(port.getText().toString().equals("")){
                        viewValueUser.setPort(Integer.parseInt("0"));
                    }else{
                        viewValueUser.setPort(Integer.parseInt(port.getText().toString()));
                    }
                    connectionViewModel.uploadValueUser(viewValueUser);
                }catch (Exception e){
                    System.err.println("error text Port: "+ e);
                    e.printStackTrace();
                }
            }
        });
        clientId = view.findViewById(R.id.clientId);
        clientId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if(clientId.getText().toString().equals("")) {
                        viewValueUser.setClientId("clientId..??");
                    }
                    else{
                        viewValueUser.setClientId(clientId.getText().toString());
                    }
                    connectionViewModel.uploadValueUser(viewValueUser);
                }catch (Exception e){
                    System.err.println("error text ClientId: "+ e);
                    e.printStackTrace();
                }
            }
        });
    }
    private void setupCheckedChanged(){
        cleanSessionCheckBox = view.findViewById(R.id.cleanSessionCheckBox);
        cleanSessionCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                try {
                    viewValueUser.setCleanSession(cleanSessionCheckBox.isChecked());
                    connectionViewModel.uploadValueUser(viewValueUser);
                }catch (Exception e){
                    System.err.println("error text cleanSession: "+ e);
                    e.printStackTrace();
                }
            }
        });
    }

}

