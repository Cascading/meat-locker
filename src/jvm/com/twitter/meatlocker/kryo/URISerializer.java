package com.twitter.meatlocker.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.net.URI;

/** User: sritchie Date: 2/9/12 Time: 2:53 PM */
public class URISerializer implements Serializer<java.net.URI> {

    public void write(Kryo kryo, Output output, URI uri) {
        output.writeString(uri.toString());
    }

    public URI read(Kryo kryo, Input input, Class<URI> uriClass) {
        return URI.create(input.readString());
    }
}
