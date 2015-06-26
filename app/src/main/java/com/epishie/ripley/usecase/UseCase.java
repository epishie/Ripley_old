package com.epishie.ripley.usecase;

import java.util.concurrent.Executor;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public abstract class UseCase<T> {

    private final Scheduler mMainScheduler;
    private final Scheduler mWorkerScheduler;

    private Subscription mSubscription = Subscriptions.empty();

    protected UseCase(Scheduler mainScheduler,
                      Scheduler workerScheduler) {
        mMainScheduler = mainScheduler;
        mWorkerScheduler = workerScheduler;
    }

    protected abstract Observable<T> createObservable();

    public void execute(Subscriber<T> subscriber) {
        createObservable().observeOn(mMainScheduler)
                .subscribeOn(mWorkerScheduler)
                .subscribe(subscriber);
    }

    public void unSubscribe() {
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
