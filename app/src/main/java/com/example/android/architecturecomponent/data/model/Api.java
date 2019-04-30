package com.example.android.architecturecomponent.data.model;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    @POST("post/{new}.json")
    Single<PublishModel> setPublish(@Path("new") String s1, @Body PublishModel publishModel);
}
