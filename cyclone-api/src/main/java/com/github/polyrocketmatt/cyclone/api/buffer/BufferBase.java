package com.github.polyrocketmatt.cyclone.api.buffer;

import org.jetbrains.annotations.NotNull;

public interface BufferBase<T> {

    /**
     * Returns the buffer as a specialized type.
     *
     * @return The buffer as a specialized type.
     * @param <G> The specialized type of the buffer.
     */
    @NotNull <G extends BufferBase<T>> G as();

}
