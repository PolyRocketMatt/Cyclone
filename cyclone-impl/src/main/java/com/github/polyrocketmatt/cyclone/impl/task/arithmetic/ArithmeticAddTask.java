package com.github.polyrocketmatt.cyclone.impl.task.arithmetic;

import com.github.polyrocketmatt.cyclone.api.TensorType;
import com.github.polyrocketmatt.cyclone.impl.kernel.ArithmeticKernels;
import com.github.polyrocketmatt.cyclone.impl.task.ParallelTensorTask;
import com.github.polyrocketmatt.cyclone.impl.utils.TaskUtils;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.TaskGraph;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;
import uk.ac.manchester.tornado.api.types.arrays.TornadoNativeArray;

public class ArithmeticAddTask extends ParallelTensorTask {

    private float value;
    private final int size;
    private final String id;

    public ArithmeticAddTask(@NotNull TornadoNativeArray buffer, float value, int size) {
        super(buffer);
        this.value = value;
        this.size = size;
        this.id = "add_%s".formatted(TaskUtils.randomIdentifier(8));
    }

    @Override
    public void resolve(@NotNull TaskGraph graph, @NotNull TensorType type) {
        switch (type) {
            case FLOAT      -> graph.task(id, ArithmeticKernels::addFloat, (FloatArray) buffer, value, size);
            default         -> throw new UnsupportedOperationException("Unsupported buffer type: %s".formatted(type));
        }
    }

}
