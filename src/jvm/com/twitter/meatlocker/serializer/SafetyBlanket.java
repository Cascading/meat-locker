package com.twitter.meatlocker.serializer;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.serializer.Deserializer;
import org.apache.hadoop.io.serializer.Serialization;
import org.apache.hadoop.io.serializer.SerializationFactory;
import org.apache.hadoop.io.serializer.Serializer;
import org.apache.hadoop.mapred.JobConf;

/** User: sritchie Date: 2/16/12 Time: 2:24 PM */
public class SafetyBlanket<T> extends Configured implements Serialization<T> {
    public static final String IO_SERIALIZATIONS = "io.serializations";
    Serialization<T> wrapped;
    SerializationFactory factory;

    public SafetyBlanket(Serialization<T> wrapped) {
        this.wrapped = wrapped;
    }

    public void processConf(Configuration conf) {
        // This key won't ever be null, since we know that THIS serialization is registered.
        String[] serializations = conf.get(IO_SERIALIZATIONS).split(",");

        StringBuilder builder = new StringBuilder();
        boolean encountered = false;
        boolean isFirst = true;

        for (String serialization : serializations) {
            if (encountered) {
                if (!isFirst)
                    builder.append(",");
                isFirst = false;
                builder.append(serialization);
            }

            if (serialization.equals(this.getClass().getName()))
                encountered = true;
        }

        conf.set(IO_SERIALIZATIONS, builder.toString());
    }

    @Override public void setConf( Configuration conf ) {
        processConf(conf);
        if (wrapped instanceof Configurable)
            ((Configurable) wrapped).setConf(conf);
        else
            super.setConf(conf);
    }

    @Override public Configuration getConf() {
        if( super.getConf() == null )
            setConf( new JobConf() );
        return (wrapped instanceof Configurable) ? ((Configurable) wrapped).getConf() : super.getConf();
    }

    public boolean accept(Class<?> aClass) {
        if (factory == null)
            factory = new SerializationFactory(getConf());
        return (factory.getSerialization(aClass) != null || wrapped.accept(aClass));
    }

    public Serializer<T> getSerializer(Class<T> aClass) {
        Serializer<T> factorySerializer = factory.getSerializer(aClass);
        return (factorySerializer != null) ? factorySerializer : wrapped.getSerializer(aClass);
    }

    public Deserializer<T> getDeserializer(Class<T> aClass) {
        Deserializer<T> factoryDeserializer = factory.getDeserializer(aClass);
        return (factoryDeserializer != null) ? factoryDeserializer : wrapped.getDeserializer(aClass);
    }
}
