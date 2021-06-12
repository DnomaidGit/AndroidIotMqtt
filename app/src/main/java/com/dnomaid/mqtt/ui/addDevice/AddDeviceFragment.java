package com.dnomaid.mqtt.ui.addDevice;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.device.ActionsDevice;
import com.dnomaid.mqtt.global.Constants.TypeDevice;

import java.util.ArrayList;

public class AddDeviceFragment extends Fragment {

    private View view;
    private Button btnAddDevice;
    private AutoCompleteTextView spinnerNumberDevice;
    private Activity activity;
    private ActionsDevice actions;

    private ArrayList<DeviceItem> deviceItems;
    private DeviceAdapter deviceAdapter;
    private Spinner deviceSpinner;
    private EditText aliasDeviceUser;
    private String selectTypeDevice;
    private String selectNumberDevice;
    private String aliasDevice;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_device, container, false);
        setupViewOnclick(view);
        setupEditTextChange();
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
        spinnerNumberDevice = view.findViewById(R.id.spinnerNumberDevice);
        ArrayAdapter<CharSequence> adapater = ArrayAdapter.createFromResource(getContext(),R.array.numberDevice,android.R.layout.simple_spinner_item);
        spinnerNumberDevice.setAdapter(adapater);
        spinnerNumberDevice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectNumberDevice = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(),"Number selected : "
                        +selectNumberDevice,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void setupEditTextChange(){
        aliasDeviceUser = view.findViewById(R.id.aliasDeviceUser);
        aliasDeviceUser.setText("");
        aliasDeviceUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    aliasDevice = aliasDeviceUser.getText().toString();
                }catch (Exception e){
                    System.err.println("error text Alias: "+ e);
                    e.printStackTrace();
                }
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
                if(aliasDeviceUser.getText().toString().equals("")){
                    aliasDevice = "Anonymous";
                }
                else{
                    aliasDevice = aliasDeviceUser.getText().toString();
                }
                actions.newDevice(TypeDevice.valueOf(
                        selectTypeDevice),
                        selectNumberDevice,aliasDevice);
            }
        });
    }
    private void initList(){
        deviceItems = new ArrayList<>();
        deviceItems.add(new DeviceItem(TypeDevice.SonoffS20,R.drawable.imag_sonoff_s20_128x128));
        deviceItems.add(new DeviceItem(TypeDevice.SonoffSNZB02,R.drawable.imag_sonoff_snzb02_128x128));
        deviceItems.add(new DeviceItem(TypeDevice.AqaraTemp,R.drawable.imag_aqara_temp_128x128));
        deviceItems.add(new DeviceItem(TypeDevice.TuyaZigBeeSensor,R.drawable.imag_tuya_zigbee_sensor_128x128));
        deviceItems.add(new DeviceItem(TypeDevice.XiaomiZNCZ04LM,R.drawable.imag_xiaomi_zncz04lm_128x128));
    }
    private void setupClickSpinner(){
        deviceSpinner = view.findViewById(R.id.deviceSpinner);
        deviceAdapter = new DeviceAdapter(getContext(), R.layout.device_spinner_row,deviceItems);
        deviceSpinner.setAdapter(deviceAdapter);
        deviceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectTypeDevice = deviceItems.get(position).getType().toString();
                Toast.makeText(parent.getContext(),"Type selected : "
                        +deviceItems.get(position).getType().toString(),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}