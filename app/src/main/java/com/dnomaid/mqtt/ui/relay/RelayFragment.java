package com.dnomaid.mqtt.ui.relay;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.client.ActionsMqtt;
import com.dnomaid.mqtt.device.Devices;

public class RelayFragment extends Fragment {

    private View view;
    private RelayViewModel relayViewModel;
    private RelayDataAdapter adapter;
    private RecyclerView recyclerView;
    private Activity activity;
    private ActionsMqtt actions;
    private RelayRecyclerViClickList listener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        relayViewModel =
                ViewModelProviders.of(this).get(RelayViewModel.class);
        view = inflater.inflate(R.layout.fragment_relay, container, false);
        buttonClickPower();
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
        recyclerView = view.findViewById(R.id.recyclerViRelayDevice);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter = new RelayDataAdapter(listener);
        recyclerView.setAdapter(adapter);
    }
    private void setupViewModel() {
        relayViewModel = new ViewModelProvider(requireActivity()).get(RelayViewModel.class);
        relayViewModel.viewLD.observe(getViewLifecycleOwner(), item -> {
                adapter.updateData(item.getDevice());
                recyclerView.setAdapter(adapter);
        });
    }
    private void buttonClickPower(){
        recyclerView =  view.findViewById(R.id.recyclerViRelayDevice);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        listener = (view, position) -> {
            String topic = Devices.getInst().getPublishTopicRelay(position+1);
            String message = "";
            switch (view.getId()) {
                case R.id.btnRelayOFF:
                    message = "OFF";
                    actions.publish(topic,message);
                    break;
                case R.id.btnRelayON:
                    message = "ON";
                    actions.publish(topic,message);
                    break;
                default:
                    break;
            }
        };
        adapter = new RelayDataAdapter(listener);
        recyclerView.setAdapter(adapter);
    }
}