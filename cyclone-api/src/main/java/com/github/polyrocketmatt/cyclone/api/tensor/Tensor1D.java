package com.github.polyrocketmatt.cyclone.api.tensor;

import org.jetbrains.annotations.NotNull;

public interface Tensor1D<T> extends Tensor<T> {

    @NotNull T get(int index) throws IndexOutOfBoundsException;

    void set(int index, @NotNull T value) throws IndexOutOfBoundsException;

}
