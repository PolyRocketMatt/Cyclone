package com.github.polyrocketmatt.cyclone.impl.buffer;

import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBuffer;
import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBufferType;
import com.github.polyrocketmatt.cyclone.api.exception.CycloneException;

import java.util.UUID;

public class CycloneBufferFactory {

    public static <T> CycloneBuffer<T> construct(CycloneBufferType type, int dimension, int size) {
        return construct(type, dimension, size, 0.0f);
    }

    @SuppressWarnings("unchecked")
    public static <T> CycloneBuffer<T> construct(CycloneBufferType type, int dimension, int size, float value) {
        return switch (type) {
            case FLOAT          -> (CycloneBuffer<T>) constructFloatBuffer(dimension, size, value);
            default             -> throw new CycloneException("Unsupported buffer type: %s", type.name());
        };
    }

    private static AbstractFloatBuffer constructFloatBuffer(int dimension, int size, float value) {
        return switch (dimension) {
            case 1              -> new FloatBuffer1D(size, value);
            default             -> throw new CycloneException("Unsupported buffer dimension: %d", dimension);
        };
    }

    protected static String getBufferTaskName() {
        return UUID.randomUUID().toString();
    }

}
