package com.github.polyrocketmatt.cyclone.impl.tensor;

public class TensorFactory {

    public static FloatTensor createFloatTensor(int size) { return new FloatTensor(size, 0.0f); }

}
