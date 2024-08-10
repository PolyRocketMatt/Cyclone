package com.github.polyrocketmatt.cyclone.impl;

import com.github.polyrocketmatt.cyclone.api.CycloneBuffer;
import com.github.polyrocketmatt.cyclone.impl.exception.CycloneException;

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

    private static AbstractFloatCycloneBuffer constructFloatBuffer(int dimension, int size, float value) {
        return switch (dimension) {
            case 1              -> new CycloneFloatBuffer1D(size, value);
            default             -> throw new CycloneException("Unsupported buffer dimension: %d", dimension);
        };
    }

}
