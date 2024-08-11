package com.github.polyrocketmatt.cyclone.api.buffer.ops;

import com.github.polyrocketmatt.cyclone.api.buffer.CycloneBuffer;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a buffer on which aggregation operations can be performed.
 *
 * @param <T> The type of elements in this buffer.
 *
 * @since 0.0.1
 * @version 0.0.1
 * @author Matthias Kovacic
 */
public interface AggregationBufferOps<T> extends CycloneBuffer<T> {

    /**
     * Calculates the sum of all elements in this buffer.
     *
     * @return The sum of all elements in this buffer.
     */
    @NotNull T sum();

}
