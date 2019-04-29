package com.example.android.architecturecomponent.presentation.adapter.viewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.android.architecturecomponent.R;
import com.example.android.architecturecomponent.data.model.PublishModel;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

public class EventViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.category_event)
    TextView eventCategory;
    @BindView(R.id.tag_event)
    TextView eventTag;
    @BindView(R.id.header_event)
    TextView eventHeader;
    @BindView(R.id.description_event)
    TextView eventDescription;
    @BindView(R.id.view_flipper_event)
    ViewFlipper viewFlipper;
    @BindView(R.id.link_event)
    TextView eventLink;
    @BindView(R.id.link_event_word)
    TextView eventLinkWord;
    @BindView(R.id.date_event)
    TextView eventDate;
    @BindView(R.id.date_event_word)
    TextView eventDateWord;
    @BindView(R.id.load_txt_event)
    TextView textLoad;
    private StringBuilder categoryBuilder = new StringBuilder();
    private StringBuilder tagBuilder = new StringBuilder();
    private StringBuilder linkBuilder = new StringBuilder();
    private ImageHelper imageHelper = new ImageHelper();

    public EventViewHolder(@NonNull View v) {
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
                eventCategory.setText(categoryBuilder.toString());
                categoryBuilder.setLength(0);
            }

            if (publishModel.getTag() != null && publishModel.getTag().size() != 0) {
                for (String tag : publishModel.getTag()) {
                    tagBuilder.append(" ").append(tag).append(",");
                }
                tagBuilder.deleteCharAt(tagBuilder.length() - 1);
                eventTag.setText(tagBuilder.toString());
                tagBuilder.setLength(0);
            }

            if (publishModel.getLink() != null && publishModel.getLink().size() != 0) {
                for (int i = 0; i < publishModel.getLink().size(); i++) {
                    linkBuilder.append(" " + "<a href=").append(publishModel.getLink().get(i)).append("><font color=#AAA>").append(publishModel.getLinkName().get(i)).append("</font></a>").append(",");
                }
                linkBuilder.deleteCharAt(linkBuilder.length() - 1);
                eventLink.setText(Html.fromHtml(linkBuilder.toString()));
                eventLink.setMovementMethod(LinkMovementMethod.getInstance());
                linkBuilder.setLength(0);
            } else {
                eventLink.setVisibility(GONE);
                eventLinkWord.setVisibility(GONE);
            }

            if (publishModel.getHeader() != null) {
                eventHeader.setText(publishModel.getHeader());
            } else {
                eventHeader.setVisibility(GONE);
            }

            if (publishModel.getDescription() != null) {
                eventDescription.setText(Html.fromHtml(publishModel.getDescription()));
            } else {
                eventDescription.setVisibility(GONE);
            }

            if (publishModel.getImageFile() != null && publishModel.getImageFile().size() != 0) {
                viewFlipper.removeAllViews();
                for (String imageUrl : publishModel.getImageFile()) {
                    viewFlipper.addView(imageHelper.setImageInFlipper(itemView, imageUrl));
                }
            } else {
                viewFlipper.setVisibility(GONE);
            }

            if (publishModel.getDate() != null) {
                eventDate.setText(publishModel.getDate());
            } else {
                eventDate.setVisibility(GONE);
                eventDateWord.setVisibility(GONE);
            }
        }
    }
}