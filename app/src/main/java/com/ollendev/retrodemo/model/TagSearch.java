package com.ollendev.retrodemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TagSearch {
    @SerializedName("data")
    List<TagInfo> tagList;
}
