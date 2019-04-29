package com.example.android.architecturecomponent.presentation.mvvm.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.arch.paging.RxPagedListBuilder;

import com.example.android.architecturecomponent.data.model.PublishModel;
import com.example.android.architecturecomponent.domain.iteractor.IPublishIteractor;
import com.example.android.architecturecomponent.presentation.adapter.PublishSourceFactory;

import javax.inject.Inject;

import io.reactivex.Observable;

import static com.example.android.architecturecomponent.presentation.Constant.LOAD_FIRST_ITEM_SIZE;
import static com.example.android.architecturecomponent.presentation.Constant.LOAD_ITEM_SIZE;

public class MainViewModel extends BaseViewModel {
    private PublishSourceFactory publishSourceFactory;
    private PagedList.Config config;

    @Inject
    public MainViewModel(IPublishIteractor publishIteractor) {
        super(publishIteractor);
        publishSourceFactory = new PublishSourceFactory(publishIteractor);
        config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(LOAD_FIRST_ITEM_SIZE)
                .setPageSize(LOAD_ITEM_SIZE)
                .build();
    }

    public LiveData<PagedList<PublishModel>> getPagedList() {
        return new LivePagedListBuilder<>(publishSourceFactory, config).build();
    }

    public Observable<PagedList<PublishModel>> getPagedListObservable() {
        return new RxPagedListBuilder<>(publishSourceFactory, config).buildObservable();
    }
}
