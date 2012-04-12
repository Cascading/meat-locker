package com.twitter.meatlocker.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.sql.Time;

/** User: sritchie Date: 2/9/12 Time: 2:52 PM */
public class SqlTimeSerializer implements Serializer<Time> {

    public void write(Kryo kryo, Output output, Time time) {
        output.writeLong(time.getTime(), true);
    }

    public Time read(Kryo kryo, Input input, Class<Time> timeClass) {
        return new Time(input.readLong(true));
    }
}
