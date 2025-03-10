package com.github.polyrocketmatt.cyclone.impl.task.sequential;

import com.github.polyrocketmatt.cyclone.api.TensorType;
import com.github.polyrocketmatt.cyclone.api.TriFunction;
import com.github.polyrocketmatt.cyclone.api.tensor.Tensor;
import com.github.polyrocketmatt.cyclone.impl.kernel.SequentialKernels;
import com.github.polyrocketmatt.cyclone.impl.task.SequentialTensorTask;
import com.github.polyrocketmatt.cyclone.impl.tensor.LinearizedFloatTensor;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.types.arrays.TornadoNativeArray;

public class SequentialZipWithIndexedTask extends SequentialTensorTask {

    private final Tensor<?> other;
    private final TriFunction<Integer, ?, ?, ?> zipper;

    public SequentialZipWithIndexedTask(@NotNull TornadoNativeArray buffer, @NotNull Tensor<?> other,
                                        @NotNull TriFunction<Integer, ?, ?, ?> zipper) {
        super(buffer);
        this.other = other;
        this.zipper = zipper;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void run(@NotNull Tensor<?> tensor, @NotNull TensorType type) {
        switch (type) {
            case FLOAT      -> new SequentialZipWithIndexedTask.HomogeneousFloatZipWithIndexedTask((LinearizedFloatTensor) tensor,
                    (LinearizedFloatTensor) other, (TriFunction<Integer, Float, Float, Float>) zipper).run();
            default         -> throw new UnsupportedOperationException("Unsupported buffer type: %s".formatted(type));
        }
    }

    private static class HomogeneousFloatZipWithIndexedTask {

        private final LinearizedFloatTensor tensor;
        private final LinearizedFloatTensor other;
        private final TriFunction<Integer, Float, Float, Float> zipper;

        public HomogeneousFloatZipWithIndexedTask(@NotNull LinearizedFloatTensor tensor, @NotNull LinearizedFloatTensor other,
                                                  @NotNull TriFunction<Integer, Float, Float, Float> zipper) {
            this.tensor = tensor;
            this.other = other;
            this.zipper = zipper;
        }

        public void run() {
            SequentialKernels.zipWithIndexedFloat(tensor, other, zipper);
        }
    }

}