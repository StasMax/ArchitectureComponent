package com.example.android.architecturecomponent.presentation.mvvm.ui.fragment;

import android.app.Notification;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.drm.DrmStore;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.example.android.architecturecomponent.presentation.mvvm.viewModel.BaseViewModel;
import com.example.android.architecturecomponent.presentation.mvvm.viewModel.CommonFieldsViewModel;
import com.google.firebase.database.annotations.NotNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

abstract class AbstractFragment<T extends CommonFieldsViewModel> extends BaseFragment {

    protected ViewModel viewModel = ViewModelProviders.of(this).get(getGenericsClass(getClass()));

  /*  public AbstractFragment() {
        // Required empty public constructor
    }*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onViewModelReady();
    }

    protected abstract void onViewModelReady();

    protected final void observe(@NotNull LiveData liveData, @NotNull final Class clazz) {
       liveData.observe(this, (it -> {
           if (it != null) {
               clazz.cast(it);
           }
       }));
    }

    public static <T> Class<T> getGenericsClass(Class clazz) {
        ParameterizedType genericSuperclass = (ParameterizedType) clazz.getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Type first = actualTypeArguments[0];
        return (Class<T>) first;
    }
}
