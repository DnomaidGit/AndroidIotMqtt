package com.dnomaid.mqtt;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.dnomaid.mqtt.client.Actions;
import com.dnomaid.mqtt.client.Connection;
import com.dnomaid.mqtt.client.Mqtt;
import com.dnomaid.mqtt.device.Devices;
import com.dnomaid.mqtt.global.Constants;
import com.dnomaid.mqtt.ui.config.ConfigViewModel;
import com.dnomaid.mqtt.ui.connection.ConnectionViewModel;
import com.dnomaid.mqtt.ui.history.HistoryViewModel;
import com.dnomaid.mqtt.ui.relay.RelayViewModel;
import com.dnomaid.mqtt.ui.temperature.TemperatureViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity
        implements Actions {
    public static final long PERIODO = 500; // 60 segundos (6 * 1000 millisegundos)
    private Handler handler;
    private Runnable runnable;

    private Connection connection = null;
    private Mqtt mqtt;
    private Devices devices;
    private AppBarConfiguration mAppBarConfiguration;
    private ConnectionViewModel connectionViewModel;
    private HistoryViewModel historyViewModel;
    private TemperatureViewModel temperatureViewModel;
    private RelayViewModel relayViewModel;
    private ConfigViewModel configViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_connection ,R.id.nav_relay, R.id.nav_temperature, R.id.nav_history, R.id.nav_config
        )
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        if (devices== null){
            Devices.getInst().getDevicesConfig().clear();
            devices = Devices.getInst();
            devices.newDevice(Constants.TypeDevice.SonoffS20, "1");
            devices.newDevice(Constants.TypeDevice.SonoffS20, "2");
            devices.newDevice(Constants.TypeDevice.SonoffS20, "3");
            devices.newDevice(Constants.TypeDevice.SonoffS20, "4");
            devices.newDevice(Constants.TypeDevice.SonoffS20, "5");
            devices.newDevice(Constants.TypeDevice.SonoffSNZB02, "1");
            devices.newDevice(Constants.TypeDevice.AqaraTemp, "1");
            devices.newDevice(Constants.TypeDevice.TuyaZigBeeSensor, "1");
            devices.newDevice(Constants.TypeDevice.XiaomiZNCZ04LM, "1");
            }

        setupViewModel();
        if (connection == null) connection = Connection.getInstance(this);
        if (mqtt == null) mqtt = new Mqtt(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, PERIODO);
                updateState();
            }
        };
        handler.postDelayed(runnable, PERIODO);
        //Recover connections.
        connection = Connection.getInstance(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //Interface Actions
    @Override
    public void connection() {
        mqtt.connection();
    }
    @Override
    public void disconnection() {
        mqtt.disconnection();
    }
    @Override
    public void subscribe() {
        mqtt.subscribe();
    }
    @Override
    public void unsubscribe() {
        mqtt.unsubscribe();
    }
    @Override
    public void publish(String topic, String message) {
        mqtt.publish(topic,message);
    }

    private void setupViewModel(){
        if (connectionViewModel == null) connectionViewModel = new ViewModelProvider(this).get(ConnectionViewModel.class);
        if (historyViewModel == null) historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        if (temperatureViewModel == null) temperatureViewModel = new ViewModelProvider(this).get(TemperatureViewModel.class);
        if (relayViewModel == null) relayViewModel = new ViewModelProvider(this).get(RelayViewModel.class);
        if (configViewModel == null) configViewModel = new ViewModelProvider(this).get(ConfigViewModel.class);
    }
    private void updateState() {
            connectionViewModel.updateState();
            historyViewModel.updateState();
            temperatureViewModel.updateState();
            relayViewModel.updateState();
            configViewModel.updateState();
    }

}