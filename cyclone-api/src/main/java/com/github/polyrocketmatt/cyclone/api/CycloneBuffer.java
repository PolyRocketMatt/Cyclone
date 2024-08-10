package com.github.polyrocketmatt.cyclone.api;

import com.github.polyrocketmatt.cyclone.api.function.TriFunction;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Represents a buffer of elements whose values can be accessed and modified
 * using hardware-accelerated operations through TornadoVM.
 *
 * @param <T> The type of elements in this buffer.
 *
 * @since 0.0.1
 * @version 0.0.1
 * @author Matthias Kovacic
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

    /**
     * Filter the elements in the buffer based on the specified predicate.
     *
     * @param predicate The predicate to filter the elements with.
     * @return A new buffer containing the elements that satisfy the predicate.
     */
    @NotNull CycloneBuffer<T> filter(Predicate<T> predicate);

    /**
     * Map the elements in the buffer using the specified mapping function.
     *
     * @param mapper The mapping function to map the elements with.
     * @return A new buffer containing the mapped elements.
     */
    @NotNull CycloneBuffer<T> map(Function<T, T> mapper);

    /**
     * Map the elements in the buffer using the specified indexed mapping function.
     *
     * @param mapper The indexed mapping function to map the elements with.
     * @return A new buffer containing the mapped elements.
     */
    @NotNull CycloneBuffer<T> mapIndexed(BiFunction<Integer, T, T> mapper);

    /**
     * Zip the elements in this buffer with the elements in the specified buffer using the specified zipper function.
     *
     * @param other The other buffer to zip with.
     * @param zipper The zipper function to zip the elements with.
     * @return A new buffer containing the zipped elements.
     */
    @NotNull CycloneBuffer<T> zipWith(CycloneBuffer<T> other, BiFunction<T, T, T> zipper);

    /**
     * Zip the elements in this buffer with the elements in the specified buffer using the specified indexed zipper function.
     *
     * @param other The other buffer to zip with.
     * @param zipper The indexed zipper function to zip the elements with.
     * @return A new buffer containing the zipped elements.
     */
    @NotNull CycloneBuffer<T> zipWithIndex(CycloneBuffer<T> other, TriFunction<T, T, Integer, T> zipper);

}
