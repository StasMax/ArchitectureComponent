package com.example.android.architecturecomponent.presentation.mvvm.ui.fragment;


import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.architecturecomponent.R;
import com.example.android.architecturecomponent.presentation.app.App;
import com.example.android.architecturecomponent.presentation.mvvm.viewModel.EventViewModel;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

import static com.example.android.architecturecomponent.presentation.Constant.PICK_IMAGE;

public class EventFragment extends BaseFragment {
    private EventViewModel model;
    private Unbinder unbinder;

    public EventFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        App.getComponent().inject(this);
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        unbinder = ButterKnife.bind(this, view);
        model = ViewModelProviders.of(this).get(EventViewModel.class);
        return view;
    }

    @OnTextChanged({R.id.edit_category_event, R.id.edit_header_event, R.id.edit_tag_event,
            R.id.edit_description_event, R.id.edit_link_event, R.id.edit_link_event_name})
    public void onFieldsTextChanged(CharSequence s, int start, int before, int count) {
        if (getActivity() == null || getActivity().getCurrentFocus() == null) {
            return;
        }

        String text = s.toString();
        switch (getActivity().getCurrentFocus().getId()) {
            case R.id.edit_category_event:
                model.fieldCategory(text);
                break;
            case R.id.edit_tag_event:
                model.fieldTag(text);
                break;
            case R.id.edit_header_event:
                model.fieldHeader(text);
                break;
            case R.id.edit_description_event:
                model.fieldDescription(text);
                break;
            case R.id.edit_link_event:
                model.fieldLink(text);
                break;
            case R.id.edit_link_event_name:
                model.fieldLinkName(text);
                break;
        }
    }

    @OnClick(R.id.button_send_event)
    void onClickPost() {
        if (model.getCategories() .size() != 0 || model.getTags() .size() != 0 || model.getLinks().size() != model.getLinksNames().size()) {
            showMessage(R.string.error_fields);
        } else {
            model.initSendEvent();
            showMessage(R.string.success_post);
        }
    }

    @OnClick(R.id.button_image_event)
    void onClickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Выбирите изображение"), PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        model.initUploadImage(requestCode, resultCode, data);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
