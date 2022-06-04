package com.github.as2122.backend.api;

import com.github.as2122.backend.api.requests.Login;
import com.github.as2122.backend.api.requests.Request;
import com.google.gson.*;

import java.lang.reflect.Type;

public class RequestDeserializer implements JsonDeserializer<Request> {
    @Override
    public Request deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final JsonObject jsonObject = jsonElement.getAsJsonObject();

        if (jsonObject.has("type"))
            switch (jsonObject.get("type").getAsString()) {
                case "login":
                    return jsonDeserializationContext.deserialize(jsonObject.get("query"), Login.class);
                default:
                    return null;
            }

        return null;
    }
}
