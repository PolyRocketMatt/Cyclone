package com.github.polyrocketmatt.cyclone.impl.task.arithmetic;

import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBufferType;
import com.github.polyrocketmatt.cyclone.api.task.ArithmeticTask;
import com.github.polyrocketmatt.cyclone.impl.utils.NameResolver;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.TaskGraph;
import uk.ac.manchester.tornado.api.annotations.Parallel;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;
import uk.ac.manchester.tornado.api.types.arrays.TornadoNativeArray;

public class PowerTask implements ArithmeticTask {

    private final TornadoNativeArray input;
    private final TornadoNativeArray output;
    private final float power;
    private final int size;

    public PowerTask(@NotNull TornadoNativeArray input, @NotNull TornadoNativeArray output,
                     float power, int size) {
        this.input = input;
        this.output = output;
        this.power = power;
        this.size = size;
    }

    @Override
    public void resolve(@NotNull TaskGraph graph, @NotNull CycloneBufferType type) {
        switch (type) {
            case FLOAT      -> graph.task(NameResolver.resolveName("add"),
                    PowerTask::intPowFloat, (FloatArray) input, (FloatArray) output, power, size);

            default         -> throw new UnsupportedOperationException("Unsupported buffer type: %s".formatted(type));
        }
    }

    public static void intPowFloat(FloatArray input, FloatArray output,
                                   float power, int size) {
        for (@Parallel int i = 0; i < size; i++)
            output.set(i, (float) Math.pow(input.get(i), power));
    }

}
