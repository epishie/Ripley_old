package com.epishie.ripley.usecase;

import com.epishie.ripley.entity.model.Feed;
import com.epishie.ripley.entity.repository.FeedRepository;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class GetFeedUseCase extends UseCase<Feed> {

    private final FeedRepository mFeedRepository;

    @Inject
    public GetFeedUseCase(@Named("main") Scheduler mainScheduler,
                          @Named("worker") Scheduler workerScheduler,
                          FeedRepository feedRepository) {
        super(mainScheduler, workerScheduler);
        mFeedRepository = feedRepository;
    }

    @Override
    protected Observable<Feed> createObservable() {
        return mFeedRepository.getStream();
    }
}
