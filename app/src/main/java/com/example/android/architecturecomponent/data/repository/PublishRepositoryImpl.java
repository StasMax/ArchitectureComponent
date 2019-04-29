package com.example.android.architecturecomponent.data.repository;

import com.example.android.architecturecomponent.data.model.PublishModel;

import javax.inject.Inject;

import io.reactivex.Single;

public class PublishRepositoryImpl implements IPublishRepository {


    @Override
    public Single<PublishModel> insertInFirestore(PublishModel publishModel) {
        return null;
    }
}

