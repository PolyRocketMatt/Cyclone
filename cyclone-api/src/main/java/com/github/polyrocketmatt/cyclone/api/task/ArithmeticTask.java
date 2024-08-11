package com.github.polyrocketmatt.cyclone.api.task;

import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBuffer;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;

/**
 * Represents a task that performs arithmetic operations on a buffer.
 *
 * @since 0.0.1
 * @version 0.0.1
 * @author Matthias Kovacic
 */
public interface ArithmeticTask extends BufferTask {

    /**
     * Execute an arithmetic task on a float-based buffer.
     *
     * @param buffer The buffer to execute the arithmetic task on.
     * @param output The output buffer to store the result in.
     * @param value The value to use in the arithmetic task.
     * @param size The size of the buffer.
     * @return The output buffer with the result of the arithmetic task.
     */
    @NotNull CycloneBuffer<Float> execute(@NotNull CycloneBuffer<Float> buffer, @NotNull FloatArray output, float value, int size);

    /**
     * Execute an arithmetic task on a float-based buffer.
     *
     * @param buffer The buffer to execute the arithmetic task on.
     * @param output The output buffer to store the result in.
     * @param values The values to use in the arithmetic task.
     * @param size The size of the buffer.
     * @return The output buffer with the result of the arithmetic task.
     */
    @NotNull CycloneBuffer<Float> execute(@NotNull CycloneBuffer<Float> buffer, @NotNull FloatArray output, @NotNull FloatArray values, int size);

}
