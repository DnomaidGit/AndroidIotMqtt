package com.dnomaid.mqtt;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.dnomaid.mqtt.client.ActionsMqtt;
import com.dnomaid.mqtt.client.Connection;
import com.dnomaid.mqtt.client.Mqtt;
import com.dnomaid.mqtt.device.ActionsDevice;
import com.dnomaid.mqtt.device.Devices;
import com.dnomaid.mqtt.global.Constants;
import com.dnomaid.mqtt.global.Status;
import com.dnomaid.mqtt.ui.setting.SettingViewModel;
import com.dnomaid.mqtt.ui.connection.ConnectionViewModel;
import com.dnomaid.mqtt.ui.history.HistoryViewModel;
import com.dnomaid.mqtt.ui.relay.RelayViewModel;
import com.dnomaid.mqtt.ui.settingConnection.SettingConnectionViewModel;
import com.dnomaid.mqtt.ui.temperature.TemperatureViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
        implements ActionsMqtt, ActionsDevice, View.OnClickListener {
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
    private SettingViewModel configViewModel;
    private SettingConnectionViewModel settingConnectionViewModel;
    Toolbar toolbar;
    FloatingActionButton fab,fab1,fab2;
    DrawerLayout drawer;
    NavigationView navigationView;
    NavController navController;

    Float translationY = 100f;
    OvershootInterpolator interpolator = new OvershootInterpolator();
    Boolean isMenuOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initFabMenu();
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_connection, R.id.nav_addDevice ,R.id.nav_relay, R.id.nav_temperature
                , R.id.nav_history, R.id.nav_config, R.id.nav_setting_connection
        )
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
            //    if(destination.getId() == R.id.nav_connection) {
                    fab.show();
                    fab1.show();
                    fab2.show();
            //    }else {
            //        fab.hide();
            //        fab1.hide();
            //        fab2.hide();
            //    }
            }
        });
        setupDevice();
        setupViewModel();
        if (connection == null) connection = Connection.getInstance(this);
        if (mqtt == null) mqtt = new Mqtt(this);
    }
    private void initFabMenu() {
        fab = findViewById(R.id.fabMain);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        fab1.setAlpha(0f);
        fab2.setAlpha(0f);
        fab1.setTranslationY(translationY);
        fab2.setTranslationY(translationY);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
    }
    private void openMenu() {
        isMenuOpen = !isMenuOpen;
        fab.animate().setInterpolator(interpolator).rotation(45f).setDuration(300).start();
        fab1.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fab2.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
    }
    private void closeMenu() {
        isMenuOpen = !isMenuOpen;
        fab.animate().setInterpolator(interpolator).rotation(0f).setDuration(300).start();
        fab1.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fab2.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabMain:
                if (isMenuOpen) {
                    closeMenu();
                } else {
                    openMenu();
                }
                break;
            case R.id.fab1:
                connection();
                break;
            case R.id.fab2:
                disconnection();
                break;
        }
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
                if (Status.getInst().isConnected()&(Status.getInst().getTopicStatus().equals(Status.TopicStatus.NONE.name())))subscribe();
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
    //Interface ActionsMqtt
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
    //Interface ActionsDevice
    @Override
    public void newDevice(Constants.TypeDevice typeDevice, String numberDevice) {
        devices = Devices.getInst();
        devices.newDevice(typeDevice, numberDevice);
    }
    @Override
    public void deleteDevice(Integer position) {
        devices = Devices.getInst();
        devices.deleteDevice(devices.getDevicesConfig().get(position));
        updateState();
    }

    private void setupViewModel(){
        if (connectionViewModel == null) connectionViewModel = new ViewModelProvider(this).get(ConnectionViewModel.class);
        if (historyViewModel == null) historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        if (temperatureViewModel == null) temperatureViewModel = new ViewModelProvider(this).get(TemperatureViewModel.class);
        if (relayViewModel == null) relayViewModel = new ViewModelProvider(this).get(RelayViewModel.class);
        if (configViewModel == null) configViewModel = new ViewModelProvider(this).get(SettingViewModel.class);
        if (settingConnectionViewModel == null) settingConnectionViewModel = new ViewModelProvider(this).get(SettingConnectionViewModel.class);
    }
    private void updateState(){
            connectionViewModel.updateState();
            historyViewModel.updateState();
            temperatureViewModel.updateState(devices.getSensorsClimate());
            relayViewModel.updateState(devices.getRelays());
            configViewModel.updateState(devices.getDevicesConfig());
            settingConnectionViewModel.updateState();
    }
    private void setupDevice(){
        if (devices == null){
            Devices.getInst().getDevicesConfig().clear();
            if(Devices.getInst().getDevicesConfig().isEmpty()) {
                devices = Devices.getInst();
                devices.persistence(this);
                /*
                devices.newDevice(Constants.TypeDevice.SonoffS20, "1");
                devices.newDevice(Constants.TypeDevice.SonoffS20, "2");
                devices.newDevice(Constants.TypeDevice.SonoffS20, "3");
                devices.newDevice(Constants.TypeDevice.SonoffS20, "4");
                devices.newDevice(Constants.TypeDevice.SonoffS20, "5");
                devices.newDevice(Constants.TypeDevice.SonoffSNZB02, "1");
                devices.newDevice(Constants.TypeDevice.AqaraTemp, "1");
                devices.newDevice(Constants.TypeDevice.TuyaZigBeeSensor, "1");
                devices.newDevice(Constants.TypeDevice.XiaomiZNCZ04LM, "1");

                 */
            }
        }
    }
}