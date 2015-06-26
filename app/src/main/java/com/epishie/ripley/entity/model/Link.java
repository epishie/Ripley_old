package com.epishie.ripley.entity.model;

public class Link {

    private final String mTitle;
    private final String mAuthor;

    protected Link(String title,
                   String author) {
        mTitle = title;
        mAuthor = author;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public static class Builder {

        private String mTitle;
        private String mAuthor;

        public Link create() {
            return new Link(mTitle, mAuthor);
        }

        public void setTitle(String title) {
            mTitle = title;
        }

        public void setAuthor(String author) {
            mAuthor = author;
        }
    }
}
