package com.github.polyrocketmatt.cyclone.impl.task;

import com.github.polyrocketmatt.cyclone.api.TensorTask;
import com.github.polyrocketmatt.cyclone.api.TensorType;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.TaskGraph;
import uk.ac.manchester.tornado.api.types.arrays.TornadoNativeArray;

public abstract class ParallelTensorTask implements TensorTask {

    protected TornadoNativeArray buffer;

    public ParallelTensorTask(@NotNull TornadoNativeArray buffer) {
        this.buffer = buffer;
    }

    public abstract void resolve(@NotNull TaskGraph graph, @NotNull TensorType type);

}
