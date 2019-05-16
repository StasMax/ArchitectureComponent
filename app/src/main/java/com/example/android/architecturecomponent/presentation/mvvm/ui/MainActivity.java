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

import com.example.android.architecturecomponent.R;
import com.example.android.architecturecomponent.databinding.ActivityMainBinding;
import com.example.android.architecturecomponent.presentation.adapter.PublishDiffUtilCallback;
import com.example.android.architecturecomponent.presentation.adapter.PublishPagedListAdapter;
import com.example.android.architecturecomponent.presentation.mvvm.viewModel.MainViewModel;
import com.example.android.architecturecomponent.presentation.mvvm.viewModel.ViewModelFactory;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        RecyclerView recyclerView = binding.recyclerPublishes;
        ProgressBar progressBar = binding.mainProgress;
        ButterKnife.bind(this);
        MainViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory()).get(MainViewModel.class);
        PublishDiffUtilCallback publishDiffUtil = new PublishDiffUtilCallback();
        PublishPagedListAdapter adapter = new PublishPagedListAdapter(publishDiffUtil.diffUtilCallback);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
        viewModel.getPagedList().observe(this, adapter::submitList);
    }

    @OnClick(R.id.float_button)
    void onSaveClick() {
        startActivity(new Intent(MainActivity.this, PublishActivity.class));
    }
}
