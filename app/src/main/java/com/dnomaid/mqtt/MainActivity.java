package com.dnomaid.mqtt;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
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
import com.dnomaid.mqtt.ui.settingDevice.SettingDeviceViewModel;
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
    private static Boolean FLAGINI = false;

    private Connection connection = null;
    private Mqtt mqtt;
    private Devices devices;
    private AppBarConfiguration mAppBarConfiguration;
    private ConnectionViewModel connectionViewModel;
    private HistoryViewModel historyViewModel;
    private TemperatureViewModel temperatureViewModel;
    private RelayViewModel relayViewModel;
    private SettingDeviceViewModel configViewModel;
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
                , R.id.nav_history, R.id.nav_setting_device, R.id.nav_setting_connection
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
                showFloatingIcons();
            }
        });
        setup();
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
                showFloatingIcons();
            }
        };
        handler.postDelayed(runnable, PERIODO);
        //Recover connections.
        //connection = Connection.getInstance(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting_device:
                navController.navigate(R.id.nav_setting_device);
                return true;
            case R.id.action_setting_connection:
                navController.navigate(R.id.nav_setting_connection);
                return true;
            case R.id.action_addDevice:
                navController.navigate(R.id.nav_addDevice);
            default:
                return super.onOptionsItemSelected(item);
        }
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
    public void newDevice(Constants.TypeDevice typeDevice, String numberDevice, String alias) {
        devices = Devices.getInst();
        devices.newDevice(typeDevice, numberDevice, alias);
    }
    @Override
    public void deleteDevice(Integer position) {
        devices = Devices.getInst();
        devices.deleteDevice(devices.getDevicesConfig().get(position));
        updateState();
    }
    //Methods
    private void showFloatingIcons(){
        fab.show();
        fab2.show();
        if (Status.getInst().isConnected()|!isMenuOpen)
            {
            fab1.hide();
            }
        else{
            fab1.show();
        }
        if (Status.getInst().isConnectedOrConnecting()&isMenuOpen)
        {
            fab2.show();
        }
        else{
            fab2.hide();
        }
    }
    private void updateState(){
        connectionViewModel.updateState();
        historyViewModel.updateState();
        temperatureViewModel.updateState(devices.getSensorsClimate());
        relayViewModel.updateState(devices.getRelays());
        configViewModel.updateState(devices.getDevicesConfig());
        settingConnectionViewModel.updateState();
    }
    private void setup(){
        setupDevice();
        setupViewModel();
        setupConnect();
        FLAGINI = true;
    }
    private void setupDevice(){
        if (devices == null){
            if(!FLAGINI) {
                Devices.getInst().getDevicesConfig().clear();
                if (Devices.getInst().getDevicesConfig().isEmpty()) {
                    devices = Devices.getInst();
                    devices.persistence(this);
                }
            }
            devices = Devices.getInst();
        }
    }
    private void setupViewModel(){
        connectionViewModel = new ViewModelProvider(this).get(ConnectionViewModel.class);
        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        temperatureViewModel = new ViewModelProvider(this).get(TemperatureViewModel.class);
        relayViewModel = new ViewModelProvider(this).get(RelayViewModel.class);
        configViewModel = new ViewModelProvider(this).get(SettingDeviceViewModel.class);
        settingConnectionViewModel = new ViewModelProvider(this).get(SettingConnectionViewModel.class);
    }
    private void setupConnect(){
        if (connection == null) connection = Connection.getInstance(this);
        if (mqtt == null) mqtt = new Mqtt(this);
    }
}