package com.epishie.ripley.di.module;

import android.app.Activity;

import com.epishie.ripley.di.scope.FeedScope;
import com.epishie.ripley.entity.repository.FeedRepository;
import com.epishie.ripley.interfaceadapter.FeedRepositoryImpl;

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
    public FeedRepository provideFeedRepository() {
        return new FeedRepositoryImpl();
    }
}
