package com.github.polyrocketmatt.cyclone.impl;

import com.github.polyrocketmatt.cyclone.api.CycloneBuffer;
import org.jetbrains.annotations.NotNull;

public class CycloneFloatBuffer1D extends AbstractFloatCycloneBuffer {

    protected CycloneFloatBuffer1D(int size) {
        this(size, 0.0f);
    }

    protected CycloneFloatBuffer1D(int size, float value) {
        super(size, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <G extends CycloneBuffer<Float>> @NotNull G as() {
        return (G) this;
    }
}
