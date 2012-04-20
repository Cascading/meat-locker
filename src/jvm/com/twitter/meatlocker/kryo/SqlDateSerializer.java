package com.twitter.meatlocker.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.sql.Date;

/** User: sritchie Date: 2/9/12 Time: 2:52 PM */
public class SqlDateSerializer extends Serializer<Date> {

    @Override
    public void write(Kryo kryo, Output output, Date date) {
        output.writeLong(date.getTime(), true);
    }

    @Override
    public Date create(Kryo kryo, Input input, Class<Date> dateClass) {
        return new Date(input.readLong(true));
    }
}
