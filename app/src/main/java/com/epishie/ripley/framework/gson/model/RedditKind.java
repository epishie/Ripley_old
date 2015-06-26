package com.epishie.ripley.framework.gson.model;

import com.google.gson.annotations.SerializedName;

public enum RedditKind {

    @SerializedName("Listing")
    LISTING(RedditListing.class),

    @SerializedName("t3")
    LINK(RedditLink.class);

    private final Class mClass;

    RedditKind(Class cls) {
        mClass = cls;
    }

    public Class getDerivedClass() {
        return mClass;
    }
}
