package com.epishie.ripley.entity.repository;

import com.epishie.ripley.entity.model.Feed;

import rx.Observable;

public interface FeedRepository {

    Observable<Feed> getStream();
    void refresh();
    void fetchNext();
}
