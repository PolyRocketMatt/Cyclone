package com.github.polyrocketmatt.cyclone.impl.tensor.wrapper;

import com.github.polyrocketmatt.cyclone.api.tensor.Tensor3D;
import com.github.polyrocketmatt.cyclone.impl.tensor.LinearizedFloatTensor;
import org.jetbrains.annotations.NotNull;

public class Float3D extends LinearizedFloatTensor implements Tensor3D<Float> {

    private final int[] shape;

    public Float3D(int[] shape) {
        super(shape[0] * shape[1] * shape[2], 0.0f);
        this.shape = shape;
    }

    public Float3D(int[] shape, float value) {
        super(shape[0] * shape[1] * shape[2], value);
        this.shape = shape;
    }

    @Override
    public int[] getShape() {
        return shape;
    }

    @Override
    public int getWidth() {
        return shape[0];
    }

    @Override
    public int getHeight() {
        return shape[1];
    }

    @Override
    public int getDepth() {
        return shape[2];
    }

    @Override
    public @NotNull Float get(int x, int y, int z) throws IndexOutOfBoundsException {
        return get(x * shape[0] + y * shape[1] + z);
    }

    @Override
    public void set(int x, int y, int z, @NotNull Float value) throws IndexOutOfBoundsException {
        set(x * shape[0] + y * shape[1] + z, value);
    }
}
