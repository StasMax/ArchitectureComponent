package com.example.android.architecturecomponent.presentation.mvvm.viewModel;

import android.content.Intent;
import android.net.Uri;

import com.example.android.architecturecomponent.data.model.PublishModel;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;
import static com.example.android.architecturecomponent.presentation.Constant.PICK_IMAGE;
import static com.example.android.architecturecomponent.presentation.Constant.TYPE_POST;

public class PostViewModel extends CommonFieldsViewModel {
    private List<String> fileImage = new ArrayList<>();
    //  private IPublishIteractor publishIteractor;


 /*   @Inject
    public PostViewModel(IPublishIteractor publishIteractor) {
                this.publishIteractor = publishIteractor;
    }*/

    public void initSendPost() {

        PublishModel publishModel = PublishModel.builder()
                .id(lastId)
                .category(categories)
                .tag(tags)
                .header(header)
                .description(description)
                .imageFile(fileImage)
                .link(links)
                .linkName(linksNames)
                .typeViewHolder(TYPE_POST)
                .build();

     /*   disposeBag(getPublishIteractor().insertPostInDb(publishModel)
                .doAfterSuccess(publishModel12 -> fileImage.clear())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());*/
    }

    public void initUploadImage(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            if (filePath != null) {
              /*  ProgressDialog progressDialog = new ProgressDialog(getApplication().getBaseContext());
                progressDialog.setTitle("Загрузка...");
                progressDialog.show();*/
                StorageReference ref = getPublishIteractor().getStorageReference();
                ref.putFile(filePath)
                        .addOnSuccessListener(taskSnapshot -> {
                            //      progressDialog.dismiss();
                            ref.getDownloadUrl().addOnCompleteListener(task -> fileImage.add(task.getResult().toString()));
                            //       Toast.makeText(getApplication().getBaseContext(), R.string.uploaded, Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            //         progressDialog.dismiss();
                            //         Toast.makeText(getApplication().getBaseContext(), R.string.error_uploading, Toast.LENGTH_SHORT).show();
                        })
                        .addOnProgressListener(taskSnapshot -> {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            //         progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        });
            }
        }
    }
}