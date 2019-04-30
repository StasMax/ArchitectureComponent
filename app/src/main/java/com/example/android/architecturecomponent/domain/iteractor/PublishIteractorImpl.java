package com.example.android.architecturecomponent.domain.iteractor;

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
    public Single<List<PublishModel>> getNextPublishModelsList(long startRank, int size) {
        return databaseRepository.getNextPublishModels(startRank, size);
    }

    @Override
    public Single<List<PublishModel>> getFirstPublishModelsList(long startRank, int size) {
        return databaseRepository.getFirstPublishModels(startRank, size);
    }

    @Override
    public Single<PublishModel> getLastId() {
        return databaseRepository.getLastId();
    }

    @Override
    public Single<PublishModel> insertPostInDb(PublishModel publishModel) {
        return publishRepository.insertPublishModel(publishModel);
    }
}
