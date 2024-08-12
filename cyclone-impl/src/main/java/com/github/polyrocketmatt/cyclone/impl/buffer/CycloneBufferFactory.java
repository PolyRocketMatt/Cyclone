package com.github.polyrocketmatt.cyclone.impl.buffer;

import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBufferType;
import com.github.polyrocketmatt.cyclone.api.buffer.dimension.Buffer1D;
import com.github.polyrocketmatt.cyclone.api.exception.CycloneException;

public class CycloneBufferFactory {

    public static Buffer1D constructBuffer1D(CycloneBufferType type, int size, float value) {
        return switch (type) {
            case FLOAT  -> new FloatBuffer1D(size, value);
            default     -> throw new CycloneException("Unsupported buffer type: %s".formatted(type));
        };
    }

}
