package com.github.polyrocketmatt.cyclone.impl.utils;

import uk.ac.manchester.tornado.api.types.arrays.FloatArray;

import java.util.Arrays;
import java.util.stream.Stream;

public class TypeUtils {

    public static Stream<Float> toFloatStream(FloatArray nativeArray) {
        float[] values = nativeArray.toHeapArray();
        Float[] boxedValues = new Float[values.length];
        for (int i = 0; i < values.length; i++)
            boxedValues[i] = values[i];
        return Arrays.stream(boxedValues);
    }

}
