package com.github.polyrocketmatt.cyclone.api;

public interface TensorTask {

    default boolean isSequential() {
        return false;
    }

}
