package com.epishie.ripley.framework.provider;

import com.epishie.ripley.entity.model.Feed;
import com.epishie.ripley.entity.model.Link;
import com.epishie.ripley.framework.gson.model.RedditLink;
import com.epishie.ripley.framework.gson.model.RedditListing;
import com.epishie.ripley.framework.gson.model.RedditObject;
import com.epishie.ripley.framework.gson.model.RedditResponse;
import com.epishie.ripley.framework.retrofit.RedditService;
import com.epishie.ripley.interfaceadapter.provider.FeedProvider;

import java.util.ArrayList;
import java.util.List;

public class SimpleFeedProvider implements FeedProvider {

    private final RedditService mService;

    public SimpleFeedProvider(RedditService service) {
        mService = service;
    }

    @Override
    public Feed getFeed() {
        RedditResponse<RedditListing> response = mService.getFeed();
        if (response == null || response.getData() == null) {
            return null;
        }

        RedditListing redditListing = response.getData();
        List<Link> links = new ArrayList<>();
        for (RedditObject redditObject : redditListing.getChildren()) {
            if (redditObject instanceof RedditLink) {
                links.add(((RedditLink) redditObject).toLink());
            }
        }
        return new Feed(links);
    }
}
