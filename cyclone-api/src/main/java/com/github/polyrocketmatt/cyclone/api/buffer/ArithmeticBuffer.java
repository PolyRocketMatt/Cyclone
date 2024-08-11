package com.github.polyrocketmatt.cyclone.api.buffer;

import org.jetbrains.annotations.NotNull;

public interface ArithmeticBuffer<T> extends CycloneBuffer<T> {

    @NotNull ArithmeticBuffer<T> add(@NotNull CycloneBuffer<T> other);

    @NotNull ArithmeticBuffer<T> add(@NotNull T value);

    @NotNull ArithmeticBuffer<T> sub(@NotNull CycloneBuffer<T> other);

    @NotNull ArithmeticBuffer<T> sub(@NotNull T value);

    @NotNull ArithmeticBuffer<T> mul(@NotNull CycloneBuffer<T> other);

    @NotNull ArithmeticBuffer<T> mul(@NotNull T value);

    @NotNull ArithmeticBuffer<T> div(@NotNull CycloneBuffer<T> other);

    @NotNull ArithmeticBuffer<T> div(@NotNull T value);

}
