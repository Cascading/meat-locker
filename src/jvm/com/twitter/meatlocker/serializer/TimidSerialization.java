package com.twitter.meatlocker.serializer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.serializer.Deserializer;
import org.apache.hadoop.io.serializer.Serialization;
import org.apache.hadoop.io.serializer.Serializer;

/** User: sritchie Date: 2/16/12 Time: 4:55 PM */
public abstract class TimidSerialization<T> extends Configured implements Serialization<T> {

    SafetyBlanket<T> blanket;

    public abstract Serialization<T> getWrappedSerialization();

    public final boolean accept(Class<?> aClass) {
        if(blanket == null) {
            blanket = new SafetyBlanket<T>(getWrappedSerialization());
        }
        return blanket.accept(aClass);
    }

    @Override public final void setConf( Configuration conf ) {
        blanket.setConf(conf);
    }

    @Override public final Configuration getConf() {
        return blanket.getConf();
    }

    public final Serializer<T> getSerializer(Class<T> tClass) {
        return blanket.getSerializer(tClass);
    }

    public final Deserializer<T> getDeserializer(Class<T> tClass) {
        return blanket.getDeserializer(tClass);
    }
}
