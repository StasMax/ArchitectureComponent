package com.example.android.architecturecomponent.domain.iteractor;

import android.arch.paging.ItemKeyedDataSource;

import com.example.android.architecturecomponent.data.model.PublishModel;
import com.example.android.architecturecomponent.data.repository.IDatabaseRepository;
import com.example.android.architecturecomponent.data.repository.IPublishRepository;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class PublishIteractorImpl implements IPublishIteractor {
    private IPublishRepository publishRepository;
    private IDatabaseRepository databaseRepository;

    @Inject
    public PublishIteractorImpl(IPublishRepository publishRepository, IDatabaseRepository databaseRepository) {
        this.publishRepository = publishRepository;
        this.databaseRepository = databaseRepository;
    }

    @Override
    public void getPublishModelsList(long startRank, int size, ItemKeyedDataSource.LoadCallback<PublishModel> callback) {
        databaseRepository.getPublishModels(startRank, size, callback);
    }

    @Override
    public Single<PublishModel> getLastId() {
        return databaseRepository.getLastId();
    }

    @Override
    public StorageReference getStorageReference() {
        return databaseRepository.getImageStorageReference();
    }
}
