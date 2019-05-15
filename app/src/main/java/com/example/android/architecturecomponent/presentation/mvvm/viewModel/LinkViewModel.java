package com.example.android.architecturecomponent.presentation.mvvm.viewModel;

import android.arch.lifecycle.MutableLiveData;

import com.example.android.architecturecomponent.R;
import com.example.android.architecturecomponent.data.model.PublishModel;
import com.example.android.architecturecomponent.domain.iteractor.IPublishIteractor;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;

import static com.example.android.architecturecomponent.presentation.Constant.TYPE_LINK;

public class LinkViewModel extends CommonFieldsViewModel {

    private IPublishIteractor publishIteractor;
    @Getter
    MutableLiveData<Integer> showToast = new MutableLiveData<>();

    public LinkViewModel(IPublishIteractor publishIteractor) {
        super(publishIteractor);
        this.publishIteractor = publishIteractor;
    }

    public void initSendLink() {
        PublishModel publishModel = PublishModel.builder()
                .id(lastId)
                .category(categories)
                .tag(tags)
                .link(links)
                .linkName(linksNames)
                .typeViewHolder(TYPE_LINK)
                .build();

        disposeBag(publishIteractor.insertPostInDb(publishModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    public void onClickButtonSendLink() {
        if (getCategories().size() == 0 || getTags().size() == 0 || getLinks().size() != getLinksNames().size()) {
            showToast.setValue(R.string.error_fields);
        } else {
            initSendLink();
            showToast.setValue(R.string.success_post);
        }
    }
}
