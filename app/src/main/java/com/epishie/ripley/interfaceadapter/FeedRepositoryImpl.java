package com.epishie.ripley.interfaceadapter;

import com.epishie.ripley.entity.model.Feed;
import com.epishie.ripley.entity.repository.FeedRepository;

import javax.inject.Inject;

import rx.Observable;

public class FeedRepositoryImpl implements FeedRepository {

    @Inject
    public FeedRepositoryImpl() {

    }

    @Override
    public Observable<Feed> getFeed() {
        return null;
    }
}
