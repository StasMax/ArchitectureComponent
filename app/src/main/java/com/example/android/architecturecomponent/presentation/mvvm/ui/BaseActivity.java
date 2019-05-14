package com.example.android.architecturecomponent.presentation.mvvm.ui;

import android.content.Context;
import android.net.ConnectivityManager;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public void baseShowMessage(int resource) {
        Toast.makeText(this, resource, Toast.LENGTH_SHORT).show();
    }
}
