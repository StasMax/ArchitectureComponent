package com.example.android.architecturecomponent.di.module;

import android.arch.lifecycle.ViewModelProvider;

import com.example.android.architecturecomponent.data.model.Api;
import com.example.android.architecturecomponent.data.repository.DatabaseRepositoryImpl;
import com.example.android.architecturecomponent.data.repository.IDatabaseRepository;
import com.example.android.architecturecomponent.data.repository.IPublishRepository;
import com.example.android.architecturecomponent.data.repository.PublishRepositoryImpl;
import com.example.android.architecturecomponent.domain.iteractor.IPublishIteractor;
import com.example.android.architecturecomponent.domain.iteractor.PublishIteractorImpl;
import com.example.android.architecturecomponent.presentation.RetrofitInit;
import com.example.android.architecturecomponent.presentation.mvvm.viewModel.ViewModelFactory;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PublishModule {
    @Provides
    @Singleton
    IPublishIteractor publishIteractor(IPublishRepository publishRepository, IDatabaseRepository databaseRepository) {
        return new PublishIteractorImpl(publishRepository, databaseRepository);
    }

    @Provides
    @Singleton
    IDatabaseRepository databaseRepository(DatabaseReference databaseReference, StorageReference storageReference) {
        return new DatabaseRepositoryImpl(databaseReference, storageReference);
    }

    @Provides
    @Singleton
    IPublishRepository publishRepository(Api getApi) {
        return new PublishRepositoryImpl(getApi);
    }

    @Provides
    @Singleton
    StorageReference storageReference() {
        return FirebaseStorage.getInstance().getReference("images");
    }

    @Provides
    @Singleton
    DatabaseReference databaseReference() {
        return FirebaseDatabase.getInstance().getReference();
    }

    @Provides
    @Singleton
    FirebaseFirestore firestoreDB() {
        return FirebaseFirestore.getInstance();
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getViewModelFactory(IPublishIteractor publishIteractor) {
        return new ViewModelFactory(publishIteractor);
    }

    @Provides
    @Singleton
    Api getApi() {
        return new RetrofitInit().getRetrofit().create(Api.class);
    }

    @Named("images")
    @Provides
    @Singleton
    StorageReference getImageStorageReference(StorageReference storageReference) {
        return storageReference.child("images/" + UUID.randomUUID().toString());
    }
}
