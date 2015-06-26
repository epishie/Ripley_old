package com.epishie.ripley.usecase;

import com.epishie.ripley.entity.model.Feed;
import com.epishie.ripley.entity.repository.FeedRepository;
import com.epishie.ripley.mock.MockExecutor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.TestScheduler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GetFeedUseCaseTest {

    TestScheduler mScheduler = new TestScheduler();
    @Mock
    FeedRepository mFeedRepository;
    @Mock
    Feed mFeed;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(mFeedRepository.getFeed()).thenReturn(Observable.just(mFeed));
    }

    @Test
    public void testExecute() throws InterruptedException {
        GetFeedUseCase useCase = new GetFeedUseCase(mScheduler, mScheduler, mFeedRepository);
        useCase.execute(new Subscriber<Feed>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Feed feed) {
                assertEquals(feed, mFeed);
            }
        });
        mScheduler.advanceTimeBy(2, TimeUnit.SECONDS);

        verify(mFeedRepository).getFeed();
    }
}