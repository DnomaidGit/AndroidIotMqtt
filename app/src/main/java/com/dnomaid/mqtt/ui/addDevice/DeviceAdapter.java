package com.dnomaid.mqtt.ui.addDevice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dnomaid.mqtt.R;

import java.util.ArrayList;

public class DeviceAdapter extends ArrayAdapter <DeviceItem> {
    LayoutInflater layoutInflater;
    TextView textView;
    ImageView imageView;
    DeviceItem deviceItem;

    public DeviceAdapter(Context context,int resource, ArrayList<DeviceItem> deviceList){
        super(context,resource,deviceList);
        layoutInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = layoutInflater.inflate(R.layout.device_spinner_row, null,true);
        if(rowView != null) {
            deviceItem = getItem(position);
            textView = rowView.findViewById(R.id.textViDeviceSpinner);
            imageView = rowView.findViewById(R.id.imageDeviceSpinner);
            if (deviceItem != null) {
                imageView.setImageResource(deviceItem.getImage());
                textView.setText(deviceItem.getType().name());
            }
        }
        return rowView;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.device_spinner_row,parent,false);
        }
        imageView = convertView.findViewById(R.id.imageDeviceSpinner);
        textView =  convertView.findViewById(R.id.textViDeviceSpinner);
        deviceItem = getItem(position);
        if(deviceItem != null) {
            imageView.setImageResource(deviceItem.getImage());
            textView.setText(deviceItem.getType().name());
        }
        return convertView;
    }
}
