package com.epishie.ripley.di.module;

import android.app.Activity;

import com.epishie.ripley.di.scope.FeedScope;
import com.epishie.ripley.entity.repository.FeedRepository;
import com.epishie.ripley.framework.provider.SimpleFeedProvider;
import com.epishie.ripley.framework.retrofit.RedditService;
import com.epishie.ripley.interfaceadapter.provider.FeedProvider;
import com.epishie.ripley.interfaceadapter.repository.FeedRepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class FeedModule {

    private final Activity mActivity;

    public FeedModule(Activity activity) {
        mActivity = activity;
    }

    @FeedScope
    @Provides
    public FeedRepository provideFeedRepository(FeedProvider feedProvider) {
        return new FeedRepositoryImpl(feedProvider);
    }

    @FeedScope
    @Provides
    public FeedProvider provideFeedProvider(RedditService redditService) {
        return new SimpleFeedProvider(redditService);
    }
}
