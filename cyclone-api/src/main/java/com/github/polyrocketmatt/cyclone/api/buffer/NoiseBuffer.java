package com.github.polyrocketmatt.cyclone.api.buffer;

import org.jetbrains.annotations.NotNull;

public interface NoiseBuffer<T> extends CycloneBuffer<T> {

    @NotNull NoiseBuffer<T> test();

}
