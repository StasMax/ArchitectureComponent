package com.example.android.architecturecomponent.domain.iteractor;

import android.arch.paging.ItemKeyedDataSource;

import com.example.android.architecturecomponent.data.model.PublishModel;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import io.reactivex.Single;

public interface IPublishIteractor {



    void getPublishModelsList(long startRank, int size, ItemKeyedDataSource.LoadCallback<PublishModel> callback);

    Single<PublishModel>getLastId();

    StorageReference getStorageReference();
}