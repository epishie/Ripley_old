package com.epishie.ripley.framework.gson.model;

import com.google.gson.annotations.SerializedName;

public class RedditResponse<T> {
    @SerializedName("data")
    private T mData;

    public T getData() {
        return mData;
    }
}
