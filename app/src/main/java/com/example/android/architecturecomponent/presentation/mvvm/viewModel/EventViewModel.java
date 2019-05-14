package com.example.android.architecturecomponent.presentation.mvvm.viewModel;

import android.annotation.SuppressLint;

import com.example.android.architecturecomponent.R;
import com.example.android.architecturecomponent.data.model.PublishModel;
import com.example.android.architecturecomponent.domain.iteractor.IPublishIteractor;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lombok.val;

import static com.example.android.architecturecomponent.presentation.Constant.TYPE_EVENT;

public class EventViewModel extends CommonFieldsViewModel {

    private IPublishIteractor publishIteractor;
    val showToast = ActionLiveData<String>()

    @Inject
    public EventViewModel(IPublishIteractor publishIteractor) {
        super(publishIteractor);
        this.publishIteractor = publishIteractor;
    }

    public void onClickButtonSendEvent(){
        if (getCategories().size() == 0 || getTags().size() == 0 || getLinks().size() != getLinksNames().size()) {
            showMessage(R.string.error_fields);
        } else {
            initSendEvent();
            showMessage(R.string.success_post);
        }    }

    public void initSendEvent() {
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
}