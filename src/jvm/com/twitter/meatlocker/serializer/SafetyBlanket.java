package com.twitter.meatlocker.serializer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.serializer.Deserializer;
import org.apache.hadoop.io.serializer.Serialization;
import org.apache.hadoop.io.serializer.SerializationFactory;
import org.apache.hadoop.io.serializer.Serializer;
import org.apache.hadoop.mapred.JobConf;

/** User: sritchie Date: 2/16/12 Time: 2:24 PM */
public class SafetyBlanket<T> implements Serialization<T> {
    public static final String IO_SERIALIZATIONS = "io.serializations";
    SerializationFactory factory;
    Configuration conf;

    public SafetyBlanket(Configuration conf, Class<? extends Serialization> wrapped) {
        Configuration config = (conf != null) ? conf : new JobConf();
        this.conf = processConf(config, wrapped);
    }

    public static Configuration processConf(Configuration oldConf, Class klass) {
        Configuration conf = new Configuration(oldConf);
        // This key won't ever be null, since we know that THIS serialization is registered.
        String serString = conf.get(IO_SERIALIZATIONS);
        String[] serializations = serString.split(",");

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

            if (serialization.equals(klass.getName()))
                encountered = true;
        }

        String newVal = builder.toString();

        // If the serialization simply wasn't present, set the old value.
        conf.set(IO_SERIALIZATIONS, newVal.equals("") ? serString : newVal);
        return conf;
    }

    public boolean accept(Class<?> aClass) {
        if (factory == null)
            factory = new SerializationFactory(conf);
        return factory.getSerialization(aClass) != null;
    }

    public Serializer<T> getSerializer(Class<T> aClass) {
        return factory.getSerializer(aClass);
    }

    public Deserializer<T> getDeserializer(Class<T> aClass) {
        return factory.getDeserializer(aClass);
    }
}
