package com.example.android.architecturecomponent.presentation.adapter.viewHolders;

import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

class ImageHelper {
    ImageView setImageInFlipper(View itemView, String imageUrl) {
        ImageView image = new ImageView(itemView.getContext());
        Picasso.with(itemView.getContext()).load(imageUrl).into(image);
        return image;
    }
}

