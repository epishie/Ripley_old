package com.epishie.ripley.entity.model;

import java.util.List;

public class Feed {

    private final List<Link> mLinks;

    public Feed(List<Link> links) {
        mLinks = links;
    }

    public List<Link> getLinks() {
        return mLinks;
    }
}
