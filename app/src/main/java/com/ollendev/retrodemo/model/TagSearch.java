package com.ollendev.retrodemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TagSearch {
    @SerializedName("data") public List<TagInfo> tagList;
}
