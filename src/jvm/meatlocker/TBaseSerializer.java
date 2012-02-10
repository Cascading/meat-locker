package meatlocker;

import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.serialize.IntSerializer;
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
            byte[] serThrift = serializer.serialize(thrift);
            IntSerializer.put(byteBuffer, serThrift.length, true);
            byteBuffer.put(serThrift);
        } catch (TException e) {
            throw new RuntimeException(e);
        }
    }

    @Override public <T> T readObjectData(ByteBuffer byteBuffer, Class<T> tClass) {
        try {
            TBase prototype = (TBase) tClass.newInstance();
            int tSize = IntSerializer.get(byteBuffer, true);
            byte[] barr = new byte[tSize];
            byteBuffer.get(barr);
            deserializer.deserialize(prototype, barr);
            return (T) prototype;
        } catch (Exception e) {
            throw new RuntimeException("Could not create " + tClass, e);
        }
    }
}
