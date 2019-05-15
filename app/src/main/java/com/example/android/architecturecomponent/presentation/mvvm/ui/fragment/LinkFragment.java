package com.example.android.architecturecomponent.presentation.mvvm.ui.fragment;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.architecturecomponent.R;
import com.example.android.architecturecomponent.presentation.app.App;
import com.example.android.architecturecomponent.presentation.mvvm.viewModel.LinkViewModel;
import com.example.android.architecturecomponent.presentation.mvvm.viewModel.ViewModelFactory;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

public class LinkFragment extends AbstractFragment<LinkViewModel> {
    private Unbinder unbinder;

    public LinkFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        App.getComponent().inject(this);
        View view = inflater.inflate(R.layout.fragment_link, container, false);
        unbinder = ButterKnife.bind(this, view);
                return view;
    }

    @OnTextChanged({R.id.edit_category_link, R.id.edit_tag_link,
            R.id.edit_link_link, R.id.edit_link_link_name})
    public void onFieldsTextChanged(CharSequence s, int start, int before, int count) {
        if (getActivity() == null || getActivity().getCurrentFocus() == null) {
            return;
        }

        String text = s.toString();
        switch (getActivity().getCurrentFocus().getId()) {
            case R.id.edit_category_link:
                getViewModel().fieldCategory(text);
                break;
            case R.id.edit_tag_post:
                getViewModel().fieldTag(text);
                break;
            case R.id.edit_link_link:
                getViewModel().fieldLink(text);
                break;
            case R.id.edit_link_link_name:
                getViewModel().fieldLinkName(text);
                break;
        }
    }

    @OnClick(R.id.button_send_link)
    void onClickPost() {
        getViewModel().onClickButtonSendLink();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected void onViewModelReady() {
        getViewModel().getShowToast().observe(this, integer -> {
            if (integer != null) {
                showMessage(integer);
            }
        });
    }
}
