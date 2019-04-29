package com.example.android.architecturecomponent.presentation.adapter;

import android.support.annotation.NonNull;

import com.example.android.architecturecomponent.data.model.PublishModel;
import com.example.android.architecturecomponent.domain.iteractor.IPublishIteractor;
import com.example.android.architecturecomponent.presentation.app.App;

import java.util.Comparator;

import javax.inject.Inject;

public class PublishPositionalDataSource extends android.arch.paging.ItemKeyedDataSource<Long, PublishModel> {

    IPublishIteractor publishIteractor;

    @Inject
    public PublishPositionalDataSource(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
    }

    public Comparator<PublishModel> compareByType = (o1, o2) -> {
        if (o1.getTypeViewHolder() == 1 && o2.getTypeViewHolder() != 1) {
            return -1;
        } else if (o1.getTypeViewHolder() != 1 && o2.getTypeViewHolder() == 1) {
            return 1;
        }
        return 0;
    };

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<PublishModel> callback) {
        publishIteractor.getPublishModelsList(0, params.requestedLoadSize, callback);
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<PublishModel> callback) {
        publishIteractor.getPublishModelsList(params.key, params.requestedLoadSize, callback);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<PublishModel> callback) {

    }

    @NonNull
    @Override
    public Long getKey(@NonNull PublishModel item) {
        return item.getId();
    }
}
