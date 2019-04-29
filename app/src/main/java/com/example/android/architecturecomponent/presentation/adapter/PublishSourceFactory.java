package com.example.android.architecturecomponent.presentation.adapter;

import android.arch.paging.DataSource;

import com.example.android.architecturecomponent.data.model.PublishModel;
import com.example.android.architecturecomponent.domain.iteractor.IPublishIteractor;

import javax.inject.Inject;

public class PublishSourceFactory extends DataSource.Factory<Long, PublishModel> {

    IPublishIteractor publishIteractor;

    @Inject
    public PublishSourceFactory(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
    }

    @Override
    public DataSource<Long, PublishModel> create() {
        return new PublishPositionalDataSource(publishIteractor);
    }
}

