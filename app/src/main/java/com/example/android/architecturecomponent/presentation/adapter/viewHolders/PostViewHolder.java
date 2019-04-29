package com.example.android.architecturecomponent.presentation.adapter.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.architecturecomponent.R;
import com.example.android.architecturecomponent.data.model.PublishModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.category_post)
    TextView postCategory;
    @BindView(R.id.tag_post)
    TextView postTag;
    @BindView(R.id.header_post)
    TextView postHeader;
    @BindView(R.id.description_post)
    TextView postDescription;
    @BindView(R.id.file_picture_post)
    ImageView postFilePicture;
    @BindView(R.id.link_post)
    TextView postLink;
    @BindView(R.id.link_post_word)
    TextView postLinkWord;
    @BindView(R.id.load_txt_post)
    TextView textLoad;
    private StringBuilder categoryBuilder = new StringBuilder();
    private StringBuilder tagBuilder = new StringBuilder();
    private StringBuilder linkBuilder = new StringBuilder();


    public PostViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }

    public void bind(PublishModel publishModel) {
        if (publishModel == null) {
            textLoad.setText(R.string.load_txt);
        } else {
            if (publishModel.getCategory() != null && publishModel.getCategory().size() != 0) {
                for (String category : publishModel.getCategory()) {
                    categoryBuilder.append(" ").append(category).append(",");
                }
                categoryBuilder.deleteCharAt(categoryBuilder.length() - 1);
                postCategory.setText(categoryBuilder.toString());
                categoryBuilder.setLength(0);
            }

            if (publishModel.getTag() != null && publishModel.getTag().size() != 0) {
                for (String tag : publishModel.getTag()) {
                    tagBuilder.append(" ").append(tag).append(",");
                }
                tagBuilder.deleteCharAt(tagBuilder.length() - 1);
                postTag.setText(tagBuilder.toString());
                tagBuilder.setLength(0);
            }

            if (publishModel.getLink() != null && publishModel.getLink().size() != 0) {
                for (int i = 0; i < publishModel.getLink().size(); i++) {
                    linkBuilder.append(" " + "<a href=").append(publishModel.getLink().get(i)).append("><font color=#AAA>").append(publishModel.getLinkName().get(i)).append("</font></a>").append(",");
                }
                linkBuilder.deleteCharAt(linkBuilder.length() - 1);
                postLink.setText(Html.fromHtml(linkBuilder.toString()));
                postLink.setMovementMethod(LinkMovementMethod.getInstance());
                linkBuilder.setLength(0);
            } else {
                postLink.setVisibility(View.GONE);
                postLinkWord.setVisibility(View.GONE);
            }

            if (publishModel.getHeader() != null) {
                postHeader.setText(publishModel.getHeader());
            } else {
                postHeader.setVisibility(View.GONE);
            }

            if (publishModel.getDescription() != null) {
                postDescription.setText(Html.fromHtml(publishModel.getDescription()));
            } else {
                postDescription.setVisibility(View.GONE);
            }

            if (publishModel.getImageFile() != null && publishModel.getImageFile().size() != 0) {
                Picasso.with(itemView.getContext()).load(publishModel.getImageFile().get(0)).into(postFilePicture);
            } else {
                postFilePicture.setVisibility(View.GONE);
            }
        }
    }
}
