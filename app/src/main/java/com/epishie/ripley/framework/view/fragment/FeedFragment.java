package com.epishie.ripley.framework.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epishie.ripley.R;
import com.epishie.ripley.di.HasComponent;
import com.epishie.ripley.di.component.FeedComponent;
import com.epishie.ripley.entity.model.Feed;
import com.epishie.ripley.entity.model.Link;
import com.epishie.ripley.interfaceadapter.presenter.FeedPresenter;
import com.epishie.ripley.interfaceadapter.view.FeedView;

import javax.inject.Inject;

public class FeedFragment extends Fragment implements FeedView {

    @Inject
    protected FeedPresenter mPresenter;

    private Adapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new Adapter();

        RecyclerView feedList = (RecyclerView)view.findViewById(R.id.feed_list);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        feedList.setLayoutManager(lm);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        HasComponent<FeedComponent> activity = (HasComponent<FeedComponent>) getActivity();
        activity.getComponent().injectFragment(this);
        mPresenter.setView(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        mPresenter.onStart();
    }

    @Override
    public void showFeed(Feed feed) {
        mAdapter.setFeed(feed);
    }

    private static final class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private Feed mFeed;

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View view = inflater.inflate(R.layout.item_feed, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public int getItemCount() {
            if (mFeed == null || mFeed.getLinks() == null) {
                return 0;
            }

            return mFeed.getLinks().size();
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            Link link = mFeed.getLinks().get(i);
            viewHolder.mLinkTitle.setText(link.getTitle());
        }

        public void setFeed(Feed feed) {
            mFeed = feed;
            notifyDataSetChanged();
        }

        protected static final class ViewHolder extends RecyclerView.ViewHolder {

            private final TextView mLinkTitle;

            public ViewHolder(View itemView) {
                super(itemView);
                mLinkTitle = (TextView)itemView.findViewById(R.id.link_title);
            }
        }
    }
}
