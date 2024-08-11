package com.github.polyrocketmatt.cyclone.impl.task.arithmetic;

import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBuffer;
import com.github.polyrocketmatt.cyclone.api.task.ArithmeticTask;
import com.github.polyrocketmatt.cyclone.impl.exception.CycloneException;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.ImmutableTaskGraph;
import uk.ac.manchester.tornado.api.TaskGraph;
import uk.ac.manchester.tornado.api.TornadoExecutionPlan;
import uk.ac.manchester.tornado.api.TornadoExecutionResult;
import uk.ac.manchester.tornado.api.annotations.Parallel;
import uk.ac.manchester.tornado.api.enums.DataTransferMode;
import uk.ac.manchester.tornado.api.exceptions.TornadoExecutionPlanException;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;

public class MultiplicationTask implements ArithmeticTask {

    @Override
    public @NotNull CycloneBuffer<Float> execute(@NotNull CycloneBuffer<Float> buffer, @NotNull FloatArray output,
                                                 float value, int size) {
        FloatArray values = new FloatArray(size);
        values.init(value);
        return execute(buffer, output, values, size);
    }

    @Override
    public @NotNull CycloneBuffer<Float> execute(@NotNull CycloneBuffer<Float> buffer,
                                                 @NotNull FloatArray output, @NotNull FloatArray values, int size) {
        if (!(buffer.asNativeArray() instanceof FloatArray input))
            throw new CycloneException("Buffer type does not match native array type!");

        TaskGraph graph = new TaskGraph("multiplication_graph")
                .transferToDevice(DataTransferMode.FIRST_EXECUTION, input, output, values, size)
                .task("multiplication", MultiplicationTask::multFloat, input, output, values, size)
                .transferToHost(DataTransferMode.EVERY_EXECUTION, output);
        ImmutableTaskGraph itg = graph.snapshot();

        try (TornadoExecutionPlan plan = new TornadoExecutionPlan(itg)) {
            TornadoExecutionResult result = plan.execute();

            if (result.isReady()) {
                for (int i = 0; i < size; i++)
                    buffer.set(i, output.get(i));
            }
        } catch (TornadoExecutionPlanException ex) {
            throw new CycloneException("Failed to execute multiplication task!", ex);
        }

        return buffer;
    }

    private static void multFloat(FloatArray input, FloatArray output,
                                 FloatArray values, int size) {
        for (@Parallel int i = 0; i < size; i++)
            output.set(i, input.get(i) * values.get(i));
    }


}
