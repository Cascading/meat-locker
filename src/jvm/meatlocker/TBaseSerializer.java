package meatlocker;

import com.esotericsoftware.kryo.Serializer;
import org.apache.thrift.TBase;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;

import java.nio.ByteBuffer;

/** User: sritchie Date: 2/9/12 Time: 2:54 PM */
public class TBaseSerializer extends Serializer {
    TSerializer serializer  = new TSerializer();
    TDeserializer deserializer  = new TDeserializer();

    @Override public void writeObjectData(ByteBuffer byteBuffer, Object o) {
        TBase thrift = (TBase) o;
        try {
            byteBuffer.put(serializer.serialize(thrift));
        } catch (TException e) {
            throw new RuntimeException(e);
        }
    }

    @Override public <T> T readObjectData(ByteBuffer byteBuffer, Class<T> tClass) {
        try {
            byte[] barr = new byte[byteBuffer.remaining()];
            TBase prototype = (TBase) tClass.newInstance();
            deserializer.deserialize(prototype, barr);
            return (T) prototype;
        } catch (Exception e) {
            throw new RuntimeException("Could not create " + tClass, e);
        }
    }
}
