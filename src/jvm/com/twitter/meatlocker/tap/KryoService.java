package com.twitter.meatlocker.tap;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.ObjectBuffer;
import org.apache.log4j.Logger;

public class KryoService {
    static final ObjectBuffer kryoBuf = new ObjectBuffer(new Kryo(), 2000, 2000000000);
    static final Logger LOG = Logger.getLogger(KryoService.class);

    public static byte[] serialize(Object obj) {
        LOG.debug("Serializing " + obj);
        return kryoBuf.writeClassAndObject(obj);
    }

    public static Object deserialize(byte[] serialized) {
        Object o = kryoBuf.readClassAndObject(serialized);
        LOG.debug("Deserialized " + o);
        return o;
    }
}