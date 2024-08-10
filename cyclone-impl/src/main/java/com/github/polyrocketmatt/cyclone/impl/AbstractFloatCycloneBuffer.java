package com.github.polyrocketmatt.cyclone.impl;

import com.github.polyrocketmatt.cyclone.api.CycloneBuffer;
import com.github.polyrocketmatt.cyclone.api.LinAlgBuffer;
import com.github.polyrocketmatt.cyclone.api.NoiseBuffer;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;

public abstract class AbstractFloatCycloneBuffer implements CycloneBuffer<Float>,
        NoiseBuffer<Float>, LinAlgBuffer<Float> {

    protected final int size;
    protected final FloatArray buffer;

    public AbstractFloatCycloneBuffer(int size, float value) {
        this.size = size;
        this.buffer = new FloatArray(size);
        this.buffer.init(value);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public @NotNull Float get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        return buffer.get(index);
    }

    @Override
    public void set(int index, @NotNull Float value) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        buffer.set(index, value);
    }
}
