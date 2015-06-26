package com.epishie.ripley.di.module;

import android.app.Application;

import com.epishie.ripley.framework.executor.MainExecutor;
import com.epishie.ripley.framework.executor.WorkerExecutor;
import com.epishie.ripley.framework.retrofit.RedditService;

import java.util.concurrent.Executor;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import rx.Scheduler;
import rx.schedulers.Schedulers;

@Module
public class AppModule {

    private final Application mApp;
    private final String mRedditUri;

    public AppModule(Application app, String redditUri) {
        mApp = app;
        mRedditUri = redditUri;
    }

    @Singleton
    @Named("main")
    @Provides
    public Scheduler provideMainScheduler(MainExecutor mainExecutor) {
        return Schedulers.from(mainExecutor);
    }

    @Singleton
    @Named("worker")
    @Provides
    public Scheduler provideWorkerScheduler(WorkerExecutor workerExecutor) {
        return Schedulers.from(workerExecutor);
    }

    @Singleton
    @Provides
    RedditService provideRedditService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(mRedditUri)
                .build();

        return restAdapter.create(RedditService.class);
    }
}
