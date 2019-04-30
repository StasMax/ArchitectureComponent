package com.example.android.architecturecomponent.data.repository;

import android.support.annotation.NonNull;

import com.example.android.architecturecomponent.data.model.PublishModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

import static com.example.android.architecturecomponent.presentation.Constant.FIREBASE_DATABASE_LOCATION_MODEL;
import static com.example.android.architecturecomponent.presentation.Logger.logErrorDatabase;


public class DatabaseRepositoryImpl implements IDatabaseRepository {
    private DatabaseReference databaseReference;

    @Inject
    public DatabaseRepositoryImpl(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    @Override
    public Single<List<PublishModel>> getFirstPublishModels(long startRank, int size) {
        List<PublishModel> models = new ArrayList<>();
        Query query = databaseReference
                .child(FIREBASE_DATABASE_LOCATION_MODEL)
                .orderByKey()
                .limitToFirst(size);
        return Single.create(subscriber -> query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    models.add(userSnapshot.getValue(PublishModel.class));
                }
                subscriber.onSuccess(models);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                logErrorDatabase(databaseError.getMessage());
            }
        }));
    }

    @Override
    public Single<List<PublishModel>> getNextPublishModels(long startRank, int size) {
        List<PublishModel> models = new ArrayList<>();
        Query query = databaseReference
                .child(FIREBASE_DATABASE_LOCATION_MODEL)
                .orderByChild("id")
                .startAt(startRank + 1)
                .limitToFirst(size);
        return Single.create(subscriber -> query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    models.add(userSnapshot.getValue(PublishModel.class));
                }
                subscriber.onSuccess(models);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                logErrorDatabase(databaseError.getMessage());
            }
        }));
    }

    @Override
    public Single<PublishModel> getLastId() {
        List<PublishModel> models = new ArrayList<>();
        Query query = databaseReference
                .child(FIREBASE_DATABASE_LOCATION_MODEL)
                .orderByChild("id")
                .limitToLast(1);
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
}

