package com.twitter.meatlocker.serializer;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.serializer.Deserializer;
import org.apache.hadoop.io.serializer.Serialization;
import org.apache.hadoop.io.serializer.Serializer;

/** User: sritchie Date: 2/16/12 Time: 4:55 PM */
public class TimidSerialization<T> extends Configured implements Serialization<T> {
    SafetyBlanket<T> blanket;

    public boolean accept(Class<?> aClass) {
        if(blanket == null)
            blanket = new SafetyBlanket<T>(this.getConf(), this.getClass());
        return blanket.accept(aClass);
    }

    public Serializer<T> getSerializer(Class<T> tClass) {
        return blanket.getSerializer(tClass);
    }

    public Deserializer<T> getDeserializer(Class<T> tClass) {
        return blanket.getDeserializer(tClass);
    }
}
