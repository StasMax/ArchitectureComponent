package com.example.android.architecturecomponent.presentation.mvvm.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.android.architecturecomponent.data.model.PublishModel;
import com.example.android.architecturecomponent.domain.iteractor.IPublishIteractor;
import com.example.android.architecturecomponent.presentation.adapter.PublishSourceFactory;

import static com.example.android.architecturecomponent.presentation.Constant.LOAD_FIRST_ITEM_SIZE;
import static com.example.android.architecturecomponent.presentation.Constant.LOAD_ITEM_SIZE;

public class MainViewModel extends BaseViewModel {

    private PublishSourceFactory publishSourceFactory;
    private PagedList.Config config;

    public MainViewModel(IPublishIteractor publishIteractor) {
        publishSourceFactory = new PublishSourceFactory(publishIteractor, compositeDisposable);
        config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(LOAD_FIRST_ITEM_SIZE)
                .setPageSize(LOAD_ITEM_SIZE)
                .build();
    }

    public LiveData<PagedList<PublishModel>> getPagedList() {
        return new LivePagedListBuilder<>(publishSourceFactory, config).build();
    }
}
