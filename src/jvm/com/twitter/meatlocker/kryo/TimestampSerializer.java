package com.twitter.meatlocker.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.sql.Timestamp;

/** User: sritchie Date: 2/9/12 Time: 2:53 PM */
public class TimestampSerializer implements Serializer<Timestamp> {

    public void write(Kryo kryo, Output output, Timestamp timestamp) {
        output.writeLong(timestamp.getTime(), true);
        output.writeInt(timestamp.getNanos(), true);
    }

    public Timestamp read(Kryo kryo, Input input, Class<Timestamp> timestampClass) {
        Timestamp ts = new Timestamp(input.readLong(true));
        ts.setNanos(input.readInt(true));
        return ts;
    }
}
