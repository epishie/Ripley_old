package com.epishie.ripley.interfaceadapter.presenter;

import com.epishie.ripley.entity.model.Feed;
import com.epishie.ripley.interfaceadapter.view.FeedView;
import com.epishie.ripley.usecase.GetFeedUseCase;

import javax.inject.Inject;

import rx.Subscriber;

public class FeedPresenter {

    private final GetFeedUseCase mGetFeedUseCase;
    private FeedView mView;

    @Inject
    public FeedPresenter(GetFeedUseCase getFeedUseCase) {
        mGetFeedUseCase = getFeedUseCase;
    }

    public void setView(FeedView view) {
        mView = view;
    }

    public void onStart() {
        mGetFeedUseCase.execute(new Subscriber<Feed>() {
            @Override
            public void onCompleted() {
                mGetFeedUseCase.unSubscribe();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Feed feed) {
                mView.showFeed(feed);
            }
        });
    }
}
