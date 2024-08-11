package com.github.polyrocketmatt.cyclone.impl.task.aggregate;

import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBuffer;
import com.github.polyrocketmatt.cyclone.api.task.AggregateTask;
import com.github.polyrocketmatt.cyclone.impl.exception.CycloneException;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.ImmutableTaskGraph;
import uk.ac.manchester.tornado.api.TaskGraph;
import uk.ac.manchester.tornado.api.TornadoExecutionPlan;
import uk.ac.manchester.tornado.api.TornadoExecutionResult;
import uk.ac.manchester.tornado.api.annotations.Parallel;
import uk.ac.manchester.tornado.api.annotations.Reduce;
import uk.ac.manchester.tornado.api.enums.DataTransferMode;
import uk.ac.manchester.tornado.api.exceptions.TornadoExecutionPlanException;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;

public class SumTask implements AggregateTask {

    @Override
    public float execute(@NotNull CycloneBuffer<Float> buffer, @NotNull FloatArray output, int size) {
        if (!(buffer.asNativeArray() instanceof FloatArray input))
            throw new CycloneException("Buffer type does not match native array type!");

        TaskGraph graph = new TaskGraph("sum_graph")
                .transferToDevice(DataTransferMode.FIRST_EXECUTION, input, output, size)
                .task("sum", SumTask::sumFloat, input, output, size)
                .transferToHost(DataTransferMode.EVERY_EXECUTION, output);
        ImmutableTaskGraph itg = graph.snapshot();

        float result = 0.0f;
        try (TornadoExecutionPlan plan = new TornadoExecutionPlan(itg)) {
            TornadoExecutionResult executionResult = plan.execute();

            if (executionResult.isReady()) {
                result = output.get(0);
            }
        } catch (TornadoExecutionPlanException ex) {
            throw new CycloneException("Failed to execute summation task!", ex);
        }

        return result;
    }

    private static void sumFloat(FloatArray input, @Reduce FloatArray output, int size) {
        output.set(0, 0.0f);
        for (@Parallel int i = 0; i < size; i++)
            output.set(0, output.get(0) + input.get(i));
    }

}
