package com.example.android.architecturecomponent.presentation.adapter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.architecturecomponent.data.model.PublishModel;
import com.example.android.architecturecomponent.domain.iteractor.IPublishIteractor;

import java.util.Comparator;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PublishPositionalDataSource extends android.arch.paging.ItemKeyedDataSource<Long, PublishModel> {

    private IPublishIteractor publishIteractor;
    private CompositeDisposable disposables;

    public PublishPositionalDataSource(IPublishIteractor publishIteractor, CompositeDisposable compositeDisposable) {
        this.publishIteractor = publishIteractor;
        this.disposables = compositeDisposable;
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
        disposables.add(publishIteractor.getFirstPublishModelsList(0, params.requestedLoadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(publishModels -> callback.onResult(publishModels, 0, publishModels.size())));
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<PublishModel> callback) {
        disposables.add(publishIteractor.getNextPublishModelsList(params.key, params.requestedLoadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onResult));
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
