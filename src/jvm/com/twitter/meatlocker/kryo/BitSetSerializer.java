package com.twitter.meatlocker.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.util.BitSet;

/** User: sritchie Date: 2/9/12 Time: 2:54 PM */
public class BitSetSerializer extends Serializer<BitSet> {

    public void write(Kryo kryo, Output output, BitSet bitSet) {
        int len = bitSet.length();

        output.writeInt(len, true);

        for(int i = 0; i < len; i++) {
            output.writeBoolean(bitSet.get(i));
        }
    }

    public BitSet read(Kryo kryo, Input input, Class<BitSet> bitSetClass) {
        int len = input.readInt(true);
        BitSet ret = new BitSet(len);

        for(int i = 0; i < len; i++) {
            ret.set(i, input.readBoolean());
        }

        return ret;
    }
}
