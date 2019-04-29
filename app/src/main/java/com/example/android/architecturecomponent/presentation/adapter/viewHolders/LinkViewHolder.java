package com.example.android.architecturecomponent.presentation.adapter.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.example.android.architecturecomponent.R;
import com.example.android.architecturecomponent.data.model.PublishModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LinkViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.category_link)
    TextView linkCategory;
    @BindView(R.id.tag_link)
    TextView linkTag;
    @BindView(R.id.link_link)
    TextView linkLink;
    @BindView(R.id.load_txt_link)
    TextView textLoad;
    private StringBuilder linkBuilder = new StringBuilder();
    private StringBuilder categoryBuilder = new StringBuilder();
    private StringBuilder tagBuilder = new StringBuilder();

    public LinkViewHolder(View v) {
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
                linkCategory.setText(categoryBuilder.toString());
                categoryBuilder.setLength(0);
            }

            if (publishModel.getTag() != null && publishModel.getTag().size() != 0) {
                for (String tag : publishModel.getTag()) {
                    tagBuilder.append(" ").append(tag).append(",");
                }
                tagBuilder.deleteCharAt(tagBuilder.length() - 1);
                linkTag.setText(tagBuilder.toString());
                tagBuilder.setLength(0);
            }

            if (publishModel.getLink() != null && publishModel.getLink().size() != 0) {
                for (int i = 0; i < publishModel.getLink().size(); i++) {
                    linkBuilder.append(" " + "<a href=").append(publishModel.getLink().get(i)).append("><font color=#AAA>").append(publishModel.getLinkName().get(i)).append("</font></a>").append(",");
                }
                linkBuilder.deleteCharAt(linkBuilder.length() - 1);
                linkLink.setText(Html.fromHtml(linkBuilder.toString()));
                linkLink.setMovementMethod(LinkMovementMethod.getInstance());
                linkBuilder.setLength(0);
            }
        }
    }
}
