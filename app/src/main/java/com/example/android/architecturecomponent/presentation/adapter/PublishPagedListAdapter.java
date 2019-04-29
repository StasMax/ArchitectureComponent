package com.example.android.architecturecomponent.presentation.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.architecturecomponent.R;
import com.example.android.architecturecomponent.data.model.PublishModel;
import com.example.android.architecturecomponent.presentation.adapter.viewHolders.EventViewHolder;
import com.example.android.architecturecomponent.presentation.adapter.viewHolders.LinkViewHolder;
import com.example.android.architecturecomponent.presentation.adapter.viewHolders.PostViewHolder;

import static com.example.android.architecturecomponent.presentation.Constant.TYPE_EVENT;
import static com.example.android.architecturecomponent.presentation.Constant.TYPE_LINK;
import static com.example.android.architecturecomponent.presentation.Constant.TYPE_POST;

public class PublishPagedListAdapter extends android.arch.paging.PagedListAdapter<PublishModel, RecyclerView.ViewHolder> {


    public PublishPagedListAdapter(@NonNull DiffUtil.ItemCallback<PublishModel> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case TYPE_POST:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_post, parent, false);
                return new PostViewHolder(view);
            case TYPE_EVENT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_event, parent, false);
                return new EventViewHolder(view);
            case TYPE_LINK:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_link, parent, false);
                return new LinkViewHolder(view);
        }
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_post, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof PostViewHolder) {
            ((PostViewHolder) viewHolder).bind(getItem(i));
        } else if (viewHolder instanceof EventViewHolder) {
            ((EventViewHolder) viewHolder).bind(getItem(i));
        } else if (viewHolder instanceof LinkViewHolder) {
            ((LinkViewHolder) viewHolder).bind(getItem(i));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) != null) {
            PublishModel object = getItem(position);
            if (object != null) {
                return object.getTypeViewHolder();
            }
        }
        return 0;
    }
}