package com.example.android.architecturecomponent.presentation.mvvm.viewModel;

import com.example.android.architecturecomponent.domain.iteractor.IPublishIteractor;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;
import lombok.Setter;

public class CommonFieldsViewModel extends BaseViewModel {
    @Setter
    long lastId;
    @Getter
    List<String> categories = new ArrayList<>();
    @Getter
    List<String> tags = new ArrayList<>();
    String header = null;
    String description = null;
    @Getter
    List<String> links = new ArrayList<>();
    @Getter
    List<String> linksNames = new ArrayList<>();
    @Getter
    List<String> fileImage = new ArrayList<>();
    private IPublishIteractor publishIteractor;

    @Inject
    public CommonFieldsViewModel(IPublishIteractor publishIteractor) {
        this.publishIteractor = publishIteractor;
        initFieldId();
    }

    public void fieldCategory(String category) {
        String[] linkSplit = category.split(", ");
        categories = Arrays.asList(linkSplit);
    }

    public void fieldTag(String tag) {
        String[] linkSplit = tag.split(", ");
        tags = Arrays.asList(linkSplit);
    }

    public void fieldHeader(String headerField) {
        header = headerField;
    }

    public void fieldDescription(String descriptionField) {
        description = descriptionField;
    }

    public void fieldLink(String link) {
        String[] linkSplit = link.split(", ");
        links = Arrays.asList(linkSplit);
    }

    public void fieldLinkName(String linkName) {
        String[] linkSplit = linkName.split(", ");
        linksNames = Arrays.asList(linkSplit);
    }

    private void initFieldId() {
        disposeBag(publishIteractor.getLastId()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(publishModel -> setupId(publishModel.getId())));
    }

    private void setupId(long idLast) {
        setLastId(++idLast);
    }
}
