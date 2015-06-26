package com.epishie.ripley;

import android.app.Application;

import com.epishie.ripley.di.HasComponent;
import com.epishie.ripley.di.component.AppComponent;
import com.epishie.ripley.di.component.DaggerAppComponent;
import com.epishie.ripley.di.module.AppModule;

public class Ripley extends Application implements HasComponent<AppComponent> {

    private static final String URI_REDDIT = "http://reddit.com";

    private AppComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, URI_REDDIT))
                .build();
    }

    @Override
    public AppComponent getComponent() {
        return mComponent;
    }
}
