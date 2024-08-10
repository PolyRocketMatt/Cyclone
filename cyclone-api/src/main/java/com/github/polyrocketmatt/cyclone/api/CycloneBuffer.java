package com.github.polyrocketmatt.cyclone.api;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a buffer of elements whose values can be accessed and modified
 * using hardware-accelerated operations through TornadoVM.
 *
 * @param <T> The type of elements in this buffer.
 */
public interface CycloneBuffer<T> {

    /**
     * Returns the buffer as a specialized type.
     *
     * @return The buffer as a specialized type.
     * @param <G> The specialized type of the buffer.
     */
    @NotNull <G extends CycloneBuffer<T>> G as();

    /**
     * Get the size of the buffer.
     *
     * @return The size of the buffer.
     */
    int size();

    /**
     * Get the element at the specified index.
     *
     * @param index The index of the element to get.
     * @return The element at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    @NotNull T get(int index) throws IndexOutOfBoundsException;

    /**
     * Set the element at the specified index.
     *
     * @param index The index of the element to set.
     * @param value The value to set the element to.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    void set(int index, @NotNull T value) throws IndexOutOfBoundsException;

}
