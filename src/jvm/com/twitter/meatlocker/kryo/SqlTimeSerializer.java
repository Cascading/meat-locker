package com.twitter.meatlocker.kryo;

import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.serialize.LongSerializer;

import java.nio.ByteBuffer;
import java.sql.Time;

/** User: sritchie Date: 2/9/12 Time: 2:52 PM */
public class SqlTimeSerializer extends Serializer {
    @Override public void writeObjectData(ByteBuffer byteBuffer, Object o) {
        Time time = (Time) o;
        LongSerializer.put(byteBuffer, time.getTime(), true);
    }

    @Override public <T> T readObjectData(ByteBuffer byteBuffer, Class<T> tClass) {
        return (T) new Time(LongSerializer.get(byteBuffer, true));
    }
}
