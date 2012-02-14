package com.twitter.meatlocker.kryo;

import com.esotericsoftware.kryo.Serializer;

import java.nio.ByteBuffer;

/** User: sritchie Date: 2/9/12 Time: 2:54 PM */
public class ProtobufSerializer extends Serializer {
    @Override public void writeObjectData(ByteBuffer byteBuffer, Object o) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override public <T> T readObjectData(ByteBuffer byteBuffer, Class<T> tClass) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
