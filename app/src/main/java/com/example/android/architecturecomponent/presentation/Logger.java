package com.example.android.architecturecomponent.presentation;

import android.util.Log;

import static com.example.android.architecturecomponent.presentation.Constant.DATABASE;

public class Logger {
    public static void logErrorDatabase(String errorText){
        Log.e(DATABASE, errorText);
    }
}

