package com.github.polyrocketmatt.cyclone.api.tensor;

import org.jetbrains.annotations.NotNull;

public interface Tensor2D<T> extends Tensor<T> {

    int[] getShape();

    int getWidth();

    int getHeight();

    @NotNull T get(int x, int y) throws IndexOutOfBoundsException;

    void set(int x, int y, @NotNull T value) throws IndexOutOfBoundsException;

}
