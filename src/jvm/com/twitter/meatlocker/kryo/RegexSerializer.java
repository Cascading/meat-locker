package com.twitter.meatlocker.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.util.regex.Pattern;

/** User: sritchie Date: 2/1/12 Time: 10:21 AM */
public class RegexSerializer extends Serializer<Pattern> {

    @Override
    public void write(Kryo kryo, Output output, Pattern pattern) {
        output.writeString(pattern.pattern());
    }

    @Override
    public Pattern create(Kryo kryo, Input input, Class<Pattern> patternClass) {
        return Pattern.compile(input.readString());
    }
}
