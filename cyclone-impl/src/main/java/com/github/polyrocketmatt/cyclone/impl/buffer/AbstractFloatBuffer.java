package com.github.polyrocketmatt.cyclone.impl.buffer;

import com.github.polyrocketmatt.cyclone.api.buffer.AggregationBuffer;
import com.github.polyrocketmatt.cyclone.api.buffer.ArithmeticBuffer;
import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBuffer;
import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBufferType;
import com.github.polyrocketmatt.cyclone.api.function.TriFunction;
import com.github.polyrocketmatt.cyclone.api.exception.CycloneException;
import com.github.polyrocketmatt.cyclone.api.task.AggregationTask;
import com.github.polyrocketmatt.cyclone.api.task.BufferTask;
import com.github.polyrocketmatt.cyclone.impl.task.aggregate.MaxTask;
import com.github.polyrocketmatt.cyclone.impl.task.aggregate.MinTask;
import com.github.polyrocketmatt.cyclone.impl.task.aggregate.SumTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.AdditionTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.DivisionTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.MultiplicationTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.SubtractionTask;
import com.github.polyrocketmatt.cyclone.impl.task.functional.FillTask;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.ImmutableTaskGraph;
import uk.ac.manchester.tornado.api.TaskGraph;
import uk.ac.manchester.tornado.api.TornadoExecutionPlan;
import uk.ac.manchester.tornado.api.enums.DataTransferMode;
import uk.ac.manchester.tornado.api.exceptions.TornadoExecutionPlanException;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractFloatBuffer implements CycloneBuffer<Float>,
        AggregationBuffer<Float>, ArithmeticBuffer<Float> {

    protected final int size;
    protected final FloatArray nativeBuffer;
    protected final FloatArray tmpBuffer;
    protected final List<BufferTask> tasks;

    private TaskGraph graph;
    protected boolean tmpIsMain;

    public AbstractFloatBuffer(int size, float value) {
        this.size = size;
        this.nativeBuffer = new FloatArray(size);
        this.tmpBuffer = new FloatArray(size);
        this.tmpIsMain = false;
        this.tasks = new ArrayList<>();

        this.graph = new TaskGraph("f1bg_%s".formatted(hashCode()))
                .transferToDevice(DataTransferMode.FIRST_EXECUTION, nativeBuffer, tmpBuffer);

        appendTask(new FillTask(nativeBuffer, tmpBuffer, value, size));
        run();
    }

    protected AbstractFloatBuffer(int size, List<Float> values) {
        this.size = size;
        this.nativeBuffer = new FloatArray(size);
        this.tmpBuffer = new FloatArray(size);
        this.tmpIsMain = false;
        this.tasks = new ArrayList<>();

        this.graph = new TaskGraph("f1bg_%s".formatted(hashCode()))
                .transferToDevice(DataTransferMode.FIRST_EXECUTION, nativeBuffer, tmpBuffer);

        for (int i = 0; i < size; i++)
            nativeBuffer.set(i, values.get(i));
    }

    private AbstractFloatBuffer checkBufferArgument(CycloneBuffer<Float> other) {
        if (size != other.size())
            throw new CycloneException("Buffer sizes do not match!");
        if (!(other instanceof AbstractFloatBuffer otherBuffer))
            throw new CycloneException("Buffer types do not match!");
        return otherBuffer;
    }

    @Override
    public @NotNull CycloneBuffer<Float> run() {
        if (tasks.isEmpty())
            return this;

        //  First, resolve all tasks and swap buffers to propagate changes
        tasks.forEach(task -> task.resolve(graph, CycloneBufferType.FLOAT));

        //  Finally, transfer data back to host
        graph.transferToHost(DataTransferMode.EVERY_EXECUTION, nativeBuffer)
                .transferToHost(DataTransferMode.EVERY_EXECUTION, tmpBuffer);

        //  Create and run immutable task graph
        ImmutableTaskGraph itg = graph.snapshot();
        try (TornadoExecutionPlan plan = new TornadoExecutionPlan(itg)) {
            plan.execute();
        } catch (TornadoExecutionPlanException ex) {
            throw new CycloneException("Failed to execute buffer task!", ex);
        }

        //  Clear all tasks
        tasks.clear();

        //  Re-assign graph for next run
        graph = new TaskGraph("f1bg_%s".formatted(hashCode()))
                .transferToDevice(DataTransferMode.FIRST_EXECUTION, nativeBuffer, tmpBuffer, size);

        //  Finally, return this buffer
        return this;
    }

    @Override
    public @NotNull Float reduce(@NotNull AggregationTask task) throws CycloneException {
        run();
        appendTask(task);
        run();
        return getMain().get(0);
    }

    protected void appendTask(@NotNull BufferTask task) {
        tasks.add(task);
        tmpIsMain = !tmpIsMain;
    }

    protected FloatArray constructFilled(float value) {
        FloatArray values = new FloatArray(size);
        for (int i = 0; i < size; i++)
            values.set(i, value);
        return values;
    }

    protected @NotNull FloatArray getMain() {
        return (tmpIsMain) ? tmpBuffer : nativeBuffer;
    }

    protected @NotNull FloatArray getTemp() {
        return (tmpIsMain) ? nativeBuffer : tmpBuffer;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public @NotNull Float get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds: %d".formatted(index));
        return getMain().get(index);
    }

    @Override
    public @NotNull Float getTemp(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds: %d".formatted(index));
        return getTemp().get(index);
    }

    @Override
    public void set(int index, @NotNull Float value) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds: %d".formatted(index));
        getMain().set(index, value);
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
        appendTask(new FillTask(getMain(), getTemp(), value, size));
        return this;
    }

    @Override
    public @NotNull Float sum() {
        return reduce(new SumTask(getMain(), getTemp()));
    }

    @Override
    public @NotNull Float average() {
        return sum() / size;
    }

    @Override
    public @NotNull Float max() {
        return reduce(new MaxTask(getMain(), getTemp()));
    }

    @Override
    public @NotNull Float min() {
        return reduce(new MinTask(getMain(), getTemp()));
    }

    @Override
    public @NotNull CycloneBuffer<Float> add(@NotNull CycloneBuffer<Float> other) {
        appendTask(new AdditionTask(getMain(), getTemp(), checkBufferArgument(other).getMain(), size));
        return this;
    }

    @Override
    public @NotNull CycloneBuffer<Float> add(@NotNull Float value) {
        appendTask(new AdditionTask(getMain(), getTemp(), constructFilled(value), size));
        return this;
    }

    @Override
    public @NotNull CycloneBuffer<Float> sub(@NotNull CycloneBuffer<Float> other) {
        appendTask(new SubtractionTask(getMain(), getTemp(), checkBufferArgument(other).getMain(), size));
        return this;
    }

    @Override
    public @NotNull CycloneBuffer<Float> sub(@NotNull Float value) {
        appendTask(new SubtractionTask(getMain(), getTemp(), constructFilled(value), size));
        return this;
    }

    @Override
    public @NotNull CycloneBuffer<Float> mul(@NotNull CycloneBuffer<Float> other) {
        appendTask(new MultiplicationTask(getMain(), getTemp(), checkBufferArgument(other).getMain(), size));
        return this;
    }

    @Override
    public @NotNull CycloneBuffer<Float> mul(@NotNull Float value) {
        appendTask(new MultiplicationTask(getMain(), getTemp(), constructFilled(value), size));
        return this;
    }

    @Override
    public @NotNull CycloneBuffer<Float> div(@NotNull CycloneBuffer<Float> other) {
        appendTask(new DivisionTask(getMain(), getTemp(), checkBufferArgument(other).getMain(), size));
        return this;
    }

    @Override
    public @NotNull CycloneBuffer<Float> div(@NotNull Float value) {
        if (value == 0.0f)
            throw new CycloneException("Division by zero!");
        appendTask(new DivisionTask(getMain(), getTemp(), constructFilled(value), size));
        return this;
    }

}
