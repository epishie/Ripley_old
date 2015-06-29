package com.epishie.ripley.interfaceadapter.repository;

import com.epishie.ripley.entity.model.Feed;
import com.epishie.ripley.entity.repository.FeedRepository;
import com.epishie.ripley.interfaceadapter.provider.FeedProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.schedulers.TestScheduler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FeedRepositoryTest {

    @Mock
    FeedProvider mFeedProvider;
    @Mock
    Feed mFeed1;
    @Mock
    Feed mFeed2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(mFeedProvider.getFeed()).thenReturn(mFeed1, mFeed2);
    }

    @Test
    public void testGetStream() throws InterruptedException {
        FeedRepository feedRepository = new FeedRepositoryImpl(mFeedProvider);
        TestScheduler scheduler = Schedulers.test();
        @SuppressWarnings("unchecked")
        Action1<Feed> subscriber1 = spy(Action1.class);
        @SuppressWarnings("unchecked")
        Action1<Feed> subscriber2 = spy(Action1.class);
        feedRepository.getStream()
                .subscribeOn(scheduler)
                .observeOn(scheduler)
                .subscribe(subscriber1);
        feedRepository.getStream()
                .subscribeOn(scheduler)
                .observeOn(scheduler)
                .subscribe(subscriber2);

        scheduler.advanceTimeBy(5, TimeUnit.SECONDS);
        verify(subscriber1, times(1)).call(mFeed1);
        verify(subscriber1, never()).call(mFeed2);
        verify(subscriber2, times(1)).call(mFeed1);
        verify(subscriber2, never()).call(mFeed2);
    }

    @Test
    public void testRefresh() {
        FeedRepository feedRepository = new FeedRepositoryImpl(mFeedProvider);
        TestScheduler scheduler = Schedulers.test();
        @SuppressWarnings("unchecked")
        Action1<Feed> subscriber1 = spy(Action1.class);
        @SuppressWarnings("unchecked")
        Action1<Feed> subscriber2 = spy(Action1.class);
        feedRepository.getStream()
                .subscribeOn(scheduler)
                .observeOn(scheduler)
                .subscribe(subscriber1);
        scheduler.advanceTimeBy(5, TimeUnit.SECONDS);

        feedRepository.refresh();
        feedRepository.getStream()
                .subscribeOn(scheduler)
                .observeOn(scheduler)
                .subscribe(subscriber2);
        scheduler.advanceTimeBy(5, TimeUnit.SECONDS);

        verify(subscriber1, times(1)).call(mFeed1);
        verify(subscriber1, never()).call(mFeed2);
        verify(subscriber2, never()).call(mFeed1);
        verify(subscriber2, times(1)).call(mFeed2);
    }

    @Test
    public void testFetchNext() {
        FeedRepository feedRepository = new FeedRepositoryImpl(mFeedProvider);
        TestScheduler scheduler = Schedulers.test();
        @SuppressWarnings("unchecked")
        Action1<Feed> subscriber = spy(Action1.class);
        feedRepository.getStream()
                .subscribeOn(scheduler)
                .observeOn(scheduler)
                .subscribe(subscriber);
        scheduler.advanceTimeBy(5, TimeUnit.SECONDS);
        feedRepository.fetchNext();
        scheduler.advanceTimeBy(5, TimeUnit.SECONDS);

        verify(subscriber, times(1)).call(mFeed1);
        verify(subscriber, times(1)).call(mFeed2);
    }
}
