package com.example.android.architecturecomponent.presentation.mvvm.ui;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.android.architecturecomponent.R;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PublishActivity extends FragmentActivity {
    private NavController navController;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.radioButtonPost)
    RadioButton radioButtonPost;
    @BindView(R.id.radioButtonEvent)
    RadioButton radioButtonEvent;
    @BindView(R.id.radioButtonLink)
    RadioButton radioButtonLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        ButterKnife.bind(this);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case -1:
                    break;
                case R.id.radioButtonPost:
                    navController.navigate(R.id.postFragment);
                    break;
                case R.id.radioButtonEvent:
                    navController.navigate(R.id.eventFragment);
                    break;
                case R.id.radioButtonLink:
                    navController.navigate(R.id.linkFragment);
                    break;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}