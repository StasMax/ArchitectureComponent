package com.example.android.architecturecomponent.presentation.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.architecturecomponent.presentation.mvvm.ui.fragment.EventFragment;
import com.example.android.architecturecomponent.presentation.mvvm.ui.fragment.LinkFragment;
import com.example.android.architecturecomponent.presentation.mvvm.ui.fragment.PostFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentsViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();

    public FragmentsViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new PostFragment());
        fragments.add(new EventFragment());
        fragments.add(new LinkFragment());
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return fragments.get(0);
            case 1:
                return fragments.get(1);
            case 2:
                return fragments.get(2);
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
