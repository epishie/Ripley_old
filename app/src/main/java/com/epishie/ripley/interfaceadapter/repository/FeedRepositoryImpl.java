package com.epishie.ripley.interfaceadapter.repository;

import com.epishie.ripley.entity.model.Feed;
import com.epishie.ripley.entity.repository.FeedRepository;
import com.epishie.ripley.interfaceadapter.provider.FeedProvider;

import javax.inject.Inject;
import javax.security.auth.Subject;

import rx.Observable;
import rx.Subscriber;
import rx.subjects.ReplaySubject;

public class FeedRepositoryImpl implements FeedRepository {

    private final FeedProvider mFeedProvider;
    private ReplaySubject<Feed> mFeedSubject;

    @Inject
    public FeedRepositoryImpl(FeedProvider feedProvider) {
        mFeedProvider = feedProvider;
    }

    @Override
    public Observable<Feed> getStream() {
        if (mFeedSubject == null) {
            mFeedSubject = ReplaySubject.create();
            mFeedSubject.onNext(mFeedProvider.getFeed());
        }
        return mFeedSubject.asObservable();
    }

    @Override
    public void refresh() {
        if (mFeedSubject == null) {
            return;
        }

        mFeedSubject.onCompleted();
        mFeedSubject = null;
    }

    @Override
    public void fetchNext() {
        if (mFeedSubject == null) {
            return;
        }

        mFeedSubject.onNext(mFeedProvider.getFeed());
    }
}
