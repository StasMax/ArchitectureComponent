package com.example.android.architecturecomponent.data.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublishModel {
    private long id;
    private List<String> category;
    private List<String> tag;
    private String header;
    private String description;
    private List<String> imageFile;
    private List<String> link;
    private List<String> linkName;
    private String date;
    private int typeViewHolder;

    public PublishModel(int typeViewHolder) {
        this.typeViewHolder = typeViewHolder;
    }
}