package com.github.polyrocketmatt.cyclone.impl.task;

import com.github.polyrocketmatt.cyclone.api.tensor.Tensor;
import com.github.polyrocketmatt.cyclone.api.TensorTask;
import com.github.polyrocketmatt.cyclone.api.TensorType;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.types.arrays.TornadoNativeArray;

public abstract class SequentialTensorTask implements TensorTask {

    protected TornadoNativeArray buffer;

    public SequentialTensorTask(@NotNull TornadoNativeArray buffer) {
        this.buffer = buffer;
    }

    public abstract void run(@NotNull Tensor<?> tensor, @NotNull TensorType type);

    @Override
    public boolean isSequential() {
        return true;
    }
}
