package com.github.polyrocketmatt.cyclone.impl.kernel;

import org.jetbrains.annotations.NotNull;
import uk.ac.manchester.tornado.api.annotations.Parallel;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;

public class ElementKernels {

    public static void addFloat(@NotNull FloatArray array, @NotNull FloatArray other, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, array.get(i) + other.get(i));
    }

    public static void subFloat(@NotNull FloatArray array, @NotNull FloatArray other, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, array.get(i) - other.get(i));
    }

    public static void mulFloat(@NotNull FloatArray array, @NotNull FloatArray other, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, array.get(i) * other.get(i));
    }

    public static void divFloat(@NotNull FloatArray array, @NotNull FloatArray other, int size) {
        for (@Parallel int i = 0; i < size; i++)
            array.set(i, array.get(i) / other.get(i));
    }

}
