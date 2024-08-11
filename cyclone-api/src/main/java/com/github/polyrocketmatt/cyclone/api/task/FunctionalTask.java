package com.github.polyrocketmatt.cyclone.api.task;

import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBuffer;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;

/**
 * Represents a task that performs a functional operation on a buffer.
 *
 * @since 0.0.1
 * @version 0.0.1
 * @author Matthias Kovacic
 */
public interface FunctionalTask {

    /**
     * Execute a functional task on a float-based buffer.
     *
     * @param buffer The buffer to execute the functional task on.
     * @param output The output buffer to store the result in.
     * @param value The value to use in the functional task.
     * @param size The size of the buffer.
     * @return The output buffer with the result of the functional task.
     */
    @NotNull CycloneBuffer<Float> execute(@NotNull CycloneBuffer<Float> buffer, @NotNull FloatArray output, float value, int size);

}
