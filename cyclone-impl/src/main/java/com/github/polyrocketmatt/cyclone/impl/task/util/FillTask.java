package com.github.polyrocketmatt.cyclone.impl.task.util;

import com.github.polyrocketmatt.cyclone.api.CycloneBuffer;
import com.github.polyrocketmatt.cyclone.api.task.FunctionalTask;
import com.github.polyrocketmatt.cyclone.impl.exception.CycloneException;
import com.github.polyrocketmatt.cyclone.impl.task.arithmetic.AdditionTask;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.ImmutableTaskGraph;
import uk.ac.manchester.tornado.api.TaskGraph;
import uk.ac.manchester.tornado.api.TornadoExecutionPlan;
import uk.ac.manchester.tornado.api.TornadoExecutionResult;
import uk.ac.manchester.tornado.api.annotations.Parallel;
import uk.ac.manchester.tornado.api.enums.DataTransferMode;
import uk.ac.manchester.tornado.api.exceptions.TornadoExecutionPlanException;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;

public class FillTask implements FunctionalTask {

    @Override
    public @NotNull CycloneBuffer<Float> execute(@NotNull CycloneBuffer<Float> buffer, @NotNull FloatArray output, float value, int size) {
        if (!(buffer.asNativeArray() instanceof FloatArray input))
            throw new CycloneException("Buffer type does not match native array type!");

        TaskGraph graph = new TaskGraph("fill_graph")
                .transferToDevice(DataTransferMode.FIRST_EXECUTION, input, output, value, size)
                .task("fill", FillTask::fillFloat, input, output, value, size)
                .transferToHost(DataTransferMode.EVERY_EXECUTION, output);
        ImmutableTaskGraph itg = graph.snapshot();

        try (TornadoExecutionPlan plan = new TornadoExecutionPlan(itg)) {
            TornadoExecutionResult result = plan.execute();

            if (result.isReady()) {
                for (int i = 0; i < size; i++)
                    buffer.set(i, output.get(i));
            }
        } catch (TornadoExecutionPlanException ex) {
            throw new CycloneException("Failed to execute addition task!", ex);
        }

        return buffer;
    }

    private static void fillFloat(FloatArray input, FloatArray output,
                                  float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            output.set(i, value);
    }

}
