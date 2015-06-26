package com.epishie.ripley.interfaceadapter.presenter;

import com.epishie.ripley.entity.model.Feed;
import com.epishie.ripley.interfaceadapter.view.FeedView;
import com.epishie.ripley.usecase.GetFeedUseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import rx.Subscriber;

import static org.mockito.Mockito.*;

public class FeedPresenterTest {

    @Mock
    GetFeedUseCase mGetFeedUseCase;
    @Mock
    FeedView mView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        doAnswer(new Answer() {

            @SuppressWarnings("unchecked")
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Subscriber<Feed> subscriber = (Subscriber<Feed>) invocation.getArguments()[0];
                subscriber.onNext(mock(Feed.class));
                subscriber.onCompleted();
                return null;
            }
        }).when(mGetFeedUseCase).execute(Matchers.<Subscriber<Feed>>any());
    }

    @Test
    public void testOnStart() {
        FeedPresenter presenter = new FeedPresenter(mGetFeedUseCase);
        presenter.setView(mView);
        presenter.onStart();

        verify(mGetFeedUseCase).execute(Matchers.<Subscriber<Feed>>any());
        verify(mView).showFeed(any(Feed.class));
        verify(mGetFeedUseCase).unSubscribe();
    }
}