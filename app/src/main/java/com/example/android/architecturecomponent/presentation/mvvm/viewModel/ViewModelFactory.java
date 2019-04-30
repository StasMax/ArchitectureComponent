package com.example.android.architecturecomponent.presentation.mvvm.viewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.android.architecturecomponent.domain.iteractor.IPublishIteractor;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private IPublishIteractor publishIteractor;

    @Inject
    public ViewModelFactory(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(publishIteractor);
        } else if (modelClass.isAssignableFrom(PostViewModel.class)) {
            return (T) new PostViewModel(publishIteractor);
        } else if (modelClass.isAssignableFrom(EventViewModel.class)) {
            return (T) new EventViewModel(publishIteractor);
        } else if (modelClass.isAssignableFrom(LinkViewModel.class)) {
            return (T) new LinkViewModel(publishIteractor);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
