package com.dnomaid.mqtt.ui.addDevice;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.device.ActionsDevice;
import com.dnomaid.mqtt.global.Constants;
import com.dnomaid.mqtt.ui.history.HistoryViewModel;

import java.util.ArrayList;

public class AddDeviceFragment extends Fragment {

    private View view;
    private TextView textNumberDevice, textTypeDevice;
    private Button btnAddDevice;
    private Spinner spinnerNumberDevice, spinnerTypeDevice;
    private Activity activity;
    private ActionsDevice actions;

    private ArrayList<DeviceItem> deviceItems;
    private DeviceAdapter deviceAdapter;
    private Spinner deviceSpinner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_device, container, false);
        setupViewOnclick(view);
        initList();
        setupClickSpinner();

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            activity = (Activity) context;
            actions = (ActionsDevice) activity;
        }
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
        textTypeDevice = view.findViewById(R.id.textTypeDevice);
        spinnerTypeDevice = view.findViewById(R.id.spinnerTypeDevice);
        ArrayList<Constants.TypeDevice> spinTypeDeviceList = new ArrayList<>();
        for(Constants.TypeDevice value: Constants.TypeDevice.values() )
        {
            spinTypeDeviceList.add(value);
        }
        ArrayAdapter<Constants.TypeDevice> adapter = new ArrayAdapter<>(this.getContext(),android.R.layout.simple_spinner_item,spinTypeDeviceList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypeDevice.setAdapter(adapter);
        spinnerTypeDevice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    private void setupViewModel() {

    }
    private void setupViewOnclick(View view) {
        btnAddDevice = view.findViewById(R.id.btnAddDevice);
        btnAddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actions.newDevice(Constants.TypeDevice.valueOf(
                        spinnerTypeDevice.getSelectedItem().toString()),
                        spinnerNumberDevice.getSelectedItem().toString());
            }
        });


    }
    private void initList(){
        deviceItems = new ArrayList<>();
        deviceItems.add(new DeviceItem(Constants.TypeDevice.SonoffS20.toString(),R.drawable.ic_baseline_library_add_24));
        deviceItems.add(new DeviceItem(Constants.TypeDevice.SonoffSNZB02.toString(),R.drawable.ic_baseline_library_add_24));
        deviceItems.add(new DeviceItem(Constants.TypeDevice.AqaraTemp.toString(),R.drawable.ic_baseline_library_add_24));
        deviceItems.add(new DeviceItem(Constants.TypeDevice.TuyaZigBeeSensor.toString(),R.drawable.ic_baseline_library_add_24));
        deviceItems.add(new DeviceItem(Constants.TypeDevice.XiaomiZNCZ04LM.toString(),R.drawable.ic_baseline_library_add_24));
    }
    private void setupClickSpinner(){
        deviceSpinner = view.findViewById(R.id.deviceSpinner);
        deviceAdapter = new DeviceAdapter(getContext(), R.layout.device_spinner_row,deviceItems);
        deviceSpinner.setAdapter(deviceAdapter);
        deviceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}