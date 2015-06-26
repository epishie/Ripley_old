package com.epishie.ripley.framework.gson.model;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

public class RedditWrapper {

    @SerializedName("kind")
    private RedditKind mRedditKind;
    @SerializedName("data")
    private JsonElement mData;

    public RedditKind getRedditKind() {
        return mRedditKind;
    }

    public JsonElement getData() {
        return mData;
    }
}
