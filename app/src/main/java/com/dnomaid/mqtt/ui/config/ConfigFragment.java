package com.dnomaid.mqtt.ui.config;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.device.ActionsDevice;

public class ConfigFragment extends Fragment {

    private View view;
    private ConfigViewModel configViewModel;
    private ConfigDataAdapter adapter;
    private TextView textViServer,textViPort,textViClientId,textViCleanSession;
    private RecyclerView recyclerView;
    private Activity activity;
    private ActionsDevice actions;
    private ConfigRecyclerViClickList listener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        configViewModel =
                ViewModelProviders.of(this).get(ConfigViewModel.class);
        view = inflater.inflate(R.layout.fragment_config, container, false);
        buttonClickDevice();
        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            activity = (Activity) context;
            actions = (ActionsDevice) activity;
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
        setupViewModel();
    }

    private void setupView(View view) {
        textViServer = view.findViewById(R.id.textViServer);
        textViPort = view.findViewById(R.id.textViPort);
        textViClientId = view.findViewById(R.id.textViClientId);
        textViCleanSession = view.findViewById(R.id.textViCleanSession);
        recyclerView = view.findViewById(R.id.recyclerViConfigDevice);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter = new ConfigDataAdapter(listener) ;
        recyclerView.setAdapter(adapter);

    }
    private void setupViewModel() {
        configViewModel = new ViewModelProvider(requireActivity()).get(ConfigViewModel.class);
        configViewModel.viewLD.observe(getViewLifecycleOwner(), item -> {
            textViServer.setText(item.getServer());
            textViPort.setText(item.getPort());
            textViClientId.setText(item.getClientId());
            textViCleanSession.setText(item.getCleanSession());
            adapter.updateData(item.getDeviceConfig());
            recyclerView.setAdapter(adapter);
        });
    }
    private void buttonClickDevice(){
        recyclerView =  view.findViewById(R.id.recyclerViConfigDevice);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        listener = (view, position) -> {
            switch (view.getId()) {
                case R.id.btnDeleteDevice:
                    actions.deleteDevice(position);
                    break;
                default:
                    break;
            }
        };
        adapter = new ConfigDataAdapter(listener);
        recyclerView.setAdapter(adapter);
    }
}