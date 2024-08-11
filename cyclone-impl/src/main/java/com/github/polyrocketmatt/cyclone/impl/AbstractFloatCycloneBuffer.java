package com.github.polyrocketmatt.cyclone.impl;

import com.github.polyrocketmatt.cyclone.api.ArithmeticBuffer;
import com.github.polyrocketmatt.cyclone.api.CycloneBuffer;
import com.github.polyrocketmatt.cyclone.api.LinAlgBuffer;
import com.github.polyrocketmatt.cyclone.api.NoiseBuffer;
import com.github.polyrocketmatt.cyclone.api.function.TriFunction;
import com.github.polyrocketmatt.cyclone.impl.exception.CycloneException;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.AdditionTask;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;
import uk.ac.manchester.tornado.api.types.arrays.TornadoNativeArray;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractFloatCycloneBuffer implements CycloneBuffer<Float>,
        ArithmeticBuffer<Float>, NoiseBuffer<Float>, LinAlgBuffer<Float> {

    protected final int size;
    protected final float[] buffer;
    protected final FloatArray nativeBuffer;

    public AbstractFloatCycloneBuffer(int size, float value) {
        this.size = size;
        this.buffer = new float[size];
        this.nativeBuffer = new FloatArray(size);

        // Fill buffer with initial value
        for (int i = 0; i < size; i++)
            buffer[i] = value;

        // Reload values in native buffer
        reloadNativeBuffer();
    }

    private AbstractFloatCycloneBuffer checkBufferArgument(CycloneBuffer<Float> other) {
        if (size != other.size())
            throw new CycloneException("Buffer sizes do not match!");
        if (!(other instanceof AbstractFloatCycloneBuffer otherBuffer))
            throw new CycloneException("Buffer types do not match!");
        return otherBuffer;
    }

    private void reloadNativeBuffer() {
        for (int i = 0; i < size; i++)
            nativeBuffer.set(i, buffer[i]);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public @NotNull Float get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds: %d".formatted(index));
        return buffer[index];
    }

    @Override
    public void set(int index, @NotNull Float value) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds: %d".formatted(index));
        buffer[index] = value;
    }

    @Override
    public @NotNull TornadoNativeArray asNativeArray() {
        return nativeBuffer;
    }

    @Override
    public @NotNull CycloneBuffer<Float> filter(Predicate<Float> predicate, Float defaultValue) {
        return null;
    }

    @Override
    public @NotNull CycloneBuffer<Float> map(Function<Float, Float> mapper) {
        return null;
    }

    @Override
    public @NotNull CycloneBuffer<Float> mapIndexed(BiFunction<Integer, Float, Float> mapper) {
        return null;
    }

    @Override
    public @NotNull CycloneBuffer<Float> zipWith(CycloneBuffer<Float> other, BiFunction<Float, Float, Float> zipper) {
        return null;
    }

    @Override
    public @NotNull CycloneBuffer<Float> zipWithIndex(CycloneBuffer<Float> other, TriFunction<Float, Float, Integer, Float> zipper) {
        return null;
    }

    @Override
    public @NotNull ArithmeticBuffer<Float> add(@NotNull CycloneBuffer<Float> other) {
        return (ArithmeticBuffer<Float>) new AdditionTask().execute(this, new FloatArray(size),
                checkBufferArgument(other).nativeBuffer, size);
    }

    @Override
    public @NotNull ArithmeticBuffer<Float> add(@NotNull Float value) {
        return (ArithmeticBuffer<Float>) new AdditionTask().execute(this, new FloatArray(size),
                value, size);
    }

    @Override
    public @NotNull NoiseBuffer<Float> test() {
        return null;
    }

}
