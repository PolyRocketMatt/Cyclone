package com.github.polyrocketmatt.cyclone.impl.task;

import com.github.polyrocketmatt.cyclone.api.Tensor;
import com.github.polyrocketmatt.cyclone.api.TensorTask;
import com.github.polyrocketmatt.cyclone.api.TensorType;
import com.github.polyrocketmatt.cyclone.impl.Cyclone;
import com.github.polyrocketmatt.cyclone.impl.utils.TaskUtils;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.ImmutableTaskGraph;
import uk.ac.manchester.tornado.api.TaskGraph;
import uk.ac.manchester.tornado.api.TornadoExecutionPlan;
import uk.ac.manchester.tornado.api.TornadoExecutionResult;
import uk.ac.manchester.tornado.api.TornadoProfilerResult;
import uk.ac.manchester.tornado.api.enums.DataTransferMode;
import uk.ac.manchester.tornado.api.enums.ProfilerMode;
import uk.ac.manchester.tornado.api.exceptions.TornadoExecutionPlanException;
import uk.ac.manchester.tornado.api.types.arrays.TornadoNativeArray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CycloneTaskGraph<T> {

    private final Tensor<T> tensor;
    private final TornadoNativeArray nativeArray;
    private final List<TensorTask> tensorTasks;

    private TaskGraph tornadoTaskGraph;

    public CycloneTaskGraph(@NotNull Tensor<T> tensor, @NotNull List<TensorTask> tensorTasks) {
        this.tensor = tensor;
        this.nativeArray = tensor.getNativeArray();
        this.tensorTasks = tensorTasks;
        this.tornadoTaskGraph = new TaskGraph("ctg_%s".formatted(TaskUtils.randomIdentifier(8)))
                .transferToDevice(DataTransferMode.FIRST_EXECUTION, nativeArray, nativeArray);
    }

    public void dispatchInternalTaskChain() {
        if (tensorTasks.isEmpty())
            return;

        Queue<TensorTask> queuedTasks = new LinkedList<>(tensorTasks);

        while (!queuedTasks.isEmpty()) {
            List<TensorTask> batch = new ArrayList<>();
            TensorTask currentTask = queuedTasks.poll();

            //  Add the current task to the batch
            batch.add(currentTask);

            //  Check the state of the current task
            boolean isSequential = currentTask.isSequential();
            boolean currentState = currentTask.isSequential();

            //  As long as the queue contains a task of the same state
            while (!queuedTasks.isEmpty() && queuedTasks.peek().isSequential() == currentState) {
                //  Poll the task
                currentTask = queuedTasks.poll();

                //  Add the task to the batch
                batch.add(currentTask);
            }

            //  At this point, we have peeked a task of a different state, or the queue is empty
            //  If the current state is sequential, we run the sequential task chain
            if (isSequential)
                runSequentialTasks(batch.stream().map(task -> (SequentialTensorTask) task).toList());
            //  Otherwise, we run the parallellizable task chain
            else
                runParallellizableTasks(batch.stream().map(task -> (ParallelTensorTask) task).toList());
        }
    }

    private void runSequentialTasks(List<SequentialTensorTask> tasks) {
        System.out.println("Running sequential tasks");

        if (tasks.isEmpty())
            return;

        tasks.forEach(task -> task.run(tensor, tensor.getBufferType()));
    }

    private void runParallellizableTasks(List<ParallelTensorTask> tasks) {
        System.out.println("Running parallellizable tasks");

        if (tasks.isEmpty())
            return;

        TornadoNativeArray nativeArray = tensor.getNativeArray();

        tasks.forEach(task -> task.resolve(tornadoTaskGraph, TensorType.FLOAT));
        tornadoTaskGraph.transferToHost(DataTransferMode.FIRST_EXECUTION, nativeArray)
                .transferToHost(DataTransferMode.FIRST_EXECUTION, nativeArray);

        //  Create and run immutable task graph
        ImmutableTaskGraph itg = tornadoTaskGraph.snapshot();

        if (Cyclone.PROFILER) {
            try(TornadoExecutionPlan plan = new TornadoExecutionPlan(itg)
                    .withProfiler(ProfilerMode.SILENT)
                    .withDevice(Cyclone.BACKEND.getDevice())) {
                TornadoExecutionResult result = plan.execute();
                TornadoProfilerResult profilerResult = result.getProfilerResult();
            } catch (TornadoExecutionPlanException ex) {
                ex.printStackTrace();
            }
        } else {
            try(TornadoExecutionPlan plan = new TornadoExecutionPlan(itg)
                    .withDevice(Cyclone.BACKEND.getDevice())) {
                plan.execute();
            } catch (TornadoExecutionPlanException ex) {
                ex.printStackTrace();
            }
        }

        System.out.println("Done!");

        tornadoTaskGraph = new TaskGraph("ctg_%s".formatted(TaskUtils.randomIdentifier(8)))
                .transferToDevice(DataTransferMode.FIRST_EXECUTION, nativeArray, nativeArray, tensor.getSize());
    }

}
