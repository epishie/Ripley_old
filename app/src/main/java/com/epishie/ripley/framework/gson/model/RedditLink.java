package com.epishie.ripley.framework.gson.model;

import com.epishie.ripley.entity.model.Link;
import com.google.gson.annotations.SerializedName;

public class RedditLink extends RedditObject {

    @SerializedName("title")
    private String mTitle;
    @SerializedName("author")
    private String mAuthor;

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public Link toLink() {
        Link.Builder builder = new Link.Builder();
        builder.setTitle(mTitle);
        builder.setAuthor(mAuthor);

        return builder.create();
    }
}
