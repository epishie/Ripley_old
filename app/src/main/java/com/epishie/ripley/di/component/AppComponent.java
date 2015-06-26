package com.epishie.ripley.di.component;

import com.epishie.ripley.di.module.AppModule;
import com.epishie.ripley.framework.retrofit.RedditService;

import java.util.concurrent.Executor;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    RedditService getRedditService();
    @Named("main")
    Scheduler getMainScheduler();
    @Named("worker")
    Scheduler getWorkerScheduler();
}
