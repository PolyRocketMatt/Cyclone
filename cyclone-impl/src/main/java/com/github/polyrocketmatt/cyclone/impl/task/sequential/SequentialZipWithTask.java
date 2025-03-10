package com.github.polyrocketmatt.cyclone.impl.task.sequential;

import com.github.polyrocketmatt.cyclone.api.TensorType;
import com.github.polyrocketmatt.cyclone.api.tensor.Tensor;
import com.github.polyrocketmatt.cyclone.impl.kernel.SequentialKernels;
import com.github.polyrocketmatt.cyclone.impl.task.SequentialTensorTask;
import com.github.polyrocketmatt.cyclone.impl.tensor.LinearizedFloatTensor;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.types.arrays.TornadoNativeArray;

import java.util.function.BiFunction;

public class SequentialZipWithTask extends SequentialTensorTask {

    private final Tensor<?> other;
    private final BiFunction<?, ?, ?> zipper;

    public SequentialZipWithTask(@NotNull TornadoNativeArray buffer, @NotNull Tensor<?> other,
                                 @NotNull BiFunction<?, ?, ?> zipper) {
        super(buffer);
        this.other = other;
        this.zipper = zipper;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void run(@NotNull Tensor<?> tensor, @NotNull TensorType type) {
        switch (type) {
            case FLOAT      -> new SequentialZipWithTask.HomogeneousFloatZipWithTask((LinearizedFloatTensor) tensor,
                    (LinearizedFloatTensor) other, (BiFunction<Float, Float, Float>) zipper).run();
            default         -> throw new UnsupportedOperationException("Unsupported buffer type: %s".formatted(type));
        }
    }

    private static class HomogeneousFloatZipWithTask {

        private final LinearizedFloatTensor tensor;
        private final LinearizedFloatTensor other;
        private final BiFunction<Float, Float, Float> zipper;

        public HomogeneousFloatZipWithTask(@NotNull LinearizedFloatTensor tensor, @NotNull LinearizedFloatTensor other,
                                           @NotNull BiFunction<Float, Float, Float> zipper) {
            this.tensor = tensor;
            this.other = other;
            this.zipper = zipper;
        }

        public void run() {
            SequentialKernels.zipWithFloat(tensor, other, zipper);
        }
    }

}
