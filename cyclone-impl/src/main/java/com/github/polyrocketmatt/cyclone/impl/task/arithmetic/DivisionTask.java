package com.github.polyrocketmatt.cyclone.impl.task.arithmetic;

import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBufferType;
import com.github.polyrocketmatt.cyclone.api.task.ArithmeticTask;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.TaskGraph;
import uk.ac.manchester.tornado.api.annotations.Parallel;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;
import uk.ac.manchester.tornado.api.types.arrays.TornadoNativeArray;

public class DivisionTask implements ArithmeticTask {

    private final TornadoNativeArray input;
    private final TornadoNativeArray output;
    private final TornadoNativeArray values;
    private final int size;

    public DivisionTask(@NotNull TornadoNativeArray input, @NotNull TornadoNativeArray output,
                        @NotNull TornadoNativeArray values, int size) {
        this.input = input;
        this.output = output;
        this.values = values;
        this.size = size;
    }

    @Override
    public void resolve(@NotNull TaskGraph graph, @NotNull CycloneBufferType type) {
        switch (type) {
            case FLOAT      -> graph.task("div",
                    DivisionTask::divFloat, (FloatArray) input, (FloatArray) output, (FloatArray) values, size);

            default         -> throw new UnsupportedOperationException("Unsupported buffer type: %s".formatted(type));
        }
    }

    public static void divFloat(FloatArray input, FloatArray output,
                                FloatArray values, int size) {
        for (@Parallel int i = 0; i < size; i++)
            output.set(i, input.get(i) / values.get(i));
    }

}
