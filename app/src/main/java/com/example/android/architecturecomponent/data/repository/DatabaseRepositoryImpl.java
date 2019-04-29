package com.example.android.architecturecomponent.data.repository;

import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;

import com.example.android.architecturecomponent.data.model.PublishModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.Single;

import static com.example.android.architecturecomponent.presentation.Logger.logErrorDatabase;


public class DatabaseRepositoryImpl implements IDatabaseRepository {

    private StorageReference storageReference;
    private FirebaseFirestore firestoreDB;

    @Inject
    public DatabaseRepositoryImpl(FirebaseFirestore firestoreDB, StorageReference storageReference) {
        this.firestoreDB = firestoreDB;
        this.storageReference = storageReference;
    }

    @Override
    public void getPublishModels(long startRank, int size, @NonNull final ItemKeyedDataSource.LoadCallback<PublishModel> callback) {
        List<PublishModel> models = new ArrayList<>();

         firestoreDB.collection("publishers")
                .whereGreaterThan("id", startRank)
                .limit(size).addSnapshotListener((snapshots, e) -> {

                    if (e != null) {
                        logErrorDatabase(e.getMessage());
                        return;
                    }

                    for (DocumentSnapshot doc : snapshots.getDocuments()) {
                        models.add(doc.toObject(PublishModel.class));
                    }

                    if(models.size() == 0){
                        return;
                    }



                    if(callback instanceof ItemKeyedDataSource.LoadInitialCallback){
                        //initial load
                        ItemKeyedDataSource.LoadInitialCallback loadInitialCallback = (ItemKeyedDataSource.LoadInitialCallback) callback;
                        loadInitialCallback.onResult(models, 0, models.size());
                    }else{
                        //next pages load
                        callback.onResult(models);
                    }
                });
    }

    @Override
    public Single<PublishModel> getLastId() {
        List<PublishModel> models = new ArrayList<>();
        Query query = null;

        return Single.create(subscriber -> query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    models.add(userSnapshot.getValue(PublishModel.class));
                }
                subscriber.onSuccess(models.get(0));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                logErrorDatabase(databaseError.getMessage());
            }
        }));
    }

    @Override
    public StorageReference getImageStorageReference() {
        return storageReference.child("images/" + UUID.randomUUID().toString());
    }

    @Override
    public Single<PublishModel> insertInFirestore(PublishModel publishModel) {
        return Single.create(subscriber -> firestoreDB.collection("publishes")
                .add(publishModel)
                .addOnSuccessListener(documentReference -> {
                })
                .addOnFailureListener(e -> logErrorDatabase(e.getMessage())));
    }
}

