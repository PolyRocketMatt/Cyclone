package com.github.polyrocketmatt.cyclone.impl.buffer;

import com.github.polyrocketmatt.cyclone.api.buffer.ops.AggregationBufferOps;
import com.github.polyrocketmatt.cyclone.api.buffer.ops.ArithmeticBufferOps;
import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBuffer;
import com.github.polyrocketmatt.cyclone.api.function.TriFunction;
import com.github.polyrocketmatt.cyclone.impl.exception.CycloneException;
import com.github.polyrocketmatt.cyclone.impl.task.aggregate.SumTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.AdditionTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.DivisionTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.MultiplicationTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.SubtractionTask;
import com.github.polyrocketmatt.cyclone.impl.task.util.FillTask;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;
import uk.ac.manchester.tornado.api.types.arrays.TornadoNativeArray;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractFloatBuffer implements CycloneBuffer<Float>,
        AggregationBufferOps<Float>, ArithmeticBufferOps<Float> {

    protected final int size;
    protected final FloatArray nativeBuffer;
    protected final FloatArray tmpBuffer;

    public AbstractFloatBuffer(int size, float value) {
        this.size = size;
        this.nativeBuffer = new FloatArray(size);
        this.tmpBuffer = new FloatArray(size);

        fill(value);
    }

    private AbstractFloatBuffer checkBufferArgument(CycloneBuffer<Float> other) {
        if (size != other.size())
            throw new CycloneException("Buffer sizes do not match!");
        if (!(other instanceof AbstractFloatBuffer otherBuffer))
            throw new CycloneException("Buffer types do not match!");
        return otherBuffer;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public @NotNull Float get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds: %d".formatted(index));
        return nativeBuffer.get(index);
    }

    @Override
    public void set(int index, @NotNull Float value) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds: %d".formatted(index));
        nativeBuffer.set(index, value);
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
    public @NotNull CycloneBuffer<Float> fill(Float value) {
        return new FillTask().execute(this, tmpBuffer, value, size);
    }

    @Override
    public @NotNull Float sum() {
        return new SumTask().execute(this, tmpBuffer, size);
    }

    @Override
    public @NotNull ArithmeticBufferOps<Float> add(@NotNull CycloneBuffer<Float> other) {
        return (ArithmeticBufferOps<Float>) new AdditionTask().execute(this, tmpBuffer,
                checkBufferArgument(other).nativeBuffer, size);
    }

    @Override
    public @NotNull ArithmeticBufferOps<Float> add(@NotNull Float value) {
        return (ArithmeticBufferOps<Float>) new AdditionTask().execute(this, tmpBuffer,
                value, size);
    }

    @Override
    public @NotNull ArithmeticBufferOps<Float> sub(@NotNull CycloneBuffer<Float> other) {
        return (ArithmeticBufferOps<Float>) new SubtractionTask().execute(this, tmpBuffer,
                checkBufferArgument(other).nativeBuffer, size);
    }

    @Override
    public @NotNull ArithmeticBufferOps<Float> sub(@NotNull Float value) {
        return (ArithmeticBufferOps<Float>) new SubtractionTask().execute(this, tmpBuffer,
                value, size);
    }

    @Override
    public @NotNull ArithmeticBufferOps<Float> mul(@NotNull CycloneBuffer<Float> other) {
        return (ArithmeticBufferOps<Float>) new MultiplicationTask().execute(this, tmpBuffer,
                checkBufferArgument(other).nativeBuffer, size);
    }

    @Override
    public @NotNull ArithmeticBufferOps<Float> mul(@NotNull Float value) {
        return (ArithmeticBufferOps<Float>) new MultiplicationTask().execute(this, tmpBuffer,
                value, size);
    }

    @Override
    public @NotNull ArithmeticBufferOps<Float> div(@NotNull CycloneBuffer<Float> other) {
        return (ArithmeticBufferOps<Float>) new DivisionTask().execute(this, tmpBuffer,
                checkBufferArgument(other).nativeBuffer, size);
    }

    @Override
    public @NotNull ArithmeticBufferOps<Float> div(@NotNull Float value) {
        return (ArithmeticBufferOps<Float>) new DivisionTask().execute(this, tmpBuffer,
                value, size);
    }

}
