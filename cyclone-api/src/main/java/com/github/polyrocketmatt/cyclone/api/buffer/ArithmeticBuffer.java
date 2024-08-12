package com.github.polyrocketmatt.cyclone.api.buffer;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a buffer on which arithmetic operations can be performed.
 *
 * @param <T> The type of elements in this buffer.
 *
 * @since 0.0.1
 * @version 0.0.1
 * @author Matthias Kovacic
 */
public interface ArithmeticBuffer<T> extends BufferBase<T> {

    /**
     * Adds the elements of this buffer with the elements of another buffer.
     *
     * @param other The other buffer to add.
     * @return The result of adding the elements of this buffer with the elements of the other buffer.
     */
    @NotNull ArithmeticBuffer<T> add(@NotNull CycloneBuffer<T> other);

    /**
     * Adds the elements of this buffer with a value.
     *
     * @param value The value to add.
     * @return The result of adding the elements of this buffer with the value.
     */
    @NotNull ArithmeticBuffer<T> add(@NotNull T value);

    /**
     * Subtracts the elements of this buffer with the elements of another buffer.
     *
     * @param other The other buffer to subtract.
     * @return The result of subtracting the elements of this buffer with the elements of the other buffer.
     */
    @NotNull ArithmeticBuffer<T> sub(@NotNull CycloneBuffer<T> other);

    /**
     * Subtracts the elements of this buffer with a value.
     *
     * @param value The value to subtract.
     * @return The result of subtracting the elements of this buffer with the value.
     */
    @NotNull ArithmeticBuffer<T> sub(@NotNull T value);

    /**
     * Multiplies the elements of this buffer with the elements of another buffer.
     *
     * @param other The other buffer to multiply.
     * @return The result of multiplying the elements of this buffer with the elements of the other buffer.
     */
    @NotNull ArithmeticBuffer<T> mul(@NotNull CycloneBuffer<T> other);

    /**
     * Multiplies the elements of this buffer with a value.
     *
     * @param value The value to multiply.
     * @return The result of multiplying the elements of this buffer with the value.
     */
    @NotNull ArithmeticBuffer<T> mul(@NotNull T value);

    /**
     * Divides the elements of this buffer with the elements of another buffer.
     *
     * @param other The other buffer to divide.
     * @return The result of dividing the elements of this buffer with the elements of the other buffer.
     */
    @NotNull ArithmeticBuffer<T> div(@NotNull CycloneBuffer<T> other);

    /**
     * Divides the elements of this buffer with a value.
     *
     * @param value The value to divide.
     * @return The result of dividing the elements of this buffer with the value.
     */
    @NotNull ArithmeticBuffer<T> div(@NotNull T value);

}
