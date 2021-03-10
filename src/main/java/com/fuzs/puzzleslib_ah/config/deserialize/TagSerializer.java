package com.fuzs.puzzleslib_ah.config.deserialize;

import com.google.gson.*;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITagCollection;

import java.lang.reflect.Type;

public abstract class TagSerializer<T> implements JsonDeserializer<ITag<T>>, JsonSerializer<ITag<T>> {

    abstract ITagCollection<T> getTagCollection();

    @Override
    public ITag<T> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(ITag<T> src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }

}
