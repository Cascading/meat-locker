package com.twitter.meatlocker.kryo;

import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.serialize.LongSerializer;

import java.nio.ByteBuffer;
import java.sql.Date;

/** User: sritchie Date: 2/9/12 Time: 2:52 PM */
public class SqlDateSerializer extends Serializer {
    @Override public void writeObjectData(ByteBuffer byteBuffer, Object o) {
        Date date = (Date) o;
        LongSerializer.put(byteBuffer, date.getTime(), true);
    }

    @Override public <T> T readObjectData(ByteBuffer byteBuffer, Class<T> tClass) {
        return (T) new Date(LongSerializer.get(byteBuffer, true));
    }
}
