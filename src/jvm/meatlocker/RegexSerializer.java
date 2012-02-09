package meatlocker;

import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.serialize.StringSerializer;

import java.nio.ByteBuffer;
import java.util.regex.Pattern;

/** User: sritchie Date: 2/1/12 Time: 10:21 AM */
public class RegexSerializer extends Serializer {

    @Override public <T> T readObjectData(ByteBuffer byteBuffer, Class<T> tClass) {
        String s = StringSerializer.get(byteBuffer);
        return (T) java.util.regex.Pattern.compile(s);
    }

    @Override public void writeObjectData(ByteBuffer byteBuffer, Object p) {
        StringSerializer.put(byteBuffer, ((Pattern) p).pattern());
    }
}
