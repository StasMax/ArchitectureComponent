package com.example.android.architecturecomponent.di.module;

import android.arch.lifecycle.ViewModelProvider;

import com.example.android.architecturecomponent.data.repository.DatabaseRepositoryImpl;
import com.example.android.architecturecomponent.data.repository.IDatabaseRepository;
import com.example.android.architecturecomponent.data.repository.IPublishRepository;
import com.example.android.architecturecomponent.data.repository.PublishRepositoryImpl;
import com.example.android.architecturecomponent.domain.iteractor.IPublishIteractor;
import com.example.android.architecturecomponent.domain.iteractor.PublishIteractorImpl;
import com.example.android.architecturecomponent.presentation.mvvm.viewModel.ViewModelFactory;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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
    IDatabaseRepository databaseRepository(FirebaseFirestore firestoreDB, StorageReference storageReference) {
        return new DatabaseRepositoryImpl(firestoreDB, storageReference);
    }

    @Provides
    @Singleton
    IPublishRepository publishRepository(){return new PublishRepositoryImpl();
    }

    @Provides
    @Singleton
    StorageReference storageReference() {
        return FirebaseStorage.getInstance().getReference("images");
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
}
