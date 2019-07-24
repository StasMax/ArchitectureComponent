package com.example.android.architecturecomponent.presentation.mvvm.viewModel;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.net.Uri;

import com.example.android.architecturecomponent.R;
import com.example.android.architecturecomponent.data.model.PublishModel;
import com.example.android.architecturecomponent.domain.iteractor.IPublishIteractor;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;

import static android.app.Activity.RESULT_OK;
import static com.example.android.architecturecomponent.presentation.Constant.PICK_IMAGE;
import static com.example.android.architecturecomponent.presentation.Constant.TYPE_EVENT;

public class EventViewModel extends CommonFieldsViewModel {

    private IPublishIteractor publishIteractor;
    private StorageReference storageReference;
    @Getter
    MutableLiveData<Integer> showToast = new MutableLiveData<>();

    @Inject
    public EventViewModel (IPublishIteractor publishIteractor, StorageReference storageReference) {
        super(publishIteractor);
        this.publishIteractor = publishIteractor;
        this.storageReference = storageReference;
    }

    public void onClickButtonSendEvent() {
        if (getCategories().size() == 0 || getTags().size() == 0 || getLinks().size() != getLinksNames().size()) {
            showToast.setValue(R.string.error_fields);
        } else {
            initSendEvent();
            showToast.setValue(R.string.success_post);
        }
    }

    private void initSendEvent() {
        PublishModel publishModel = PublishModel.builder()
                .id(lastId)
                .category(categories)
                .tag(tags)
                .header(header)
                .description(description)
                .imageFile(fileImage)
                .link(links)
                .linkName(linksNames)
                .date(initDate())
                .typeViewHolder(TYPE_EVENT)
                .build();

        disposeBag(publishIteractor.insertPostInDb(publishModel)
                .doAfterSuccess(publishModel12 -> fileImage.clear())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    private String initDate() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa");
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    public void loadImage(int requestCode, int resultCode, Intent data, ProgressDialog progressDialog) {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            if (filePath != null) {
                progressDialog.setTitle("Загрузка...");
                progressDialog.show();
                StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
                ref.putFile(filePath)
                        .addOnSuccessListener(taskSnapshot -> {
                            progressDialog.dismiss();
                            ref.getDownloadUrl().addOnCompleteListener(task -> getFileImage().add(task.getResult().toString()));
                            showToast.setValue(R.string.uploaded);
                        })
                        .addOnFailureListener(e -> {
                            progressDialog.dismiss();
                            showToast.setValue(R.string.error_uploading);
                        })
                        .addOnProgressListener(taskSnapshot -> {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        });
            }
        }
    }
}