package com.ollendev.retrodemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 3/9/15.
 */
public class ServiceManager {
    private static ServiceManager instance;

    private List<ResponseListener> listeners;

    private ServiceManager() {
        listeners = new ArrayList<>();
    }

    public static ServiceManager getInstance() {
        if (instance == null) {
            instance = new ServiceManager();
        }
        return instance;
    }

    public void addListener(ResponseListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ResponseListener listener) {
        listeners.remove(listener);
    }

    public void getMessage(DemoModel demoModel) {

    }
}
