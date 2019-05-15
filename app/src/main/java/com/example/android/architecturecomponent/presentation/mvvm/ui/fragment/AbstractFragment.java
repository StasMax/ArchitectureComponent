package com.example.android.architecturecomponent.presentation.mvvm.ui.fragment;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.android.architecturecomponent.presentation.mvvm.viewModel.CommonFieldsViewModel;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractFragment<T extends CommonFieldsViewModel> extends BaseFragment {
    private T viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass(this));
        onViewModelReady();
    }

    @SuppressWarnings("unchecked")
    private static <T extends ViewModel> Class<T> getViewModelClass(Fragment fragment) {
        return ((Class<T>) ((ParameterizedType) fragment.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public T getViewModel() {
        return viewModel;
    }

    protected abstract void onViewModelReady();
}
