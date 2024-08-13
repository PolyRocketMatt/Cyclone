package com.github.polyrocketmatt.cyclone.impl.buffer;

import com.github.polyrocketmatt.cyclone.impl.buffer.base.FloatBuffer1D;

public class CycloneBufferFactory {

    public static FloatBuffer1D constructFloat1D(int size, float value) {
        return new FloatBuffer1D(size, value);
    }

    public static FloatBuffer1D constructFloat1D(int size) {
        return new FloatBuffer1D(size, 0.0f);
    }

}
