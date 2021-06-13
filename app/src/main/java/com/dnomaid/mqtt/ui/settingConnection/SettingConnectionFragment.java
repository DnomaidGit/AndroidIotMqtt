package com.dnomaid.mqtt.ui.settingConnection;

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

public class SettingConnectionFragment extends Fragment {

    private View view;
    private SettingConnectionViewModel connectionViewModel;
    private SettingConnectionViewValueUser viewValueUser;
    private Button btnSaveSettingConnection;
    private EditText serverUser, portUser, clientIdUser, timeOutUser, keepAliveUser, usernameUser, passwordUser;
    private CheckBox cleanSessionCheckBoxUser;
    private TextView serverSetting, portSetting, clientIdSetting, cleanSessionSetting,
            timeOutSetting, keepAliveSetting, usernameSetting;
    //private SettingConnectionVMFactory connectionVMFactory;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        connectionViewModel =
                ViewModelProviders.of(this).get(SettingConnectionViewModel.class);
        view = inflater.inflate(R.layout.fragment_setting_connection, container, false);
        setupViewValueUser();
        setupViewOnclick(view);
        setupCheckedChanged();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupTextView(view);
        setupViewModel();
        setupEditText(view);
        setupEditTextChange();
    }

    private void setupTextView(View view) {
        serverSetting = view.findViewById(R.id.serverSetting);
        portSetting = view.findViewById(R.id.portSetting);
        clientIdSetting = view.findViewById(R.id.clientIdSetting);
        cleanSessionSetting = view.findViewById(R.id.cleanSessionSetting);
        timeOutSetting = view.findViewById(R.id.timeOutSetting);
        keepAliveSetting = view.findViewById(R.id.keepAliveSetting);
        usernameSetting = view.findViewById(R.id.usernameSetting);
    }
    private void setupEditText(View view) {
        serverUser = view.findViewById(R.id.serverUser);
        portUser = view.findViewById(R.id.portUser);
        clientIdUser = view.findViewById(R.id.clientIdUser);
        timeOutUser = view.findViewById(R.id.timeOutUser);
        keepAliveUser = view.findViewById(R.id.keepAliveUser);
        usernameUser = view.findViewById(R.id.usernameUser);
        passwordUser = view.findViewById(R.id.passwordUser);
    }

    private void setupViewModel() {
        //connectionVMFactory = new SettingConnectionVMFactory(viewValueUser);
        //connectionViewModel = new ViewModelProvider(requireActivity(),connectionVMFactory).get(SettingConnectionViewModel.class);
        connectionViewModel = new ViewModelProvider(requireActivity()).get(SettingConnectionViewModel.class);
        connectionViewModel.viewLD.observe(getViewLifecycleOwner(), item -> {
            serverSetting.setText(item.getServer());
            portSetting.setText(item.getPort());
            clientIdSetting.setText(item.getClientId());
            cleanSessionSetting.setText(item.getCleanSession());
            timeOutSetting.setText(item.getTimeOut());
            keepAliveSetting.setText(item.getKeepAlive());
            usernameSetting.setText(item.getUsername());
        });
        connectionViewModel.updateState();
    }
    private void setupViewOnclick(View view) {
        btnSaveSettingConnection = view.findViewById(R.id.btnSaveSettingConnection);
        btnSaveSettingConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectionViewModel.uploadValueUser(viewValueUser);
                connectionViewModel.saveSettingConnection();
            }
        });
    }
    private void setupViewValueUser() {
        viewValueUser = new SettingConnectionViewValueUser();
    }
    private void setupEditTextChange(){
        serverUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    viewValueUser.setServer(serverUser.getText().toString());
                }catch (Exception e){
                    System.err.println("error text Server: "+ e);
                    e.printStackTrace();
                }
            }
        });
        portUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    viewValueUser.setPort(Integer.parseInt(portUser.getText().toString()));
                }catch (Exception e){
                    System.err.println("error text Port: "+ e);
                    e.printStackTrace();
                }
            }
        });
        clientIdUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    viewValueUser.setClientId(clientIdUser.getText().toString());
                }catch (Exception e){
                    System.err.println("error text ClientId: "+ e);
                    e.printStackTrace();
                }
            }
        });
        timeOutUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    viewValueUser.setTimeOut(Integer.parseInt(timeOutUser.getText().toString()));
                }catch (Exception e){
                    System.err.println("error text TimeOut: "+ e);
                    e.printStackTrace();
                }
            }
        });
        keepAliveUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    viewValueUser.setKeepAlive(Integer.parseInt(keepAliveUser.getText().toString()));
                }catch (Exception e){
                    System.err.println("error text KeepAlive: "+ e);
                    e.printStackTrace();
                }
            }
        });
        usernameUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    viewValueUser.setUsername(usernameUser.getText().toString());
                }catch (Exception e){
                    System.err.println("error text Username: "+ e);
                    e.printStackTrace();
                }
            }
        });
        passwordUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    viewValueUser.setPassword(passwordUser.getText().toString());
                }catch (Exception e){
                    System.err.println("error text Password: "+ e);
                    e.printStackTrace();
                }
            }
        });
    }
    private void setupCheckedChanged(){
        cleanSessionCheckBoxUser = view.findViewById(R.id.cleanSessionCheckBoxUser);
        cleanSessionCheckBoxUser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                try {
                    viewValueUser.setCleanSession(cleanSessionCheckBoxUser.isChecked());
                }catch (Exception e){
                    System.err.println("error text cleanSession: "+ e);
                    e.printStackTrace();
                }
            }
        });
    }

}

