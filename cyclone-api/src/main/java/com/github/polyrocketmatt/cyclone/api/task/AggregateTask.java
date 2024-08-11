package com.github.polyrocketmatt.cyclone.api.task;

import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBuffer;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;

/**
 * Represents a task that aggregates data from a buffer into a single value.
 *
 * @since 0.0.1
 * @version 0.0.1
 * @author Matthias Kovacic
 */
public interface AggregateTask extends BufferTask {

    /**
     * Execute an aggregate task on a float-based buffer.
     *
     * @param input The buffer to execute the aggregate task on.
     * @param output The output buffer to store the result in.
     * @param size The size of the buffer.
     * @return The result of the aggregate task.
     */
    float execute(@NotNull CycloneBuffer<Float> input, @NotNull FloatArray output, int size);

}
