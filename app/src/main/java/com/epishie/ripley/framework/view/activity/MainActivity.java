package com.epishie.ripley.framework.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.epishie.ripley.R;
import com.epishie.ripley.di.HasComponent;
import com.epishie.ripley.di.component.AppComponent;
import com.epishie.ripley.di.component.DaggerFeedComponent;
import com.epishie.ripley.di.component.FeedComponent;
import com.epishie.ripley.di.module.FeedModule;
import com.epishie.ripley.framework.view.fragment.FeedFragment;


public class MainActivity extends AppCompatActivity implements HasComponent<FeedComponent> {

    private static final String TAG_CONTENT = "content";

    private FeedComponent mComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportFragmentManager().findFragmentByTag(TAG_CONTENT) == null) {
            FeedFragment fragment = new FeedFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content, fragment, TAG_CONTENT)
                    .commit();
        }

        setupDependencyInjection();
    }

    @SuppressWarnings("unchecked")
    void setupDependencyInjection() {
        HasComponent<AppComponent> app = (HasComponent<AppComponent>) getApplication();
        mComponent = DaggerFeedComponent.builder()
                .appComponent(app.getComponent())
                .feedModule(new FeedModule(this))
                .build();
    }

    @Override
    public FeedComponent getComponent() {
        return mComponent;
    }
}
