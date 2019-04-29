package com.example.android.architecturecomponent.data.repository;

import android.arch.paging.ItemKeyedDataSource;

import com.example.android.architecturecomponent.data.model.PublishModel;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import io.reactivex.Single;

public interface IDatabaseRepository {
    void getPublishModels(long startRank, int size, ItemKeyedDataSource.LoadCallback<PublishModel> callback);

    Single<PublishModel> getLastId();

    StorageReference getImageStorageReference();

    Single<PublishModel> insertInFirestore(PublishModel publishModel);
}