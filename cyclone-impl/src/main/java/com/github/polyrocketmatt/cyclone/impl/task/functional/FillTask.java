package com.github.polyrocketmatt.cyclone.impl.task.functional;

import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBufferType;
import com.github.polyrocketmatt.cyclone.api.task.FunctionalTask;
import com.github.polyrocketmatt.cyclone.impl.utils.NameResolver;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.TaskGraph;
import uk.ac.manchester.tornado.api.annotations.Parallel;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;
import uk.ac.manchester.tornado.api.types.arrays.TornadoNativeArray;

public class FillTask implements FunctionalTask {

    private final TornadoNativeArray input;
    private final TornadoNativeArray output;
    private final float value;
    private final int size;

    public FillTask(@NotNull TornadoNativeArray input, @NotNull TornadoNativeArray output, float value, int size) {
        this.input = input;
        this.output = output;
        this.value = value;
        this.size = size;
    }

    @Override
    public void resolve(@NotNull TaskGraph graph, @NotNull CycloneBufferType type) {
        switch (type) {
            case FLOAT      -> graph.task(NameResolver.resolveName("fill"),
                    FillTask::fillFloat, (FloatArray) input, (FloatArray) output, value, size);

            default         -> throw new UnsupportedOperationException("Unsupported buffer type: %s".formatted(type));
        }
    }

    public static void fillFloat(FloatArray input, FloatArray output, float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            output.set(i, value);
    }

}
