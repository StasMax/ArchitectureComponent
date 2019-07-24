package com.example.android.architecturecomponent.presentation.mvvm.ui.fragment;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.example.android.architecturecomponent.presentation.mvvm.viewModel.CommonFieldsViewModel;
import com.example.android.architecturecomponent.presentation.mvvm.viewModel.ViewModelFactory;
import java.lang.reflect.ParameterizedType;

import lombok.Getter;

public abstract class AbstractFragment<T extends CommonFieldsViewModel> extends BaseFragment {
    @Getter
    private T viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory()).get(getViewModelClass(this));
        onViewModelReady();
    }

    @SuppressWarnings("unchecked")
    private static <T extends ViewModel> Class<T> getViewModelClass(Fragment fragment) {
        return ((Class<T>) ((ParameterizedType) fragment.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

   protected abstract void onViewModelReady();
}
