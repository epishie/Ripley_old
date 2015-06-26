package com.epishie.ripley.framework.gson.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RedditListing extends RedditObject {

    @SerializedName("children")
    private List<RedditObject> mChildren;

    public List<RedditObject> getChildren() {
        return mChildren;
    }

    public RedditListing() {

    }
}

