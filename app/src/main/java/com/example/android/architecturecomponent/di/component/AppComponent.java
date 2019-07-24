package com.example.android.architecturecomponent.di.component;

import com.example.android.architecturecomponent.di.module.PublishModule;
import com.example.android.architecturecomponent.presentation.mvvm.ui.fragment.EventFragment;
import com.example.android.architecturecomponent.presentation.mvvm.ui.fragment.LinkFragment;
import com.example.android.architecturecomponent.presentation.mvvm.ui.fragment.PostFragment;
import com.example.android.architecturecomponent.presentation.mvvm.viewModel.MainViewModel;
import com.example.android.architecturecomponent.presentation.mvvm.viewModel.ViewModelFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(dependencies = {}, modules = {PublishModule.class})
public interface AppComponent {

    void inject(PostFragment postFragment);

    void inject(EventFragment eventFragment);

    void inject(LinkFragment linkFragment);

    void inject(MainViewModel mainViewModel);

    void inject(ViewModelFactory viewModelFactory);
}
