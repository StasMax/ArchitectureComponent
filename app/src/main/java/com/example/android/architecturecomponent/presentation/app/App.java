package com.example.android.architecturecomponent.presentation.app;

import android.app.Application;

import com.example.android.architecturecomponent.di.component.AppComponent;
import com.example.android.architecturecomponent.di.component.DaggerAppComponent;
import com.example.android.architecturecomponent.di.module.PublishModule;
import com.google.firebase.FirebaseApp;

public class App extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .publishModule(new PublishModule())
                .build();

        FirebaseApp.initializeApp(this);
    }

    public static AppComponent getComponent() {
        return component;
    }
}