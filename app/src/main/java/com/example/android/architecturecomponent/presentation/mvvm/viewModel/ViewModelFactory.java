package com.example.android.architecturecomponent.presentation.mvvm.viewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.android.architecturecomponent.domain.iteractor.IPublishIteractor;
import com.google.firebase.storage.StorageReference;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private IPublishIteractor publishIteractor;
    private StorageReference storageReference;

    @Inject
    public ViewModelFactory(IPublishIteractor publishIteractor, StorageReference storageReference) {
        this.publishIteractor = publishIteractor;
        this.storageReference = storageReference;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(publishIteractor);
        } else if (modelClass.isAssignableFrom(PostViewModel.class)) {
            return (T) new PostViewModel(publishIteractor, storageReference);
        } else if (modelClass.isAssignableFrom(EventViewModel.class)) {
            return (T) new EventViewModel(publishIteractor, storageReference);
        } else if (modelClass.isAssignableFrom(LinkViewModel.class)) {
            return (T) new LinkViewModel(publishIteractor);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
