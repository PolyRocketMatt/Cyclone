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

import java.util.function.Function;

public class SequentialMapTask extends SequentialTensorTask {

    private final Function<?, ?> mapper;

    public SequentialMapTask(@NotNull TornadoNativeArray buffer, @NotNull Function<?, ?> mapper) {
        super(buffer);
        this.mapper = mapper;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void run(@NotNull Tensor<?> tensor, @NotNull TensorType type) {
        switch (type) {
            case FLOAT      -> new HomogeneousFloatMapTask((FloatTensor) tensor, buffer, (Function<Float, Float>) mapper).run();
            default         -> throw new UnsupportedOperationException("Unsupported buffer type: %s".formatted(type));
        }
    }

    private static class HomogeneousFloatMapTask {

        private final FloatTensor tensor;
        private final FloatArray buffer;
        private final Function<Float, Float> mapper;

        public HomogeneousFloatMapTask(@NotNull FloatTensor tensor, @NotNull TornadoNativeArray buffer,
                                       @NotNull Function<Float, Float> mapper) {
            this.tensor = tensor;
            this.buffer = (FloatArray) buffer;
            this.mapper = mapper;
        }

        public void run() {
            SequentialKernels.mapFloat(tensor, TypeUtils.toFloatStream(buffer), mapper);
        }
    }

}
