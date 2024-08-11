package com.github.polyrocketmatt.cyclone.api.task;

import com.github.polyrocketmatt.cyclone.api.CycloneBuffer;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;

public interface ArithmeticTask extends BufferTask {

    @NotNull CycloneBuffer<Float> execute(@NotNull CycloneBuffer<Float> buffer, @NotNull FloatArray output, float value, int size);

    @NotNull CycloneBuffer<Float> execute(@NotNull CycloneBuffer<Float> buffer, @NotNull FloatArray output, @NotNull FloatArray value, int size);

}
