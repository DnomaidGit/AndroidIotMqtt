package com.dnomaid.mqtt.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.client.Actions;

public class HistoryFragment extends Fragment {
    private TextView textViHistory;
    View view;
    Activity activity;
    Actions actions;
    public HistoryFragment() { }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_history, container, false);
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
        textViHistory = view.findViewById(R.id.textViHistory);
    }
    public interface OnFragmentCommunicationListener {
        void onNameChangeHistory();
    }
    public void onNameChangeHistory(Spanned[] history) {
        String vv="";
        for(Spanned v : history) {
            vv = vv + v + "\n";
            textViHistory.setText(vv);
        }
    }
}