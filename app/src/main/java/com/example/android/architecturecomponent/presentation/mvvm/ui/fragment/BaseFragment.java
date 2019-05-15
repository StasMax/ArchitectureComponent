package com.example.android.architecturecomponent.presentation.mvvm.ui.fragment;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.android.architecturecomponent.presentation.app.App;
import com.example.android.architecturecomponent.presentation.mvvm.viewModel.ViewModelFactory;

import javax.inject.Inject;

public class BaseFragment extends Fragment {
    @Inject
    ViewModelFactory viewModelFactory;

    public BaseFragment() {
        App.getComponent().inject(this);
    }

    public void showMessage(int resource) {
        Toast.makeText(getContext(), resource, Toast.LENGTH_SHORT).show();
    }
}

