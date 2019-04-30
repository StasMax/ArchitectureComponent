package com.example.android.architecturecomponent.presentation.adapter;

import android.arch.paging.DataSource;

import com.example.android.architecturecomponent.data.model.PublishModel;
import com.example.android.architecturecomponent.domain.iteractor.IPublishIteractor;

import io.reactivex.disposables.CompositeDisposable;

public class PublishSourceFactory extends DataSource.Factory<Long, PublishModel> {

    private IPublishIteractor publishIteractor;
    private CompositeDisposable compositeDisposable;

    public PublishSourceFactory(IPublishIteractor publishIteractor, CompositeDisposable compositeDisposable) {
        this.publishIteractor = publishIteractor;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public DataSource<Long, PublishModel> create() {
        return new PublishPositionalDataSource(publishIteractor, compositeDisposable);
    }
}

