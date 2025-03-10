package com.github.polyrocketmatt.cyclone.impl.tensor;

import com.github.polyrocketmatt.cyclone.impl.tensor.wrapper.Float1D;
import com.github.polyrocketmatt.cyclone.impl.tensor.wrapper.Float2D;
import com.github.polyrocketmatt.cyclone.impl.tensor.wrapper.Float3D;

public class TensorFactory {

    public static Float1D createFloat1D(int size) { return new Float1D(size, 0.0f); }

    public static Float1D createFloat1D(int size, float value) { return new Float1D(size, value); }

    public static Float2D createFloat2D(int[] shape) {
        if (shape.length != 2)
            throw new IllegalArgumentException("Shape must have 2 dimensions");
        return new Float2D(shape, 0.0f);
    }

    public static Float2D createFloat2D(int[] shape, float value) {
        if (shape.length != 2)
            throw new IllegalArgumentException("Shape must have 2 dimensions");
        return new Float2D(shape, value);
    }

    public static Float3D createFloat3D(int[] shape) {
        if (shape.length != 3)
            throw new IllegalArgumentException("Shape must have 3 dimensions");
        return new Float3D(shape, 0.0f);
    }

    public static Float3D createFloat3D(int[] shape, float value) {
        if (shape.length != 3)
            throw new IllegalArgumentException("Shape must have 3 dimensions");
        return new Float3D(shape, value);
    }

}
