package com.github.polyrocketmatt.cyclone.impl.tensor.wrapper;

import com.github.polyrocketmatt.cyclone.api.tensor.Tensor1D;
import com.github.polyrocketmatt.cyclone.impl.tensor.LinearizedFloatTensor;

public class Float1D extends LinearizedFloatTensor implements Tensor1D<Float>{

    public Float1D(int size) {
        super(size, 0.0f);
    }

    public Float1D(int size, float value) {
        super(size, value);
    }

}
