package com.github.polyrocketmatt.cyclone.api.task;

import com.github.polyrocketmatt.cyclone.api.CycloneBuffer;
import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;

public interface FunctionalTask {

    @NotNull CycloneBuffer<Float> execute(@NotNull CycloneBuffer<Float> buffer, @NotNull FloatArray output, float value, int size);

}
