package com.github.polyrocketmatt.cyclone.impl.tensor;

import com.github.polyrocketmatt.cyclone.api.TensorTask;
import com.github.polyrocketmatt.cyclone.api.TensorType;
import com.github.polyrocketmatt.cyclone.api.Tensor;
import com.github.polyrocketmatt.cyclone.impl.task.CycloneTaskGraph;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticAbsTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticAcosTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticAcoshTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticAdditionTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticAsinTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticAsinhTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticAtan2Task;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticAtanTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticAtanhTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticCeilTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticCosTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticCoshTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticCotTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticCscTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticDivisionTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticExpTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticFloorTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticLog10Task;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticLog2Task;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticLogTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticLogXTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticModuloTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticNegateTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticPowerTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticMultiplicationTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticRootTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticSecTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticSinTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticSinhTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticSubtractionTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticTanTask;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.ArithmeticTanhTask;
import com.github.polyrocketmatt.cyclone.impl.task.functional.FunctionalFillTask;
import com.github.polyrocketmatt.cyclone.impl.task.functional.FunctionalRandomTask;
import com.github.polyrocketmatt.cyclone.impl.task.sequential.SequentialMapIndexedTask;
import com.github.polyrocketmatt.cyclone.impl.task.sequential.SequentialMapTask;
import com.github.polyrocketmatt.cyclone.impl.utils.TensorUtils;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FloatTensor implements Tensor<Float> {

    protected final int size;
    protected final FloatArray buffer;
    protected final List<TensorTask> tasks;

    private final Random rng;

    protected FloatTensor(int size, float value) {
        this.size = size;
        this.buffer = new FloatArray(size);
        this.tasks = new ArrayList<>();

        this.rng = new Random();

        fill(value);
    }

    protected @NotNull FloatTensor queueTask(@NotNull TensorTask task) {
        tasks.add(task);
        return this;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public @NotNull TensorType getBufferType() {
        return TensorType.FLOAT;
    }

    @Override
    public @NotNull Float get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds: %d".formatted(index));
        return buffer.get(index);
    }

    @Override
    public void set(int index, @NotNull Float value) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds: %d".formatted(index));
        buffer.set(index, value);
    }

    @Override
    public @NotNull FloatArray getNativeArray() {
        return buffer;
    }

    @Override
    public FloatTensor dispatch() {
        new CycloneTaskGraph<>(this, tasks).dispatchInternalTaskChain();
        tasks.clear();
        return this;
    }

    @Override
    public @NotNull FloatTensor add(@NotNull Float value) {
        return queueTask(new ArithmeticAdditionTask(buffer, value, size));
    }

    @Override
    public @NotNull FloatTensor subtract(@NotNull Float value) {
        return queueTask(new ArithmeticSubtractionTask(buffer, value, size));
    }

    @Override
    public @NotNull FloatTensor multiply(@NotNull Float value) {
        return queueTask(new ArithmeticMultiplicationTask(buffer, value, size));
    }

    @Override
    public @NotNull FloatTensor divide(@NotNull Float value) {
        return queueTask(new ArithmeticDivisionTask(buffer, value, size));
    }

    @Override
    public @NotNull Tensor<Float> modulo(@NotNull Float value) {
        return queueTask(new ArithmeticModuloTask(buffer, value, size));
    }

    @Override
    public @NotNull FloatTensor power(@NotNull Float value) {
        return queueTask(new ArithmeticPowerTask(buffer, value, size));
    }

    @Override
    public @NotNull FloatTensor sqrt() {
        return queueTask(new ArithmeticRootTask(buffer, 2.0f, size));
    }

    @Override
    public @NotNull FloatTensor cbrt() {
        return queueTask(new ArithmeticRootTask(buffer, 3.0f, size));
    }

    @Override
    public @NotNull FloatTensor root(@NotNull Float value) {
        return queueTask(new ArithmeticRootTask(buffer, value, size));
    }

    @Override
    public @NotNull FloatTensor exp() {
        return queueTask(new ArithmeticExpTask(buffer, size));
    }

    @Override
    public @NotNull FloatTensor log() {
        return queueTask(new ArithmeticLogTask(buffer, size));
    }

    @Override
    public @NotNull FloatTensor log2() {
        return queueTask(new ArithmeticLog2Task(buffer, size));
    }

    @Override
    public @NotNull FloatTensor log10() {
        return queueTask(new ArithmeticLog10Task(buffer, size));
    }

    @Override
    public @NotNull FloatTensor logx(@NotNull Float value) {
        return queueTask(new ArithmeticLogXTask(buffer, value, size));
    }

    @Override
    public @NotNull FloatTensor sin() {
        return queueTask(new ArithmeticSinTask(buffer, size));
    }

    @Override
    public @NotNull FloatTensor cos() {
        return queueTask(new ArithmeticCosTask(buffer, size));
    }

    @Override
    public @NotNull FloatTensor tan() {
        return queueTask(new ArithmeticTanTask(buffer, size));
    }

    @Override
    public @NotNull FloatTensor csc() {
        return queueTask(new ArithmeticCscTask(buffer, size));
    }

    @Override
    public @NotNull FloatTensor sec() {
        return queueTask(new ArithmeticSecTask(buffer, size));
    }

    @Override
    public @NotNull FloatTensor cot() {
        return queueTask(new ArithmeticCotTask(buffer, size));
    }

    @Override
    public @NotNull FloatTensor asin() {
        return queueTask(new ArithmeticAsinTask(buffer, size));
    }

    @Override
    public @NotNull FloatTensor acos() {
        return queueTask(new ArithmeticAcosTask(buffer, size));
    }

    @Override
    public @NotNull FloatTensor atan() {
        return queueTask(new ArithmeticAtanTask(buffer, size));
    }

    @Override
    public @NotNull FloatTensor atan2(@NotNull Float value) {
        return queueTask(new ArithmeticAtan2Task(buffer, value, size));
    }

    @Override
    public @NotNull FloatTensor sinh() {
        return queueTask(new ArithmeticSinhTask(buffer, size));
    }

    @Override
    public @NotNull FloatTensor cosh() {
        return queueTask(new ArithmeticCoshTask(buffer, size));
    }

    @Override
    public @NotNull FloatTensor tanh() {
        return queueTask(new ArithmeticTanhTask(buffer, size));
    }

    @Override
    public @NotNull FloatTensor asinh() {
        return queueTask(new ArithmeticAsinhTask(buffer, size));
    }

    @Override
    public @NotNull FloatTensor acosh() {
        return queueTask(new ArithmeticAcoshTask(buffer, size));
    }

    @Override
    public @NotNull FloatTensor atanh() {
        return queueTask(new ArithmeticAtanhTask(buffer, size));
    }

    @Override
    public @NotNull FloatTensor negate() {
        return queueTask(new ArithmeticNegateTask(buffer, size));
    }

    @Override
    public @NotNull FloatTensor floor() {
        return queueTask(new ArithmeticFloorTask(buffer, size));
    }

    @Override
    public @NotNull FloatTensor ceil() {
        return queueTask(new ArithmeticCeilTask(buffer, size));
    }

    @Override
    public @NotNull FloatTensor abs() {
        return queueTask(new ArithmeticAbsTask(buffer, size));
    }

    @Override
    public @NotNull FloatTensor fill(@NotNull Float value) {
        return queueTask(new FunctionalFillTask(buffer, value, size));
    }

    @Override
    public @NotNull FloatTensor random() {
        return queueTask(new FunctionalRandomTask(buffer, rng.nextInt(), size));
    }

    @Override
    public @NotNull FloatTensor random(int seed) {
        return queueTask(new FunctionalRandomTask(buffer, seed, size));
    }

    @Override
    public @NotNull FloatTensor map(Function<Float, Float> mapper) {
        return queueTask(new SequentialMapTask(buffer, mapper));
    }

    @Override
    public @NotNull FloatTensor mapIndexed(BiFunction<Integer, Float, Float> mapper) {
        return queueTask(new SequentialMapIndexedTask(buffer, mapper));
    }

    /*
    @Override
    public @NotNull FloatTensor zipWith(FloatTensor other, BiFunction<Float, Float, Float> zipper) {
        Float[] otherArray = TypeUtils.toFloatStream(checkBufferArgument(other).getMain())
                .toArray(Float[]::new);
        Float[] zipped = IntStream.range(0, size)
                .parallel()
                .mapToObj(i -> zipper.apply(buffer.get(i), otherArray[i]))
                .toArray(Float[]::new);
        TensorUtils.mapIntoNative(this, zipped);
        return this;
    }

    @Override
    public @NotNull FloatTensor zipWithIndex(FloatTensor other, TriFunction<Float, Float, Integer, Float> zipper) {
        Float[] otherArray = TypeUtils.toFloatStream(checkBufferArgument(other).getMain())
                .toArray(Float[]::new);
        Float[] zipped = IntStream.range(0, size)
                .parallel()
                .mapToObj(i -> zipper.apply(buffer.get(i), otherArray[i], i))
                .toArray(Float[]::new);
        TensorUtils.mapIntoNative(this, zipped);
        return this;
    }

    public @Nullable TornadoProfilerResult getProfilerResult() {
        return profilerResult;
    }

     */

    @Override
    public @NotNull String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Buffer -> Type: ").append(getBufferType().name()).append("\n");
        builder.append("Buffer -> Size: ").append(size).append("\n");
        builder.append("Buffer -> Scheduled Tasks: ").append(tasks.size()).append("\n");
        builder.append("Buffer -> Data: ").append("\n\n");
        builder.append(TensorUtils.buildBuffer1D(this));
        builder.append("\n");

        return builder.toString();
    }

}
