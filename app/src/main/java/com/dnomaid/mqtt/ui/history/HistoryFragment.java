package com.dnomaid.mqtt.ui.history;

import android.os.Bundle;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.dnomaid.mqtt.R;

public class HistoryFragment extends Fragment {

    private View view;
    private HistoryViewModel historyViewModel;
    private TextView textViHistory;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        historyViewModel =
                ViewModelProviders.of(this).get(HistoryViewModel.class);
        view = inflater.inflate(R.layout.fragment_history, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
        setupViewModel();
    }

    private void setupView(View view) {
        textViHistory = view.findViewById(R.id.textViHistory);
    }
    private void setupViewModel() {
        historyViewModel = new ViewModelProvider(requireActivity()).get(HistoryViewModel.class);
        historyViewModel.viewLD.observe(getViewLifecycleOwner(), item -> {
            String vv="";
            for(Spanned v : item.getHistory()) {
                vv = vv + v + "\n";
                textViHistory.setText(vv);
            }
            textViHistory.setText(vv);
        });
    }
}