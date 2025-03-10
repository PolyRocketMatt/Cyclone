package com.github.polyrocketmatt.cyclone.impl.kernel;

import com.github.polyrocketmatt.cyclone.impl.tensor.FloatTensor;
import com.github.polyrocketmatt.cyclone.impl.utils.TensorUtils;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SequentialKernels {

    public static void mapFloat(@NotNull FloatTensor tensor, @NotNull Stream<Float> stream,
                                @NotNull Function<Float, Float> mapper) {
        Float[] array = stream.parallel()
                .map(mapper)
                .toArray(Float[]::new);
        TensorUtils.mapIntoNative(tensor, array);
    }

    public static void mapIndexedFloat(@NotNull FloatTensor tensor, @NotNull BiFunction<Integer, Float, Float> mapper) {
        Float[] array =  IntStream.range(0, tensor.getSize())
                .parallel()
                .mapToObj(i -> mapper.apply(i, tensor.get(i)))
                .toArray(Float[]::new);
        TensorUtils.mapIntoNative(tensor, array);
    }

}
