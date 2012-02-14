package com.twitter.meatlocker.kryo;

import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.serialize.BooleanSerializer;
import com.esotericsoftware.kryo.serialize.IntSerializer;

import java.nio.ByteBuffer;
import java.util.BitSet;

/** User: sritchie Date: 2/9/12 Time: 2:54 PM */
public class BitSetSerializer extends Serializer {
    BooleanSerializer booleanSerializer  = new BooleanSerializer();

    @Override public void writeObjectData(ByteBuffer byteBuffer, Object o) {
        BitSet bs = (BitSet) o;
        int len = bs.length();

        IntSerializer.put(byteBuffer, len, true);

        for(int i = 0; i < len; i++) {
            booleanSerializer.writeObjectData(byteBuffer, bs.get(i));
        }
    }

    @Override public <T> T readObjectData(ByteBuffer byteBuffer, Class<T> tClass) {
        int len = IntSerializer.get(byteBuffer, true);
        BitSet ret = new BitSet(len);

        for(int i = 0; i < len; i++) {
            ret.set(i, booleanSerializer.readObjectData(byteBuffer, boolean.class));
        }

        return (T) ret;
    }
}
