package com.github.polyrocketmatt.cyclone.api.tensor;

import org.jetbrains.annotations.NotNull;

public interface Tensor3D<T> extends Tensor<T> {

    int[] getShape();

    int getWidth();

    int getHeight();

    int getDepth();

    @NotNull T get(int x, int y, int z) throws IndexOutOfBoundsException;

    void set(int x, int y, int z, @NotNull T value) throws IndexOutOfBoundsException;

}
