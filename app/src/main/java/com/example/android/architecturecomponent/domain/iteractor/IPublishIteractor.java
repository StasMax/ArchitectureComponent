package com.example.android.architecturecomponent.domain.iteractor;

import com.example.android.architecturecomponent.data.model.PublishModel;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import io.reactivex.Single;

public interface IPublishIteractor {

    Single<List<PublishModel>> getNextPublishModelsList(long startRank, int size);

    Single<List<PublishModel>> getFirstPublishModelsList(long startRank, int size);

    Single<PublishModel>getLastId();

    StorageReference getStorageReference();
}