package com.example.android.architecturecomponent.presentation.mvvm.viewModel;

import com.example.android.architecturecomponent.data.model.PublishModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.android.architecturecomponent.presentation.Constant.TYPE_POST;

public class PostViewModel extends CommonFieldsViewModel {

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

        disposeBag(getPublishIteractor().insertPostInDb(publishModel)
                .doAfterSuccess(publishModel12 -> fileImage.clear())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }
}