package com.example.android.architecturecomponent.presentation.mvvm.viewModel;

import android.arch.lifecycle.ViewModel;

import com.example.android.architecturecomponent.domain.iteractor.IPublishIteractor;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import lombok.Getter;

public class BaseViewModel extends ViewModel {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Getter
    private IPublishIteractor publishIteractor;

    @Inject
    public BaseViewModel(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
    }

    public BaseViewModel() {
    }

    void disposeBag(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    private void unsubscribe() {
        compositeDisposable.clear();
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        unsubscribe();
    }
}