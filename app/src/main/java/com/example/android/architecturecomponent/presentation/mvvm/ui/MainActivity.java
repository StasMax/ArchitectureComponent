package com.example.android.architecturecomponent.presentation.mvvm.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.android.architecturecomponent.R;
import com.example.android.architecturecomponent.data.model.PublishModel;

import com.example.android.architecturecomponent.databinding.ActivityMainBinding;
import com.example.android.architecturecomponent.presentation.adapter.PublishDiffUtilCallback;
import com.example.android.architecturecomponent.presentation.adapter.PublishPagedListAdapter;
import com.example.android.architecturecomponent.presentation.app.App;
import com.example.android.architecturecomponent.presentation.mvvm.viewModel.MainViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycler_publishes)
    RecyclerView recyclerView;
    @BindView(R.id.txt_empty_list)
    TextView textEmptyList;
    private PublishPagedListAdapter adapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        App.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ButterKnife.bind(this);
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        PublishDiffUtilCallback publishDiffUtil = new PublishDiffUtilCallback();
        adapter = new PublishPagedListAdapter(publishDiffUtil.diffUtilCallback);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        compositeDisposable.add(viewModel.getPagedListObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pagedList -> adapter.submitList(pagedList)));
    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }

    @OnClick(R.id.float_button)
    void onSaveClick() {
        startActivity(new Intent(MainActivity.this, PublishActivity.class));
    }
}
