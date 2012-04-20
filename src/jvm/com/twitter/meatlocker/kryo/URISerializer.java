package com.twitter.meatlocker.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.net.URI;

/** User: sritchie Date: 2/9/12 Time: 2:53 PM */
public class URISerializer extends Serializer<java.net.URI> {

    @Override
    public void write(Kryo kryo, Output output, URI uri) {
        output.writeString(uri.toString());
    }

    @Override
    public URI create(Kryo kryo, Input input, Class<URI> uriClass) {
        return URI.create(input.readString());
    }
}
