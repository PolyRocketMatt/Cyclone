package com.github.polyrocketmatt.cyclone.impl.task.element;

import com.github.polyrocketmatt.cyclone.api.TensorType;
import com.github.polyrocketmatt.cyclone.impl.kernel.ElementKernels;
import com.github.polyrocketmatt.cyclone.impl.task.ParallelTensorTask;
import com.github.polyrocketmatt.cyclone.impl.utils.TaskUtils;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.TaskGraph;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;
import uk.ac.manchester.tornado.api.types.arrays.TornadoNativeArray;

public class ElementAddTask extends ParallelTensorTask {

    private final TornadoNativeArray other;
    private final int size;
    private final String id;

    public ElementAddTask(@NotNull TornadoNativeArray buffer, @NotNull TornadoNativeArray other, int size) {
        super(buffer);
        this.other = other;
        this.size = size;
        this.id = "eadd_%s".formatted(TaskUtils.randomIdentifier(8));
    }

    @Override
    public void resolve(@NotNull TaskGraph graph, @NotNull TensorType type) {
        switch (type) {
            case FLOAT      -> graph.task(id, ElementKernels::addFloat, (FloatArray) buffer, (FloatArray) other, size);
            default         -> throw new UnsupportedOperationException("Unsupported buffer type: %s".formatted(type));
        }
    }

}
