package com.dnomaid.mqtt.fragment;

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

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.client.Actions;

public class TemperatureFragment extends Fragment {
    View view;
    Activity activity;
    Actions actions;
    public TemperatureFragment() { }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_temperature, container, false);
        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            activity = (Activity) context;
            actions = (Actions) activity;
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public interface OnFragmentCommunicationListener {
        void onStatusChangeTemp(int numberSensor, String status);
        void onStatusChangeHum(int numberSensor, String status);
    }
    public void onStatusChangeTemp(int numberSensor, String status) {
        setTextView("textViTemp0", numberSensor, status);
    }
    public void onStatusChangeHum(int numberSensor, String status) {
        setTextView("textViHum0", numberSensor, status);
    }
    public void setTextView(String textIndex, int numberSensor,String name){
        String textID = textIndex + numberSensor;
        int resID = getResources().getIdentifier(textID, "id", this.getContext().getPackageName());
        TextView textView =  view.findViewById(resID);
        textView.setText(name);
    }
}