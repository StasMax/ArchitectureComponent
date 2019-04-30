package com.example.android.architecturecomponent.presentation.mvvm.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.architecturecomponent.R;

import com.example.android.architecturecomponent.databinding.ActivityMainBinding;
import com.example.android.architecturecomponent.presentation.adapter.PublishDiffUtilCallback;
import com.example.android.architecturecomponent.presentation.adapter.PublishPagedListAdapter;
import com.example.android.architecturecomponent.presentation.app.App;
import com.example.android.architecturecomponent.presentation.mvvm.viewModel.MainViewModel;
import com.example.android.architecturecomponent.presentation.mvvm.viewModel.ViewModelFactory;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private TextView textEmptyList;
    private ProgressBar progressBar;
    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getComponent().inject(MainActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        recyclerView = binding.recyclerPublishes;
        textEmptyList = binding.txtEmptyList;
        progressBar = binding.mainProgress;
        progressBar.setVisibility(View.GONE);
        ButterKnife.bind(this);
        MainViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        PublishDiffUtilCallback publishDiffUtil = new PublishDiffUtilCallback();
        PublishPagedListAdapter adapter = new PublishPagedListAdapter(publishDiffUtil.diffUtilCallback);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        recyclerView.setAdapter(adapter);
      /*  compositeDisposable.add(viewModel.getPagedListObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pagedList -> adapter.submitList(pagedList)));*/
        viewModel.getPagedList().observe(this, adapter::submitList);
    }

    @OnClick(R.id.float_button)
    void onSaveClick() {
        startActivity(new Intent(MainActivity.this, PublishActivity.class));
    }
}
