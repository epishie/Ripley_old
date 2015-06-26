package com.epishie.ripley.di.component;

import com.epishie.ripley.di.module.FeedModule;
import com.epishie.ripley.di.scope.FeedScope;
import com.epishie.ripley.framework.view.fragment.FeedFragment;

import dagger.Component;

@FeedScope
@Component(
    modules = FeedModule.class,
    dependencies = AppComponent.class
)
public interface FeedComponent {
    void injectFragment(FeedFragment fragment);
}
