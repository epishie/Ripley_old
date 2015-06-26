package com.epishie.ripley.framework.executor;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class WorkerExecutor implements Executor {

    private final ExecutorService mExecutorService;

    @Inject
    public WorkerExecutor() {
        mExecutorService = Executors.newCachedThreadPool();
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mExecutorService.execute(command);
    }
}
