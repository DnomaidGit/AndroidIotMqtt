package com.dnomaid.mqtt.ui.addDevice;

import android.os.Bundle;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.ui.history.HistoryViewModel;

public class AddDeviceFragment extends Fragment {

    private View view;
    private TextView textNumberDevice;
    private Spinner spinnerNumberDevice;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_device, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);

    }

    private void setupView(View view) {
        textNumberDevice = view.findViewById(R.id.textNumberDevice);
        spinnerNumberDevice = view.findViewById(R.id.spinnerNumberDevice);
        ArrayAdapter<CharSequence> adapater = ArrayAdapter.createFromResource(getContext(),R.array.numberDevice,android.R.layout.simple_spinner_item);
        spinnerNumberDevice.setAdapter(adapater);
        spinnerNumberDevice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textNumberDevice.setText(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void setupViewModel() {

    }
}