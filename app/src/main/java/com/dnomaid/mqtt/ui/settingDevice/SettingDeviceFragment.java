package com.dnomaid.mqtt.ui.settingDevice;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.dnomaid.mqtt.device.Devices;

public class SettingDeviceFragment extends Fragment {

    private View view;
    private SettingDeviceViewModel configViewModel;
    private SettingDeviceDataAdapter adapter;
    private RecyclerView recyclerView;
    private Activity activity;
    private ActionsDevice actions;
    private SettingDeviceRecyclerViClickList listener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        configViewModel =
                ViewModelProviders.of(this).get(SettingDeviceViewModel.class);
        view = inflater.inflate(R.layout.fragment_setting, container, false);
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
        recyclerView = view.findViewById(R.id.recyclerViConfigDevice);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter = new SettingDeviceDataAdapter(listener) ;
        recyclerView.setAdapter(adapter);
    }
    private void setupViewModel() {
        configViewModel = new ViewModelProvider(requireActivity()).get(SettingDeviceViewModel.class);
        configViewModel.viewLD.observe(getViewLifecycleOwner(), item -> {
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
                case R.id.btnInfoDevice:
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.getContext());
                    alertDialog.setTitle("Setting Topic");
                    String topic = "";
                    for (int i = 0; i < Devices.getInst().getDevices().get(position).getTopics().size() ; i++) {
                        topic = topic + "Topic " +i+": \n";
                        topic = topic + Devices.getInst().getDevices().get(position).getTopics().get(i).getName()+"\n";
                    }
                    alertDialog.setMessage(topic);
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = alertDialog.create();
                    dialog.show();
                    break;
                default:
                    break;
            }
        };
        adapter = new SettingDeviceDataAdapter(listener);
        recyclerView.setAdapter(adapter);
    }
}