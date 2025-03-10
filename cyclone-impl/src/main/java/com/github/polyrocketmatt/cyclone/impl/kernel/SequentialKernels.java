package com.github.polyrocketmatt.cyclone.impl.kernel;

import com.github.polyrocketmatt.cyclone.api.TriFunction;
import com.github.polyrocketmatt.cyclone.impl.tensor.LinearizedFloatTensor;
import com.github.polyrocketmatt.cyclone.impl.utils.TensorUtils;
import com.github.polyrocketmatt.cyclone.impl.utils.TypeUtils;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SequentialKernels {

    public static void mapFloat(@NotNull LinearizedFloatTensor tensor, @NotNull Stream<Float> stream,
                                @NotNull Function<Float, Float> mapper) {
        Float[] array = stream.parallel()
                .map(mapper)
                .toArray(Float[]::new);
        TensorUtils.mapIntoNative(tensor, array);
    }

    public static void mapIndexedFloat(@NotNull LinearizedFloatTensor tensor, @NotNull BiFunction<Integer, Float, Float> mapper) {
        Float[] array =  IntStream.range(0, tensor.getSize())
                .parallel()
                .mapToObj(i -> mapper.apply(i, tensor.get(i)))
                .toArray(Float[]::new);
        TensorUtils.mapIntoNative(tensor, array);
    }

    public static void zipWithFloat(@NotNull LinearizedFloatTensor tensor, @NotNull LinearizedFloatTensor other,
                                    @NotNull BiFunction<Float, Float, Float> zipper) {
        int size = tensor.getSize();
        Float[] otherArray = TypeUtils.toFloatStream(other.getNativeArray())
                .toArray(Float[]::new);
        Float[] zipped = IntStream.range(0, size)
                .parallel()
                .mapToObj(i -> zipper.apply(tensor.get(i), otherArray[i]))
                .toArray(Float[]::new);
        TensorUtils.mapIntoNative(tensor, zipped);
    }

    public static void zipWithIndexedFloat(@NotNull LinearizedFloatTensor tensor, @NotNull LinearizedFloatTensor other,
                                      @NotNull TriFunction<Integer, Float, Float, Float> zipper) {
        int size = tensor.getSize();
        Float[] otherArray = TypeUtils.toFloatStream(other.getNativeArray())
                .toArray(Float[]::new);
        Float[] zipped = IntStream.range(0, size)
                .parallel()
                .mapToObj(i -> zipper.apply(i, tensor.get(i), otherArray[i]))
                .toArray(Float[]::new);
        TensorUtils.mapIntoNative(tensor, zipped);
    }

}
