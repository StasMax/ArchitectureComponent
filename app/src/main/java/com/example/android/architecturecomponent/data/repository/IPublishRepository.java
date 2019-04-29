package com.example.android.architecturecomponent.data.repository;

import com.example.android.architecturecomponent.data.model.PublishModel;

import io.reactivex.Single;

public interface IPublishRepository {


    Single<PublishModel> insertInFirestore(PublishModel publishModel);

}
