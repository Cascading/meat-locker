package meatlocker;

import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.serialize.LongSerializer;

import java.nio.ByteBuffer;
import java.sql.Timestamp;

/** User: sritchie Date: 2/9/12 Time: 2:53 PM */
public class TimestampSerializer extends Serializer {

    @Override public void writeObjectData(ByteBuffer byteBuffer, Object o) {
        Timestamp timestamp = (Timestamp) o;
        LongSerializer.put(byteBuffer, timestamp.getTime(), true);
        LongSerializer.put(byteBuffer, timestamp.getNanos(), true);
    }

    @Override public <T> T readObjectData(ByteBuffer byteBuffer, Class<T> tClass) {
        Timestamp ts = new Timestamp(LongSerializer.get(byteBuffer, true));
        ts.setNanos((int) LongSerializer.get(byteBuffer, true));
        return (T) ts;
    }
}
