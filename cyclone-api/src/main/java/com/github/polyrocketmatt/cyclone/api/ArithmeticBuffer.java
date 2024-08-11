package com.github.polyrocketmatt.cyclone.api;

import org.jetbrains.annotations.NotNull;

public interface ArithmeticBuffer<T> extends CycloneBuffer<T> {

    @NotNull ArithmeticBuffer<T> add(@NotNull CycloneBuffer<T> other);

    @NotNull ArithmeticBuffer<T> add(@NotNull T value);

}
