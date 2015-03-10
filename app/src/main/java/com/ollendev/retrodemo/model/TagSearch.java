package com.ollendev.retrodemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Chris on 3/10/15.
 */
public class TagSearch {
    @SerializedName("data")
    List<TagInfo> tagList;
}
