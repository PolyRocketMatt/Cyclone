package com.github.polyrocketmatt.cyclone.api.tensor;

import com.github.polyrocketmatt.cyclone.api.TensorType;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.types.arrays.TornadoNativeArray;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface Tensor<T> {

    int getSize();

    @NotNull TensorType getBufferType();

    @NotNull T get(int index) throws IndexOutOfBoundsException;

    void set(int index, @NotNull T value) throws IndexOutOfBoundsException;

    @NotNull TornadoNativeArray getNativeArray();

    @NotNull Tensor<T> dispatch();

    @NotNull Tensor<T> add(@NotNull T value);

    @NotNull Tensor<T> subtract(@NotNull T value);

    @NotNull Tensor<T> multiply(@NotNull T value);

    @NotNull Tensor<T> divide(@NotNull T value);

    @NotNull Tensor<T> modulo(@NotNull T value);

    @NotNull Tensor<T> power(@NotNull T value);

    @NotNull Tensor<T> sqrt();

    @NotNull Tensor<T> cbrt();

    @NotNull Tensor<T> root(@NotNull T value);

    @NotNull Tensor<T> exp();

    @NotNull Tensor<T> log();

    @NotNull Tensor<T> log2();

    @NotNull Tensor<T> log10();

    @NotNull Tensor<T> logx(@NotNull T value);

    @NotNull Tensor<T> sin();

    @NotNull Tensor<T> cos();

    @NotNull Tensor<T> tan();

    @NotNull Tensor<T> csc();

    @NotNull Tensor<T> sec();

    @NotNull Tensor<T> cot();

    @NotNull Tensor<T> asin();

    @NotNull Tensor<T> acos();

    @NotNull Tensor<T> atan();

    @NotNull Tensor<T> atan2(@NotNull T value);

    @NotNull Tensor<T> sinh();

    @NotNull Tensor<T> cosh();

    @NotNull Tensor<T> tanh();

    @NotNull Tensor<T> asinh();

    @NotNull Tensor<T> acosh();

    @NotNull Tensor<T> atanh();

    @NotNull Tensor<T> negate();

    @NotNull Tensor<T> floor();

    @NotNull Tensor<T> ceil();

    @NotNull Tensor<T> abs();

    @NotNull Tensor<T> fill(@NotNull T value);

    @NotNull Tensor<T> random();

    @NotNull Tensor<T> random(int seed);

    @NotNull Tensor<T> map(Function<T, T> mapper);

    @NotNull Tensor<T> mapIndexed(BiFunction<Integer, T, T> mapper);

}
