package com.example.android.architecturecomponent.presentation.mvvm.viewModel;

import com.example.android.architecturecomponent.data.model.PublishModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.android.architecturecomponent.presentation.Constant.TYPE_LINK;

public class LinkViewModel extends CommonFieldsViewModel {

    public void initSendLink() {

        PublishModel publishModel = PublishModel.builder()
                .id(lastId)
                .category(categories)
                .tag(tags)
                .link(links)
                .linkName(linksNames)
                .typeViewHolder(TYPE_LINK)
                .build();

        disposeBag(getPublishIteractor().insertPostInDb(publishModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }
}
