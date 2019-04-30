package com.example.android.architecturecomponent.presentation.adapter.viewHolders;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

class ImageHelper {
    ImageView setImageInFlipper(View itemView, String imageUrl) {
        ImageView image = new ImageView(itemView.getContext());
        Glide.with(itemView.getContext()).load(imageUrl).into(image);
        return image;
    }
}

