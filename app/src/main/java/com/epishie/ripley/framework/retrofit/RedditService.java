package com.epishie.ripley.framework.retrofit;

import com.epishie.ripley.framework.gson.model.RedditListing;
import com.epishie.ripley.framework.gson.model.RedditResponse;

import retrofit.http.GET;

public interface RedditService {

    @GET("/.json")
    RedditResponse<RedditListing> getFeed();
}
