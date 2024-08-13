package com.github.polyrocketmatt.cyclone.api.function;

import java.util.function.Function;

/**
 * Represents a function that accepts three arguments and produces a result.
 * This is the three-arity specialization of {@link Function}.
 *
 * @param <T> The type of the first argument to the function.
 * @param <S> The type of the second argument to the function.
 * @param <U> The type of the third argument to the function.
 * @param <R> The type of the result of the function.
 *
 * @see Function
 * @since 0.O.1
 * @version 0.O.1
 * @author Matthias Kovacic
 */
@FunctionalInterface
public interface TriFunction<T, S, U, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
     */
    R apply(T t, S s, U u);

}
