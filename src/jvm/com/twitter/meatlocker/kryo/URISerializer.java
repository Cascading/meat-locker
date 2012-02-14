package com.twitter.meatlocker.kryo;

import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.serialize.StringSerializer;

import java.nio.ByteBuffer;

/** User: sritchie Date: 2/9/12 Time: 2:53 PM */
public class URISerializer extends Serializer {

    @Override public void writeObjectData(ByteBuffer byteBuffer, Object o) {
        StringSerializer.put(byteBuffer, o.toString());
    }

    @Override public <T> T readObjectData(ByteBuffer byteBuffer, Class<T> tClass) {
        String s = StringSerializer.get(byteBuffer);
        return (T) java.net.URI.create(s);
    }
}
