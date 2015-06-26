package com.epishie.ripley.framework.gson;

import com.epishie.ripley.framework.gson.model.RedditObject;
import com.epishie.ripley.framework.gson.model.RedditKind;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class RedditDeserializer implements JsonDeserializer<RedditObject> {

    private static final String ELEMENT_KIND = "kind";
    private static final String ELEMENT_DATA = "data";

    @Override
    public RedditObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        RedditKind redditKind = context.deserialize(jsonObject.get(ELEMENT_KIND), RedditKind.class);
        RedditObject redditObject = context.deserialize(jsonObject.get(ELEMENT_DATA), redditKind.getDerivedClass());
        return redditObject;
    }
}
