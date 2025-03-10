package com.github.polyrocketmatt.cyclone.impl.task.sequential;

import com.github.polyrocketmatt.cyclone.api.Tensor;
import com.github.polyrocketmatt.cyclone.api.TensorType;
import com.github.polyrocketmatt.cyclone.impl.kernel.SequentialKernels;
import com.github.polyrocketmatt.cyclone.impl.task.SequentialTensorTask;
import com.github.polyrocketmatt.cyclone.impl.tensor.FloatTensor;
import com.github.polyrocketmatt.cyclone.impl.utils.TypeUtils;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;
import uk.ac.manchester.tornado.api.types.arrays.TornadoNativeArray;

import java.util.function.BiFunction;
import java.util.function.Function;

public class SequentialMapIndexedTask extends SequentialTensorTask {

    private final BiFunction<Integer, ?, ?> mapper;

    public SequentialMapIndexedTask(@NotNull TornadoNativeArray buffer, @NotNull BiFunction<Integer, ?, ?> mapper) {
        super(buffer);
        this.mapper = mapper;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void run(@NotNull Tensor<?> tensor, @NotNull TensorType type) {
        switch (type) {
            case FLOAT      -> new SequentialMapIndexedTask.HomogeneousFloatMapIndexedTask((FloatTensor) tensor, (BiFunction<Integer, Float, Float>) mapper).run();
            default         -> throw new UnsupportedOperationException("Unsupported buffer type: %s".formatted(type));
        }
    }

    private static class HomogeneousFloatMapIndexedTask {

        private final FloatTensor tensor;
        private final BiFunction<Integer, Float, Float> mapper;

        public HomogeneousFloatMapIndexedTask(@NotNull FloatTensor tensor, @NotNull BiFunction<Integer, Float, Float> mapper) {
            this.tensor = tensor;
            this.mapper = mapper;
        }

        public void run() {
            SequentialKernels.mapIndexedFloat(tensor, mapper);
        }
    }

}
