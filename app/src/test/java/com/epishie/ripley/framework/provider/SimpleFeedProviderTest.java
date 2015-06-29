package com.epishie.ripley.framework.provider;

import com.epishie.ripley.entity.model.Feed;
import com.epishie.ripley.framework.gson.RedditDeserializer;
import com.epishie.ripley.framework.gson.model.RedditObject;
import com.epishie.ripley.framework.retrofit.RedditService;
import com.epishie.ripley.interfaceadapter.provider.FeedProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;

import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedByteArray;

import static org.junit.Assert.*;

public class SimpleFeedProviderTest {

    private RedditService mService;

    @Before
    public void setUp() {
        Gson gson = new GsonBuilder().registerTypeAdapter(RedditObject.class, new RedditDeserializer())
                .create();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new MockClient())
                .setEndpoint("http://reddit.com")
                .setConverter(new GsonConverter(gson))
                .build();
        mService = restAdapter.create(RedditService.class);
    }

    @Test
    public void testGetFeed() {
        FeedProvider feedProvider = new SimpleFeedProvider(mService);
        Feed feed  = feedProvider.getFeed();

        assertNotNull(feed);
    }

    private static class MockClient implements Client {

        @Override
        public Response execute(Request request) throws IOException {
            URI uri = URI.create(request.getUrl());

            String responseString = "";
            if (uri.getPath().equalsIgnoreCase("/.json")) {
                InputStream is = new FileInputStream("src/test/assets/all.json");
                byte[] raw = new byte[is.available()];
                int read = is.read(raw);
                if (read > 0) {
                    responseString = new String(raw);
                }
            }

            return new Response(request.getUrl(),
                    200,
                    "",
                    new ArrayList<Header>(),
                    new TypedByteArray("application/json", responseString.getBytes()));
        }
    }
}