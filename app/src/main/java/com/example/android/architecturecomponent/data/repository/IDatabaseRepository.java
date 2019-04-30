package com.example.android.architecturecomponent.data.repository;

import com.example.android.architecturecomponent.data.model.PublishModel;

import java.util.List;

import io.reactivex.Single;

public interface IDatabaseRepository {
    Single<List<PublishModel>> getFirstPublishModels(long startRank, int size);

    Single<List<PublishModel>> getNextPublishModels(long startRank, int size);

    Single<PublishModel> getLastId();
}