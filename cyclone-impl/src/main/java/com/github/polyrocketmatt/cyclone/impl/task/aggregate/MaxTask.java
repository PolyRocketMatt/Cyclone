package com.github.polyrocketmatt.cyclone.impl.task.aggregate;

import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBufferType;
import com.github.polyrocketmatt.cyclone.api.task.AggregationTask;
import com.github.polyrocketmatt.cyclone.impl.utils.NameResolver;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.TaskGraph;
import uk.ac.manchester.tornado.api.annotations.Parallel;
import uk.ac.manchester.tornado.api.annotations.Reduce;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;
import uk.ac.manchester.tornado.api.types.arrays.TornadoNativeArray;

public class MaxTask implements AggregationTask {

    private final TornadoNativeArray input;
    private final TornadoNativeArray output;

    public MaxTask(@NotNull TornadoNativeArray input, @NotNull TornadoNativeArray output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void resolve(@NotNull TaskGraph graph, @NotNull CycloneBufferType type) {
        switch (type) {
            case FLOAT      -> graph.task(NameResolver.resolveName("max"),
                    MaxTask::maxFloat, (FloatArray) input, (FloatArray) output);

            default         -> throw new UnsupportedOperationException("Unsupported buffer type: %s".formatted(type));
        }
    }

    public static void maxFloat(FloatArray input, FloatArray output) {
        float max = Float.MIN_VALUE;
        for (@Parallel int i = 0; i < input.getSize(); i++)
            if (input.get(i) > max)
                max = input.get(i);
        output.set(0, max);
    }

}
