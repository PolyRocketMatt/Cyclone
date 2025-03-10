package com.github.polyrocketmatt.cyclone.impl.kernel;

import uk.ac.manchester.tornado.api.annotations.Parallel;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;

public class FunctionalKernels {

    public static void fillFloat(FloatArray buffer, float value, int size) {
        for (@Parallel int i = 0; i < size; i++)
            buffer.set(i, value);
    }

    public static void randomFloat(FloatArray buffer, int seed, int size) {
        for (@Parallel int i = 0; i < size; i++) {
            long rn = seed + i;

            // generate a pseudo random number (you do need it twice)
            rn = (rn * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1);
            rn = (rn * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1);

            // this generates a number between 0 and 1 (with an awful entropy)
            buffer.set(i, (rn & 0x0FFFFFFF) / 268435455f);
        }
    }

}
