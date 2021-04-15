package com.dnomaid.mqtt.ui.temperature;

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

public class TemperatureFragment extends Fragment {

    private View view;
    private TemperatureViewModel temperatureViewModel;
    private TemperatureDataAdapter adapter;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        temperatureViewModel =
                ViewModelProviders.of(this).get(TemperatureViewModel.class);
        view = inflater.inflate(R.layout.fragment_temperature, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
        setupViewModel();
    }
    private void setupView(View view) {
        recyclerView = view.findViewById(R.id.recyclerViDevice);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter = new TemperatureDataAdapter();
        recyclerView.setAdapter(adapter);
    }
    private void setupViewModel() {
        temperatureViewModel = new ViewModelProvider(requireActivity()).get(TemperatureViewModel.class);
        temperatureViewModel.viewLD.observe(getViewLifecycleOwner(), item -> {
            adapter.updateData(item.getDevice());
            recyclerView.setAdapter(adapter);
        });

    }
}