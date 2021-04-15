package com.dnomaid.mqtt.ui.connection;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.InvocationTargetException;

public class ConnectionVMFactory implements ViewModelProvider.Factory {
    private ConnectionViewValueUser viewValueUser;

    public ConnectionVMFactory(ConnectionViewValueUser viewValueUser) {
        this.viewValueUser = viewValueUser;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        T constructor = null;
        try {
            constructor = modelClass.getConstructor(ConnectionViewValueUser.class).newInstance(viewValueUser);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return  constructor;
    }
}
