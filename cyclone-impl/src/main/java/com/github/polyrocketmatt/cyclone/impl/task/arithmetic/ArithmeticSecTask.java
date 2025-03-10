package com.github.polyrocketmatt.cyclone.impl.task.arithmetic;

import com.github.polyrocketmatt.cyclone.api.TensorType;
import com.github.polyrocketmatt.cyclone.impl.kernel.ArithmeticKernels;
import com.github.polyrocketmatt.cyclone.impl.task.ParallelTensorTask;
import com.github.polyrocketmatt.cyclone.impl.utils.TaskUtils;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.TaskGraph;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;
import uk.ac.manchester.tornado.api.types.arrays.TornadoNativeArray;

public class ArithmeticSecTask extends ParallelTensorTask {

    private final int size;
    private final String id;

    public ArithmeticSecTask(@NotNull TornadoNativeArray buffer, int size) {
        super(buffer);
        this.size = size;
        this.id = "sec_%s".formatted(TaskUtils.randomIdentifier(8));
    }

    @Override
    public void resolve(@NotNull TaskGraph graph, @NotNull TensorType type) {
        switch (type) {
            case FLOAT      -> graph.task(id, ArithmeticKernels::secFloat, (FloatArray) buffer, size);
            default         -> throw new UnsupportedOperationException("Unsupported buffer type: %s".formatted(type));
        }
    }

}
